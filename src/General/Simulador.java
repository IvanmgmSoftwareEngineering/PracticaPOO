package General;

import Banco.*;
import Bolsa.BolsaDeValores;
import Bolsa.Empresa;
import ExcepcionesPropias.*;
import Utilidades.*;

import java.io.FileNotFoundException;
import java.io.IOException;


public class Simulador {

    private InterfazDeUsuario interfaz;
    private BolsaDeValores bolsa;
    private Banco banco;
    private int eleccion;
    private int constructorUtilizado;
    private AgenteDeInversiones broker;

    public Simulador() {
        this.constructorUtilizado = 0;
    }

    public Simulador(InterfazDeUsuario menuYEleccion) {
        interfaz = menuYEleccion;
        this.constructorUtilizado = 1;

    }

    public Simulador(BolsaDeValores bolsa) {
        this.bolsa = bolsa;
        this.constructorUtilizado =2;
    }

    public Simulador(InterfazDeUsuario menuYEleccion, BolsaDeValores bolsa) {
        interfaz = menuYEleccion;
        this.bolsa = bolsa;
        this.constructorUtilizado =3;
    }

    public Simulador(Banco banco) {
        this.banco = banco;
        this.constructorUtilizado =4;
    }

    public Simulador(InterfazDeUsuario menuYEleccion, Banco banco) {
        interfaz = menuYEleccion;
        this.constructorUtilizado =5;
    }

    public Simulador(BolsaDeValores bolsa, Banco banco) {
        this.bolsa = bolsa;
        this.banco = banco;
        this.constructorUtilizado =6;
    }

    public Simulador(InterfazDeUsuario menuYEleccion, BolsaDeValores bolsa, Banco banco, AgenteDeInversiones broker) {
        interfaz = menuYEleccion;
        this.bolsa = bolsa;
        this.banco = banco;
        this.constructorUtilizado = 7;
        this.broker= broker;
    }

    public void principal() throws ClassNotFoundException,IOException, BancoNoTieneGestor,ObjetoInterfazDeUsuarioNoPasadoConstructorSimulador,ClassCastException, IntentsLimitAchieveException, ObjetoEscannerNoPasadoConstructorInterfazDeUsuario {

            if ((this.constructorUtilizado==0)||(this.constructorUtilizado==2)||(this.constructorUtilizado==4)||(this.constructorUtilizado==6)) {
                throw new ObjetoInterfazDeUsuarioNoPasadoConstructorSimulador("La clase 'Simulador' debe recibir un objeto de tipo 'Interfaz de Usuario' para funcionar correctamente");
            }
            interfaz.muestraMenu();
            eleccion = Integer.parseInt(interfaz.getEleccion());
            while (eleccion !=0) {
                switch (eleccion) {
                    case 1:     // IMPRIMIR ESTADO DE LOS CLIENTES
                        interfaz.muestraClientesBanco();
                        banco.showClientes();
                        break;


                    case 2:     //IMPRIMIR ESTADO DE LAS EMPRESAS DE LA BOLSA
                        interfaz.muestraEmpresasBanco();
                        bolsa.showEmpresas();
                        break;

                    case 3:     //AÑADIR UN NUEVO CLIENTE AL BANCO
                        try {
                            interfaz.altaClienteBanco();
                            Cliente cliente1 = interfaz.crearCliente();
                            banco.addCliente(cliente1);
                        }
                        catch (IntentsLimitAchieveException ilae){
                            System.out.println("Ha alcanzado al límite de intentos permitidos");
                        }

                        break;

                    case 4:     //ELIMINAR A UN CLIENTE DEL BANCO

                        try {
                            interfaz.bajaClienteBanco();
                            banco.removeCliente(interfaz.getDni());
                        }
                        catch (IntentsLimitAchieveException ilae){
                            System.out.println("Ha alcanzado al límite de intentos permitidos");
                        }

                        break;

                    case 5:     //REALIZAR COPIA DE SEGURIDAD DEL BANCO (CLIENTES)
                        if(banco.getClientes().size()==0) System.out.println("El Banco no tiene clientes. No se puede guardar nada.");
                        else {
                            Output serializa = new Output();
                            interfaz.hazCopiaSeguridadBanco();
                            banco.copiaSeguridadBanco(interfaz.getPath(),serializa);
                            System.out.println("Copia realizada con exito.");
                            System.out.println();
                        }
                        break;

                    case 6:     //RESTAURAR COPIA DE SEGURIDAD DEL BANCO (CLIENTES)
                        Input deserializa = new Input();
                        try {
                            interfaz.restauraCopiaSeguridadBanco();
                            banco.restaurarCopiaSeguridadClientes(interfaz.getPath(), deserializa);
                            System.out.println("Restauración realizada con exito.");
                        }
                        catch (FileNotFoundException fnfe) {

                            System.out.println("La ruta indicada no existe.");
                        }
                        catch (IOException ioe) {
                            ioe.getStackTrace();
                            throw new IOException("Ruta correcta pero otro error de E/S");
                        }
                        System.out.println();
                        break;

                    case 7:     //MEJORAR A CLIENTE PREMIUM
                        try {
                            interfaz.promocionaPremium();
                            banco.promocionAClientePremium(interfaz.getDni());
                        }
                        catch (IntentsLimitAchieveException ilae){
                            System.out.println("Ha alcanzado al límite de intentos permitidos");
                        }

                        break;

                    case 8:     //SOLICITA RECOMENDACION DE INVERSION AL GESTOR
                        interfaz.consultaValores();
                        banco.recomendacionDeInversion(interfaz.getDni());

                        break;

                    case 9:     //AÑADIR EMPRESA A LA BOLSA
                        try {
                            interfaz.altaEmpresaBolsa();
                            Empresa empresa1 = interfaz.crearEmpresa();
                            bolsa.addEmpresa(empresa1);
                        }
                        catch (IntentsLimitAchieveException ilae){
                            System.out.println("Ha alcanzado al límite de intentos permitidos");
                        }

                        break;

                    case 10:    //ELIMINAR EMPRESA DE LA BOLSA
                        try {
                            interfaz.bajaEmpresaBolsa();
                            bolsa.removeEmpresa(interfaz.getNombreEmpresa());
                        }
                        catch (IntentsLimitAchieveException ilae){
                            System.out.println("Ha alcanzado al límite de intentos permitidos");
                        }

                        break;

                    case 11:    //ACTUALIZAR VALORES
                        interfaz.actualizaValores();
                        if(bolsa.getEmpresas().size()==0){
                            System.out.println("No hay empresas en la bolsa");
                        }
                        else{
                            System.out.println("Actualizando...");
                            bolsa.actualizarValoresEmpresas();
                            System.out.println("La actualización se ha realizaco con exito");
                        }

                        break;

                    case 12:    //REALIZAR COPIA DE SEGURIDAD BOLSA (EMPRESAS)
                        if(bolsa.getEmpresas().size()==0) System.out.println("la Bolsa no tiene empresas. No se puede guardar nada disco.");
                        else {
                            Output serializa = new Output();
                            interfaz.hazCopiaSeguridadBolsa();
                            bolsa.copiaSeguridadEmpresas(interfaz.getPath(), serializa);
                            System.out.println("Copia realizada con exito.");
                            System.out.println();
                        }
                        break;

                    case 13:    //RESTAURAR COPIA DE SEGURIDAD DE LA BOLSA (EMPRESAS)
                        Input deserializa2 = new Input();
                        try {
                            interfaz.restauraCopiaSeguridadBolsa();
                            bolsa.restaurarCopiaSeguridadEmpresas(interfaz.getPath(), deserializa2);
                            System.out.println("Restauración realizada con exito.");
                        }
                        catch (FileNotFoundException fnfe) {
                            System.out.println("La ruta indicada no existe.");
                        }
                        catch (IOException ioe) {
                            ioe.getStackTrace();
                            throw new IOException("Ruta correcta pero otro error de E/S");
                        }
                        System.out.println();
                        break;

                    case 14:    //SOLICITAR COMPRA DE ACCIONES
                        interfaz.solicitaCompraDeAcciones();
                        banco.compraAcciones(interfaz.getDni(),interfaz.getNombreEmpresa(),interfaz.getCantidadMaxAInvertir());

                        break;

                    case 15:    //SOLICITAR VENTA DE ACCIONES
                        break;

                    case 16:    //SOLICITAR ACTUALIZACION DE VALORES DE LAS CARTERAS DE UN CLIENTE
                        break;

                    case 17:    //IMPRIMIR OPERACIONES PENDIENTES
                        interfaz.muestraOperacionesPendientes();
                        broker.muestraOperacionesPendientes();
                        break;

                    case 18:    //EJECUTAR OPERACIONES PENDIENTES
                        interfaz.ejecutaPeticionesDeAcciones();
                        broker.ejecutaPeticionesDeAcciones();
                        banco.actualizaEstadoClientes();//A partir de los resultados de las opraciones almacenadas en la lista opracionesRealizadas del broker tenemos que actualizar el/los paquete/s de acciones del cliente
                        break;
                }
                System.out.println();
                System.out.print("Pulse la tecla ENTER para volver al MENU");
                interfaz.leeTeclado.leeDatos();
                interfaz.muestraMenu();
                eleccion = Integer.parseInt(interfaz.getEleccion());
            }
            System.out.println("Adios");



    }

        }




















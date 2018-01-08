package General;

import Banco.*;
import Bolsa.*;
import ExcepcionesPropias.*;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Simulador {
    //ZONA DE VARIABLES
    private InterfazDeUsuario interfaz;
    private BolsaDeValores bolsa;
    private Banco banco;
    private int eleccion;
    private AgenteDeInversiones broker;
    // FIN ZONA VALIABLES

    //ZONA DE CONSTRUCTORES
    public Simulador(InterfazDeUsuario menuYEleccion, BolsaDeValores bolsa, Banco banco, AgenteDeInversiones broker) {
        interfaz = menuYEleccion;
        this.bolsa = bolsa;
        this.banco = banco;
        this.broker = broker;
    }
    //FIN ZONA DE CONSTRUCTORES

    //ZONA DE METODOS PUBLICOS
    public void principal() throws ClassNotFoundException, IOException, ClassCastException, IntentsLimitAchieveException  {
        try {
            interfaz.muestraMenu();
            eleccion = Integer.parseInt(interfaz.getEleccion());
        }
        catch(IntentsLimitAchieveException ile){
            System.out.println("1-Se han superado el número de intentos permitidos");
            System.out.println();
            eleccion = 0;
        }

        while (eleccion != 0) {
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
                        } catch (IntentsLimitAchieveException ilae) {
                            System.out.println("1-Ha alcanzado al límite de intentos permitidos");
                        }
                        break;

                    case 4:     //ELIMINAR A UN CLIENTE DEL BANCO
                        try {
                            interfaz.bajaClienteBanco();
                            banco.removeCliente(interfaz.getDni());
                        }
                        catch (IntentsLimitAchieveException ilae) {
                            System.out.println("Ha alcanzado al límite de intentos permitidos");
                        }
                        break;

                    case 5:     //REALIZAR COPIA DE SEGURIDAD DEL BANCO (CLIENTES)
                        if (banco.getClientes().size() == 0) System.out.println("El Banco no tiene clientes. No se puede guardar nada.");
                        else {
                            try {
                                Utilidades serializa = new Utilidades();
                                interfaz.hazCopiaSeguridadBanco();
                                banco.copiaSeguridadBanco(interfaz.getPath(), serializa);
                                System.out.println("Copia realizada con exito.");
                                System.out.println();
                            }
                            catch(FileNotFoundException fnfe){
                                System.out.println();
                                System.out.println("El path introducido no se corresponde con el nombre de un fichero");
                            }
                        }
                        break;

                    case 6:     //RESTAURAR COPIA DE SEGURIDAD DEL BANCO (CLIENTES)
                        Utilidades deserializa = new Utilidades();
                        try {
                            interfaz.restauraCopiaSeguridadBanco();
                            banco.restaurarCopiaSeguridadClientes(interfaz.getPath(), deserializa);
                            System.out.println();
                            System.out.println("Restauración realizada con exito.");
                        }
                        catch (FileNotFoundException fnfe) {
                            System.out.println();
                            System.out.println("ERROR: La ruta indicada no existe.");
                        }
                        catch (IOException ioe) {
                            System.out.println();
                            System.out.println("EEROR: la ruta existe y la ruta indicada es un fichero que almacena clientes. Otro error de tipo IOException.");
                            System.out.println();
                        }
                        catch (ClassCastException cce) {
                            System.out.println();
                            System.out.println("EEROR: La ruta indicada es de un fichero que no almacena clientes. Pruebe con otro fichero.");
                        }
                        System.out.println();
                        break;

                    case 7:     //MEJORAR A CLIENTE PREMIUM
                        try {
                            interfaz.promocionaPremium();
                            banco.promocionAClientePremium(interfaz.getDni());
                        }
                        catch (IntentsLimitAchieveException ilae) {
                            System.out.println();
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
                        catch (IntentsLimitAchieveException ilae) {
                            System.out.println();
                            System.out.println("Ha alcanzado al límite de intentos permitidos");
                        }

                        break;

                    case 10:    //ELIMINAR EMPRESA DE LA BOLSA
                        try {
                            interfaz.bajaEmpresaBolsa();
                            bolsa.removeEmpresa(interfaz.getNombreEmpresa());
                        }
                        catch (IntentsLimitAchieveException ilae) {
                            System.out.println();
                            System.out.println("Ha alcanzado al límite de intentos permitidos");
                        }
                        break;

                    case 11:    //ACTUALIZAR VALORES
                        interfaz.actualizaValores();
                        if (bolsa.getEmpresas().size() == 0) {
                            System.out.println();
                            System.out.println("No hay empresas en la bolsa");
                        }
                        else {
                            System.out.println();
                            System.out.println("Actualizando...");
                            bolsa.actualizarValoresEmpresas();
                            System.out.println();
                            System.out.println("La actualización se ha realizaco con exito");
                            System.out.println();
                        }
                        break;

                    case 12:    //REALIZAR COPIA DE SEGURIDAD BOLSA (EMPRESAS)
                        if (bolsa.getEmpresas().size() == 0) {System.out.println();
                            System.out.println("la Bolsa no tiene empresas. No se puede guardar nada disco.");
                            System.out.println();
                        }
                        else {
                            try {
                                Utilidades serializa = new Utilidades();
                                interfaz.hazCopiaSeguridadBolsa();
                                bolsa.copiaSeguridadEmpresas(interfaz.getPath(), serializa);
                                System.out.println();
                                System.out.println("Copia realizada con exito.");
                                System.out.println();
                            }
                            catch(FileNotFoundException fnfe) {
                                System.out.println();
                                System.out.println("El path introducido no se corresponde con el nombre de un fichero");
                            }
                        }
                        break;

                    case 13:    //RESTAURAR COPIA DE SEGURIDAD DE LA BOLSA (EMPRESAS)
                        Utilidades deserializa2 = new Utilidades();
                        try {
                            interfaz.restauraCopiaSeguridadBolsa();
                            bolsa.restaurarCopiaSeguridadEmpresas(interfaz.getPath(), deserializa2);
                            System.out.println();
                            System.out.println("Restauración realizada con exito.");
                            System.out.println();
                        }
                        catch (FileNotFoundException fnfe) {
                            System.out.println();
                            System.out.println("EEROR: La ruta indicada no existe.");
                            System.out.println();
                        }
                        catch (IOException ioe) {
                            System.out.println();
                            System.out.println("EEROR: la ruta existe y la ruta indicada es un fichero que almacena empresas. Otro error de tipo IOException.");
                            System.out.println();
                        }
                        catch (ClassCastException cce) {
                            System.out.println();
                            System.out.println("EEROR: La ruta indicada es de un fichero que no almacena empresas. Pruebe con otro fichero.");
                            System.out.println();
                        }
                        System.out.println();
                        break;

                    case 14:    //SOLICITAR COMPRA DE ACCIONES
                        try {
                            interfaz.solicitaCompraDeAcciones();
                            banco.compraAcciones(interfaz.getDni(), interfaz.getNombreEmpresa(), interfaz.getCantidadMaxAInvertir());
                        }
                        catch (IntentsLimitAchieveException ile) {
                            System.out.println("Se han superado el número de intentos permitidos");
                            System.out.println();
                        }
                        break;

                    case 15:    //SOLICITAR VENTA DE ACCIONES
                        try {
                            interfaz.solicitaVentaDeAcciones();
                            banco.ventaAcciones(interfaz.getDni(), interfaz.getNombreEmpresa(), interfaz.getNumTitulosAVender());
                        }
                        catch (IntentsLimitAchieveException ile) {
                            System.out.println("Se han superado el número de intentos permitidos");
                            System.out.println();
                        }
                        break;

                    case 16:    //SOLICITAR ACTUALIZACION DE VALORES DE LAS CARTERAS DE UN CLIENTE
                        try {
                            interfaz.actualizaValoresCliente();
                            banco.actualizacionDeAccicones(interfaz.getDni());
                        }
                        catch (IntentsLimitAchieveException ile) {
                            System.out.println("Se han superado el número de intentos permitidos");
                            System.out.println();
                        }
                        break;

                    case 17:    //IMPRIMIR OPERACIONES PENDIENTES
                        interfaz.muestraOperacionesPendientes();
                        broker.muestraOperacionesPendientes();
                        break;

                    case 18:    //EJECUTAR OPERACIONES PENDIENTES
                        try {
                            interfaz.ejecutaPeticionesDeAcciones();
                            broker.ejecutaPeticionesDeAcciones();
                            banco.actualizaEstadoClientes();//A partir de los resultados de las opraciones almacenadas en la lista opracionesRealizadas del broker tenemos que actualizar el/los paquete/s de acciones del cliente
                        }
                        catch( NullPointerException npe){
                            System.out.println("La cadena codificada enviada al broker no cumple con el formato adecuado");
                        }
                        break;
                }//FIN SWITCH

                try {
                    System.out.println();
                    System.out.print("Pulse la tecla ENTER para volver al MENU");
                    interfaz.leeTeclado.leeDatos();
                    interfaz.muestraMenu();
                    eleccion = Integer.parseInt(interfaz.getEleccion());
                }
                catch(IntentsLimitAchieveException ile){
                    System.out.println("2-Se han superado el número de intentos permitidos");
                    System.out.println();
                    eleccion = 0;
                }
            }
            System.out.println("Adios");
    }
    //FIN ZONA DE METODOS PUBLICOS
}






















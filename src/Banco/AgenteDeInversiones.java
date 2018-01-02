package Banco;

import Bolsa.BolsaDeValores;
import Bolsa.Empresa;
import Mensajes.*;
import com.sun.tools.corba.se.idl.constExpr.BooleanOr;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class AgenteDeInversiones extends Persona {
    private BolsaDeValores bolsa;
    private ArrayList<Mensaje> operacionesPendientes;
    private ArrayList<Mensaje> resultadosOperaciones;
    private String tipoAgente;


    //ZONA DE CONSTRUCTORES

    public AgenteDeInversiones(String nombre, String dni,String tipoAgente) {
        super(nombre, dni);
        this.tipoAgente = tipoAgente;
    }

    public AgenteDeInversiones(String nombre, String dni, BolsaDeValores bolsa, String tipoAgente) {
        super(nombre, dni);
        this.bolsa = bolsa;
        this.tipoAgente = tipoAgente;
        this.operacionesPendientes = new ArrayList<Mensaje>();
        this.resultadosOperaciones = new ArrayList<Mensaje>();
    }



    // FIN ZONA DE CONSTRUCTORES

    //ZONA DE GETTERS

    public ArrayList<Mensaje> getOperacionesPendientes() {
        return operacionesPendientes;
    }

    public ArrayList<Mensaje> getResultadosOperaciones() {
        return resultadosOperaciones;
    }

    public BolsaDeValores getBolsa() {
        return bolsa;
    }

    //FIN ZONA DE GETTERS

    //ZONA DE METODOS PRIVADOS




    // FIN ZONA DE PRIVADOS

    //ZONA DE METODOS PUBLICOS

    /*Nombre método: consultaDeInversiones
              Entradas: nada
              Salidas: nada
              Excepciones:
              Descripción: Realiza una recomendacion al cliente de la empresa que una variacion a tenido
              */
    public String consultaDeInversiones() {

        if (bolsa.getEmpresas().size() == 0) {
            System.out.println("No hay empresas en la bolsa");
            return "1";
        } else {
            Empresa empresa1 = null;
            double variacionMaxima = -100;
            Iterator iterador = bolsa.getEmpresas().iterator(); // creo un objeto Iterator para recorrer la coleccion
            while (iterador.hasNext()) {
                Empresa empresa = (Empresa) iterador.next();

                if (empresa.calculaVariacion() > variacionMaxima) {
                    variacionMaxima = empresa.calculaVariacion();
                    empresa1 = empresa;
                }
            }
            return empresa1.getNombre();
        }
    }

     /*Nombre método: ejecutaPeticionesDeAcciones
          Entradas: nada
          Salidas: nada
          Excepciones:
          Descripción: ejecuta la peticiones que tieen el blorker en la lista de peticiones pendientes generando una cadena codificada que envia a la bolsa
          */

    public void ejecutaPeticionesDeAcciones() {
        if(operacionesPendientes.size()!=0) {
            String cadenaCompraRespuestaCodificada = null;
            Iterator iterador = operacionesPendientes.iterator(); // creo un objeto Iterator para recorrer la coleccion
            while (iterador.hasNext()) {
                Mensaje mensaje = (Mensaje) iterador.next();

                // OPERACION DE COMPRA

                if (mensaje.getTipoOperacion() == TipoOperacion.COMPRA) {
                    MensajeCompra mensajeCompra = (MensajeCompra) mensaje;
                    String cadenaCompraCodificada;
                    //1º// Llamo al metodo de esta misma clase que construye una cadena de texto codificada con el formato que impone el enunciado. Notar que no hace falta crearse un metodo para codificar y decodificar ya que lo tenemos creado en la clase Mensaje. En cambio en la bolsa si sera necesario crearse metodos para codificar y decodificar porque la bolsa no ve a la clase mensaje
                    System.out.println(" ----El borker esta codificando la opracion con id: " + mensajeCompra.getIdOperacion());
                    System.out.println();
                    cadenaCompraCodificada = mensajeCompra.codificar();
                    System.out.println(" ----Se ha terminado de codificar la cadena");
                    System.out.println();

                    //2º// Envio cadena a la bolsa. LLamo desde aqui al metodo de la bolsa encargado de recpcionar las cadenas codificadas que tiene que :
                    //  1º decodificar la cadena
                    //  2º generar una cadena codificada de respuesta
                    //  3º aumentar el valor de la accion de la empresa acorde a un valor proporcional al numero de acciones que compro y al valor actual de la accion. (ojo se debe cambiar valor actual y valor previo)
                    //  4º devolver una cadena codificada de respuesta String

                    System.out.println(cadenaCompraCodificada + " ----Enviando cadena de texto codifica a la bolsa...");
                    System.out.println();
                    cadenaCompraRespuestaCodificada = bolsa.recepcioncadenaCodificadaCompraDesdeElBroker(cadenaCompraCodificada);

                    System.out.println("----El broker ha recibido la cadena de respuesta codificada");
                    System.out.println();


                    //3º// Decodificar cadena codificada recibida desde la bolsa y crear un objeto de tipo MensajeRespuestaCompra
                    System.out.println("----Decodificando la cadena de texto en el broker ...");
                    System.out.println();

                    //Variables para decodificar
                    String idOperacionDecodificado = "";
                    String nombreClienteDecodificado = "";
                    String dniClienteDecodificado = "";
                    String resultadoOperaciondecodificado = "";
                    String numAccionesCompradas = "";
                    String precioDeAccion = "";
                    String dineroSobrante = "";

                    int i = 0;
                    char caracter;
                    //DECODIFICAMOS RESPUESTA DE COMPRA DEDE LA BOLSA
                    caracter = cadenaCompraRespuestaCodificada.charAt(i);
                    while (caracter != '|') {
                        idOperacionDecodificado = idOperacionDecodificado + caracter;
                        i = i + 1;
                        caracter = cadenaCompraRespuestaCodificada.charAt(i);
                    }
                    i = i + 1;
                    //Decodificamos el nombre del cliente
                    caracter = cadenaCompraRespuestaCodificada.charAt(i);
                    while (caracter != '|') {
                        nombreClienteDecodificado = nombreClienteDecodificado + caracter;
                        i = i + 1;
                        caracter = cadenaCompraRespuestaCodificada.charAt(i);
                    }
                    i = i + 1;
                    //Decodificamos el DNI del cliente
                    caracter = cadenaCompraCodificada.charAt(i);
                    while (caracter != '|') {
                        dniClienteDecodificado = dniClienteDecodificado + caracter;
                        i = i + 1;
                        caracter = cadenaCompraCodificada.charAt(i);
                    }
                    i = i + 1;
                    //Decodificamos el resultado de la opracion
                    caracter = cadenaCompraRespuestaCodificada.charAt(i);
                    while (caracter != '|') {
                        resultadoOperaciondecodificado = resultadoOperaciondecodificado + caracter;
                        i = i + 1;
                        caracter = cadenaCompraRespuestaCodificada.charAt(i);
                    }
                    // Aqui comprobamos si la operacion ha ido bien o se ha rechazado para saber si hay que seguir decodificando o no
                    Boolean efectuada = Boolean.parseBoolean(resultadoOperaciondecodificado);
                    if (efectuada) {
                        i = i + 1;
                        //Decodificamos el numero de acciones compradas
                        caracter = cadenaCompraRespuestaCodificada.charAt(i);
                        while (caracter != '|') {
                            numAccionesCompradas = numAccionesCompradas + caracter;
                            i = i + 1;
                            caracter = cadenaCompraRespuestaCodificada.charAt(i);
                        }
                        i = i + 1;
                        //Decodificamos el precio de la accion comprada
                        caracter = cadenaCompraRespuestaCodificada.charAt(i);
                        while (caracter != '|') {
                            precioDeAccion = precioDeAccion + caracter;
                            i = i + 1;
                            caracter = cadenaCompraRespuestaCodificada.charAt(i);
                        }
                        i = i + 1;
                        //Decodificamos el dinero sobrante de la operacion de compra
                        caracter = cadenaCompraRespuestaCodificada.charAt(i);
                        while (caracter != '|') {
                            dineroSobrante = dineroSobrante + caracter;
                            i = i + 1;
                            caracter = cadenaCompraRespuestaCodificada.charAt(i);
                        }
                        System.out.println("----Se ha terminado de decodificar la cadena de respuesta en el borker");
                        System.out.println();
                        System.out.println("----Los datos decodificados son: ");
                        System.out.println("-------------IDoperacion= " + idOperacionDecodificado);
                        System.out.println("-------------Nombre Cliente= " + nombreClienteDecodificado);
                        System.out.println("-------------DNIcliente= " + dniClienteDecodificado);
                        System.out.println("-------------Resultado opreracion= " + resultadoOperaciondecodificado);
                        System.out.println("-------------Numero acciones compradas= " + numAccionesCompradas);
                        System.out.println("-------------Precio accion= " + precioDeAccion);
                        System.out.println("-------------Dinrero sobrante= " + dineroSobrante);
                        System.out.println();

                    }

                    // FIN DECODIFICAMOS RESPUESTA DE COMPRA DEDE LA BOLSA
                    //Transformamos los String de la decodificacion a los tipos adecuados y cremoa un objeto de tipo mensaje que añadiremos a la lista de operaciones realizadas
                    int idOperacion = Integer.parseInt(idOperacionDecodificado);


                    if (efectuada.equals(false)) { //si el resultado de la operacion es false es porque la empresa de la que se intentan comprar acciones no se encuentra en la bolsa.
                        System.out.println("----Se ha terminado de decodificar la cadena de respuesta en el borker");
                        System.out.println();
                        System.out.println("----Los datos decodificados son: ");
                        System.out.println("-------------IDoperacion= " + idOperacionDecodificado);
                        System.out.println("-------------Nombre Cliente= " + nombreClienteDecodificado);
                        System.out.println("-------------DNIcliente= " + dniClienteDecodificado);
                        System.out.println("-------------Resultado opreracion= " + resultadoOperaciondecodificado);

                        System.out.println("----Almacenando el resultado de la operación en la lista de operaciones reralizadas del broker...");
                        System.out.println();
                        Mensaje mensajeResuestaCompra = new MensajeRespuestaCompra(idOperacion, nombreClienteDecodificado, dniClienteDecodificado, TipoOperacion.COMPRA,mensajeCompra.getCantidadMaximaAInvertir(), false);
                        resultadosOperaciones.add(mensajeResuestaCompra);
                        System.out.println("FIN OPERACIÓN: " + mensajeCompra.getIdOperacion() + " EN EL BROKER");
                        System.out.println("---------------------------------------------------------------------------");

                    }

                    // 4º// Añadir el mensajeRespuesCompra a la lista resultadosOperaciones
                    else {//el resultado de la operacion es true
                        double numeroAccionesCompradas = Double.parseDouble(numAccionesCompradas);
                        double precioAccionComprada = Double.parseDouble(precioDeAccion);
                        double sobranteDinero = Double.parseDouble(dineroSobrante);
                        Mensaje mensajeRespuestaCompra = new MensajeRespuestaCompra(idOperacion, nombreClienteDecodificado, dniClienteDecodificado, mensajeCompra.getNombreEmpresa(), TipoOperacion.COMPRA, efectuada, numeroAccionesCompradas, precioAccionComprada, sobranteDinero);
                        resultadosOperaciones.add(mensajeRespuestaCompra);
                        System.out.println("FIN OPERACIÓN: " + mensajeCompra.getIdOperacion() + " EN EL BROKER");
                        System.out.println("---------------------------------------------------------------------------");
                    }

                    // FIN OPERACION DE COMPRA

                    // OPERACION DE VENTA

                } else if (mensaje.getTipoOperacion() == TipoOperacion.VENTA) {
                    MensajeVenta mensajeVenta = (MensajeVenta) mensaje;
                    if (!bolsa.getEmpresas().contains(mensaje.getNombreEmpresa())) { // Si la bolsa no tiene a la empresa de la que se quieren vender titúlos el resultado de la opracion será NO EFECTUADO: motivo "LA EMPRESA NO EXISTE"
                        Mensaje mensajeResuestaVenta = new MensajeRespuestaVenta(mensajeVenta.getIdOperacion(), mensaje.getNombreCliente(), mensaje.getDniCliente(), mensajeVenta.getNombreEmpresa(), mensajeVenta.getTipoOperacion(), false);
                        resultadosOperaciones.add(mensajeResuestaVenta);
                    } else {//La empresa si esta en la bolsa


                        // Llamo al metodo de esta misma clase que construye una cadena de texto codificada con el formato que impone el enunciado. Notar que no hace falta crearse un metodo para codificar y decodificar ya que lo tenemos creado en la clase Mensaje. En cambio en la bolsa si sera necesario crearse metodos para codificar y decodificar porque la bolsa no ve a la clase mensaje
                        // Envio cadena a la bolsa. El metodo de la bolsa encargado de recpcionar las cadenas codificadas tiene que :
                        //  1º decodificar la cadena
                        //  2º generar una cadena codificada de respuesta
                        //  3º disminuir el valor de la accion de la empresa acorde a un valor proporcional al numero de acciones que se venden y al valor actual de la accion. (ojo se debe cambiar valor actual y valor previo)
                        //  4º devolver una sadena codificada String
                        // Decoddificar cadena codificada recibida desde la bolsa y crear un objeto de tipo MensajeRespuestaCompra
                        // Añadir el mensajeRespuesCompra a la lista resultadosOperaciones

                    }

                    //FIN OPERACION DE VENTA

                    // OPERACION DE ACTULIZACION

                } else if (mensaje.getTipoOperacion() == TipoOperacion.ACTUALIZACION) {// OPERACION DE ACTULIZACION
                    MensajeActualizacion mensajeActualizacion = (MensajeActualizacion) mensaje;
                    Iterator iterador1 = mensajeActualizacion.getEmpresasQueSeQuierenActualizar().iterator(); // creo un objeto Iterator para recorrer la coleccion de empresas que se quieren actualizar
                    HashSet<Empresa> empresasActualizadas = new HashSet<Empresa>();
                    while (iterador1.hasNext()) {
                        Empresa empresaParaActualizar = (Empresa) iterador1.next();
                        Iterator iterador2 = bolsa.getEmpresas().iterator(); // creo un objeto Iterator para recorrer la coleccion de empresas que existen en la bolsa
                        while (iterador2.hasNext()) {
                            Empresa empresaBolsa = (Empresa) iterador1.next();
                            if (empresaBolsa.equals(empresaParaActualizar)) {
                                empresaParaActualizar.setValorTituloActual(empresaBolsa.getValorTituloActual());
                                empresaParaActualizar.setValorTituloPrevio(empresaBolsa.getValorTituloPrevio());
                                empresasActualizadas.add(empresaParaActualizar);
                            }
                        }
                    }
                    Mensaje mensajeResuestaActualizacion = new MensajeRespuestaActualizacion(mensajeActualizacion.getIdOperacion(), mensajeActualizacion.getNombreCliente(), mensajeActualizacion.getDniCliente(), mensajeActualizacion.getTipoOperacion(), true, empresasActualizadas);
                    resultadosOperaciones.add(mensajeResuestaActualizacion);
                }

                // FIN OPERACION DE ACTULIZACION

            }
            operacionesPendientes.clear(); // borramos toda la lista de opraciones pendientes
            System.out.println("---------------------------------------------------------------------------");
            System.out.println("---------------------------------------------------------------------------");
            System.out.println("SE HAN EJECUTADO TODAS LAS OPERACIONES PENDIENTES DE LA LISTA DEL BROKER.");
            System.out.println();
            System.out.println("Borrando lista de operaciones pendientes del broker... ");
            System.out.println();
            System.out.println("Lista de operaciones pendientes del broker borrada");
            System.out.println();
        }


    }

        /*Nombre método: añadePeticionCompraALaListaDeOperacionesPendientesDelBorker
          Entradas: int idOperacion,String nombreCliente , String dniCliente, String nombreEmpresa, TipoOperacion tipoOperacion, float cantidadMaxAInvertir
          Salidas: nada
          Excepciones:
          Descripción: Añade una solicitud de compra la lista de operaciones pendientes del broker
          */

    public void añadePeticionCompraALaListaDeOperacionesPendientesDelBorker(int idOperacion,String nombreCliente , String dniCliente, String nombreEmpresa,  float cantidadMaxAInvertir) {
        MensajeCompra peticionCompra = new MensajeCompra(idOperacion, nombreCliente, dniCliente, nombreEmpresa, TipoOperacion.COMPRA, cantidadMaxAInvertir);
        operacionesPendientes.add(peticionCompra);
    }

    /*Nombre método: añadePeticionVentaALaListaDeOperacionesPendientesDelBorker
          Entradas: int idOperacion,String nombreCliente , String dniCliente, String nombreEmpresa, TipoOperacion tipoOperacion, int numTitulosAVender)
          Salidas: nada
          Excepciones:
          Descripción: Añade una solicitud de venta a la lista de operaciones pendientes del broker
          */

    public void añadePeticionVentaALaListaDeOperacionesPendientesDelBorker(int idOperacion,String nombreCliente , String dniCliente, String nombreEmpresa, int numTitulosAVender) {

        MensajeVenta peticionVenta = new MensajeVenta(idOperacion,nombreCliente,dniCliente,nombreEmpresa,TipoOperacion.VENTA,numTitulosAVender);
        operacionesPendientes.add(peticionVenta);
    }

     /*Nombre método: añadePeticionActualizacionALaListaDeOperacionesPendientesDelBorker
          Entradas: int idOperacion,String nombreCliente , String dniCliente)
          Salidas: nada
          Excepciones:
          Descripción: Añade una solicitud de actualizacion a la lista de operaciones pendientes del broker
          */

    public void añadePeticionActualizacionALaListaDeOperacionesPendientesDelBorker(int idOperacion,String nombreCliente , String dniCliente, HashSet<Empresa> empresasQueSeQuierenActualizar) {

        MensajeActualizacion peticionActualizacion = new MensajeActualizacion(idOperacion,nombreCliente,dniCliente, TipoOperacion.ACTUALIZACION, empresasQueSeQuierenActualizar);
        operacionesPendientes.add(peticionActualizacion);
    }

    /*Nombre método: muestraOperacionesPendientes
          Entradas: nada
          Salidas: nada
          Excepciones:
          Descripción: Muestra las operaciones pendientes
          */

    public void muestraOperacionesPendientes(){

        if(operacionesPendientes.size()==0){
            System.out.println("No hay operaciones pendientes");
        }
        else{
            Iterator iterador = operacionesPendientes.iterator(); // creo un objeto Iterator para recorrer la coleccion
            while (iterador.hasNext()) {
                Mensaje peticion = (Mensaje) iterador.next();
                if(peticion.getTipoOperacion()==TipoOperacion.COMPRA){
                    MensajeCompra mensajeCompra = (MensajeCompra) peticion;
                    System.out.println(mensajeCompra.toString());
                }
                else if(peticion.getTipoOperacion()==TipoOperacion.VENTA) {
                    MensajeVenta mensajeVenta = (MensajeVenta) peticion;
                    System.out.println(mensajeVenta.toString());
                }
                else{
                    MensajeActualizacion mensajeActualizacion = (MensajeActualizacion) peticion;
                    System.out.println(mensajeActualizacion.toString());
                }
            }
        }
    }

//ZONA DE METODOS PUBLICOS

}







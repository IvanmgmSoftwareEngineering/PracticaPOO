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
        if(operacionesPendientes.size()!=0) {//en primer lugar el broker compruba si hay alguna peticion pendiente en la lsita

            String cadenaCompraRespuestaCodificada = null;
            String cadenaVentaRespuestaCodificada = null;

            Iterator iterador = operacionesPendientes.iterator(); // creo un objeto Iterator para recorrer la coleccion

            while (iterador.hasNext()) {
                Mensaje mensaje = (Mensaje) iterador.next();

                // OPERACION DE COMPRA
                //Pasos a seguir:
                //
                    // Llamo al metodo de esta misma clase que construye una cadena de texto codificada con el formato que impone el enunciado. Notar que no hace falta crearse un metodo para codificar y decodificar ya que lo tenemos creado en la clase Mensaje. En cambio en la bolsa si sera necesario crearse metodos para codificar y decodificar porque la bolsa no ve a la clase mensaje
                    // Envio cadena a la bolsa. El metodo de la bolsa encargado de recpcionar las cadenas codificadas tiene que :
                    //  1º decodificar la cadena
                    //  2º generar una cadena codificada de respuesta
                    //  3º disminuir el valor de la accion de la empresa acorde a un valor proporcional al numero de acciones que se venden y al valor actual de la accion. (ojo se debe cambiar valor actual y valor previo)
                    //  4º devolver una sadena codificada String
                    // Decoddificar cadena codificada recibida desde la bolsa y crear un objeto de tipo MensajeRespuestaCompra
                    // Añadir el mensajeRespuesCompra a la lista resultadosOperaciones

                if (mensaje.getTipoOperacion() == TipoOperacion.COMPRA) {
                    MensajeCompra mensajeCompra = (MensajeCompra) mensaje;
                    String cadenaCompraCodificada;
                    //1º// Llamo al metodo de esta misma clase que construye una cadena de texto codificada con el formato que impone el enunciado. Notar que no hace falta crearse un metodo para codificar y decodificar ya que lo tenemos creado en la clase Mensaje. En cambio en la bolsa si sera necesario crearse metodos para codificar y decodificar porque la bolsa no ve a la clase mensaje
                    System.out.println("-------------------------------------INICIO OPERACIÓN COMPRA: " + mensajeCompra.getIdOperacion() + "-------------------------------------------------------------");
                    System.out.println();
                    System.out.println(" -------------- ZONA BROKER -------------");
                    System.out.println();
                    System.out.println(" 1º) El broker esta codificando la operación de COMPRA con id: " + mensajeCompra.getIdOperacion());
                    System.out.println();
                    cadenaCompraCodificada = mensajeCompra.codificar();
                    System.out.println(" -------- Se ha terminado de codificar la cadena.");
                    System.out.println();

                    //2º// Envio cadena a la bolsa. LLamo desde aqui al metodo de la bolsa encargado de recpcionar las cadenas codificadas que tiene que :
                    //  1º decodificar la cadena
                    //  2º generar una cadena codificada de respuesta
                    //  3º aumentar el valor de la accion de la empresa acorde a un valor proporcional al numero de acciones que compro y al valor actual de la accion. (ojo se debe cambiar valor actual y valor previo)
                    //  4º devolver una cadena codificada de respuesta String

                    System.out.println(" -------- La cadena de texto codificada de compra generada por el broker es: " + cadenaCompraCodificada);
                    System.out.println();
                    System.out.println(" 2º) El broker esta enviando la cadena de texto codifica a la bolsa...");
                    System.out.println();
                    cadenaCompraRespuestaCodificada = bolsa.recepcioncadenaCodificadaCompraDesdeElBroker(cadenaCompraCodificada);

                    System.out.println(" -------------- ZONA BROKER -------------");
                    System.out.println();
                    System.out.println(" -------- EL broker ha recibido la cadena de texto de respuesta de compra codificada enviada por la bolsa.");
                    System.out.println();


                    //3º// Decodificar cadena codificada recibida desde la bolsa y crear un objeto de tipo MensajeRespuestaCompra
                    System.out.println(" 7º) El broker esta decodificando la cadena de texto de respues de compra codificada...");
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
                        System.out.println(" -------- El broker ha terminado de decodificar la cadena de texto recibida de la bolsa.");
                        System.out.println();
                        System.out.println(" ------------ Los datos decodificados son: ");
                        System.out.println();
                        System.out.println(" ---------------- ID Operacion = " + idOperacionDecodificado);
                        System.out.println(" ---------------- Nombre Cliente = " + nombreClienteDecodificado);
                        System.out.println(" ---------------- DNI Cliente = " + dniClienteDecodificado);
                        System.out.println(" ---------------- Número de acciones compradas " + numAccionesCompradas);
                        System.out.println(" ---------------- Precio de la acción " + precioDeAccion);
                        System.out.println(" ---------------- Dinero sobrante " + dineroSobrante);
                        System.out.println();

                    }

                    // FIN DECODIFICAMOS RESPUESTA DE COMPRA DEDE LA BOLSA
                    //Transformamos los String de la decodificacion a los tipos adecuados y cremoa un objeto de tipo mensaje respuesta compra que añadiremos a la lista de operaciones realizadas
                    int idOperacion = Integer.parseInt(idOperacionDecodificado);


                    if (efectuada.equals(false)) { //si el resultado de la operacion es false es porque la empresa de la que se intentan comprar acciones no se encuentra en la bolsa.
                        System.out.println(" -------- El broker ha terminado de decodificar la cadena de texto recibida de la bolsa.");
                        System.out.println();
                        System.out.println(" ------------ Los datos decodificados son: ");
                        System.out.println();
                        System.out.println(" ---------------- ID Operacion = " + idOperacionDecodificado);
                        System.out.println(" ---------------- Nombre Cliente = " + nombreClienteDecodificado);
                        System.out.println(" ---------------- DNI Cliente = " + dniClienteDecodificado);
                        System.out.println(" ---------------- Resultado opreracion= " + resultadoOperaciondecodificado);
                        System.out.println();

                        System.out.println(" 8º) El broker esta almacenando el resultado de la operación de compra con id:" + idOperacionDecodificado + " en la lista de operaciones reralizadas del broker...");
                        System.out.println();
                        Mensaje mensajeResuestaCompra = new MensajeRespuestaCompra(idOperacion, nombreClienteDecodificado, dniClienteDecodificado, TipoOperacion.COMPRA, mensajeCompra.getCantidadMaximaAInvertir(), false);
                        resultadosOperaciones.add(mensajeResuestaCompra);
                        System.out.println("  El resultado de la operación de compra con id:" + idOperacionDecodificado + " ha sido almacenado con éxito");
                        System.out.println();
                        System.out.println("-------------------------------------------FIN OPERACIÓN COMPRA: " + mensajeCompra.getIdOperacion() + "-------------------------------------------------------");
                        System.out.println();
                        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");

                    }

                    // 4º// Añadir el mensajeRespuesCompra a la lista resultadosOperaciones
                    else {//el resultado de la operacion es true
                        double numeroAccionesCompradas = Double.parseDouble(numAccionesCompradas);
                        double precioAccionComprada = Double.parseDouble(precioDeAccion);
                        double sobranteDinero = Double.parseDouble(dineroSobrante);
                        Mensaje mensajeRespuestaCompra = new MensajeRespuestaCompra(idOperacion, nombreClienteDecodificado, dniClienteDecodificado, mensajeCompra.getNombreEmpresa(), TipoOperacion.COMPRA, efectuada, numeroAccionesCompradas, precioAccionComprada, sobranteDinero);
                        System.out.println(" 8º) El broker esta almacenando el resultado de la operación con id:" + idOperacionDecodificado + " en la lista de operaciones reralizadas del broker...");
                        System.out.println();
                        resultadosOperaciones.add(mensajeRespuestaCompra);
                        System.out.println("  El resultado de la operación de compra con id:" + idOperacionDecodificado + " ha sido almacenado con éxito");
                        System.out.println();
                        System.out.println("-------------------------------------------FIN OPERACIÓN COMPRA: " + mensajeCompra.getIdOperacion() + "-------------------------------------------------------");
                        System.out.println();
                        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    }

                    // FIN OPERACION DE COMPRA
                }

                    // OPERACION DE VENTA
                 else if (mensaje.getTipoOperacion() == TipoOperacion.VENTA) {

                    MensajeVenta mensajeVenta = (MensajeVenta) mensaje;
                    String cadenaCompraCodificada;

                    //1º// Llamo al metodo de esta misma clase que construye una cadena de texto codificada con el formato que impone el enunciado. Notar que no hace falta crearse un metodo para codificar y decodificar ya que lo tenemos creado en la clase Mensaje. En cambio en la bolsa si sera necesario crearse metodos para codificar y decodificar porque la bolsa no ve a la clase mensaje
                    System.out.println("-------------------------------------INICIO OPERACIÓN VENTA: " + mensajeVenta.getIdOperacion() + "-------------------------------------------------------------");
                    System.out.println();
                    System.out.println(" -------------- ZONA BROKER -------------");
                    System.out.println();
                    System.out.println(" 1º) El broker esta codificando la operación de VENTA con id: " + mensajeVenta.getIdOperacion());
                    System.out.println();
                    cadenaCompraCodificada = mensajeVenta.codificar();
                    System.out.println(" -------- Se ha terminado de codificar la cadena.");
                    System.out.println();


                    // OPERACION DE VENTA
                    //Pasos a seguir:
                    //
                    // Llamo al metodo de esta misma clase que construye una cadena de texto codificada con el formato que impone el enunciado. Notar que no hace falta crearse un metodo para codificar y decodificar ya que lo tenemos creado en la clase Mensaje. En cambio en la bolsa si sera necesario crearse metodos para codificar y decodificar porque la bolsa no ve a la clase mensaje
                    // Envio cadena a la bolsa. El metodo de la bolsa encargado de recpcionar las cadenas codificadas tiene que :
                    //  1º decodificar la cadena
                    //  2º generar una cadena codificada de respuesta
                    //  3º disminuir el valor de la accion de la empresa acorde a un valor proporcional al numero de acciones que se venden y al valor actual de la accion. (ojo se debe cambiar valor actual y valor previo)
                    //  4º devolver una sadena codificada String
                    // Decoddificar cadena codificada recibida desde la bolsa y crear un objeto de tipo MensajeRespuestaCompra
                    // Añadir el mensajeRespuesCompra a la lista resultadosOperaciones


                    //2º// Envio cadena a la bolsa. LLamo desde aqui al metodo de la bolsa encargado de recpcionar las cadenas codificadas que tiene que :
                    //  1º decodificar la cadena
                    //  2º generar una cadena codificada de respuesta
                    //  3º aumentar el valor de la accion de la empresa acorde a un valor proporcional al numero de acciones que compro y al valor actual de la accion. (ojo se debe cambiar valor actual y valor previo)
                    //  4º devolver una cadena codificada de respuesta String

                    System.out.println(" -------- La cadena de texto codificada de venta generada por el broker es: " + cadenaCompraCodificada);
                    System.out.println();
                    System.out.println(" 2º) El broker esta enviando la cadena de texto codifica a la bolsa...");
                    System.out.println();
                    cadenaVentaRespuestaCodificada = bolsa.recepcioncadenaCodificadaVentaDesdeElBroker(cadenaCompraCodificada);

                    System.out.println(" -------------- ZONA BROKER -------------");
                    System.out.println();
                    System.out.println(" -------- EL broker ha recibido la cadena de texto de respuesta de venta codificada enviada por la bolsa.");
                    System.out.println();


                    //3º// Decodificar cadena codificada recibida desde la bolsa y crear un objeto de tipo MensajeRespuestaCompra
                    System.out.println(" 7º) El broker esta decodificando la cadena de texto de respuesta de venta codificada...");
                    System.out.println();

                    //Variables para decodificar
                    String idOperacionDecodificado = "";
                    String nombreClienteDecodificado = "";
                    String dniClienteDecodificado = "";
                    String resultadoOperaciondecodificado = "";
                    String numAccionesVendidas = "";
                    String precioDeAccion = "";
                    String beneficioTotal = "";

                    int i = 0;
                    char caracter;
                    //DECODIFICAMOS RESPUESTA DE VENTA DEDE LA BOLSA
                    caracter = cadenaCompraRespuestaCodificada.charAt(i);
                    while (caracter != '|') {
                        idOperacionDecodificado = idOperacionDecodificado + caracter;
                        i = i + 1;
                        caracter = cadenaVentaRespuestaCodificada.charAt(i);
                    }
                    i = i + 1;
                    //Decodificamos el nombre del cliente
                    caracter = cadenaCompraRespuestaCodificada.charAt(i);
                    while (caracter != '|') {
                        nombreClienteDecodificado = nombreClienteDecodificado + caracter;
                        i = i + 1;
                        caracter = cadenaVentaRespuestaCodificada.charAt(i);
                    }
                    i = i + 1;
                    //Decodificamos el DNI del cliente
                    caracter = cadenaCompraCodificada.charAt(i);
                    while (caracter != '|') {
                        dniClienteDecodificado = dniClienteDecodificado + caracter;
                        i = i + 1;
                        caracter = cadenaVentaRespuestaCodificada.charAt(i);
                    }
                    i = i + 1;
                    //Decodificamos el resultado de la opracion
                    caracter = cadenaCompraRespuestaCodificada.charAt(i);
                    while (caracter != '|') {
                        resultadoOperaciondecodificado = resultadoOperaciondecodificado + caracter;
                        i = i + 1;
                        caracter = cadenaVentaRespuestaCodificada.charAt(i);
                    }
                    // Aqui comprobamos si la operacion ha ido bien o se ha rechazado para saber si hay que seguir decodificando o no
                    Boolean efectuada = Boolean.parseBoolean(resultadoOperaciondecodificado);
                    if (efectuada) {
                        i = i + 1;
                        //Decodificamos el numero de acciones vendidas
                        caracter = cadenaCompraRespuestaCodificada.charAt(i);
                        while (caracter != '|') {
                            numAccionesVendidas = numAccionesVendidas + caracter;
                            i = i + 1;
                            caracter = cadenaVentaRespuestaCodificada.charAt(i);
                        }
                        i = i + 1;
                        //Decodificamos el precio de la accion comprada
                        caracter = cadenaCompraRespuestaCodificada.charAt(i);
                        while (caracter != '|') {
                            precioDeAccion = precioDeAccion + caracter;
                            i = i + 1;
                            caracter = cadenaVentaRespuestaCodificada.charAt(i);
                        }
                        i = i + 1;
                        //Decodificamos el beneficio de la operacion de venta
                        caracter = cadenaCompraRespuestaCodificada.charAt(i);
                        while (caracter != '|') {
                            beneficioTotal = beneficioTotal + caracter;
                            i = i + 1;
                            caracter = cadenaVentaRespuestaCodificada.charAt(i);
                        }
                        System.out.println(" -------- El broker ha terminado de decodificar la cadena de texto recibida de la bolsa.");
                        System.out.println();
                        System.out.println(" ------------ Los datos decodificados son: ");
                        System.out.println();
                        System.out.println(" ---------------- ID Operacion = " + idOperacionDecodificado);
                        System.out.println(" ---------------- Nombre Cliente = " + nombreClienteDecodificado);
                        System.out.println(" ---------------- DNI Cliente = " + dniClienteDecodificado);
                        System.out.println(" ---------------- Número de acciones compradas " + numAccionesVendidas);
                        System.out.println(" ---------------- Precio de la acción " + precioDeAccion);
                        System.out.println(" ---------------- Dinero sobrante " + beneficioTotal);
                        System.out.println();

                    }

                    // FIN DECODIFICAMOS RESPUESTA DE VENTA DEDE LA BOLSA
                    //Transformamos los String de la decodificacion a los tipos adecuados y creamos un objeto de tipo mensaje respuesta venta que añadiremos a la lista de operaciones realizadas
                    int idOperacion = Integer.parseInt(idOperacionDecodificado);


                    if (efectuada.equals(false)) { //si el resultado de la operacion es false es porque la empresa de la que se intentan comprar acciones no se encuentra en la bolsa.
                        System.out.println(" -------- El broker ha terminado de decodificar la cadena de texto recibida de la bolsa.");
                        System.out.println();
                        System.out.println(" ------------ Los datos decodificados son: ");
                        System.out.println();
                        System.out.println(" ---------------- ID Operacion = " + idOperacionDecodificado);
                        System.out.println(" ---------------- Nombre Cliente = " + nombreClienteDecodificado);
                        System.out.println(" ---------------- DNI Cliente = " + dniClienteDecodificado);
                        System.out.println(" ---------------- Resultado opreracion= " + resultadoOperaciondecodificado);
                        System.out.println();

                        System.out.println(" 8º) El broker esta almacenando el resultado de la operación con id:" + idOperacionDecodificado + " en la lista de operaciones reralizadas del broker...");
                        System.out.println();
                        Mensaje mensajeResuestaCompra = new MensajeRespuestaCompra(idOperacion, nombreClienteDecodificado, dniClienteDecodificado, TipoOperacion.VENTA, mensajeVenta.getNumTitulosAVender(), false);
                        resultadosOperaciones.add(mensajeResuestaCompra);
                        System.out.println("  El resultado de la operación de venta con id:" + idOperacionDecodificado + " ha sido almacenado con éxito");
                        System.out.println();
                        System.out.println("-------------------------------------------FIN OPERACIÓN VENTA: " + mensajeVenta.getIdOperacion() + "-------------------------------------------------------");
                        System.out.println();
                        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");

                    }

                    // 4º// Añadir el mensajeRespuesCompra a la lista resultadosOperaciones
                    else {//el resultado de la operacion es true
                        double numeroAccionesVendidas = Double.parseDouble(numAccionesVendidas);
                        double precioAccionVendida = Double.parseDouble(precioDeAccion);
                        double beneficioObtenido = Double.parseDouble(beneficioTotal);
                        Mensaje mensajeRespuestaVenta = new MensajeRespuestaVenta(idOperacion, nombreClienteDecodificado, dniClienteDecodificado, mensajeVenta.getNombreEmpresa(), TipoOperacion.VENTA, efectuada, numeroAccionesVendidas, precioAccionVendida, beneficioObtenido);
                        System.out.println(" 8º) El broker esta almacenando el resultado de la operación de venta con id:" + idOperacionDecodificado + " en la lista de operaciones reralizadas del broker...");
                        System.out.println();
                        resultadosOperaciones.add(mensajeRespuestaVenta);
                        System.out.println("  El resultado de la operación de venta con id:" + idOperacionDecodificado + " ha sido almacenado con éxito");
                        System.out.println();
                        System.out.println("-------------------------------------------FIN OPERACIÓN VENTA: " + mensajeVenta.getIdOperacion() + "-------------------------------------------------------");
                        System.out.println();
                        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    }

                    //FIN OPERACION DE VENTA

                }

                    // OPERACION DE ACTULIZACION

                 else if (mensaje.getTipoOperacion() == TipoOperacion.ACTUALIZACION) {// OPERACION DE ACTULIZACION
                    MensajeActualizacion mensajeActualizacion = (MensajeActualizacion) mensaje;
                    System.out.println("-------------------------------------INICIO OPERACIÓN ACTUALIZACION: " + mensajeActualizacion.getIdOperacion() + "-------------------------------------------------------------");
                    System.out.println();
                    System.out.println(" -------------- ZONA BROKER -------------");
                    System.out.println();
                    System.out.println(" El broker esta consultando en la bolsa los valores de las acciones que se desean actualizar");
                    System.out.println();

                    HashSet<Empresa> empresasActualizadas = new HashSet<Empresa>();
                    Iterator iterador1 = mensajeActualizacion.getEmpresasQueSeQuierenActualizar().iterator(); // creo un objeto Iterator para recorrer la coleccion de empresas que se quieren actualizar
                    System.out.println(" ---- Los nombres de las empresas que se quieren actualizar son: ");
                    System.out.println();
                    int i=1;
                    while (iterador1.hasNext()) {
                        Empresa empresaParaActualizar = (Empresa) iterador1.next();

                        Iterator iterador2 = bolsa.getEmpresas().iterator(); // creo un objeto Iterator para recorrer la coleccion de empresas que existen en la bolsa
                        while (iterador2.hasNext()) {
                            Empresa empresaBolsa = (Empresa) iterador1.next();
                            if (!empresaBolsa.equals(empresaParaActualizar)) {//la empresa que se pretende actualizar ya no pertenece a la bolsa. Entonces devuelvo null.
                                empresaParaActualizar.setValorTituloActual(-1); // pongo el valor del valor actual de la empresa a -1 ya que es un valor imposible que luego me servira para detectar en el banco que esta empresa ya no existe en la bolsa
                                empresasActualizadas.add(empresaParaActualizar);
                                System.out.println(" -------- Empresa para actualizar "+i+": "+ empresaParaActualizar.getNombre() + ". No pertenece a la bolsa. Entonces no se podrá actualizar su valor");
                                i = i+1;
                            }
                            else{
                                System.out.println(" -------- Empresa para actualizar "+i+": "+ empresaParaActualizar.getNombre() + ". Si pertenece a la bolsa.");
                                i = i+1;
                                empresaParaActualizar.setValorTituloActual(empresaBolsa.getValorTituloActual());
                                empresaParaActualizar.setValorTituloPrevio(empresaBolsa.getValorTituloPrevio());
                                empresasActualizadas.add(empresaParaActualizar);
                            }
                        }
                    }
                    System.out.println(" El broker esta almacenando el resultado de la operación de actualización con id:" + mensajeActualizacion.getIdOperacion() + " en la lista de operaciones reralizadas del broker...");
                    System.out.println();
                    Mensaje mensajeResuestaActualizacion = new MensajeRespuestaActualizacion(mensajeActualizacion.getIdOperacion(), mensajeActualizacion.getNombreCliente(), mensajeActualizacion.getDniCliente(), mensajeActualizacion.getTipoOperacion(), true, empresasActualizadas);
                    resultadosOperaciones.add(mensajeResuestaActualizacion);
                    System.out.println(" El resultado de la operación de actualización con id:" + mensajeActualizacion.getIdOperacion() + " ha sido almacenado con éxito");
                    System.out.println();
                    System.out.println("-------------------------------------------FIN OPERACIÓN ACTUALIZACIÓN: " + mensajeActualizacion.getIdOperacion() + "-------------------------------------------------------");
                    System.out.println();
                    System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
                }

                // FIN OPERACION DE ACTULIZACION

            }

            // FIN OPERACION DE EJECUTAR LAS ORDENES PENDIENTES DEL BROKER

            System.out.println("----------------------------------------------------------------- FIN OPERACIÓNES --------------------------------------------------------------------------");
            System.out.println();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(" ---- Borrando lista de operaciones pendientes del broker... ");
            System.out.println();
            operacionesPendientes.clear(); // borramos toda la lista de opraciones pendientes
            System.out.println(" ---- Lista de operaciones pendientes del broker borrada");
            System.out.println();
        }
        else{
            System.out.println(" No hay ninguna operación pendiente en la lista del broker");
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

    public void añadePeticionVentaALaListaDeOperacionesPendientesDelBorker(int idOperacion,String nombreCliente , String dniCliente, String nombreEmpresa, float numTitulosAVender) {

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







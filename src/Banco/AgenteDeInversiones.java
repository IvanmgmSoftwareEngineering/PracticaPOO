package Banco;

import Bolsa.BolsaDeValores;
import Bolsa.Empresa;
import Mensajes.*;

import java.util.ArrayList;
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
            float variacionMaxima = -100;
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

     /*Nombre método: compraDeAcciones
          Entradas: nada
          Salidas: nada
          Excepciones:
          Descripción: Crea una cadena codificada para la compra de acciones
          */

    public void ejecutaPeticionesCompraDeAcciones() {
        Iterator iterador = operacionesPendientes.iterator(); // creo un objeto Iterator para recorrer la coleccion
        while (iterador.hasNext()) {
            Mensaje mensaje = (Mensaje) iterador.next();

            if (mensaje.getTipoOperacion() == TipoOperacion.COMPRA) { // OPERACION DE COMPRA
                MensajeCompra mensajeCompra = (MensajeCompra) mensaje;
                if (!bolsa.getEmpresas().contains(mensaje.getNombreEmpresa())) { // Si la bolsa no tiene a la empresa de la que se quieren adquirir titúlos el resultado de la opracion será NO EFECTUADO: motivo "LA EMPRESA NO EXISTE"
                    Mensaje mensajeResuestaCompra = new MensajeRespuestaCompra(mensajeCompra.getIdOperacion(),mensajeCompra.getNombreCliente(),mensajeCompra.getDniCliente(),mensajeCompra.getNombreEmpresa(), mensajeCompra.getTipoOperacion(), false);
                    resultadosOperaciones.add(mensajeResuestaCompra);
                } else {//La empresa si esta en la bolsa

                    // Llamo al metodo de esta misma clase que construye una cadena de texto codificada con el formato que impone el enunciado. Nota hace falta crearse un metodo para codificar y decodificar ya que lo tenemos creado en la clase Mensaje. En cambio en la bolsa si sera necesario crearse metodos para codificar y decodificar porque la bolsa no ve a la clase mensaje
                        // Envio cadena a la bolsa. El metodo de la bolsa encargado de recpcionar las cadenas codificadas tiene que :
                        //  1º decodificar la cadena
                        //  2º generar una cadena codificada de respuesta
                        //  3º aumentar el valor de la accion de la empresa acorde a un valor proporcional al numero de acciones que compro y al valor actual de la accion. (ojo se debe cambiar valor actual y valor previo)
                        //  4º devolver una sadena codificada String
                        // Decoddificar cadena codificada recibida desde la bolsa y crear un objeto de tipo MensajeRespuestaCompra
                        // Añadir el mensajeRespuesCompra a la lista resultadosOperaciones


                }
            } else if (mensaje.getTipoOperacion() == TipoOperacion.VENTA) {// OPERACION DE COMPRA
                MensajeVenta mensajeVenta = (MensajeVenta) mensaje;
                if (!bolsa.getEmpresas().contains(mensaje.getNombreEmpresa())) { // Si la bolsa no tiene a la empresa de la que se quieren vender titúlos el resultado de la opracion será NO EFECTUADO: motivo "LA EMPRESA NO EXISTE"
                    Mensaje mensajeResuestaVenta = new MensajeRespuestaVenta(mensajeVenta.getIdOperacion(),mensaje.getNombreCliente(),mensaje.getDniCliente(),mensajeVenta.getNombreEmpresa(), mensajeVenta.getTipoOperacion(), false);
                    resultadosOperaciones.add(mensajeResuestaVenta);
                } else {//La empresa si esta en la bolsa


                    // Llamo al metodo de esta misma clase que construye una cadena de texto codificada con el formato que impone el enunciado. Nota hace falta crearse un metodo para codificar y decodificar ya que lo tenemos creado en la clase Mensaje. En cambio en la bolsa si sera necesario crearse metodos para codificar y decodificar porque la bolsa no ve a la clase mensaje

                    // Envio cadena a la bolsa. El metodo de la bolsa encargado de recpcionar las cadenas codificadas tiene que :
                    //  1º decodificar la cadena
                    //  2º generar una cadena codificada de respuesta
                    //  3º disminuir el valor de la accion de la empresa acorde a un valor proporcional al numero de acciones que se venden y al valor actual de la accion. (ojo se debe cambiar valor actual y valor previo)
                    //  4º devolver una sadena codificada String
                    // Decoddificar cadena codificada recibida desde la bolsa y crear un objeto de tipo MensajeRespuestaCompra
                    // Añadir el mensajeRespuesCompra a la lista resultadosOperaciones

                }
            }

            else if (mensaje.getTipoOperacion() == TipoOperacion.ACTUALIZACION) {// OPERACION DE ACTULIZACION





            }
        }
        operacionesPendientes.clear();


    }

        /*Nombre método: añadePeticionCompraALaListaDeOperacionesPendientesDelBorker
          Entradas: int idOperacion,String nombreCliente , String dniCliente, String nombreEmpresa, TipoOperacion tipoOperacion, float cantidadMaxAInvertir
          Salidas: nada
          Excepciones:
          Descripción: Añade una solicitud de compra la lista de operaciones pendientes del broker
          */

    public void añadePeticionCompraALaListaDeOperacionesPendientesDelBorker(int idOperacion,String nombreCliente , String dniCliente, String nombreEmpresa, TipoOperacion tipoOperacion, float cantidadMaxAInvertir) {
        MensajeCompra peticionCompra = new MensajeCompra(idOperacion, nombreCliente, dniCliente, nombreEmpresa, tipoOperacion, cantidadMaxAInvertir);
        operacionesPendientes.add(peticionCompra);
    }

    /*Nombre método: añadePeticionVentaALaListaDeOperacionesPendientesDelBorker
          Entradas: int idOperacion,String nombreCliente , String dniCliente, String nombreEmpresa, TipoOperacion tipoOperacion, int numTitulosAVender)
          Salidas: nada
          Excepciones:
          Descripción: Añade una solicitud de venta a la lista de operaciones pendientes del broker
          */

    public void añadePeticionVentaALaListaDeOperacionesPendientesDelBorker(int idOperacion,String nombreCliente , String dniCliente, String nombreEmpresa, TipoOperacion tipoOperacion, int numTitulosAVender) {

        MensajeVenta peticionVenta = new MensajeVenta(idOperacion,nombreCliente,dniCliente,nombreEmpresa,tipoOperacion,numTitulosAVender);
        operacionesPendientes.add(peticionVenta);
    }


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







package Mensajes;

import General.Utilidades;

public class MensajeCompra extends Mensaje{
    //ZONA DE VARIABLES
    protected double cantidadMaximaAInvertir;
    // FIN ZONA VALIABLES

    //ZONA DE CONSTRUCTORES
    public MensajeCompra(int idOperacion, String nombreCliente, String dniCliente, String nombreEmpresa, TipoOperacion tipoOperacion) {//Constructor para ser llamado desde MensajeRespuestaCompra
        super(idOperacion, nombreCliente, dniCliente, nombreEmpresa, tipoOperacion);
    }
    public MensajeCompra(int idOperacion, String nombreCliente, String dniCliente, String nombreEmpresa, TipoOperacion tipoOperacion, double cantidadMaximaAInvertir) {
        super(idOperacion, nombreCliente, dniCliente, nombreEmpresa, tipoOperacion);
        this.cantidadMaximaAInvertir = cantidadMaximaAInvertir;
    }
    public MensajeCompra(int idOperacion, String nombreCliente, String dniCliente, TipoOperacion tipoOperacion,double cantidadMaximaAInvertir) {
        super(idOperacion, nombreCliente, dniCliente,tipoOperacion);
        this.cantidadMaximaAInvertir = cantidadMaximaAInvertir;
    }
    //FIN ZONA DE CONSTRUCTORES

    //ZONA DE GETTERS
    public double getCantidadMaximaAInvertir() {
        return cantidadMaximaAInvertir;
    }
    //FIN ZONA DE GETTERS

    //ZONA DE METODOS PUBLICOS
    public String codificar() {
        return this.idOperacion + "|" + this.nombreCliente + "|" + this.nombreEmpresa + "|" + this.cantidadMaximaAInvertir+"|";
    }
    @Override
    public String toString() {
        return "IDoperacion: "+this.idOperacion+ ", Tipo: "+ this.tipoOperacion+", Nombre Cliente: "+ this.nombreCliente + ", Dni Cliente: "+ this.dniCliente + ", Nombre Empresa: "+this.nombreEmpresa + ", Importe a invertir: " + Utilidades.formatoDinero(this.cantidadMaximaAInvertir)+"\n" ;
    }
    //FIN ZONA DE METODOS PUBLICOS
}

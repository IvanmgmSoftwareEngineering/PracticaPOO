package Mensajes;

import General.Utilidades;

public class MensajeCompra extends Mensaje{
    protected double cantidadMaximaAInvertir;


    public MensajeCompra(int idOperacion, String nombreCliente, String dniCliente, String nombreEmpresa, TipoOperacion tipoOperacion) {//Constructor para ser llamado desde MensajeRespuestaCompra
        super(idOperacion, nombreCliente, dniCliente, nombreEmpresa, tipoOperacion);
    }

    public MensajeCompra(int idOperacion, String nombreCliente, String dniCliente, String nombreEmpresa, TipoOperacion tipoOperacion, double cantidadMaximaAInvertir) {
        super(idOperacion, nombreCliente, dniCliente, nombreEmpresa, tipoOperacion);
        this.cantidadMaximaAInvertir = cantidadMaximaAInvertir;
    }

    public MensajeCompra(int idOperacion, String nombreCliente, String dniCliente) {
        super(idOperacion, nombreCliente, dniCliente);
    }
    public MensajeCompra(int idOperacion, String nombreCliente, String dniCliente, TipoOperacion tipoOperacion,Double cantidadMaximaAInvertir) {
        super(idOperacion, nombreCliente, dniCliente,tipoOperacion);
        this.cantidadMaximaAInvertir = cantidadMaximaAInvertir;
    }


    public double getCantidadMaximaAInvertir() {
        return cantidadMaximaAInvertir;
    }

    protected String cadenaInversionMaxima() {
        return Utilidades.formatoDinero(this.cantidadMaximaAInvertir); //Estamos usando un método estático
    }

    public String codificar() {
        return this.idOperacion + "|" + this.nombreCliente + "|" + this.dniCliente + "|" + this.nombreEmpresa + "|" + this.cadenaInversionMaxima()+"|";
    }

    @Override
    public String toString() {
        return "IDoperacion: "+this.idOperacion+ ", Tipo: "+ this.tipoOperacion+", Nombre Cliente: "+ this.nombreCliente + ", Dni Cliente: "+ this.dniCliente + ", Nombre Empresa: "+this.nombreEmpresa + ", Importe a invertir: " + this.cantidadMaximaAInvertir+"\n" ;
    }
}

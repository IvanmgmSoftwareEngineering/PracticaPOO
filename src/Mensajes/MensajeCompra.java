package Mensajes;

import General.Utilidades;

public class MensajeCompra extends Mensaje{
    protected float cantidadMaximaAInvertir;


    public MensajeCompra(int idOperacion, String nombreCliente, String dniCliente, String nombreEmpresa, TipoOperacion tipoOperacion) {//Constructor para ser llamado desde MensajeRespuestaCompra
        super(idOperacion, nombreCliente, dniCliente, nombreEmpresa, tipoOperacion);
    }

    public MensajeCompra(int idOperacion, String nombreCliente, String dniCliente, String nombreEmpresa, TipoOperacion tipoOperacion, float cantidadMaximaAInvertir) {
        super(idOperacion, nombreCliente, dniCliente, nombreEmpresa, tipoOperacion);
        this.cantidadMaximaAInvertir = cantidadMaximaAInvertir;
    }




    public float getCantidadMaximaAInvertir() {
        return cantidadMaximaAInvertir;
    }

    protected String cadenaInversionMaxima() {
        return Utilidades.formatoDinero(this.cantidadMaximaAInvertir); //Estamos usando un método estático
    }

    public String codificar() {
        return this.idOperacion + "|" + this.nombreCliente + "|" + this.dniCliente + "|" + this.nombreEmpresa + "|" + this.cadenaInversionMaxima();
    }

    @Override
    public String toString() {
        return "IDoperacion: "+this.idOperacion+ ", Tipo: "+ this.tipoOperacion+", Nombre Cliente: "+ this.nombreCliente + ", Dni Cliente: "+ this.dniCliente + ", Nombre Empresa: "+this.nombreEmpresa + ", Importe a invertir: " + this.cantidadMaximaAInvertir+"\n" ;
    }
}

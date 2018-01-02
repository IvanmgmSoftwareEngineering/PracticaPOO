package Mensajes;

import General.Utilidades;

public class MensajeVenta extends Mensaje {
    protected String nombreEmpresa;
    protected int numTitulosAVender;


    public MensajeVenta(int idOperacion, String nombreCliente, String dniCliente, String nombreEmpresa, TipoOperacion tipoOperacion) {//Constructor para ser llamado desde MensajeRespuestaVenta
        super(idOperacion, nombreCliente, dniCliente, nombreEmpresa, tipoOperacion);
    }
    public MensajeVenta(int identificador, String nombreCliente,String dniCliente, String nombreEmpresa, int numTitulosAVender) {
        super(identificador, nombreCliente, dniCliente);
        this.nombreCliente = nombreCliente;
        this.nombreEmpresa = nombreEmpresa;
        this.numTitulosAVender = numTitulosAVender;
    }

    protected MensajeVenta(int idOperacion, String nombreCliente,String dniCliente) {   // Para MensajeRespuestaVenta
        super(idOperacion,nombreCliente,dniCliente);
    }

    public MensajeVenta(int idOperacion, String nombreCliente, String dniCliente, String nombreEmpresa, TipoOperacion tipoOperacion, int numTitulosAVender) {
        super(idOperacion, nombreCliente, dniCliente, nombreEmpresa, tipoOperacion);
        this.numTitulosAVender = numTitulosAVender;
    }

    public int getNumTitulosAVender() {
        return numTitulosAVender;
    }

    private String cadenaNumAcciones() {
        Utilidades uti = new Utilidades();
        return uti.formatoEntero(this.numTitulosAVender);
    }

    public String codificar() {
        return this.cadenaIdentificador() + "|" + this.nombreCliente + "|" + this.dniCliente + "|" + this.nombreEmpresa + "|" + this.cadenaNumAcciones()+"|";
    }

    @Override
    public String toString() {
        return "IDoperacion: "+this.idOperacion+ ", Tipo: "+ this.tipoOperacion+", Nombre Cliente: "+ this.nombreCliente + ", Dni Cliente: "+ this.dniCliente + ", Nombre Empresa: "+this.nombreEmpresa + ", Número de acciones a vender: " + this.numTitulosAVender+"\n" ;
    }
}



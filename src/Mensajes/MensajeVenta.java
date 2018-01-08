package Mensajes;

import General.Utilidades;

public class MensajeVenta extends Mensaje {
    //ZONA DE VARIABLES
    protected int numTitulosAVender;
    // FIN ZONA VALIABLES

    //ZONA DE CONSTRUCTORES
    public MensajeVenta(int idOperacion, String nombreCliente, String dniCliente, String nombreEmpresa, TipoOperacion tipoOperacion, int numTitulosAVender) {
        super(idOperacion, nombreCliente, dniCliente, nombreEmpresa, tipoOperacion);
        this.numTitulosAVender = numTitulosAVender;
    }
    //FIN ZONA DE CONSTRUCTORES

    //ZONA DE GETTERS
    public int getNumTitulosAVender() {
        return numTitulosAVender;
    }
    //FIN ZONA DE GETTERS

    //ZONA DE METODOS PRIVADOS
    private String cadenaNumAcciones() {
        Utilidades uti = new Utilidades();
        return Utilidades.formatoEntero(this.numTitulosAVender);
    }
    //ZONA DE METODOS PRIVADOS

    //ZONA DE METODOS PUBLICOS
    public String codificar() {
        return this.cadenaIdentificador() + "|" + this.nombreCliente +"|" + this.nombreEmpresa + "|" + this.cadenaNumAcciones()+"|";
    }
    @Override
    public String toString() {
        return "IDoperacion: "+this.idOperacion+ ", Tipo: "+ this.tipoOperacion+", Nombre Cliente: "+ this.nombreCliente + ", Dni Cliente: "+ this.dniCliente + ", Nombre Empresa: "+this.nombreEmpresa + ", Número de acciones a vender: " + this.cadenaNumAcciones()+"\n" ;
    }
    //FIN ZONA DE METODOS PUBLICOS
}



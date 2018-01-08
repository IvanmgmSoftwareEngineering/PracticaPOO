package Mensajes;

import General.Utilidades;


public class MensajeRespuestaCompra extends MensajeCompra {
    //ZONA DE VARIABLES
    private boolean efectuada;
    private double numAccionesCompradas;
    private double precioDeAccion;
    private double dineroSobrante;
    // FIN ZONA VALIABLES

    //ZONA DE CONSTRUCTORES
    public MensajeRespuestaCompra(int idOperacion, String nombreCliente, String dniCliente,String nombreEmpresa, TipoOperacion tipoOperacion, boolean efectuada, double numAccionesCompradas, double precioDeAccion, double dineroSobrante) {
        super(idOperacion, nombreCliente, dniCliente,nombreEmpresa, tipoOperacion);
        this.efectuada = efectuada;
        this.numAccionesCompradas = numAccionesCompradas;
        this.precioDeAccion = precioDeAccion;
        this.dineroSobrante = dineroSobrante;
    }
    public MensajeRespuestaCompra(int idOperacion, String nombreCliente, String dniCliente,TipoOperacion tipoOperacion, double cantidadMaximaAInvertir, boolean efectuada) {
        super(idOperacion, nombreCliente, dniCliente,tipoOperacion,cantidadMaximaAInvertir);
        this.efectuada = efectuada;
    }
    //FIN ZONA DE CONSTRUCTORES

    //ZONA DE GETTERS
    public boolean isEfectuada() {
        return efectuada;
    }
    public double getNumAccionesCompradas() {
        return numAccionesCompradas;
    }
    public double getPrecioDeAccion() {
        return precioDeAccion;
    }
    public double getDineroSobrante() {
        return dineroSobrante;
    }
    //FIN ZONA DE GETTERS

    //ZONA DE METODOS PRIVADOS
    private String cadenaOperacionRealizada() {
        return String.valueOf(this.efectuada);
    }
    private String cadenaNumAccionesCompradas() {
        return Utilidades.formatoDinero(this.numAccionesCompradas);
    }
    private String cadenaPrecioDeAccion() {
        return Utilidades.formatoDinero(this.precioDeAccion);
    }
    private String cadenaDineroSobrante() {
        return Utilidades.formatoDinero(this.dineroSobrante);
    }
    //FIN ZONA DE METODOS PRIVADOS

    //ZONA DE METODOS PUBLICOS
    public String codificar() {
        if (efectuada) return this.idOperacion + "|" + this.nombreCliente + "|" + this.cadenaOperacionRealizada()+ "|" + this.cadenaNumAccionesCompradas() + "|" + this.cadenaPrecioDeAccion()+ "|" + this.cadenaDineroSobrante()+"|";
        else return this.idOperacion + "|" + this.nombreCliente + "|" + this.dniCliente + "|" + this.cadenaOperacionRealizada() +"|";
    }
    //FIN ZONA DE METODOS PUBLICOS
}

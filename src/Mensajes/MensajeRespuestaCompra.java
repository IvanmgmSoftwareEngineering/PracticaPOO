package Mensajes;

import General.Utilidades;


public class MensajeRespuestaCompra extends MensajeCompra {
    private boolean efectuada;
    private int numAccionesCompradas;
    private double precioDeAccion;
    private double dineroSobrante;

    public MensajeRespuestaCompra(int idOperacion, String nombreCliente, String dniCliente, TipoOperacion tipoOperacion, boolean efectuada, int numAccionesCompradas, double precioDeAccion, double dineroSobrante) {
        super(idOperacion, nombreCliente, dniCliente, tipoOperacion);
        this.efectuada = efectuada;
        this.numAccionesCompradas = numAccionesCompradas;
        this.precioDeAccion = precioDeAccion;
        this.dineroSobrante = dineroSobrante;
    }
    public MensajeRespuestaCompra(int idOperacion, String nombreCliente, String dniCliente, String nombreEmpresa, TipoOperacion tipoOperacion, boolean efectuada) {
        super(idOperacion, nombreCliente, dniCliente, nombreEmpresa, tipoOperacion);
        this.efectuada = efectuada;
    }

    public MensajeRespuestaCompra(int idOperacion, String nombreCliente, String dniCliente, boolean efectuada) {
        super(idOperacion, nombreCliente, dniCliente);
        this.efectuada = efectuada;
    }

    public boolean isEfectuada() {
        return efectuada;
    }

    public int getNumAccionesCompradas() {
        return numAccionesCompradas;
    }

    public double getPrecioDeAccion() {
        return precioDeAccion;
    }

    public double getDineroSobrante() {
        return dineroSobrante;
    }

    private String cadenaOperacionRealizada() {
        return String.valueOf(this.efectuada);
    }
    private String cadenaNumAccionesCompradas() {
        return Utilidades.formatoEntero(this.numAccionesCompradas);
    }

    private String cadenaPrecioDeAccion() {
        return Utilidades.formatoDinero(this.precioDeAccion);
    }

    private String cadenaDineroSobrante() {
        return Utilidades.formatoDinero(this.dineroSobrante);
    }

    public String codificar() {
        if (efectuada) return this.idOperacion + "|" + this.nombreCliente + "|" + this.dniCliente + "|" + this.cadenaOperacionRealizada()+ "|" + this.cadenaNumAccionesCompradas() + "|" + this.cadenaPrecioDeAccion()+ "|" + this.cadenaDineroSobrante();
        else return this.idOperacion + "|" + this.nombreCliente + "|" + this.dniCliente + "|" + this.cadenaOperacionRealizada() + "|"+ "La empresa no existe en la bolsa";
    }


}

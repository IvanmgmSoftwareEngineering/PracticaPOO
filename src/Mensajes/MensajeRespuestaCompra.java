package Mensajes;

import General.Utilidades;


public class MensajeRespuestaCompra extends MensajeCompra {
    private boolean efectuada;
    private int numAccionesCompradas;
    private float precioDeAccion;
    private float dineroSobrante;

    public MensajeRespuestaCompra(int idOperacion, String nombreCliente, String dniCliente, String nombreEmpresa, TipoOperacion tipoOperacion, boolean efectuada, int numAccionesCompradas, float precioDeAccion, float dineroSobrante) {
        super(idOperacion, nombreCliente, dniCliente, nombreEmpresa, tipoOperacion);
        this.efectuada = efectuada;
        this.numAccionesCompradas = numAccionesCompradas;
        this.precioDeAccion = precioDeAccion;
        this.dineroSobrante = dineroSobrante;
    }
    public MensajeRespuestaCompra(int idOperacion, String nombreCliente, String dniCliente, String nombreEmpresa, TipoOperacion tipoOperacion, boolean efectuada) {
        super(idOperacion, nombreCliente, dniCliente, nombreEmpresa, tipoOperacion);
        this.efectuada = efectuada;
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
        else return this.idOperacion + "|" + this.nombreCliente + "|" + this.dniCliente + "|" + this.cadenaOperacionRealizada() + "|"+ " La empresa no existe";
    }


}

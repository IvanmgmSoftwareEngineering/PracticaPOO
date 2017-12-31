package Mensajes;

import General.Utilidades;


public class MensajeRespuestaVenta extends MensajeVenta {

    private boolean efectuada;
    private float precioDeAccion;
    private float beneficioTotal;

    public MensajeRespuestaVenta(int idOperacion, String nombreCliente, String dniCliente, String nombreEmpresa, TipoOperacion tipoOperacion, boolean efectuada) {
        super(idOperacion, nombreCliente, dniCliente, nombreEmpresa, tipoOperacion);
        this.efectuada = efectuada;

    }
    public MensajeRespuestaVenta(int idOperacion, String nombreCliente, String dniCliente, String nombreEmpresa, TipoOperacion tipoOperacion, int numTitulosAVender, boolean efectuada,float precioDeAccion,float beneficioTotal) {
        super(idOperacion, nombreCliente, dniCliente, nombreEmpresa, tipoOperacion, numTitulosAVender);
        this.efectuada = efectuada;
        this.precioDeAccion=precioDeAccion;
        this.beneficioTotal = beneficioTotal;
    }

    private String cadenaOperacionRealizada() {
        return String.valueOf(this.efectuada);
    }

    private String cadenaPrecioDeAccion() {
        return Utilidades.formatoDinero(this.precioDeAccion);
    }

    private String cadenaBeneficioTotal() {
        return Utilidades.formatoDinero(this.beneficioTotal);
    }

    public String codificar() {
        if (efectuada)         return this.cadenaIdentificador()+ "|" +this.nombreCliente+ "|" +this.dniCliente+ "|" + this.cadenaOperacionRealizada()+ "|" + this.cadenaPrecioDeAccion() + "|" + this.cadenaBeneficioTotal();
        else  return this.cadenaIdentificador()+ "|" +this.nombreCliente+ "|" +this.dniCliente+ "|" + this.cadenaOperacionRealizada()+  "|" +" La empresa no existe";

    }


}




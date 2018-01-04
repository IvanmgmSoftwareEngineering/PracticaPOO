package Mensajes;

import General.Utilidades;


public class MensajeRespuestaVenta extends MensajeVenta {

    private boolean efectuada;
    private double precioDeAccion;
    private double beneficioTotal;
    private double numAccionesVendidas;

    public MensajeRespuestaVenta(int idOperacion, String nombreCliente, String dniCliente, String nombreEmpresa, TipoOperacion tipoOperacion, boolean efectuada) {
        super(idOperacion, nombreCliente, dniCliente, nombreEmpresa, tipoOperacion);
        this.efectuada = efectuada;

    }
    public MensajeRespuestaVenta(int idOperacion, String nombreCliente, String dniCliente, String nombreEmpresa, TipoOperacion tipoOperacion, boolean efectuada, double numAccionesVendidas,double precioDeAccion,double beneficioTotal) {
        super(idOperacion, nombreCliente, dniCliente, nombreEmpresa, tipoOperacion, numAccionesVendidas);
        this.efectuada = efectuada;
        this.precioDeAccion=precioDeAccion;
        this.beneficioTotal = beneficioTotal;
        this.numAccionesVendidas = numAccionesVendidas;
    }

    public MensajeRespuestaVenta(int identificador, String nombreCliente, String dniCliente, String nombreEmpresa,TipoOperacion tipoOperacion, double numTitulosAVender, boolean efectuada) {
        super(identificador, nombreCliente, dniCliente, nombreEmpresa,tipoOperacion,numTitulosAVender);
        this.efectuada = efectuada;
    }

    public boolean isEfectuada() {
        return efectuada;
    }

    public double getPrecioDeAccion() {
        return precioDeAccion;
    }

    public double getBeneficioTotal() {
        return beneficioTotal;
    }

    public double getNumAccionesVendidas() {
        return numAccionesVendidas;
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
        if (efectuada)         return this.cadenaIdentificador()+ "|" +this.nombreCliente+ "|" +this.dniCliente+ "|" + this.cadenaOperacionRealizada()+ "|" + this.cadenaPrecioDeAccion() + "|" + this.cadenaBeneficioTotal()+"|";
        else  return this.cadenaIdentificador()+ "|" +this.nombreCliente+ "|" +this.dniCliente+ "|" + this.cadenaOperacionRealizada()+  "|";

    }


}




package Mensajes;

import General.Utilidades;


public class MensajeRespuestaVenta extends MensajeVenta {
    //ZONA DE VARIABLES
    private boolean efectuada;
    private double precioDeAccion;
    private double beneficioTotal;
    private double numAccionesVendidas;
    //FIN ZONA VALIABLES

    //ZONA DE CONSTRUCTORES
    public MensajeRespuestaVenta(int idOperacion, String nombreCliente, String dniCliente, String nombreEmpresa, TipoOperacion tipoOperacion, boolean efectuada, int numAccionesVendidas,double precioDeAccion,double beneficioTotal) {
        super(idOperacion, nombreCliente, dniCliente, nombreEmpresa, tipoOperacion, numAccionesVendidas);
        this.efectuada = efectuada;
        this.precioDeAccion=precioDeAccion;
        this.beneficioTotal = beneficioTotal;
        this.numAccionesVendidas = numAccionesVendidas;
    }
    public MensajeRespuestaVenta(int identificador, String nombreCliente, String dniCliente, String nombreEmpresa,TipoOperacion tipoOperacion, int numTitulosAVender, boolean efectuada) {
        super(identificador, nombreCliente, dniCliente, nombreEmpresa,tipoOperacion,numTitulosAVender);
        this.efectuada = efectuada;
    }
    //FIN ZONA DE CONSTRUCTORES

    //ZONA DE GETTERS
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
    //FIN ZONA DE GETTERS

    //ZONA DE METODOS PRIVADOS
    private String cadenaOperacionRealizada() {
        return String.valueOf(this.efectuada);
    }
    private String cadenaPrecioDeAccion() {
        return Utilidades.formatoDinero(this.precioDeAccion);
    }
    private String cadenaBeneficioTotal() {
        return Utilidades.formatoDinero(this.beneficioTotal);
    }
    //FIN ZONA DE METODOS PRIVADOS

    //ZONA DE METODOS PUBLICOS
    public String codificar() {
        if (efectuada)         return this.cadenaIdentificador()+ "|" +this.nombreCliente+ "|" + this.cadenaOperacionRealizada()+ "|" + this.cadenaPrecioDeAccion() + "|" + this.cadenaBeneficioTotal()+"|";
        else  return this.cadenaIdentificador()+ "|" +this.nombreCliente+ "|" + this.cadenaOperacionRealizada()+  "|";

    }
    //FIN ZONA DE METODOS PUBLICOS
}




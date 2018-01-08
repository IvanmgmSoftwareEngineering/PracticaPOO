package Mensajes;


public class Mensaje {
    //ZONA DE VARIABLES
    protected int idOperacion;
    protected String nombreCliente;
    protected String dniCliente;
    protected String nombreEmpresa;
    protected TipoOperacion tipoOperacion;
    // FIN ZONA VALIABLES

    //ZONA DE CONSTRUCTORES
    public Mensaje(int idOperacion,String nombreCliente, String dniCliente) {
        this.idOperacion = idOperacion;
        this.nombreCliente= nombreCliente;
        this.dniCliente= dniCliente;
    }
    public Mensaje(int idOperacion, String nombreCliente, String dniCliente, TipoOperacion tipoOperacion) {//Este constructor lo utilizaremos solo para las operaciones de actualizacion
        this.idOperacion = idOperacion;
        this.nombreCliente = nombreCliente;
        this.dniCliente = dniCliente;
        this.tipoOperacion = tipoOperacion;
    }
    public Mensaje(int idOperacion, String nombreCliente, String dniCliente, String nombreEmpresa, TipoOperacion tipoOperacion) {//Este constructor lo utilizaremos solo para las operaciones de compra y venta
        this.idOperacion = idOperacion;
        this.nombreCliente = nombreCliente;
        this.dniCliente = dniCliente;
        this.nombreEmpresa = nombreEmpresa;
        this.tipoOperacion = tipoOperacion;
    }
    //FIN ZONA DE CONSTRUCTORES

    //ZONA DE GETTERS
    public int getIdOperacion() {
        return idOperacion;
    }
    public String getNombreCliente() {
        return nombreCliente;
    }
    public String getDniCliente() {
        return dniCliente;
    }
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }
    public TipoOperacion getTipoOperacion() {
        return tipoOperacion;
    }
    //FIN ZONA DE GETTERS

    //ZONA DE METODOS PUBLICOS
    protected String cadenaIdentificador() {
        return Integer.toString(idOperacion);
    }
    //FIN ZONA DE METODOS PUBLICOS
}



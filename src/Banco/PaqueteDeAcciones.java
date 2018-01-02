package Banco;


public class PaqueteDeAcciones {

    private double numTitulos;
    private double valorActualTitulo;
    private String nombreEmpresa;


    public PaqueteDeAcciones(double numTitulos, double valorActualTitulo, String nombreEmpresa) {
        this.numTitulos = numTitulos;
        this.valorActualTitulo = valorActualTitulo;
        this.nombreEmpresa = nombreEmpresa;
    }

    public double getNumTitulos() {
        return numTitulos;
    }

    public double getValorActualTitulo() {
        return valorActualTitulo;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNumTitulos(double numTitulos) {
        this.numTitulos = numTitulos;
    }

    public void setValorActualTitulo(double valorActualTitulo) {
        this.valorActualTitulo = valorActualTitulo;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public double calculaValorPaquete(){

        return this.valorActualTitulo*this.numTitulos;

    }

    @Override
    public String toString() {
        return " Nombre Empresa= " + this.nombreEmpresa  + ", Valor Titulo= "+ this.valorActualTitulo+ ", Valor Paquete= " + this.calculaValorPaquete() ;
    }

    @Override
    public boolean equals (Object o) {
        if(o instanceof PaqueteDeAcciones) {
            PaqueteDeAcciones paqueDeAcciones = (PaqueteDeAcciones) o;
            return this.nombreEmpresa.equals(paqueDeAcciones.nombreEmpresa);
        }
        else return false;

    }
}

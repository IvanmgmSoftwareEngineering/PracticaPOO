package Banco;


public class PaqueteDeAcciones {

    private int numTitulos;
    private float valorActualTitulo;
    private String nombreEmpresa;


    public PaqueteDeAcciones(int numTitulos, float valorActualTitulo, String nombreEmpresa) {
        this.numTitulos = numTitulos;
        this.valorActualTitulo = valorActualTitulo;
        this.nombreEmpresa = nombreEmpresa;
    }

    public int getNumTitulos() {
        return numTitulos;
    }

    public float getValorActualTitulo() {
        return valorActualTitulo;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNumTitulos(int numTitulos) {
        this.numTitulos = numTitulos;
    }

    public void setValorActualTitulo(float valorActualTitulo) {
        this.valorActualTitulo = valorActualTitulo;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public float calculaValorPaquete(){

        return this.valorActualTitulo*this.numTitulos;

    }

    @Override
    public String toString() {
        return " Nombre Empresa= " + this.nombreEmpresa  + ", Valor Titulo= "+ this.valorActualTitulo+ ", Valor Paquete" + this.calculaValorPaquete() ;
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

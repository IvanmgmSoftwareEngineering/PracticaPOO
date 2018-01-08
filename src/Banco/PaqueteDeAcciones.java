package Banco;

import General.Utilidades;
import java.io.Serializable;

public class PaqueteDeAcciones implements Serializable {
    //ZONA DE VARIABLES
    private double numTitulos;
    private double valorActualTitulo;
    private String nombreEmpresa;
    // FIN ZONA VALIABLES

    //ZONA DE CONSTRUCTORES
    public PaqueteDeAcciones(double numTitulos, double valorActualTitulo, String nombreEmpresa) {
        this.numTitulos = numTitulos;
        this.valorActualTitulo = valorActualTitulo;
        this.nombreEmpresa = nombreEmpresa;
    }
    //FIN ZONA DE CONSTRUCTORES

    //ZONA DE GETTERS
    public double getNumTitulos() {
        return numTitulos;
    }
    public double getValorActualTitulo() {
        return valorActualTitulo;
    }
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }
    //FIN ZONA DE GETTERS

    //ZONA DE SETTERS
    public void setNumTitulos(double numTitulos) {
        this.numTitulos = numTitulos;
    }
    public void setValorActualTitulo(double valorActualTitulo) {
        this.valorActualTitulo = valorActualTitulo;
    }
    //FIN ZONA DE SETTERS

    //ZONA DE METODOS PUBLICOS
    public double calculaValorPaquete(){
        return this.valorActualTitulo*this.numTitulos;
    }

    @Override
    public String toString() {
        return "-------------------"+"Paquete de acciones de la empresa = " + this.nombreEmpresa + ", Valor Titulo = "+ Utilidades.formatoDinero(this.valorActualTitulo)+ ", Valor Paquete = " + Utilidades.formatoDinero(this.calculaValorPaquete())+"----------"+"\n" ;
    }
    @Override
    public boolean equals (Object o) {
        if(o instanceof PaqueteDeAcciones) {
            PaqueteDeAcciones paqueDeAcciones = (PaqueteDeAcciones) o;
            return this.nombreEmpresa.equals(paqueDeAcciones.nombreEmpresa);
        }
        else return false;
    }
    //FIN ZONA DE METODOS PUBLICOS
}

package Bolsa;

import General.Utilidades;
import java.io.Serializable;

public class Empresa implements Serializable{
    static final long serialVersionUID = 42L;//sirve para que la serializacion/deseralizacion de empresas sea coherente
    //ZONA DE VARIABLES
    private String nombre;
    private double valorTituloActual;
    private double valorTituloPrevio;
    // FIN ZONA VALIABLES

    //ZONA DE CONSTRUCTORES
    public Empresa() {}
    public Empresa(String nombre, double valorTituloActual) {
        this.nombre = nombre;
        this.valorTituloActual = valorTituloActual;
        this.valorTituloPrevio = valorTituloActual;
    }
    //FIN ZONA DE CONSTRUCTORES


    //ZONA DE GETTERS
    public String getNombre() {
        return nombre;
    }
    public double getValorTituloActual() {
        return this.valorTituloActual;
    }
    public double getValorTituloPrevio() {
        return valorTituloPrevio;
    }
    //FIN ZONA DE GETTERS

    //ZONA DE SETTERS
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setValorTituloActual(double valorTituloActual) {
        this.valorTituloActual = valorTituloActual;
    }
    public void setValorTituloPrevio(double valorTituloPrevio) {
        this.valorTituloPrevio = valorTituloPrevio;
    }
    //FIN ZONA DE SETTERS

    //ZONA DE METODOS PUBLICOS
    public double calculaVariacion()  {

        if(valorTituloPrevio == 0){
            return 0;
        }
        return ((valorTituloActual-valorTituloPrevio)/valorTituloPrevio)*100;
    }
    @Override
    public String toString(){
        return "                    "+"Nombre Empresa: "+this.nombre + "  |||| Valor Actual Título: "+  Utilidades.formatoDinero(this.valorTituloActual) +"  |||| Variación: "+  Utilidades.formatovariacion(this.calculaVariacion())+ " %" +"\n";
    }
    @Override
    public boolean equals (Object o) {
        if(o instanceof Empresa) {
            Empresa empresa = (Empresa) o;
            return this.nombre.equals(empresa.nombre);
        }
        else return false;

    }
    @Override
    public int hashCode(){
        return this.nombre.length();
    }
    //FIN ZONA DE METODOS PUBLICOS
}

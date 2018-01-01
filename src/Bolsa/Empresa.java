package Bolsa;

import java.io.Serializable;



public class Empresa implements Serializable{
    private String nombre;
    private double valorTituloActual;
    private double valorTituloPrevio;

    public Empresa() {
    }

    public Empresa(String nombre, double valorTituloActual) {
        this.nombre = nombre;
        this.valorTituloActual = valorTituloActual;
        this.valorTituloPrevio = valorTituloActual;
    }

    public String getNombre() {
        return nombre;
    }

    public double getValorTituloActual() {
        return this.valorTituloActual;
    }

    public double getValorTituloPrevio() {
        return valorTituloPrevio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setValorTituloActual(double valorTituloActual) {
        this.valorTituloActual = valorTituloActual;
    }

    public void setValorTituloPrevio(double valorTituloPrevio) {
        this.valorTituloPrevio = valorTituloPrevio;
    }


    public double calculaVariacion()  {

        if(valorTituloPrevio == 0){
            return 0;
        }
        return ((valorTituloActual-valorTituloPrevio)/valorTituloPrevio)*100;
    }

    public String toString(){
        return "Nombre Empresa: "+this.nombre + "  |||| Valor Actual Título: "+  this.valorTituloActual +"  |||| Variación: "+  this.calculaVariacion()+ " %" +"\n";
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

}

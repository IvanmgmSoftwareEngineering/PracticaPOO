package Banco;

import General.Utilidades;

import java.util.HashSet;

public class Cliente extends Persona {
    //ZONA DE VARIABLES
    protected double saldo;
    protected  boolean esPremium;
    protected HashSet<PaqueteDeAcciones> paquetesAcciones;
    // FIN ZONA VALIABLES

    //ZONA DE CONSTRUCTORES
    public Cliente(String nombre, String dni, double saldo) {
        super(nombre, dni);
        this.saldo = saldo;
        this.esPremium = false;
        this.paquetesAcciones = new HashSet<PaqueteDeAcciones>();
    }
    public Cliente(String nombre, String dni, double saldo,boolean esPremium) {
        super(nombre, dni);
        this.saldo = saldo;
        this.esPremium = esPremium;
        this.paquetesAcciones = new HashSet<PaqueteDeAcciones>();
    } // Este constructor esta para poder ser llamado desde la clase hija 'ClientePremium'
    public Cliente(String nombre, String dni, double saldo,boolean esPremium,HashSet<PaqueteDeAcciones> paquetesAcciones) {
        super(nombre, dni);
        this.saldo = saldo;
        this.esPremium = esPremium;
        this.paquetesAcciones = paquetesAcciones;
    }
    //FIN ZONA DE CONSTRUCTORES

    //ZONA DE GETTERS
    public double getSaldo() {
        return this.saldo;
    }
    public boolean isEsPremium() {
        return this.esPremium;
    }
    public HashSet<PaqueteDeAcciones> getPaquetesAcciones() {
        return paquetesAcciones;
    }
    //FIN ZONA DE GETTERS

    //ZONA DE SETTERS
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    //FIN ZONA DE SETTERS

    //ZONA DE METODOS PUBLICOS
    @Override
    public String toString (){
        if(paquetesAcciones.size()==0)
            return  "   "+"Nombre Cliente: "+this.nombre + "  ||||  dni: "+this.dni +"  ||||Saldo: "+ Utilidades.formatoDinero(this.saldo)+ "    |||| Categoria: NO Premium"+ "    ||||Paquetes de Acciones: NO tiene"+ "\n"+"\n";

        else {
            return "  "+ "Nombre Cliente: " + this.nombre + "  ||||  dni: " + this.dni + "  ||||Saldo: " + Utilidades.formatoDinero(this.saldo) + "    |||| Categoria: NO Premium" + "    ||||Paquetes de Acciones: " + "\n" + paquetesAcciones.toString()+"\n" ;

            }

    }
    @Override
    public boolean equals (Object o) {
        if(o instanceof Cliente) {
            Cliente cliente = (Cliente) o;
            return this.dni.equals(cliente.dni);
        }
        else return false;
    }
    @Override
    public int hashCode(){
        return this.dni.length();
    }
    //FIN ZONA DE METODOS PUBLICOS
}

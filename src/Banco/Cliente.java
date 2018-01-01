package Banco;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;

public class Cliente extends Persona {
    protected float saldo;
    protected  boolean esPremium;
    protected HashSet<PaqueteDeAcciones> paquetesAcciones;

    public Cliente(String nombre, String dni, float saldo) {
        super(nombre, dni);
        this.saldo = saldo;
        this.esPremium = false;
        this.paquetesAcciones = new HashSet<PaqueteDeAcciones>();
    }

    // Este constructor esta para poder ser llamado desde la clase hija 'ClientePremium'
    public Cliente(String nombre, String dni, float saldo,boolean esPremium) {
        super(nombre, dni);
        this.saldo = saldo;
        this.esPremium = esPremium;
        this.paquetesAcciones = new HashSet<PaqueteDeAcciones>();
    }

    public float getSaldo() {
        return this.saldo;
    }

    public boolean isEsPremium() {
        return this.esPremium;
    }

    public HashSet<PaqueteDeAcciones> getPaquetesAcciones() {
        return paquetesAcciones;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public void setEsPremium(boolean esPremium) {
        this.esPremium = esPremium;
    }

    public void setPaquetesAcciones(HashSet<PaqueteDeAcciones> paquetesAcciones) {
        this.paquetesAcciones = paquetesAcciones;
    }

    public String toString (){
        String cadena = null;

        if(paquetesAcciones.size()==0)
            cadena = "Nombre Cliente: "+this.nombre + "  ||||  dni: "+this.dni +"  ||||Saldo: "+ this.saldo+ "    |||| Categoria: NO Premium"+ "    ||||Paquetes de Acciones: NO tiene"+ "\n";

        else {

            cadena= "Nombre Cliente: " + this.nombre + "  ||||  dni: " + this.dni + "  ||||Saldo: " + this.saldo + "    |||| Categoria: NO Premium" + "    ||||Paquetes de Acciones:" + "\n" ;
            Iterator iterador = paquetesAcciones.iterator();
            while (iterador.hasNext()){
                PaqueteDeAcciones paquete = (PaqueteDeAcciones) iterador.next();
                cadena = cadena + paquete.toString() +"\n";
            }
        }
        return cadena;

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
}

package Banco;

import java.util.HashSet;
import java.util.Iterator;

public class ClientePremium extends Cliente {// Esta clase permite a un cliente normal hacerle premium
    private String nombreGestorDeInversiones;


    public ClientePremium(String nombre, String dni, float saldo) {
        super(nombre, dni, saldo, true);
    }

    public ClientePremium(String nombre, String dni, float saldo, String nombreGestorDeInversiones) {
                super(nombre, dni, saldo,true);
                this.nombreGestorDeInversiones = nombreGestorDeInversiones;
    }

    public ClientePremium(Cliente clienteOrigen, String nombreGestorDeInversiones) {
                super(clienteOrigen.nombre, clienteOrigen.dni, clienteOrigen.saldo,true);
                this.nombreGestorDeInversiones = nombreGestorDeInversiones;
    }

    public String getNombreGestorDeInversiones() {
        return nombreGestorDeInversiones;
    }

    @Override
    public String toString() {
        String cadena;

        if(paquetesAcciones.size()==0)
            cadena = "Nombre Cliente: "+this.nombre + "  ||||  dni: "+this.dni +"  ||||Saldo: "+ this.saldo + "    ||||Categoria: Premium " +"    ||||Nombre Gestor: "+nombreGestorDeInversiones + "    ||||Paquetes de Acciones: NO tiene"+ "\n";

        else {

            cadena= "Nombre Cliente: " + this.nombre + "  ||||  dni: " + this.dni + "  ||||Saldo: " + this.saldo + "    ||||Categoria: Premium " +"    ||||Nombre Gestor: "+nombreGestorDeInversiones + "    ||||Paquetes de Acciones:" + "\n" ;
            Iterator iterador = paquetesAcciones.iterator();
            while (iterador.hasNext()){
                PaqueteDeAcciones paquete = (PaqueteDeAcciones) iterador.next();
                cadena = cadena + paquete.toString() +"\n";
            }
        }
        return cadena;
    }
}







package Banco;

import java.util.HashSet;
import java.util.Iterator;

public class ClientePremium extends Cliente {// Esta clase permite a un cliente normal hacerle premium
    private String nombreGestorDeInversiones;


    public ClientePremium(String nombre, String dni, float saldo) {
        super(nombre, dni, saldo, true);
    }

    public ClientePremium(String nombre, String dni, float saldo, String nombreGestorDeInversiones) {
        super(nombre, dni, saldo, true);
        this.nombreGestorDeInversiones = nombreGestorDeInversiones;
    }

    public ClientePremium(Cliente clienteOrigen, String nombreGestorDeInversiones) {
        super(clienteOrigen.nombre, clienteOrigen.dni, clienteOrigen.saldo, true, clienteOrigen.getPaquetesAcciones());
        this.nombreGestorDeInversiones = nombreGestorDeInversiones;
    }

    public String getNombreGestorDeInversiones() {
        return nombreGestorDeInversiones;
    }


    public String toString() {
        String cadena = null;

        if (paquetesAcciones.size() == 0)
            /*cadena =*/ return "Nombre Cliente: " + this.nombre + "  ||||  dni: " + this.dni + "  ||||Saldo: " + this.saldo + "    ||||Categoria: Premium " + "    ||||Nombre Gestor: " + nombreGestorDeInversiones + "    ||||Paquetes de Acciones: NO tiene" + "\n";

        else {

            /*cadena =*/ return  "Nombre Cliente: " + this.nombre + "  ||||  dni: " + this.dni + "  ||||Saldo: " + this.saldo + "    ||||Categoria: Premium " + "    ||||Nombre Gestor: " + nombreGestorDeInversiones + "    ||||Paquetes de Acciones:" + "\n" + paquetesAcciones.toString();
            /*Iterator iterador = paquetesAcciones.iterator();
            //int i=1;
            //while (iterador.hasNext()){
                PaqueteDeAcciones paquete = (PaqueteDeAcciones) iterador.next();
                cadena = cadena + "---------->Paquete "+i+": "+paquete.toString() +"\n";
                i=i+1;
            }
        }
        return  ""+cadena;
        */

        }
    }
}







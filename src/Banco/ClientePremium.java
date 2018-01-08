package Banco;

import General.Utilidades;

public class ClientePremium extends Cliente {
    //ZONA DE VARIABLES
    private String nombreGestorDeInversiones;
    // FIN ZONA VALIABLES

    //ZONA DE CONSTRUCTORES
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
    //FIN ZONA DE CONSTRUCTORES

    //ZONA DE GETTERS
    public String getNombreGestorDeInversiones() {
        return nombreGestorDeInversiones;
    }
    //FIN ZONA DE GETTERS

    //ZONA DE METODOS PUBLICOS
    @Override
    public String toString() {
        String cadena = null;
        if (paquetesAcciones.size() == 0)
             return"  "+  "Nombre Cliente: " + this.nombre + "  ||||  dni: " + this.dni + "  ||||Saldo: " + Utilidades.formatoDinero(this.saldo) + "    ||||Categoria: Premium " + "    ||||Nombre Gestor: " + nombreGestorDeInversiones + "    ||||Paquetes de Acciones: NO tiene" + "\n"+ "\n";
        else {
            return "  "+  "Nombre Cliente: " + this.nombre + "  ||||  dni: " + this.dni + "  ||||Saldo: " + Utilidades.formatoDinero(this.saldo) + "    ||||Categoria: Premium " + "    ||||Nombre Gestor: " + nombreGestorDeInversiones + "    ||||Paquetes de Acciones:" + "\n" + paquetesAcciones.toString()+ "\n";
        }
    }
    //FIN ZONA DE METODOS PUBLICOS
}







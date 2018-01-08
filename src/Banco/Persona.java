package Banco;

import java.io.Serializable;

public class Persona implements Serializable {
    static final long serialVersionUID = 43L;//sirve para que la serializacion/deseralizacion de clientes sea coherente
    //ZONA DE VARIABLES
    protected  String nombre;
    protected String dni;
    // FIN ZONA VALIABLES

    //ZONA DE CONSTRUCTORES
    public Persona(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }
    //FIN ZONA DE CONSTRUCTORES

    //ZONA DE GETTERS
    public String getNombre() {
        return nombre;
    }
    //FIN ZONA DE GETTERS

}

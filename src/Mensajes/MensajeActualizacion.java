package Mensajes;

import java.util.HashSet;
import java.util.Iterator;

import Banco.PaqueteDeAcciones;
import Bolsa.*;

public class MensajeActualizacion extends Mensaje {
    //ZONA DE VARIABLES
    protected HashSet <Empresa> empresasQueSeQuierenActualizar; //Contiene solo el nombre de las empresas que el cliente posee en cada uno de los paquetes de acciones
    protected String fecha;
    // FIN ZONA VALIABLES

    //ZONA DE CONSTRUCTORES
    public MensajeActualizacion(int idOperacion, String fecha, String nombreCliente, String dniCliente, TipoOperacion tipoOperacion, HashSet<Empresa> empresasQueSeQuierenActualizar) {
        super(idOperacion, nombreCliente, dniCliente, tipoOperacion);
        this.fecha=fecha;
        this.empresasQueSeQuierenActualizar = empresasQueSeQuierenActualizar;
    }
    //FIN ZONA DE CONSTRUCTORES

    //ZONA DE GETTERS
    public HashSet<Empresa> getEmpresasQueSeQuierenActualizar() {
        return empresasQueSeQuierenActualizar;
    }
    public String getFecha() {
        return fecha;
    }
    //FIN ZONA DE GETTERS

    //ZONA DE METODOS PUBLICOS
    @Override
    public String toString() {
        //String cadena = null;

         return "IDoperacion: "+this.idOperacion+ ", Fecha: "+this.fecha+ ", Tipo: "+ this.tipoOperacion+", Nombre Cliente: "+ this.nombreCliente + ", Dni Cliente: "+ this.dniCliente + ", Nombre Empresas que se quieren actualizar: "+"\n" + empresasQueSeQuierenActualizar.toString();
    }
    //FIN ZONA DE METODOS PUBLICOS
}

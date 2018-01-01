package Mensajes;

import java.util.HashSet;
import java.util.Iterator;

import Banco.PaqueteDeAcciones;
import Bolsa.*;

public class MensajeActualizacion extends Mensaje {
    protected HashSet <Empresa> empresasQueSeQuierenActualizar; //Contiene solo el nombre de las empresas que el cliente posee en cada uno de los paquetes de acciones

    public MensajeActualizacion(int idOperacion, String nombreCliente, String dniCliente, TipoOperacion tipoOperacion, HashSet<Empresa> empresasQueSeQuierenActualizar) {
        super(idOperacion, nombreCliente, dniCliente, tipoOperacion);
        this.empresasQueSeQuierenActualizar = empresasQueSeQuierenActualizar;
    }

    public HashSet<Empresa> getEmpresasQueSeQuierenActualizar() {
        return empresasQueSeQuierenActualizar;
    }

    @Override
    public String toString() {
        //String cadena = null;

         return "IDoperacion: "+this.idOperacion+ ", Tipo: "+ this.tipoOperacion+", Nombre Cliente: "+ this.nombreCliente + ", Dni Cliente: "+ this.dniCliente + ", Nombre Empresas que se quieren actualizar: "+"\n" ;

          /*  cadena= "Nombre Cliente: " + this.nombre + "  ||||  dni: " + this.dni + "  ||||Saldo: " + this.saldo + "    |||| Categoria: NO Premium" + "    ||||Paquetes de Acciones:" + "\n" ;
            Iterator iterador = paquetesAcciones.iterator();
            while (iterador.hasNext()){
                PaqueteDeAcciones paquete = (PaqueteDeAcciones) iterador.next();
                cadena = cadena + paquete.toString() +"\n";
            }
            */

    }

}

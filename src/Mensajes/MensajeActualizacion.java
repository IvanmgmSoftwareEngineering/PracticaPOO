package Mensajes;

import java.util.HashSet;

public class MensajeActualizacion extends Mensaje {

    private HashSet <String> empresasQueSeQuierenActualizarCodificas; //Contiene solo el nombre de las empresas que el cliente posee en cada uno de los paquetes de acciones

    public MensajeActualizacion(int idOperacion, String nombreCliente, String dniCliente, TipoOperacion tipoOperacion, HashSet<String> empresasQueSeQuierenActualizarCodificas) {
        super(idOperacion, nombreCliente, dniCliente, tipoOperacion);
        this.empresasQueSeQuierenActualizarCodificas = empresasQueSeQuierenActualizarCodificas;
    }

    @Override
    public String toString() {
        return "IDoperacion: "+this.idOperacion+ ", Tipo: "+ this.tipoOperacion+", Nombre Cliente: "+ this.nombreCliente + ", Dni Cliente: "+ this.dniCliente + ", Nombre Empresa: "+"\n" ;
    }

}

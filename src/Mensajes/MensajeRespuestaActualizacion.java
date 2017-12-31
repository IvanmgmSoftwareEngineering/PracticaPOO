package Mensajes;

import java.util.HashSet;

public class MensajeRespuestaActualizacion extends MensajeActualizacion{
    private HashSet<String> empresasActualizadasCodificas; //Contiene solo el nombre de las empresas que el cliente posee en cada uno de los paquetes de acciones

    public MensajeRespuestaActualizacion(int idOperacion, String nombreCliente, String dniCliente, TipoOperacion tipoOperacion, HashSet<String> empresasQueSeQuierenActualizarCodificas, HashSet<String> empresasActualizadasCodificas) {
        super(idOperacion, nombreCliente, dniCliente, tipoOperacion, empresasQueSeQuierenActualizarCodificas);
        this.empresasActualizadasCodificas = empresasActualizadasCodificas;
    }
}

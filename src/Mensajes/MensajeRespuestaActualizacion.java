package Mensajes;

import Bolsa.Empresa;

import java.util.HashSet;

public class MensajeRespuestaActualizacion extends MensajeActualizacion{
    private HashSet<String> empresasActualizadas; //Contiene solo el nombre de las empresas que el cliente posee en cada uno de los paquetes de acciones
    private boolean efectuada;

    public MensajeRespuestaActualizacion(int idOperacion, String nombreCliente, String dniCliente, TipoOperacion tipoOperacion, boolean efectuada, HashSet<Empresa> empresasActualizadas) {
        super(idOperacion, nombreCliente, dniCliente, tipoOperacion, empresasActualizadas);
        this.efectuada = efectuada;
    }
}

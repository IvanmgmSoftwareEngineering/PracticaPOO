package Mensajes;

import Bolsa.Empresa;
import java.util.HashSet;

public class MensajeRespuestaActualizacion extends MensajeActualizacion{
    //ZONA DE VARIABLES
    private boolean efectuada;
    // FIN ZONA VALIABLES

    //ZONA DE CONSTRUCTORES
    public MensajeRespuestaActualizacion(int idOperacion,String fecha, String nombreCliente, String dniCliente, TipoOperacion tipoOperacion, HashSet<Empresa> empresasActualizadas, boolean efectuada) {
        super(idOperacion,fecha, nombreCliente, dniCliente, tipoOperacion, empresasActualizadas);
        this.efectuada = efectuada;
    }
    //FIN ZONA DE CONSTRUCTORES
}

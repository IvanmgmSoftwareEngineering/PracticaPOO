package Mensajes;

import Bolsa.Empresa;

import java.util.HashSet;

public class MensajeRespuestaActualizacion extends MensajeActualizacion{
    private boolean efectuada;

    public MensajeRespuestaActualizacion(int idOperacion, String nombreCliente, String dniCliente, TipoOperacion tipoOperacion, HashSet<Empresa> empresasActualizadas, boolean efectuada) {
        super(idOperacion, nombreCliente, dniCliente, tipoOperacion, empresasActualizadas);
        this.efectuada = efectuada;
    }




}

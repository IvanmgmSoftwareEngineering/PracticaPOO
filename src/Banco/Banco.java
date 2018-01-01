package Banco;


import ExcepcionesPropias.*;
import Mensajes.*;
import Utilidades.Input;
import Utilidades.Output;
import Bolsa.*;


import java.io.*;
import java.util.HashSet;
import java.util.Iterator;

public class Banco {
    //ZONA DE VARIABLES
    private String nombre;
    private AgenteDeInversiones broker;
    private HashSet<Cliente> clientes;
    private AgenteDeInversiones gestor;
    private int idOperacion;
    //FIN ZONA VARIABLES

    //ZONA CONSTRUCTORES
    //constructor que solo recibe el nombre del banco
    public Banco(String nombre) {
        this.nombre = nombre;
        this.clientes = new HashSet<Cliente>();
        this.idOperacion = 0;
    }

    //constructor que solo recibe el nombre del banco y un cliente
    public Banco(String nombre, Cliente cliente) {
        this.nombre = nombre;
        this.clientes = new HashSet<Cliente>();
        this.clientes.add(cliente);
    }

    //constructor que solo recibe el nombre del banco y un Agente de inversiones
    public Banco(String nombre, AgenteDeInversiones broker) {
        this.nombre = nombre;
        this.broker = broker;
        this.clientes = new HashSet<Cliente>();
    }

    //constructor que solo recibe el nombre del banco y un Agente de inversiones y una lista de clientes
    public Banco(String nombre, AgenteDeInversiones broker, HashSet<Cliente> clientes) {
        this.nombre = nombre;
        this.broker = broker;
        this.clientes = new HashSet<Cliente>();
        this.clientes.addAll(clientes);
    }

    //FIN ZONA CONSTRUCTORES

    //ZONA DE GETTERS

    public String getNombre() {
        return nombre;
    }

    public AgenteDeInversiones getBroker() {
        return broker;
    }

    public HashSet<Cliente> getClientes() {
        return clientes;
    }

    //FIN ZONA GETTERS

    //ZONA DE SETTERS

    public void setBroker(AgenteDeInversiones broker) {
        this.broker = broker;
    }

    public void setGestor(AgenteDeInversiones gestor) {
        this.gestor = gestor;
    }

    //FIN ZONA SETTERS

    //ZONA DE METODOS PUBLICOS

    /*Nombre método: addCliente
      Entradas: Objeto de tipo Cliente
      Salidas: nada
      Excepciones: ninguna
      Descripción: añade un nuevo cliente a la lista solo si el cliente no estaba en la lista
      */
    public void addCliente(Cliente cliente) {
        if (!this.clientes.add(cliente)) {
            System.out.println("El cliente que ha intendado añadir YA esta presente en el banco");
        } else System.out.println("Cliente añadido con exito!");
    }

    /*Nombre método: addClientes
      Entradas: Objeto de tipo HashSet<Cliente>, es decir, una lista de Clientes
      Salidas: nada
      Excepciones: ninguna
      Descripción: añade una lista de clientes a la lista solo si ningun de los clientes que se intentan añadir estaban ya en la lista
      */
    public void addClientes(HashSet<Cliente> cliente1) {
        if (!clientes.addAll(cliente1))
            System.out.println("Las empresas que ha intendado añadir, tiene alguna/s empresa que ya esta presente en la bolsa");
        else System.out.println("Empresas añadidas con exito!");
    }

    /*Nombre método: removeClientes
      Entradas: Objeto de tipo String, el dni de un cliente.
      Salidas: nada
      Excepciones: ninguna
      Descripción: elimina un cliente de la lista.
      */
    public void removeCliente(String dniCliente) {
        Cliente cliente = new Cliente("sdsd", dniCliente, 32);
        if (!clientes.remove(cliente))
            System.out.println("El cliente que ha intendado borrar NO esta presente en el banco");
        else System.out.println("Cliente eliminado con exito!");

    }

    /*Nombre método: showClientes
      Entradas: nada
      Salidas: nada
      Excepciones:
      Descripción: Muestra todas las empresas que hay contenidas en la lista de empresas.
      */
    public void showClientes() {
        if (clientes.size() == 0) System.out.println("No hay clientes en la banco!");
        else {
            System.out.println(clientes.toString());
        }
    }

    /*Nombre método: copiaSeguridadBanco
      Entradas:nada
      Salidas: nada
      Excepciones: nada
      Descripción: Serializa la informacion de los clientes que hay en el banco y los transforma en un fichero binario
      */

    public void copiaSeguridadBanco(String path, Output serializa) throws IOException {

        serializa.abrir(path);
        Iterator iterador = clientes.iterator();
        System.out.println("Copiando...");
        System.out.println();
        while (iterador.hasNext()) {
            Cliente cliente = (Cliente) iterador.next();
            serializa.escribirCliente(cliente);
        }
        serializa.cerrar();
    }

    /*Nombre método: restaurarCopiaSeguridadClientes
      Entradas: String path del fichero, objeto de tipo Input
      Salidas: nada
      Excepciones: IOException y ClassNotFoundException
      Descripción: Deserializa la información de las empresas presentes en la bolsa y las uarda en disco
      */
    public void restaurarCopiaSeguridadClientes(String path, Input deserializa) throws IOException, ClassNotFoundException {
        Cliente cliente;
        deserializa.abrir(path);
        System.out.println("Restaurando...");
        System.out.println();
        clientes.clear();//borramos toda la lista antes de cargar desde disco la nueva lista de la que dispondremos
        do {

            cliente = deserializa.leerCliente();
            clientes.add(cliente);
        } while (cliente != null);
        deserializa.cerrar();
        clientes.remove(null);
    }

    /*Nombre método: promocionAClientePremium
      Entradas: dni cliente
      Salidas: nada
      Excepciones:
      Descripción: Promociona a un cleinte ya existente a premium asignandole a un gestor
      */
    public void promocionAClientePremium(String dniCliente) throws BancoNoTieneGestor {
        boolean encontrado;
        boolean noExiste;
        try {
            Cliente cliente = new Cliente("sdsd", dniCliente, 32); //creamos este objeto auxiliar para comparar con los elemntos de la lista de clientes

            if (this.broker == null) { // miro a ver si el banco tiene un gestor asociado
                System.out.println("El bancco no tiene asignado ningún gestor");
            } else {
                encontrado = false;
                noExiste = true;
                Iterator iterador = clientes.iterator(); // creo un objeto Iterator para recorrer la coleccion
                while (iterador.hasNext() && !encontrado) {
                    Cliente cliente1 = (Cliente) iterador.next();
                    if (cliente1.isEsPremium()) {
                        System.out.println("El cliente con dni: " + dniCliente + " ya tiene categoria Premium y su gestor es: " + ((ClientePremium) cliente1).getNombreGestorDeInversiones());
                        noExiste = false;
                    } else if (cliente.equals(cliente1)) {
                        encontrado = true;
                        cliente = cliente1;//meto en la varible cliente al objeto de la colleccion cliente1 que coincide con el en el dni
                    }
                }
                if (encontrado) {
                    Cliente clientePremium = new ClientePremium(cliente, this.broker.getNombre());// creo un nuevo objeto de tipo cliente como tipo estatico pero como tipo dinamico de tipo cliente premium utilizando el constructor que recibe a un cliente antiguo y el nombre del gestor
                    clientes.remove(cliente); // borro de la coleccion al cliente antiguo, es decir el que no era premium
                    clientes.add(clientePremium); // añado a la coleccion al cliente ya promocionado a premium
                    System.out.println("El cliente con dni: " + dniCliente + " se le ha otorgado la categoria Premium y su gestor asignado es: " + this.broker.getNombre());
                } else if (noExiste) {
                    System.out.println("El cliente con dni: " + dniCliente + " no existe");
                }

            }
        } catch (NullPointerException bancoNotieneGestor) {
            throw new BancoNoTieneGestor("Se ha intentado promocionar a un cliente a premium sin que el banco tenga un gestor de inversiones");
        }


    }

    /*Nombre método: recomendacionDeInversion
          Entradas: dni cliente
          Salidas: String nombreEmpresa
          Excepciones:
          Descripción: Compara la valoración de todas las empresas y selecciona la que mejor desempeño ha tenido
          */
    public void recomendacionDeInversion(String dniCliente) {
        Cliente cliente = new Cliente("Markos", dniCliente, 10);
        boolean encontrado = false;
        if (clientes.contains(cliente)) {
            Iterator iterador = clientes.iterator();
            Cliente cliente1 = null;
            while (iterador.hasNext() && !encontrado) {

                cliente1 = (Cliente) iterador.next();
                if (cliente1.equals(cliente)) {
                    encontrado = true;
                }
            }

            if (!cliente1.isEsPremium()) {
                System.out.println("El cliente no es Premium");
            } else {
                String nombreGestorRecomendacion = gestor.consultaDeInversiones();
                if (nombreGestorRecomendacion != "1") {
                    System.out.println("Actualización finalizada con éxito");
                    System.out.println("El nombre de la empresa que mayor variación ha tenido es: " + gestor.consultaDeInversiones());

                }
            }
        } else {
            System.out.println("El cliente no existe");

        }
    }


//ZONA DE OPERACIONES

    /*Nombre método: compraAcciones
      Entradas: String dniCliente, String nombreEmpresa, float cantidadMaxAInvertir
      Salidas: nada
      Excepciones:
      Descripción: Envia al broker las peticiones de venta si todo el cliente cumple los requisitos(cliente pretenece al banco, el saldo del cleinte es superior a la cantidad que desea invertir).
      */

    public void compraAcciones(String dniCliente, String nombreEmpresa, float cantidadMaxAInvertir) {
        boolean encontrado = false;
        Cliente cliente = new Cliente("ssss", dniCliente, 2);
        if (!clientes.contains(cliente)) {
            System.out.println("El cliente con dni: " + dniCliente + " no es cliente de este banco");
        } else {
            Iterator iterador = clientes.iterator(); // creo un objeto Iterator para recorrer la coleccion
            while (iterador.hasNext() && !encontrado) {
                Cliente cliente1 = (Cliente) iterador.next();
                if (cliente1.equals(cliente)) {
                    encontrado = true;
                    cliente = cliente1;
                }
            }
            if (cliente.getSaldo() < cantidadMaxAInvertir) {
                System.out.println("El cliente con dni: " + dniCliente + " no tiene saldo suficiente." + "Saldo actual cliente: " + cliente.getSaldo());
            } else {
                // Hay que restarle al saldo del cliente la cantidad que desea a invertir ya que aunque no se haya ejecutado aun la orden de compra, de esta forma evitamos que el cleinte pueda invertir con dinero que no tiene. Si la orden luego es rechazada enonces se le devolvera a restaurar el saldo original
                cliente.setSaldo(cliente.getSaldo()-cantidadMaxAInvertir);
                clientes.remove(cliente);//quitamos al cleinte que tenia el saldo sin actualizar
                clientes.add(cliente);//añadimos al cliente con el saldo modificado
                idOperacion = idOperacion+1;
                broker.añadePeticionCompraALaListaDeOperacionesPendientesDelBorker(idOperacion , cliente.getNombre(), dniCliente, nombreEmpresa, cantidadMaxAInvertir);

            }

        }
    }

    /*Nombre método: ventaAcciones
      Entradas: String dniCliente, String nombreEmpresa, float cantidadMaxAInvertir
      Salidas: nada
      Excepciones:
      Descripción: Envia al broker las peticiones de venta si todo el cliente cumple los requisitos(cliente pretenece al banco, tieen acciones de la empresa que intenta vender y el numero de titulos que quiere vender es inferior o igual al numero de titulos que posee en su paquete de acciones).
      */

    public void ventaAcciones(String dniCliente, String nombreEmpresa, int numTitulosAComprar) {
        boolean encontrado = false;
        Cliente cliente = new Cliente("ssss", dniCliente, 2);
        if (!clientes.contains(cliente)) {
            System.out.println("El cliente con dni: " + dniCliente + " no es cliente de este banco");
        } else {
            Iterator iterador = clientes.iterator(); // creo un objeto Iterator para recorrer la coleccion
            while (iterador.hasNext() && !encontrado) {
                Cliente cliente1 = (Cliente) iterador.next();
                if (cliente1.equals(cliente)) {
                    encontrado = true;
                    cliente = cliente1;
                }
            }
            boolean encontrado1 = false;
            PaqueteDeAcciones paqueteDeAcciones1 = new PaqueteDeAcciones(1,1,nombreEmpresa);
            Iterator iterador1 = cliente.getPaquetesAcciones().iterator(); // creo un objeto Iterator para recorrer la coleccion
            while (iterador.hasNext() && !encontrado1) {//recorro los paquetes de acciones del cliente para comprobar dos cosas: que tiene un paquete de la empresa de la que iintenta vender titulos y que el numero de acciones que posee de dicha empresa es mayor o igual que el número de acciones que intenta vender
                PaqueteDeAcciones paqueteDeAcciones = (PaqueteDeAcciones) iterador1.next();
                if (paqueteDeAcciones.getNombreEmpresa().equals(nombreEmpresa)) {
                    encontrado1 = true;
                    paqueteDeAcciones1 = paqueteDeAcciones;
                }
            }
            if (!encontrado1) { // el cliente no posse ningun paquete de acciones con la empresa especificada
                System.out.println("El cliente con dni: " + dniCliente + " no tiene en su cartera de inversiones acciones de la empresa: "+ nombreEmpresa);

            }
            else {//el cliente si posee un paquete de acciones con el nombre de empresa que ha proporcionado. Entonces ahora compruebo que el numero de titulos que tiene el cliente en el paquete de acciones asociado a dicha empresa es igual o superior al numero de titulos que quiere vender
                if (paqueteDeAcciones1.getNumTitulos() < numTitulosAComprar) {
                    System.out.println("El cliente con dni: " + dniCliente + " esta intentando vender una cantidad de acciones superior al numero de acciones que posse es su paquete de acciones. El número de acciones que posee el cliente de la empresa: "+ paqueteDeAcciones1.getNombreEmpresa()+" es: "+ paqueteDeAcciones1.getNumTitulos()+" títulos" );
                }
                else{// todo bien
                    idOperacion= idOperacion+1;
                    broker.añadePeticionVentaALaListaDeOperacionesPendientesDelBorker(idOperacion, cliente.getNombre(), dniCliente, nombreEmpresa,  numTitulosAComprar);
                }
            }
        }
    }

     /*Nombre método: actualizacionDeAccicones
      Entradas: String dniCliente, String nombreEmpresa, float cantidadMaxAInvertir
      Salidas: nada
      Excepciones:
      Descripción: Envia al broker las peticiones de actualizacion si todo el cliente cumple los requisitos(cliente pretenece al banco).
      */

    public void actualizacionDeAccicones(String dniCliente) {
        boolean encontrado = false;
        Cliente cliente = new Cliente("ssss", dniCliente, 2);
        if (!clientes.contains(cliente)) {
            System.out.println("El cliente con dni: " + dniCliente + " no es cliente de este banco");
        } else {
            Iterator iterador = clientes.iterator(); // creo un objeto Iterator para recorrer la coleccion
            while (iterador.hasNext() && !encontrado) {
                Cliente cliente1 = (Cliente) iterador.next();
                if (cliente1.equals(cliente)) {
                    encontrado = true;
                    cliente = cliente1;
                }
            }

            if (cliente.getPaquetesAcciones().size() == 0) {
                System.out.println("El cliente con dni: " + dniCliente + " no tiene ningun paquete de acciones que actualizar");
            } else {
                Iterator iterador1 = cliente.getPaquetesAcciones().iterator(); // creo un objeto Iterator para recorrer la coleccion
                HashSet<Empresa> empresasQueSeQuierenActualizar = new HashSet<Empresa>();
                Empresa empresa = new Empresa();
                while (iterador.hasNext()) {//recorro los paquetes de acciones del cliente
                    PaqueteDeAcciones paqueteDeAcciones = (PaqueteDeAcciones) iterador1.next();
                    empresa.setNombre(paqueteDeAcciones.getNombreEmpresa());
                    empresasQueSeQuierenActualizar.add(empresa);
                }
                idOperacion = idOperacion + 1;
                broker.añadePeticionActualizacionALaListaDeOperacionesPendientesDelBorker(idOperacion, cliente.getNombre(), dniCliente, empresasQueSeQuierenActualizar);
            }
        }
    }

    /*Nombre método: actualizaEstadoClientes
      Entradas: nada
      Salidas: nada
      Excepciones:
      Descripción: A partir de los resultados de las opraciones almacenadas en la lista opracionesRealizadas del broker tenemos que actualizar el/los paquete/s de acciones del cliente
      */

    public void actualizaEstadoClientes() {

        Iterator iterador = broker.getResultadosOperaciones().iterator(); // creo un objeto Iterator para recorrer la coleccion
        while (iterador.hasNext()) {
            Mensaje mensaje = (Mensaje) iterador.next();

            // si la operacion realizada es de tipo compra entonces hay que actualizar un paquete de acciones ya existente o crear un paquete de acciones nuevo acorde a los datos de la operacion de compra
            if (mensaje.getTipoOperacion().equals(TipoOperacion.COMPRA)) {
                MensajeRespuestaCompra mensajeRespouestaCompra = (MensajeRespuestaCompra) mensaje;

                //buscamos el cliente de asociado a la opracion de compra
                boolean encontrado = false;
                Cliente clienteAsociadoOperacion = new Cliente("da igual", mensaje.getDniCliente(), 1);//es un objeto de tipo cleinte auxiliar del que solo nos importa el dni para localizar al cliente
                Iterator iterador1 = clientes.iterator(); // creo un objeto Iterator para recorrer la coleccion
                while (iterador.hasNext() && encontrado) {
                    Cliente cliente = (Cliente) iterador.next();
                    if (cliente.equals(clienteAsociadoOperacion)) {
                        encontrado = true;
                        clienteAsociadoOperacion = cliente;
                    }
                }
                if (!encontrado) {
                    System.out.println("No se ha podido actualizar(TIPO OPERACOON: COMPRA) la lista de paquete de acciones del cliente porque el cliente con dni: " + mensajeRespouestaCompra.getDniCliente() + " ya no pertenece a este banco");
                } else {
                    //buscamos el paquete de acciones asociado a la opracion de compra, si el paquete no existe lo creamos
                    encontrado = false;
                    PaqueteDeAcciones paqueteDeAccionesBuscado = new PaqueteDeAcciones(1, 1, mensajeRespouestaCompra.getNombreEmpresa());//es un objeto de tipo paqueteDeacciones auxiliar del que solo nos importa el nombre de la empresa para localizar al paquete de acciones asociado a esa empresa
                    Iterator iterador2 = clienteAsociadoOperacion.getPaquetesAcciones().iterator(); // creo un objeto Iterator para recorrer la coleccion
                    while (iterador.hasNext() && encontrado) {
                        PaqueteDeAcciones paqueteDeAcciones = (PaqueteDeAcciones) iterador2.next();
                        if (paqueteDeAcciones.equals(paqueteDeAccionesBuscado)) {
                            encontrado = true;
                            paqueteDeAccionesBuscado = paqueteDeAcciones;
                        }
                    }

                    if (!encontrado) {//como no hay ningun paquete de acciones asociado a la empresa de la que se han comprado tituilos entonces creo un nuevo paquete de acciones
                        PaqueteDeAcciones nuevoPaqueteAcciones = new PaqueteDeAcciones(mensajeRespouestaCompra.getNumAccionesCompradas(), mensajeRespouestaCompra.getPrecioDeAccion(), mensajeRespouestaCompra.getNombreEmpresa());
                        clienteAsociadoOperacion.getPaquetesAcciones().add(nuevoPaqueteAcciones);
                    } else {//Si ya hay un paquete de acciones asociado a la empresa de la que se han comprado titulos entonces la actualizamos

                        paqueteDeAccionesBuscado.setNumTitulos(paqueteDeAccionesBuscado.getNumTitulos() + ((MensajeRespuestaCompra) mensaje).getNumAccionesCompradas());
                        paqueteDeAccionesBuscado.setValorActualTitulo(((MensajeRespuestaCompra) mensaje).getPrecioDeAccion());

                        clienteAsociadoOperacion.getPaquetesAcciones().remove(paqueteDeAccionesBuscado);
                        clienteAsociadoOperacion.paquetesAcciones.add(paqueteDeAccionesBuscado);
                    }


                }


            }
            // si la operacion realizada es de tipo venta entonces hay que actualizar un paquete de acciones ya existente o borrar el ya existente acorde a los datos de la operacion de compra
            else if (mensaje.getTipoOperacion().equals(TipoOperacion.VENTA)) {
                MensajeRespuestaVenta mensajeRespouestaVenta = (MensajeRespuestaVenta) mensaje;


            }
            // si la operacion realizada es de tipo venta entonces hay que actualizar un paquete de acciones ya existente o borrar el ya existente acorde a los datos de la operacion de compra
            else if (mensaje.getTipoOperacion().equals(TipoOperacion.ACTUALIZACION)) {
                MensajeRespuestaActualizacion mensajeRespouestaActualizacion = (MensajeRespuestaActualizacion) mensaje;

                //buscamos el cliente de asociado a la opracion de compra
                boolean encontrado = false;
                Cliente clienteAsociadoOperacion = new Cliente("da igual", mensaje.getDniCliente(), 1);//es un objeto de tipo cleinte auxiliar del que solo nos importa el dni para localizar al cliente
                Iterator iterador1 = clientes.iterator(); // creo un objeto Iterator para recorrer la coleccion
                while (iterador.hasNext() && encontrado) {
                    Cliente cliente = (Cliente) iterador.next();
                    if (cliente.equals(clienteAsociadoOperacion)) {
                        encontrado = true;
                        clienteAsociadoOperacion = cliente;
                    }
                }
                if (!encontrado) {
                    System.out.println("No se ha podido actualizar (TIPO OPERACOON: ACTIUALIZACION) la lista de paquete de acciones del cliente porque el cliente con dni: " + mensaje.getDniCliente() + " ya no pertenece a este banco");
                } else {
                    //para cada uno de los paquetes que se quieren actualizar comprobamos que cada uno de ellos estan en la lista de pequetes de acciones del cliente
                    Iterator iterador2 = ((MensajeRespuestaActualizacion) mensaje).getEmpresasQueSeQuierenActualizar().iterator(); // creo un objeto Iterator para recorrer la coleccion
                    boolean encontrado1 = false;
                    while (iterador.hasNext()) {
                        Empresa empresaQueSeQuiereActualizar = (Empresa) iterador2.next();
                        //buscamos el paquete de acciones asociado a la opracion de actualizacion
                        encontrado = false;
                        PaqueteDeAcciones paqueteDeAccionesBuscado = new PaqueteDeAcciones(1, 1, empresaQueSeQuiereActualizar.getNombre());//es un objeto de tipo paqueteDeacciones auxiliar del que solo nos importa el nombre de la empresa para localizar al paquete de acciones asociado a esa empresa
                        Iterator iterador3 = clienteAsociadoOperacion.getPaquetesAcciones().iterator(); // creo un objeto Iterator para recorrer la coleccion
                        while (iterador.hasNext() && encontrado) {
                            PaqueteDeAcciones paqueteDeAcciones = (PaqueteDeAcciones) iterador3.next();
                            if (paqueteDeAcciones.equals(paqueteDeAccionesBuscado)) {
                                encontrado = true;
                                encontrado1 = true; //al menos encuentra 1;
                                paqueteDeAccionesBuscado = paqueteDeAcciones;
                            }
                            if (!encontrado) {//como no hay ningun paquete de acciones asociado a la empresa de la que se han comprado tituilos entonces no puedo actualizar nada
                                System.out.println("No se ha podido actualizar (TIPO OPERACON: ACTIUALIZACION) el paquete de acciones ya que el cliente con dni: " + mensaje.getDniCliente() + " no tiene ninguna paquete de acciones asociado a la empresa: " + mensajeRespouestaActualizacion.getNombreEmpresa());
                            } else {//Si ya hay un paquete de acciones asociado a la empresa de la que se quieren actualizar los  titulos entonces la actualizamos
                                paqueteDeAccionesBuscado.setValorActualTitulo(empresaQueSeQuiereActualizar.getValorTituloActual());
                            }
                        }
                    }
                    if (encontrado1){
                        System.out.println("No se ha podido actualizar (TIPO OPERACON: ACTIUALIZACION) NINGUN paquete de acciones ya que el cliente con dni: " + mensaje.getDniCliente() +"no tiene ningun paquete de acciones de los que se pretenden acutalizar");

                    }


                }
            }
        }
    }



//FIN ZONA DE OPERACIONES

}





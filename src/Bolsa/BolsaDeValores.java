package Bolsa;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import General.*;

import javax.swing.plaf.synth.SynthTextAreaUI;

public class BolsaDeValores {

    //ZONA CONNSTANTES
        final int DIFERENCIADOR_COMPRA_O_VENTA = 3;
    //FIN ZONA CONNSTANTES

    //ZONA DE VARIABLES
    private String nombre;
    private HashSet<Empresa> empresas;
    // FIN ZONA VALIABLES

    //ZONA DE CONSTRUCTORES
    //constructor que solo recibe el nombre de la bolsa
    public BolsaDeValores(String nombre) {
        this.nombre = nombre;
        empresas = new HashSet<Empresa>();
    }
    //constructor que recibe el nombre de la bolsa y una empresa que añade a la lista de empresas
    public BolsaDeValores(String nombre, Empresa empresa) {
        this.nombre = nombre;
        empresas = new HashSet<Empresa>();
        empresas.add(empresa);
    }
    //constructor que recibe el nombre de la bolsa y una lista de empresas que añade a la lista de empresas
    public BolsaDeValores(String nombre, HashSet<Empresa> empresas2) {
        this.nombre = nombre;
        empresas = new HashSet<Empresa>();
        empresas.addAll(empresas2);
    }
    //FIN ZONA DE CONSTRUCTORES

    //ZONA DE GETTERS
    public String getNombre() {
        return nombre;
    }

    public HashSet<Empresa> getEmpresas() {
        return empresas;
    }
    //FIN ZONA DE GETTERS

    //ZONA DE METODOS PRIVADOS

    /*Nombre método: recepcioncadenaCodificadaCompraDesdeElBroker
      Entradas: String con la cadena codificada de COMPRA desde el broker
      Salidas: String con una cadena codificada de respuesta de venta con el resultado de la operación y los valores necesarios en funcion del tipo de operacion
      Excepciones:
      Descripción: Dada la cadena codificada recibida desde el broker realiza:  decodifica, geenera un string de respuesta codificado (para ello tiene que hacer las operaciones de mirar el precio de la accion y realizar las operaciones necesarias y por ultimo actualiza los valores de la bolsa en funcion de si la orden era de compra o de venta
      */
    private String recepcioncadenaCodificadaCompraDesdeElBroker(String cadenaCompraCodificada) {

        System.out.println(" -------------- ZONA BOLSA -------------");
        System.out.println();
        System.out.println(" -------- La bolsa ha recibido la cadena de texto de compra codificada enviada por el broker.");
        System.out.println();

        //Variables para decodificar
        String idOperacionDecodificado ="" ;
        String nombreClienteDecodificado ="" ;
        String nombreEmpresaDecodificado = "";
        String cantidadMaximaAInvertir = "";
        //Variables para codificar cadena de respuesta
        boolean efectuada;
        int numAccionesCompradas;
        double precioDeAccion;
        double dineroSobrante;


        //DECODIFICAMOS LA CADENA
        System.out.println(" 3º) La Bolsa esta decodificando la cadena de texto codificada...");
        System.out.println();
        int i = 0;
        char caracter;
        //Decodificamos el IdOperacion
        caracter = cadenaCompraCodificada.charAt(i);
        while (caracter != '|'){
            idOperacionDecodificado = idOperacionDecodificado + caracter;
            i = i+1;
            caracter = cadenaCompraCodificada.charAt(i);
        }
        i = i+1;
        //Decodificamos el nombre del cliente
        caracter = cadenaCompraCodificada.charAt(i);
        while (caracter != '|'){
            nombreClienteDecodificado = nombreClienteDecodificado + caracter;
            i = i+1;
            caracter = cadenaCompraCodificada.charAt(i);
        }
        i = i+1;
        //Decodificamos el nombreEmpresa
        caracter = cadenaCompraCodificada.charAt(i);
        while (caracter != '|'){
            nombreEmpresaDecodificado = nombreEmpresaDecodificado + caracter;
            i = i+1;
            caracter = cadenaCompraCodificada.charAt(i);
        }
        i = i+1;
        //Decodificamos el cantidadMaximaAInvertir
        caracter = cadenaCompraCodificada.charAt(i);
        while (caracter != '|'){
            cantidadMaximaAInvertir = cantidadMaximaAInvertir + caracter;
            i = i+1;
            caracter = cadenaCompraCodificada.charAt(i);
        }

        System.out.println(" -------- La bolsa ha terminado de decodificar la cadena de texto recibida del broker.");
        System.out.println();
        System.out.println(" ------------ Los datos decodificados son: ");
        System.out.println();
        System.out.println(" ---------------- ID Operacion = "+idOperacionDecodificado);
        System.out.println(" ---------------- Nombre Cliente = "+nombreClienteDecodificado);
        System.out.println(" ---------------- Nombre Empresa = "+nombreEmpresaDecodificado);
        System.out.println(" ---------------- Max. Catidad a Invertir = "+cantidadMaximaAInvertir);
        System.out.println();

        //FIN DECODIFICAR LA CADENA

        System.out.println(" 4º) La bolsa esta realizando la operación de compra de acciones...");
        System.out.println();

        //primero comprobamos si la empresa de la que se intentan adquirir titulos esta en la bolsa, sino esta la operacion no se efectura
        boolean encontrado = false;
        Empresa empresaAComprar = new Empresa(nombreEmpresaDecodificado,1);//Solo me intera el nombre, el valor actual es cualquiera
        Iterator iterador = empresas.iterator(); // creo un objeto Iterator para recorrer la coleccion
        while (iterador.hasNext() && !encontrado) {
            Empresa empresa = (Empresa) iterador.next();
            if(empresa.equals(empresaAComprar)) {
                empresaAComprar = empresa;
                encontrado = true;
            }
        }



        if(!encontrado || (empresaAComprar.getValorTituloActual()> Double.parseDouble(cantidadMaximaAInvertir)) ){ // sino la empresa no esta en la bolsa o el valor de cada accion es superior a la cantidad maxima de dinero que el cliente esta dispuesto a invertir, entonces se devuelve una cadena de respuesta codificada al broker con resultado false
            System.out.println(" -------- La bolsa no ha podido comprar acciones porque la empresa de la que se intentan adquirir títulos no existe en la bolsa");
            System.out.println();
            System.out.println(" 5º) La bolsa esta codificando la cadena de texto de respuesta de compra para enviar al broker...");
            System.out.println();
            System.out.println(" -------- Se ha terminado de codificar la cadena.");
            System.out.println();
            System.out.println(" -------- La cadena de texto codificada de respuesta de compra generada por la bolsa es: ");
            System.out.println();
            System.out.println("                                                    "+idOperacionDecodificado + "|" + nombreClienteDecodificado + "|" + "false" + "|");
            System.out.println();
            return idOperacionDecodificado + "|" + nombreClienteDecodificado + "|" + "false" + "|";
        }

        else{ // si la empresa esta en la bolsa entonces compramos
            //1º Obtenemos el valor del titulo actual de la empresa para saber cuantos titulos podemos comprar
            precioDeAccion = empresaAComprar.getValorTituloActual();


            double maxCantidadAInvertir = Double.parseDouble(cantidadMaximaAInvertir);
            double resto;

            resto =  maxCantidadAInvertir/empresaAComprar.getValorTituloActual() - Math.floor(maxCantidadAInvertir/empresaAComprar.getValorTituloActual());

            numAccionesCompradas = (int) Math.floor(maxCantidadAInvertir/empresaAComprar.getValorTituloActual()); // auqnue numAccionesCompradas sea de tipo doule lo redondeamos al entero inferior

            dineroSobrante = resto * empresaAComprar.getValorTituloActual();

            System.out.println(" -------- Se ha terminado de comprar.");
            System.out.println();
            System.out.println(" ------------ Los datos de la compra son: ");
            System.out.println();
            System.out.println(" ---------------- ID Operacion = "+idOperacionDecodificado);
            System.out.println(" ---------------- Nombre Cliente = "+nombreClienteDecodificado);
            System.out.println(" ---------------- Nombre Empresa = "+nombreEmpresaDecodificado);
            System.out.println(" ---------------- Número de acciones compradas "+numAccionesCompradas);
            System.out.println(" ---------------- Precio de la acción "+precioDeAccion);
            System.out.println(" ---------------- Dinero sobrante "+dineroSobrante);
            System.out.println();

            //2º Modificamos el valor de la accion: hacemos que suba ya que la operacion es de compra
            System.out.println(" -------- La bolsa esta modificando el valor de la accion en la bolsa. En este caso como la operacion es de compra el valor de la acción aumentará un 1% con respecto a su valor actual");
            System.out.println(" ------------ Valor acción en la bolsa antes de la compra = "+empresaAComprar.getValorTituloActual());
            double valorModificado = empresaAComprar.getValorTituloActual() + (numAccionesCompradas * empresaAComprar.getValorTituloActual() * 0.01);
            System.out.println(" ------------ Valor acción en la bolsa después de la compra = "+valorModificado);
            System.out.println();

            empresaAComprar.setValorTituloPrevio(empresaAComprar.getValorTituloActual());
            empresaAComprar.setValorTituloActual(empresaAComprar.getValorTituloActual() + (numAccionesCompradas * empresaAComprar.getValorTituloActual() * 0.01)); //hacemos que la accion suba un 1 % del valor actual de la accion por cada accion que compramos

            //3º Devolvemos a la bolsa la empresaAcomprar con los valores aumentados debido a la compra

            Iterator iterador1 = empresas.iterator(); // creo un objeto Iterator para recorrer la coleccion
            encontrado = false;
            while (iterador1.hasNext() && !encontrado) {
                Empresa empresa = (Empresa) iterador1.next();
                if (empresa.equals(empresaAComprar)) {
                    empresas.remove(empresa);
                    empresas.add(empresaAComprar);
                    encontrado = true;
                }
            }

            System.out.println(" -------- La bolsa ha terminado de realizar la operación de compra la acciones.");
            System.out.println();
            System.out.println(" 5º) La bolsa esta codificando la cadena de texto de respuesta de compra para enviar al broker...");
            System.out.println();
            //4º DEVOLVEMOS LA CADENA CODIFICADA AL BROKER
            System.out.println(" -------- Se ha terminado de codificar la cadena.");
            System.out.println();
            System.out.println(" -------- La cadena de texto codificada de respuesta de compra generada por la bolsa es: "+idOperacionDecodificado + "|" + nombreClienteDecodificado + "|" + "true"+ "|" + numAccionesCompradas + "|" + precioDeAccion+ "|" + dineroSobrante+"|");
            System.out.println();
            System.out.println(" 6º) La bolsa esta enviando la cadena de texto de respuesta de compra al broker...");
            System.out.println();

            return idOperacionDecodificado + "|" + nombreClienteDecodificado + "|" + "true"+ "|" + numAccionesCompradas + "|" + precioDeAccion+ "|" + dineroSobrante+"|";
        }
    }

    /*Nombre método: recepcioncadenaCodificadaVentaDesdeElBroker
      Entradas: String con la cadena codificada de VENTA desde el broker
      Salidas: String con una cadena codificada de respuesta de venta con el resultado de la operación y los valores necesarios en funcion del tipo de operacion
      Excepciones:
      Descripción: Dada la cadena codificada recibida desde el broker realiza:  decodifica, geenera un string de respuesta codificado (para ello tiene que hacer las operaciones de mirar el precio de la accion y realizar las operaciones necesarias y por ultimo actualiza los valores de la bolsa en funcion de si la orden era de compra o de venta
      */
    private String recepcioncadenaCodificadaVentaDesdeElBroker(String cadenaVentaCodificada) {

        System.out.println(" -------------- ZONA BOLSA -------------");
        System.out.println();
        System.out.println(" -------- La bolsa ha recibido la cadena de texto de venta codificada enviada por el broker.");
        System.out.println();

        //Variables para decodificar
        String idOperacionDecodificado ="" ;
        String nombreClienteDecodificado ="" ;
        String nombreEmpresaDecodificado = "";
        String numAccionesVender = "";
        //Variables para codificar cadena de respuesta
        boolean efectuada;
        int numAccionesVendidas;
        double precioDeAccion;
        double beneficioTotal;


        //DECODIFICAMOS LA CADENA
        System.out.println(" 3º) La Bolsa esta decodificando la cadena de texto codificada...");
        System.out.println();

        int i = 0;
        char caracter;
        //Decodificamos el IdOperacion
        caracter = cadenaVentaCodificada.charAt(i);
        while (caracter != '|'){
            idOperacionDecodificado = idOperacionDecodificado + caracter;
            i = i+1;
            caracter = cadenaVentaCodificada.charAt(i);
        }
        i = i+1;
        //Decodificamos el nombre del cliente
        caracter = cadenaVentaCodificada.charAt(i);
        while (caracter != '|'){
            nombreClienteDecodificado = nombreClienteDecodificado + caracter;
            i = i+1;
            caracter = cadenaVentaCodificada.charAt(i);
        }
        i = i+1;
        //Decodificamos el nombreEmpresa
        caracter = cadenaVentaCodificada.charAt(i);
        while (caracter != '|'){
            nombreEmpresaDecodificado = nombreEmpresaDecodificado + caracter;
            i = i+1;
            caracter = cadenaVentaCodificada.charAt(i);
        }
        i = i+1;
        //Decodificamos el cantidadTitulosAVender
        caracter = cadenaVentaCodificada.charAt(i);
        while (caracter != '|'){
            numAccionesVender = numAccionesVender + caracter;
            i = i+1;
            caracter = cadenaVentaCodificada.charAt(i);
        }

        System.out.println(" -------- La bolsa ha terminado de decodificar la cadena de texto recibida del broker.");
        System.out.println();
        System.out.println(" ------------ Los datos decodificados son: ");
        System.out.println();
        System.out.println(" ---------------- ID Operacion = "+idOperacionDecodificado);
        System.out.println(" ---------------- Nombre Cliente = "+nombreClienteDecodificado);
        System.out.println(" ---------------- Nombre Empresa = "+nombreEmpresaDecodificado);
        System.out.println(" ---------------- Número de acciones a vender = "+numAccionesVender);
        System.out.println();


        //FIN DECODIFICAR LA CADENA

        System.out.println(" 4º) La bolsa esta realizando la operación de venta de acciones...");
        System.out.println();

        //primero comprobamos si la empresa de la que se intentan adquirir titulos esta en la bolsa, sino esta la operacion no se efectura
        boolean encontrado = false;
        Empresa empresaAVender = new Empresa(nombreEmpresaDecodificado,1);//Solo me intera el nombre, el valor actual es cualquiera
        Iterator iterador = empresas.iterator(); // creo un objeto Iterator para recorrer la coleccion
        while (iterador.hasNext() && !encontrado) {
            Empresa empresa = (Empresa) iterador.next();
            if(empresa.equals(empresaAVender)) {
                empresaAVender = empresa;
                encontrado = true;
            }
        }

        if(!encontrado){ // si la empresa no esta en la bolsa, entonces se devuelve una cadena de respuesta codificada al broker con resultado false
            efectuada = false;
            System.out.println(" -------- La bolsa no ha podido vender acciones porque la empresa de la que se intentan vender títulos no existe en la bolsa");
            System.out.println();
            System.out.println(" 5º) La bolsa esta codificando la cadena de texto de respuesta de venta para enviar al broker...");
            System.out.println();
            System.out.println(" ------------ Los datos a codificar son: ");
            System.out.println();
            System.out.println(" ---------------- ID Operacion = " + idOperacionDecodificado+"  ---- OK");
            System.out.println(" ---------------- Nombre Cliente = " + nombreClienteDecodificado+"  ---- OK");
            System.out.println(" ---------------- Resultado Operación =  " + efectuada+"  ---- OK");
            System.out.println();
            System.out.println(" -------- Se ha terminado de codificar la cadena.");
            System.out.println();
            System.out.println(" -------- La cadena de texto codificada de respuesta de venta generada por la bolsa es: ");
            System.out.println();
            System.out.println("                                          "+ idOperacionDecodificado + "|" + nombreClienteDecodificado + "|" + efectuada + "|"+"                                          ");
            System.out.println();


            return idOperacionDecodificado + "|" + nombreClienteDecodificado + "|" + "false" + "|";
        }

        else{ // si la empresa esta en la bolsa entonces compramos
            //1º Obtenemos el valor del titulo actual de la empresa para saber cuantos titulos podemos comprar
            efectuada = true;

            numAccionesVendidas = Integer.parseInt(numAccionesVender);

            precioDeAccion = empresaAVender.getValorTituloActual();

            beneficioTotal = numAccionesVendidas * precioDeAccion;

            System.out.println(" -------- Se ha terminado de vender.");
            System.out.println();
            System.out.println(" ------------ Los datos de la venta son: ");
            System.out.println();
            System.out.println(" ---------------- ID Operacion = "+idOperacionDecodificado);
            System.out.println(" ---------------- Nombre Cliente = "+nombreClienteDecodificado);
            System.out.println(" ---------------- Nombre Empresa = "+nombreEmpresaDecodificado);
            System.out.println(" ---------------- Número de acciones vendidas "+numAccionesVendidas);
            System.out.println(" ---------------- Precio de la acción "+precioDeAccion);
            System.out.println(" ---------------- Beneficio total venta "+beneficioTotal);
            System.out.println();

            //2º Modificamos el valor de la accion: hacemos que suba ya que la operacion es de compra
            System.out.println(" -------- La bolsa esta modificando el valor de la accion en la bolsa. En este caso como la operacion es de venta el valor de la acción disminuira un 1% con respecto a su valor actual");
            System.out.println();
            System.out.println(" ------------ Valor acción en la bolsa antes de la venta = "+empresaAVender.getValorTituloActual());
            double valorModificado = empresaAVender.getValorTituloActual() - (numAccionesVendidas * empresaAVender.getValorTituloActual() * 0.01);
            System.out.println(" ------------ Valor acción en la bolsa después de la venta = "+valorModificado);
            System.out.println();

            empresaAVender.setValorTituloPrevio(empresaAVender.getValorTituloActual());
            empresaAVender.setValorTituloActual(empresaAVender.getValorTituloActual() - (numAccionesVendidas * empresaAVender.getValorTituloActual() * 0.01)); //hacemos que la accion baje un 1 % del valor actual de la accion por cada accion que compramos
            //3º Devolvemos a la bolsa la empresaAcomprar con los valores aumentados debido a la compra

            Iterator iterador1 = empresas.iterator(); // creo un objeto Iterator para recorrer la coleccion
            encontrado = false;
            while (iterador.hasNext() && !encontrado) {
                Empresa empresa = (Empresa) iterador.next();
                if (empresa.equals(empresaAVender)) {
                    empresas.remove(empresa);
                    empresas.add(empresaAVender);
                    encontrado = true;
                }
            }
            System.out.println(" -------- La bolsa ha terminado de realizar la operación de venta la acciones.");
            System.out.println();
            System.out.println(" 5º) La bolsa esta codificando la cadena de texto de respuesta de venta para enviar al broker...");
            System.out.println();
            System.out.println();
            System.out.println(" ------------ Los datos de la venta son: ");
            System.out.println();
            System.out.println(" ---------------- ID Operacion = "+idOperacionDecodificado+"  ---- OK");
            System.out.println(" ---------------- Nombre Cliente = "+nombreClienteDecodificado+"  ---- OK");
            System.out.println(" ---------------- Nombre Empresa = "+nombreEmpresaDecodificado+"  ---- OK");
            System.out.println(" ---------------- Número de acciones vendidas "+numAccionesVendidas+"  ---- OK");
            System.out.println(" ---------------- Precio de la acción "+precioDeAccion+"  ---- OK");
            System.out.println(" ---------------- Beneficio total venta "+beneficioTotal+"  ---- OK");
            //4º DEVOLVEMOS LA CADENA CODIFICADA AL BROKER
            System.out.println(" -------- Se ha terminado de codificar la cadena.");
            System.out.println();
            System.out.println(" -------- La cadena de texto codificada de respuesta de venta generada por la bolsa es: ");
            System.out.println();
            System.out.println("                                                                "+idOperacionDecodificado + "|" + nombreClienteDecodificado + "|" + efectuada+ "|" + numAccionesVendidas + "|" + precioDeAccion+ "|" + beneficioTotal+"|");
            System.out.println();
            System.out.println(" 6º) La bolsa esta enviando la cadena de texto de respuesta de venta al broker...");
            System.out.println();

            return idOperacionDecodificado + "|" + nombreClienteDecodificado + "|" + efectuada+ "|" + numAccionesVendidas + "|" + precioDeAccion+ "|" + beneficioTotal+"|";
        }
    }
    //FIN ZONA DE METODOS PRIVADOS

    //ZONA DE METODOS PUBLICOS

    /*Nombre método: addEmpresa
      Entradas: Objeto de tipo Empresa
      Salidas: nada
      Excepciones: ninguna
      Descripción: añade una nueva empresa a la lista solo si la empresa no estaba en la lista
      */
    public void addEmpresa(Empresa empresa){
        if(!this.empresas.add(empresa)) {
            System.out.println("La empresa que ha intendado añadir YA esta presente en la bolsa");
        }
        else System.out.println("Empresa añadida con exito!");
    }

    /*Nombre método: removeEmpresa
      Entradas: Objeto de String, el nombre de una empresa.
      Salidas: nada
      Excepciones: ninguna
      Descripción: elimina una empresa de la lista.
      */
    public void removeEmpresa (String nombreEmpresa){
        Empresa empresa = new Empresa(nombreEmpresa,0);
        if(!empresas.remove(empresa)) System.out.println("La empresa que ha intendado borrar NO esta presente en la bolsa");
        else System.out.println("Empresa eliminada con exito!");

    }

    /*Nombre método: actualizarValoresEmpresas
      Entradas: Objeto de String, el nombre de una empresa.
      Salidas: nada
      Excepciones: ninguna
      Descripción: elimina una empresa de la lista.
      */
    public void actualizarValoresEmpresas (){
        HashSet<Empresa> empresas1 = new  HashSet<Empresa>();
        Utilidades aleatorio = new Utilidades();
        Iterator iterador = empresas.iterator();
        while (iterador.hasNext()) {
            Empresa empresa = (Empresa) iterador.next();
            empresa.setValorTituloPrevio(empresa.getValorTituloActual());
            empresa.setValorTituloActual(empresa.getValorTituloActual()+empresa.getValorTituloActual() * aleatorio.generaAleatorio());
            empresas1.add(empresa);
        }
        empresas.clear();
        empresas.addAll(empresas1);



    }
    /*Nombre método: showEmpresas
      Entradas: nada
      Salidas: nada
      Excepciones:
      Descripción: Muestra todas las empresas que hay contenidas en la lista de empresas.
      */
    public void showEmpresas(){
        if(empresas.size()==0) System.out.println("No hay empresas en la bolsa!");
        else {
                System.out.println(empresas.toString());
            }
    }

    /*Nombre método: copiaSeguridadEmpresas
      Entradas: String path del fichero, objeto de tipo Output
      Salidas: nada
      Excepciones: IOException
      Descripción: Serializa la información de las empresas presentes en la bolsa y las uarda en disco
      */
    public void copiaSeguridadEmpresas(String path, Utilidades serializa)throws IOException {

        serializa.abrirOut(path);
        Iterator iterador = empresas.iterator();
        System.out.println("Copiando...");
        System.out.println();
        while (iterador.hasNext()) {
            Empresa empresa = (Empresa) iterador.next();
            serializa.escribirEmpresa(empresa);
        }
        serializa.cerrarOut();
    }

    /*Nombre método: restaurarCopiaSeguridadEmpresas
      Entradas: String path del fichero
      Salidas: nada
      Excepciones: IOException, ClassNotFoundException
      Descripción: Deserializa la información de las empresas presentes en la bolsa y las guarda en disco
      */
    public void restaurarCopiaSeguridadEmpresas(String path, Utilidades deserializa)throws IOException, ClassNotFoundException,ClassCastException {
        float aux = 0;
        Empresa empresa;
        deserializa.abrirIn(path);
        System.out.println("Restaurando...");
        System.out.println();
        empresas.clear();//borramos toda la lista antes de cargar desde disco la nueva lista de la que dispondremos
        try {
            do {
                empresa = deserializa.leerEmpresa();
                empresas.add(empresa);
            } while (empresa != null);
            deserializa.cerrarIn();
            empresas.remove(null);
        }


        catch (InterruptedIOException iioe){
            System.out.println("EEROR:Interrupcion de tipo InterruptedIOException");
            throw new IOException();

        }
        catch (InvalidClassException | StreamCorruptedException ost){
            System.out.println("EEROR:Interrupcion de tipo InvalidClassException");
            throw new IOException();
            /* Thrown when the Serialization runtime detects one of the following problems with a Class.
                The serial version of the class does not match that of the class descriptor read from the stream
                The class contains unknown datatypes
                The class does not have an accessible no-arg constructor
            */
        } catch (ObjectStreamException ost){
            System.out.println("EEROR:Interrupcion de tipo ObjectStreamException");
            throw new IOException();

        }
        catch ( EOFException eofe){
            System.out.println("EEROR:Interrupcion de tipo EOFException");
            throw new IOException();

        }

        catch (IOException ioe){
            System.out.println("EEROR:Interrupcion de tipo IOException: es decir ninguna de las capturas antes");
            throw new IOException();

        }

        catch (ClassCastException cce){

            throw  cce;

        }
        //Comprobamos que no hay empresas en la lista que tengan un valor de titulo actual de cero y si lo tiene la quitamos de la lista.
        Iterator iterador = empresas.iterator();
        while (iterador.hasNext()) {
            Empresa empresa1 = (Empresa) iterador.next();
            if(empresa1.getValorTituloActual() == aux){
                empresas.remove(empresa1);
            }
        }
    }

    /*Nombre método: intentaOperacion
      Entradas: String con la cadena codificada de compra o venta desde el broker
      Salida: Salidas: String con una cadena codificada de respuesta de compra o venta con el resultado de la operación y los valores necesarios en funcion del tipo de operacion
      Excepciones:
      Descripcion: Clasifica la cadena en compra o venta y llama al submétodo indicado en cada caso
     */
    public String intentaOperacion(String cadenaCodificado) {
        String[] partesMensaje = cadenaCodificado.split("[|]");
        try {
            if (partesMensaje[DIFERENCIADOR_COMPRA_O_VENTA].contains(".")||partesMensaje[DIFERENCIADOR_COMPRA_O_VENTA].contains(",")) {   // En este caso es un mensaje de compra
                System.out.println(partesMensaje[DIFERENCIADOR_COMPRA_O_VENTA]);
                return recepcioncadenaCodificadaCompraDesdeElBroker(cadenaCodificado);
            }
            else {  // Si no tiene el campo [3] una coma asumimos que hay un entero, mensaje de venta
                System.out.println(partesMensaje[DIFERENCIADOR_COMPRA_O_VENTA]);
                return recepcioncadenaCodificadaVentaDesdeElBroker(cadenaCodificado);
            }
        }
        catch (NullPointerException npe) {

            throw npe;
        }
    }



}









package General;

import Banco.Cliente;
import Bolsa.Empresa;
import ExcepcionesPropias.*;

import java.util.InputMismatchException;

public class InterfazDeUsuario {

    //ESPACIO PARA CONSTANTES
    private final int INTENTOS = 5; // Contante que nos limita el número de intentos que un ususario puede introducir por teclado un valor no válido
    // FIN CONSTANTES------------

    //ESPACIO RESERVADO PARA VARIABLES
    Escaner leeTeclado;
    private String eleccion;
    private String nombreEmpresa;
    private String nombrePersona;
    private String dni;
    private float saldo;
    private float valorActualEmpresa;
    private String path;
    private float cantidadMaxAInvertir;
    private int numTitulosAVender;
    // -FIN VARIABLES

    //ESPACIO RESERVADO PARA CONSTRUCTORES
    //constructor por defecto
    public InterfazDeUsuario() {
    }

    //constructor que recibe un objeto tipo escaner como parametro de entrada
    public InterfazDeUsuario(Escaner leeTeclado) {
        this.leeTeclado = leeTeclado;
    }

    // -FIN CONSTRUCTORES

    //ESPACIO RESERVADO PARA GETTERS
    public String getEleccion() {
        return eleccion;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public float getValorActualEmpresa() {
        return valorActualEmpresa;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public String getDni() {
        return dni;
    }

    public float getSaldo() {
        return saldo;
    }

    public String getPath() {
        return path;
    }

    public float getCantidadMaxAInvertir() {
        return cantidadMaxAInvertir;
    }

    public int getNumTitulosAVender() {
        return numTitulosAVender;
    }

    // -FIN GETTERS

    // ESPACIO RESERVADO PARA GETTERS DERIVADOS O "CREADORES"
    public Cliente crearCliente() {
        Cliente c1 = new Cliente(this.getNombrePersona(),this.getDni(),this.getSaldo());
        return c1;
    }

    public Empresa crearEmpresa() {
        Empresa e1 = new Empresa(this.getNombreEmpresa(),this.getValorActualEmpresa());
        return e1;
    }

    //-FIN CREADORES

    //ESPACIO RESERVADO PARA METODOS PRIVADOS DE LA CLASE

    /*Nombre método: leeCadenaTextoTeclado
      Entradas: ninguna
      Salidas: String (cadena de caracteres introducidos por teclado)
      Excepciones: IntentsLimitAchieveException
      Descripción: utiliza el objeto escaner de la clase para leer de teclado una cade de caracteres. Si el formato de la cadena no es correcxto y se superan el límite de intentos se lanza una excepción.
      */
    private String leeCadenaTextoTeclado(String opcion) throws IntentsLimitAchieveException {
        String datos;
        boolean encontrado;
        int intentos;
        int i;
        char letraNombre;
        char letraMenu;
        char letraMenu1;
        intentos = INTENTOS;
        datos = leeTeclado.leeDatos();

            if (opcion.equals("opcionNombre")) {
                while (intentos > 1) {
                    try {
                        encontrado = false;
                        i = 0;
                        while (!encontrado && i < datos.length()) {
                            letraNombre = datos.charAt(i);
                            if (!(((letraNombre >= 65) && (letraNombre <= 90)) || ((letraNombre >= 97) && (letraNombre <= 122)))) {
                                encontrado = true;
                            }
                            i = i + 1;
                        }
                        if (encontrado) {
                            System.out.println("El nombre debe contener caracteres válidos; no números, no acentos, solo letras desde a-z o A-Z ");
                            intentos = intentos - 1;
                            System.out.println("Vuelva a intentarlo. Le quedan " + intentos + " intentos para hacerlo bien.");
                            System.out.print("Nombre: ");
                            datos = leeTeclado.leeDatos();

                        } else {
                            return datos;
                        }
                    } catch (StringIndexOutOfBoundsException siobe) {
                        System.out.println("ERROR: El dni debe contener 9 caracteres.");
                    }
                }
                } else if (opcion.equals("opcionMenu")) {
                    while (intentos > 1) {
                        try {
                            if (datos.length() > 2) {//el numero e caracteres es distinto de 1 y 2
                                intentos = intentos - 1;
                                System.out.println();
                                System.out.println("ERROR: La opcion debe contener 1 o 2 caracteres numéricos como máximo.");
                                System.out.println("Vuelva a intentarlo. Le quedan " + intentos + " intentos para hacerlo bien.");
                                System.out.print("Inserte la opción deseada: ");
                                datos = leeTeclado.leeDatos();
                            } else {
                                if (datos.length() == 1) {
                                    letraMenu = datos.charAt(0);
                                    if (!(letraMenu >= 48 && letraMenu <= 57)) {
                                        intentos = intentos - 1;
                                        System.out.println();
                                        System.out.println("ERROR: La opcion debe contener solo caracteres numéricos válidos 0-18.");
                                        System.out.println("Vuelva a intentarlo. Le quedan " + intentos + " intentos para hacerlo bien.");
                                        System.out.print("Inserte la opción deseada: ");
                                        datos = leeTeclado.leeDatos();
                                    } else return datos;

                                } else {//hay dos carateres
                                    letraMenu = datos.charAt(0);
                                    letraMenu1 = datos.charAt(0);
                                    if (!(letraMenu >= 48 && letraMenu <= 57) || !(letraMenu1 >= 48 && letraMenu1 <= 57)) {
                                        intentos = intentos - 1;
                                        System.out.println();
                                        System.out.println("ERROR: Alguno de los caracteres que ha introducido no es numérico");
                                        System.out.println("Vuelva a intentarlo. Le quedan " + intentos + " intentos para hacerlo bien.");
                                        System.out.print("Inserte la opción deseada: ");
                                        datos = leeTeclado.leeDatos();
                                    } else {
                                        int aux;
                                        aux = Integer.parseInt(datos);
                                        if (aux != 10 && aux != 11 && aux != 12 && aux != 13 && aux != 14 && aux != 15 && aux != 16 && aux != 17 && aux != 18) {
                                            intentos = intentos - 1;
                                            System.out.println();
                                            System.out.println("ERROR: El numero introducido debes tesar dentro del rango 0-18");
                                            System.out.println("Vuelva a intentarlo. Le quedan " + intentos + " intentos para hacerlo bien.");
                                            System.out.print("Inserte la opción deseada: ");
                                            datos = leeTeclado.leeDatos();
                                        } else return datos;

                                    }
                                }

                            }
                        }
                        catch (StringIndexOutOfBoundsException siobe){
                            System.out.println("ERROR: Opcion incorrecta. Se debe introducir una opción válida (0-18) ");
                            System.out.println();
                            System.out.print("Inserte la opción deseada: ");
                            datos = leeTeclado.leeDatos();
                        }
                    }

            } else if (opcion.equals("opcionDNI")) {
                while (intentos > 1) {
                    try {
                        if (datos.length() != 9) {//el numero e caracteres es distinto de 9
                            intentos = intentos - 1;
                            System.out.println();
                            System.out.println("ERROR: El dni debe contener 9 caracteres.");
                            System.out.println("Vuelva a intentarlo. Le quedan " + intentos + " intentos para hacerlo bien.");
                            System.out.print("Dni cliente: ");
                            datos = leeTeclado.leeDatos();
                        }
                        return datos;
                    }
                    catch (StringIndexOutOfBoundsException siobe ){
                        System.out.println("ERROR: El dni debe contener 9 caracteres.");
                    }
                }

            } else if (opcion.equals("opcionPath")) {
                while (intentos > 1) {
                    try {
                        encontrado = false;
                        i = 0;
                        while (!encontrado && i < datos.length()) {
                            letraNombre = datos.charAt(i);
                            if (!((letraNombre == 46) || ((letraNombre >= 65) && (letraNombre <= 90)) || ((letraNombre >= 97) && (letraNombre <= 122)))) {
                                encontrado = true;
                            }
                            i = i + 1;
                        }
                        if (encontrado) {
                            System.out.println("El nombre debe contener caracteres válidos; no números, no acentos, solo letras desde a-z o A-Z y símbolo de puntuación '.'");
                            intentos = intentos - 1;
                            System.out.println("Vuelva a intentarlo. Le quedan " + intentos + " intentos para hacerlo bien.");
                            System.out.print("Nombre empresa: ");
                            datos = leeTeclado.leeDatos();

                        } else {
                            return datos;
                        }
                    }
                    catch (StringIndexOutOfBoundsException siobe){
                        System.out.println("ERROR: El dni debe contener 9 caracteres.");
                    }

                }
            }
            throw new IntentsLimitAchieveException("Se ha superado el límite de veces que el usuario puede introducir una opción no válida");
        }


    /*Nombre método: leeNumeroDecimalTeclado
      Entradas: ninguna
      Salidas: float (numero introducido por teclado)
      Excepciones: IntentsLimitAchieveException
      Descripción: utiliza el objeto escaner de la clase para leer de teclado un número. Si el número es no positivo y se superan el límite de intentos se lanza una excepción.
      */
    private float leeNumeroDecimalTeclado() throws IntentsLimitAchieveException {
            float numero;
            int intentos;
            intentos = INTENTOS;
            while (intentos > 1) {
                numero = Float.valueOf(leeTeclado.leeDatos()).floatValue();
                if (numero <= 0) {
                    intentos = intentos - 1;
                    System.out.println("El numero debe ser mayor que cero;  no números negativos ni cero");
                    System.out.println("Vuelva a intentarlo. Le quedan " + intentos + " intentos para hacerlo bien.");
                    System.out.print("Inserte valor: ");

                }
                return numero;
            }

            throw new IntentsLimitAchieveException("Se ha superado el límite de veces que el usuario puede introducir una opción no válida");

    }

    /*Nombre método: leeNumeroEnteroTeclado
      Entradas: ninguna
      Salidas: int (numero introducido por teclado)
      Excepciones: IntentsLimitAchieveException
      Descripción: utiliza el objeto escaner de la clase para leer de teclado un número. Si el número es no positivo y se superan el límite de intentos se lanza una excepción.
      */
    private float leeNumeroEnteroTeclado() throws IntentsLimitAchieveException {
        float numero;
        int intentos;
        intentos = INTENTOS;
        while (intentos > 1) {
            try {
                numero = Float.valueOf(leeTeclado.leeDatos()).floatValue();
                if (numero <= 0) {
                    intentos = intentos - 1;
                    System.out.println("El numero debe ser mayor que cero;  no números negativos ni cero");
                    System.out.println("Vuelva a intentarlo. Le quedan " + intentos + " intentos para hacerlo bien.");
                    System.out.print("Inserte valor: ");

                }
                return (int) numero;
            }
            catch (java.lang.NumberFormatException nfe ){
                if (intentos > 1) {
                    intentos = intentos - 1;
                    System.out.println("El numero debe ser mayor que cero;  no números negativos ni cero");
                    System.out.println("Vuelva a intentarlo. Le quedan " + intentos + " intentos para hacerlo bien.");
                    System.out.print("Inserte valor: ");
                }
                else{
                    throw new IntentsLimitAchieveException("Se ha superado el límite de veces que el usuario puede introducir una opción no válida");

                }
            }
        }

        throw new IntentsLimitAchieveException("Se ha superado el límite de veces que el usuario puede introducir una opción no válida");

    }


    /*Nombre método: menu
      Entradas: ninguna
      Salidas: ninguna
      Excepciones: no
      Descripción: imprime por pantalla el menú
      */
    private void menu() {
        System.out.println("********************** MENU *********************");
        System.out.println("0.- Salir");
        System.out.println("----------------- ESTADO ----------------------");
        System.out.println("1.- Imprimir estado de los clientes");
        System.out.println("2.- Imprimir estado de la bolsa");
        System.out.println("------------------- BANCO ----------------------");
        System.out.println("3.- Añadir cliente");
        System.out.println("4.- Eliminar cliente");
        System.out.println("5.- Realizar copia de seguridad");
        System.out.println("6.- Restaurar copia de seguridad");
        System.out.println("7.- Mejorar cliente a premium");
        System.out.println("8.- Solicita recomendación de inversión");
        System.out.println("----------------- BOLSA ------------------------");
        System.out.println("9.- Añadir empresa a la bolsa");
        System.out.println("10.- Eliminar empresa de la bolsa");
        System.out.println("11.- Acutalización de valores");
        System.out.println("12.- Realizar copia de seguridad");
        System.out.println("13.- Restaurar copia de seguridad");
        System.out.println("----------------- OPERACIONES --------------------");
        System.out.println("14.- Solicitar compra de acciones");
        System.out.println("15.- Solicitar venta de acciones");
        System.out.println("16.- Solicitar actualización de valores");
        System.out.println("------------------- BROKER -----------------------");
        System.out.println("17.- Imprimir operaciones pendientes");
        System.out.println("18.- Ejecutar operaciones pendientes");
        System.out.println("**************************************************");
    }
    //-FIN METODOS PRIVADOS


    //ESPACIO RESERVADO PARA METODOS PUBLICOS DE LA CLASE

    /*Nombre método: muestraMenu
      Entradas: ninguna
      Salidas: ninguna
      Excepciones: ClassCastException,IntentsLimitAchieveException,ObjetoEscannerNoPasadoConstructorInterfazDeUsuario
      Descripción: Muestra el menu y pide al usuario ingresar un valor por teclado que se corresponda con una de las opciones del menu. Si esa entrada no es una de las opciones del menu o se supera el limite de intentos entonces se lanza excepcion.
      */
    public void muestraMenu() throws ClassCastException, IntentsLimitAchieveException, ObjetoEscannerNoPasadoConstructorInterfazDeUsuario {
        int intentos;
        intentos = INTENTOS;
        this.menu();
        System.out.print("Inserte la opción deseada: ");
        try {
            eleccion = this.leeCadenaTextoTeclado("opcionMenu");

        }catch (NullPointerException e) {
            throw new ObjetoEscannerNoPasadoConstructorInterfazDeUsuario("La clase 'Interfaz Ususario' debe recibir un objeto de tipo 'Escanner' para funcionar correctamente");
        } catch (InputMismatchException e1){

        }
    }

    /*Nombre método: muestraClientesBanco (OPCION 1)
      Entradas: ninguna
      Salidas: ninguna
      Excepciones: ninguna
      Descripción: solo imprime por pantalla un mensaje relacionado con la consulta que se desea realizar y pide los datos necesarios por teclado
      */
    public void muestraClientesBanco() {
        System.out.println("------OPCIÓN 1 SELECCIONADA: MUESTRA CLIENTES BANCO-----");
        System.out.println("A continuación le mostramos una lista con los clientes del banco: ");
        System.out.println();
    }

    /*Nombre método: muestraEmpresasBanco (OPCION 2)
      Entradas: ninguna
      Salidas: ninguna
      Excepciones: nada
      Descripción: solo imprime por pantalla un mensaje relacionado con la consulta que se desea realizar y pide los datos necesarios por teclado
      */
    public void muestraEmpresasBanco() {
        System.out.println("------OPCIÓN 2 SELECCIONADA: MUESTRA EMPRESAS BOLSA-----");
        System.out.println("A continuación le mostramos una lista con las empresas de bolsa: ");
        System.out.println();
    }

    /*Nombre método: AltaClienteBanco (OPCION 3)
      Entradas: ninguna
      Salidas: ninguna
      Excepciones: IntentsLimitAchieveException
      Descripción: solo imprime por pantalla un mensaje relacionado con la consulta que se desea realizar y pide los datos necesarios por teclado
      */
    public void altaClienteBanco() throws IntentsLimitAchieveException {
        System.out.println("------OPCIÓN 3 SELECCIONADA: AÑADIR CLIENTE A BANCO------");
        System.out.println();
        System.out.println("A continuación le solicitaremos los siguientes datos necesarios: ");
        System.out.println();
        System.out.print("      Nombre cliente: ");//6 espacios
        this.nombrePersona = this.leeCadenaTextoTeclado("opcionNombre");
        System.out.print("      DNI cliente: ");
        this.dni = this.leeCadenaTextoTeclado("opcionDNI");
        System.out.print("      Saldo: ");
        this.saldo = this.leeNumeroDecimalTeclado();
    }


    /*Nombre método: BajaClienteBanco (OPCION 4)
      Entradas: ninguna
      Salidas: ninguna
      Excepciones: IntentsLimitAchieveException
      Descripción: solo imprime por pantalla un mensaje relacionado con la consulta que se desea realizar y pide los datos necesarios por teclado
      */
    public void bajaClienteBanco() throws IntentsLimitAchieveException {
        System.out.println("------OPCIÓN 4 SELECCIONADA: ELIMINAR CLIENTE BANCO------");
        System.out.println();
        System.out.println("A continuación le solicitaremos los siguientes datos necesarios: ");
        System.out.print("Dni cliente: ");
        dni = this.leeCadenaTextoTeclado("opcionDNI");

    }

    /*Nombre método: hazCopiaSeguridadBanco(OPCION 5)
      Entradas: ninguna
      Salidas: ninguna
      Excepciones: ninguna
      Descripción: solo imprime por pantalla un mensaje relacionado con la consulta que se desea realizar y pide los datos necesarios por teclado
      */
    public void hazCopiaSeguridadBanco()throws IntentsLimitAchieveException {
        System.out.println("------OPCIÓN 5 SELECCIONADA: COPIA SEGURIDAD BANCO-----");
        System.out.println();
        System.out.println("A continuación le solicitaremos los siguientes datos necesarios: ");
        System.out.println();
        System.out.print("      Path fichero: ");//6 espacios
        this.path = this.leeCadenaTextoTeclado("opcionPath");
    }

    /*Nombre método: restauraCopiaSeguridadBanco(OPCION 6)
      Entradas: ninguna
      Salidas: ninguna
      Excepciones: ninguna
      Descripción: solo imprime por pantalla un mensaje relacionado con la consulta que se desea realizar y pide los datos necesarios por teclado
      */
    public void restauraCopiaSeguridadBanco()throws IntentsLimitAchieveException  {
        System.out.println("------OPCIÓN 6 SELECCIONADA: RESTAURA COPIA SEGURIDAD BANCO-----");
        System.out.println();
        System.out.println("A continuación le solicitaremos los siguientes datos necesarios: ");
        System.out.println();
        System.out.print("      Path fichero: ");//6 espacios
        this.path = this.leeCadenaTextoTeclado("opcionPath");
    }

    /*Nombre método: promocionaPremium (OPCION 7)
      Entradas: dni cliente
      Salidas: nada
      Excepciones:
      Descripción: solo imprime por pantalla un mensaje relacionado con la consulta que se desea realizar y pide los datos necesarios por teclado
      */
    public void promocionaPremium() throws IntentsLimitAchieveException {
        System.out.println("------OPCIÓN 7 SELECCIONADA: MEJORAR CLIENTE A PREMIUM------");
        System.out.println();
        System.out.println("A continuación le solicitaremos los siguientes datos necesarios: ");
        System.out.println();
        System.out.print("Dni cliente: ");
        dni = this.leeCadenaTextoTeclado("opcionDNI");
    }

    /*Nombre método: consultaValores(OPCION 8)
      Entradas: dni cliente
      Salidas: nada
      Excepciones:
      Descripción: solo imprime por pantalla un mensaje relacionado con la consulta que se desea realizar y pide los datos necesarios por teclado
      */
    public void consultaValores() throws IntentsLimitAchieveException{
        System.out.println("------OPCIÓN 8 SELECCIONADA: SOLICITA RECOMEDACIÓN DE INVERSIÓN------");
        System.out.println();
        System.out.println("A continuación le solicitaremos los siguientes datos necesarios: ");
        System.out.println();
        System.out.print("Dni cliente: ");
        dni = this.leeCadenaTextoTeclado("opcionDNI");
    }

    /*Nombre método: AltaEmpresaBolsa (OPCION 9)
      Entradas: ninguna
      Salidas: ninguna
      Excepciones: IntentsLimitAchieveException
      Descripción: solo imprime por pantalla un mensaje relacionado con la consulta que se desea realizar y pide los datos necesarios por teclado
      */
    public void altaEmpresaBolsa() throws IntentsLimitAchieveException {
        System.out.println("------OPCIÓN 9 SELECCIONADA: AÑADIR EMPRESA A BOLSA------");
        System.out.println();
        System.out.println("A continuación le solicitaremos los siguientes datos necesarios: ");
        System.out.println();
        System.out.print("      Nombre empresa: ");//6 espacios
        this.nombreEmpresa = this.leeCadenaTextoTeclado("opcionNombre");
        System.out.print("      Valor actual empresa: ");
        this.valorActualEmpresa = this.leeNumeroDecimalTeclado();
    }

    /*Nombre método: BajaEmpresaBolsa (OPCION 10)
      Entradas: ninguna
      Salidas: ninguna
      Excepciones: IntentsLimitAchieveException
      Descripción: solo imprime por pantalla un mensaje relacionado con la consulta que se desea realizar y pide los datos necesarios por teclado
      */
    public void bajaEmpresaBolsa() throws IntentsLimitAchieveException {
        System.out.println("------OPCIÓN 10 SELECCIONADA: ELIMINAR EMPRESA BOLSA------");
        System.out.println();
        System.out.println("A continuación le solicitaremos los siguientes datos necesarios: ");
        System.out.println();
        System.out.print("Nombre empresa: ");
        this.nombreEmpresa = this.leeCadenaTextoTeclado("opcionNombre");
    }

    /*Nombre método: actualizaValores (OPCION 11)
      Entradas: ninguna
      Salidas: ninguna
      Excepciones: IntentsLimitAchieveException
      Descripción: solo imprime por pantalla un mensaje relacionado con la consulta que se desea realizar y pide los datos necesarios por teclado
      */
    public void actualizaValores() {
        System.out.println("------OPCIÓN 11 SELECCIONADA: ACTUALIZA VALORES------");
        System.out.println();

    }

    /*Nombre método: hazCopiaSeguridadBolsa(OPCION 12)
      Entradas: ninguna
      Salidas: ninguna
      Excepciones: ninguna
      Descripción: solo imprime por pantalla un mensaje relacionado con la consulta que se desea realizar y pide los datos necesarios por teclado
      */
    public void hazCopiaSeguridadBolsa() throws IntentsLimitAchieveException {
        System.out.println("------OPCIÓN 12 SELECCIONADA: COPIA SEGURIDAD BOLSA-----");
        System.out.println();
        System.out.println("A continuación le solicitaremos los siguientes datos necesarios: ");
        System.out.println();
        System.out.print("      Path fichero: ");//6 espacios
        this.path = this.leeCadenaTextoTeclado("opcionPath");
    }

    /*Nombre método: restauraCopiaSeguridadBolsa(OPCION 13)
      Entradas: ninguna
      Salidas: ninguna
      Excepciones: ninguna
      Descripción: solo imprime por pantalla un mensaje relacionado con la consulta que se desea realizar y pide los datos necesarios por teclado
      */
    public void restauraCopiaSeguridadBolsa() throws IntentsLimitAchieveException {

        System.out.println("------OPCIÓN 13 SELECCIONADA: RESTAURA COPIA SEGURIDAD BOLSA-----");
        System.out.println();
        System.out.println("A continuación le solicitaremos los siguientes datos necesarios: ");
        System.out.println();
        System.out.print("      Path fichero: ");//6 espacios
        this.path = this.leeCadenaTextoTeclado("opcionPath");
    }

    /*Nombre método: solicitaCompraDeAcciones(OPCION 14)
      Entradas: ninguna
      Salidas: ninguna
      Excepciones: ninguna
      Descripción: solo imprime por pantalla un mensaje relacionado con la consulta que se desea realizar y pide los datos necesarios por teclado
      */
    public void solicitaCompraDeAcciones() throws IntentsLimitAchieveException {

        System.out.println("------OPCIÓN 14 SELECCIONADA: SOLICITAR COMPRA DE ACCIONES-----");
        System.out.println();
        System.out.println("A continuación le solicitaremos los siguientes datos necesarios: ");
        System.out.println();
        System.out.print("Dni cliente: ");
        dni = this.leeCadenaTextoTeclado("opcionDNI");
        System.out.println();
        System.out.print("Nombre empresa de la que dessae comprar acciones: ");//6 espacios
        this.nombreEmpresa = this.leeCadenaTextoTeclado("opcionNombre");
        System.out.println();
        System.out.print("Cantidad MAX. que desea invertir: ");
        this.cantidadMaxAInvertir = this.leeNumeroEnteroTeclado();
    }

    /*Nombre método: muestraOperacionesPendientes(OPCION 17)
      Entradas: ninguna
      Salidas: ninguna
      Excepciones: ninguna
      Descripción: solo imprime por pantalla un mensaje relacionado con la consulta que se desea realizar.
      */
    public void muestraOperacionesPendientes(){
        System.out.println("------OPCIÓN 17 SELECCIONADA: IMPRIMIR OPERACIONES PENDIENTES-----");
        System.out.println();
    }

    /*Nombre método: ejecutaPeticionesDeAcciones(OPCION 18)
      Entradas: ninguna
      Salidas: ninguna
      Excepciones: ninguna
      Descripción: solo imprime por pantalla un mensaje relacionado con la consulta que se desea realizar.
      */
    public void ejecutaPeticionesDeAcciones(){
        System.out.println("------OPCIÓN 18 SELECCIONADA: IMPRIMIR OPERACIONES PENDIENTES-----");
        System.out.println();
        System.out.println("Ejecutando...");
        System.out.println();
    }






}



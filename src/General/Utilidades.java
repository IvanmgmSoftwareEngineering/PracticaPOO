package General;

import Banco.Cliente;
import Bolsa.Empresa;

import java.io.*;
import java.util.Calendar;
import java.util.Random;
import java.text.DecimalFormat;
import java.util.GregorianCalendar;

public class Utilidades {

    public FileInputStream fileIn;
    public ObjectInputStream input;

    public FileOutputStream fileOut;
    public ObjectOutputStream output;



    //SERIALIZA/DESERILIZA
    //Abrimos el fichero
    public void abrirIn(String path) throws IOException {

        fileIn = new FileInputStream(path);
        input = new ObjectInputStream(fileIn);

    }
    //Leer el fichero un Cliente
    public Cliente leerCliente ()throws IOException,ClassNotFoundException{
        Cliente cliente = null;
        if (input!=null) {
            try{
                cliente = (Cliente) input.readObject();

            }

            catch ( EOFException eof){
                //Fin del fichero
            }
        }
        return cliente;
    }
    //Leer el fichero una Empresa
    public Empresa leerEmpresa()throws IOException,ClassNotFoundException,ClassCastException{
        Empresa empresa = null;
        if (input!=null) {
            try{
                empresa = (Empresa) input.readObject();
            }
            catch ( EOFException eof){
            }
            catch ( IOException ioee){
            }
            catch ( ClassNotFoundException cnfe){
            }

            catch ( ClassCastException cce){

                throw  cce  ;
            }
        }
        return empresa;
    }
    //Cerramos el fichero
    public void cerrarIn ()throws IOException{
        if (input!=null) input.close();
    }
    //Abrimos el fichero
    public void abrirOut(String path) throws IOException{

        fileOut = new FileOutputStream(path);
        output = new ObjectOutputStream(fileOut);

    }
    //Escribir el fichero un Cliente
    public void escribirCliente (Cliente cliente)throws IOException{
        if (output!=null) output.writeObject(cliente);
    }
    //Escribir el fichero una Empresa
    public void escribirEmpresa(Empresa empresa)throws IOException{
        if (output!=null) output.writeObject(empresa);
    }
    //Cerramos el fichero
    public void cerrarOut ()throws IOException{
        if (output!=null) output.close();
    }
    //FIN SERIALIZA/DESERILIZA

    //ALEATORIO PARA MODIFICAR BOLSA
    public double generaAleatorio () {
        double resultado = -1;
        while (resultado == -1) {

            Random generaNumeroAleatorio1 = new Random();
            Random generaNumeroAleatorio2 = new Random();

            double num1 = generaNumeroAleatorio1.nextInt(100);
            double num2 = generaNumeroAleatorio2.nextInt(100);

            resultado = (((num1 + num2) - 100)/100);
        }
        return resultado;
    }
    //FIN ALEATORIO PARA MODIFICAR BOLSA

    //METODOS ESTATICOS PARA FORMATEAR VALORES
    public static String formatoDinero(double numero) { // Método estático: no hace falta crear un objeto para llamarla
        DecimalFormat formateador = new DecimalFormat("#################.##");
        return formateador.format(numero);
    }
    public static String formatoEntero(int numero) {
        DecimalFormat formateador = new DecimalFormat("#######");
        return formateador.format(numero);
    }
    public static String formatovariacion(double numero) {
        DecimalFormat formateador = new DecimalFormat("####.##");
        return formateador.format(numero);
    }
    public static String formatoFecha(Calendar fecha) {
        int anyo = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH)+1;
        int diaDelMes = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);

        return Integer.toString(anyo) + Integer.toString(mes) + Integer.toString(diaDelMes) + Integer.toString(hora) + Integer.toString(minuto) + Integer.toString(segundo);
    }
    //FIN METODOS ESTATICOS PARA FORMATEAR VALORES
}
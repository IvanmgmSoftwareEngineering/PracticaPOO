package com.company;

import ExcepcionesPropias.*;
import General.*;
import Bolsa.*;
import Banco.*;

import java.io.IOException;


public class Main {

    public static void main(String[] args) {
        String respuesta = "";

        try {
            Escaner escanner = new Escaner();//Creamo un objeto de tipo Escaner
            InterfazDeUsuario interfazUsusario = new InterfazDeUsuario(escanner); //creamos un objeto de tipo InterfazDeUsuario y utilizamos el contructor que recibe un parametro de entrada de tipo Escaner

            BolsaDeValores bolsa = new BolsaDeValores ("Bolsa Española");//Creamos un objeto de tipo BolsaDeValores.
            Banco banco = new Banco ("Banco Bilbao Vizcaya");//Creamos un banco
            AgenteDeInversiones broker = new AgenteDeInversiones("Jhon Templeton","123456789",bolsa,"broker");//Creamo un objeto de tipo AgenteDeInversion que sera un broker
            banco.setBroker(broker);//Se lo asignamos al banco
            AgenteDeInversiones gestor = new AgenteDeInversiones("Elon James", "123456785", bolsa,"gestor");//Creamo un objeto de tipo AgenteDeInversion que sera un gestor de inversiones
            banco.setGestor(gestor);


            Simulador simula = new Simulador(interfazUsusario,bolsa,banco,broker); // Creamos un objeto de tipo Simulador y utilizamos el constructor que recibe como parmatros de entrada un objeto de tipo InterfazDeUsuario y un objeto de tipo BolsaDeValores9
            simula.principal();// Llamamos al metodo principal() de la clase Simulador.



            respuesta="Programa terminado con exito!!";
        }
        catch (IntentsLimitAchieveException e1){
            e1.printStackTrace();
            respuesta = e1.getMessage();
        }
        catch (ClassCastException castException){
            castException.printStackTrace();
            respuesta = "La opción debe ser numérica";
        }
        catch (ObjetoEscannerNoPasadoConstructorInterfazDeUsuario e2) {
            e2.printStackTrace();
            respuesta = e2.getMessage();
        }
        catch (ObjetoInterfazDeUsuarioNoPasadoConstructorSimulador e3) {
            respuesta = e3.getMessage();
        } catch (BancoNoTieneGestor e4) {
            e4.printStackTrace();
            respuesta=e4.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e5) {
            e5.printStackTrace();
            respuesta=e5.getMessage();
        }


        System.err.println(respuesta);


    }
}

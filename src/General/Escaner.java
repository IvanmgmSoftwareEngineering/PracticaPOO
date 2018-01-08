package General;
import java.util.Scanner;

public class Escaner  {

    public String leeDatos(){
        Scanner entradaDatos = new Scanner (System.in); //Creación de un objeto Scanner
        return entradaDatos.nextLine();
    }
}

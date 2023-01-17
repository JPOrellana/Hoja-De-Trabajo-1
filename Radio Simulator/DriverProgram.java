import java.text.DecimalFormat;
import java.util.Scanner;

public class DriverProgram {
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static void main(String[] args) {
        Scanner input =new Scanner(System.in);
        Controller controlador = new Controller();

        int option=0;

        System.out.println("===================================================================");
        System.out.println("|                      Bienvenido a la Radio                      |");
        System.out.println("|     Navega en nuestro sistema mientras disfrutas de tu viaje    |");
        System.out.println("===================================================================");
        boolean go = true;
        while (go){
            System.out.println("===================================================================");
            System.out.println("|               Ingrese la opcion que desea ejecutar                |");
            System.out.println("|                                                                   |");
            System.out.println("| [1.]  Encendido o Apagado                                         |");
            System.out.println("| [2.]  Cambiar Frecuencia (AM/FM)                                  |");
            System.out.println("| [3.]  Cambiar Emisoras                                            |");
            System.out.println("| [4.]  Guardar Emisoras Favoritas                                  |");
            System.out.println("| [5.]  Cargar Emisoras Favoritas                                   |");
            System.out.println("| [6.]  Salir                                                       |");
            System.out.println("===================================================================");
            boolean done = false;
            while (!done){
                try {
                    option = input.nextInt();
                    done = true;
                }catch (Exception e){
                    System.out.println("ERROR, Ingrese una opcion válida");
                    input.next();
                }
            }
        
        }
    }
}

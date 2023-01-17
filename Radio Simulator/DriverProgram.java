import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * @author diego leiva
 * @author pablo orellana
 */
public class DriverProgram {
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static void main(String[] args) {
        Scanner input =new Scanner(System.in);
        Controller controlador = new Controller();

        int option=0;

        //Mensaje de bienvenida
        System.out.println("===================================================================");
        System.out.println("|                      Bienvenido a la Radio                      |");
        System.out.println("|     Navega en nuestro sistema mientras disfrutas de tu viaje    |");
        System.out.println("===================================================================");
        boolean go = true;

        //Ciclo que permite que el usuario seleccione diferentes opciones
        while (go) {
            //Menu de opciones
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
            //Ciclo que evita que el usuario ingrese un valor no numerico
            while (!done) {
                try {
                    option = input.nextInt();
                    done = true;
                } catch (Exception e) {
                    System.out.println("ERROR, Ingrese una opcion válida");
                    input.next();
                }
            }
            switch (option) {
                case 1://Opcion de encendido apagado
                    if (controlador.isOn()) {
                        controlador.off();
                    } else if (!controlador.isOn())
                        controlador.on();
                    break;
                case 2://Opcion de cambio de estacion AM / FM
                    System.out.println("Que estación desea escuchar?");
                    System.out.println("AM");
                    System.out.println("FM");
                    String ans = input.next().toLowerCase();
                    try {
                        controlador.setFrequence(ans);
                    }catch (Exception ex){
                        System.out.println("ERROR, Estación invalida");
                    }
                    break;

                case 3://Opcion de cambio de frecuencia, despliega menu de adelante o atras
                    String next;
                    boolean flag = false;
                    while (!flag){
                        System.out.println("Para salir escriba presione cualquier letra");
                        System.out.println("[ Frecuencia ] ");
                        if (controlador.getFrequence().equals("AM")){
                            System.out.println("-  "+controlador.getAMActualStation()+"  +");
                            next = input.next();
                            if (next.equals("+")){
                                controlador.Forward();
                            }else if (next.equals("-")){
                                controlador.Backward();
                            }else if (!next.equals("+")||!next.equals("-"))  flag = true;
                        }else if (controlador.getFrequence().equals("FM")){
                            System.out.printf("-  "+df.format(controlador.getFMActualStation())+"  +");
                            next = input.next();
                            if (next.equals("+")){
                                controlador.Forward();
                            }else if (next.equals("-")){
                                controlador.Backward();
                            } else if (!next.equals("+")||!next.equals("-")) flag = true;
                        }
                    }
                    break;
                case 4://Opcion que permite guardar una frecuencia AM o FM en alguno de los 12 espacios disponibles
                    if (controlador.getFrequence().equals("AM")){
                        System.out.println("La frecuencia actual AM es:  "+controlador.getAMActualStation());

                        System.out.println("Ingrese el número donde desea guardar la estacion");
                        System.out.println("                Espacios favoritos");
                        System.out.println("[1] [2] [3] [4] [5] [6] [7] [8] [9] [10] [11] [12]");
                        int fav=0;
                        boolean exist = false;
                        while (!exist){
                            try {
                                fav = input.nextInt();
                                exist = true;
                            }catch (Exception e){
                                System.out.println("ERROR, Ingrese una opcion válida");
                                input.next();
                            }
                        }
                        controlador.saveAMStation(controlador.getAMActualStation(),fav);
                    }else if (controlador.getFrequence().equals("FM")){
                        System.out.println("La frecuencia actual FM es:  "+df.format(controlador.getFMActualStation()));
                        System.out.println("Ingrese el número donde desea guardar la estacion");
                        System.out.println("                Espacios favoritos");
                        System.out.println("[1] [2] [3] [4] [5] [6] [7] [8] [9] [10] [11] [12]");
                        int fav=0;
                        boolean exist = false;
                        while (!exist){
                            try {
                                fav = input.nextInt();
                                exist = true;
                            }catch (Exception e){
                                System.out.println("ERROR, Ingrese una opcion válida");
                                input.next();
                            }
                        }
                        controlador.saveFMStation(controlador.getFMActualStation(),fav);
                    }
                    break;
                case 5://Opcion que permite al usuario cargar la frecuencia guardada en el espacio que seleccione
                    if (controlador.getFrequence().equals("AM")){
                        System.out.println("Ingrese el número favorito para cargar la estacion guardada");
                        System.out.println("                Espacios favoritos");
                        System.out.println("[1] [2] [3] [4] [5] [6] [7] [8] [9] [10] [11] [12]");
                        int space=0;
                        boolean oks = false;
                        while (!oks){
                            try {
                                space = input.nextInt();
                                oks = true;
                            }catch (Exception e){
                                System.out.println("ERROR, Ingrese una opcion válida");
                                input.next();
                            }
                            if (controlador.getAMSlot(space)==0){
                                System.out.println("Espacio vacio");
                            }else System.out.println("La frecuencia guardada AM es: "+controlador.getAMSlot(space));
                        }
                    }else if (controlador.getFrequence().equals("FM")){
                        System.out.println("Ingrese el número favorito para cargar la estacion guardada");
                        System.out.println("                Espacios favoritos");
                        System.out.println("[1] [2] [3] [4] [5] [6] [7] [8] [9] [10] [11] [12]");
                        int slot=0;
                        boolean good = false;
                        while (!good) {
                            try {
                                slot = input.nextInt();
                                good = true;
                            } catch (Exception e) {
                                System.out.println("ERROR, Ingrese una opcion válida");
                                input.next();
                            }
                            if (controlador.getFMSlot(slot) == 0) {
                                System.out.println("Espacio vacio");
                            } else
                                System.out.println("La frecuencia guardada AM es: " + df.format(controlador.getFMSlot(slot)));}
                    }
                    break;
                case 6://Opcion que permite que el usuario salga del programa
                    System.out.println("Ha salido del programa");
                    go = false;
                    break;
            }
        }
    }
}

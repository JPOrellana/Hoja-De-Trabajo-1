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
        while (go) {
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
                case 1:
                    if (controlador.isOn()) {
                        controlador.off();
                    } else if (!controlador.isOn())
                        controlador.on();
                    break;
                case 2:
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

                case 3:

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
                case 6:
                    System.out.println("Ha salido del programa");
                    go = false;
                    break;
            }
        }
    }
}

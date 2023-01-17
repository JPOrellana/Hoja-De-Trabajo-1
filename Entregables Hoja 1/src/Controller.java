/**
 * @author diego leiva
 * @author pablo orellana
 * @version 5
 */
public class Controller implements IRadio{
    public String onoff="OFF";
    public String stationType = "AM";
    public double FMfreq = 87.9;
    public int AMfreq = 530;
    public double[] favFreqsfm = new double[12];

    public int[] favFreqsam = new int[12];
    @Override
    public void on() {
        onoff = "ON";
        System.out.println("Encendido");
    }

    @Override
    public void off() {
        onoff = "OFF";
        System.out.println("Apagado");
    }

    /***
     * Este metodo nos indica si la radio esta encendida o apagada
     * @return true si la radio esta encendida y false cuando la radio este apagada
     */
    @Override
    public boolean isOn() {
        if (onoff.equals("ON")){
            return true;
        }else return false;
    }

    /***
     * Este metodo nos ayuda a establecer la frecuencia, recibe un parametro llamado freq que puede "AM" o "FM"
     * @param freq La frecuencia la cual puede ser AM o FM, de lo contrario error.
     */
    @Override
    public void setFrequence(String freq) throws Exception {
        switch (freq){
            case "am":
                stationType = "AM";
                System.out.println("Estación cambio a AM");
                break;
            case "fm":
                stationType="FM";
                System.out.println("Estación cambio a FM");
                break;
        }
    }

    /**
     * Este metodo obtiene la estacion actual, ya sea AM o FM
     * @return stationType la cual almacena el tipo de estacion AM o FM
     */
    @Override
    public String getFrequence() {
        return stationType;
    }

    /**
     * Este metodo incrementa la frecuencia del radio, dependiendo de si la estacion es AM o FM
     * si es AM la frecuencia da saltos de 10 en 10 hasta llegar a 1610 y luego si aumenta mas regresa al valor de 530
     * si es FM la frecuencia da saltos de 0.2 en 0.2 hasta llegar a 107.9 y luego si aumenta mas regresa al valor de 87.9
     */
    @Override
    public void Forward() {
        if (stationType.equals("AM")){
            if (AMfreq >= 1610)AMfreq=530;
            else AMfreq += 10;
        }
        if (stationType.equals("FM")){
            if (FMfreq >= 107.9)FMfreq = 87.9;
            else FMfreq += 0.2;
        }
    }

    /**
     * Este metodo disminuye la frecuencia del radio, dependiendo de si la estacion es AM o FM
     * si es AM la frecuencia da saltos de 10 en 10 hasta llegar a 530 y luego si disminuye mas regresa al valor de 1610
     * si es FM la frecuencia da saltos de 0.2 en 0.2 hasta llegar a 87.9 y luego si disminuye mas regresa al valor de 107.9
     */
    @Override
    public void Backward() {
        if (stationType.equals("AM")){
            if (AMfreq <= 530)AMfreq=1610;
            else AMfreq -= 10;
        }
        if (stationType.equals("FM")){
            if (FMfreq <= 87.9)FMfreq = 107.9;
            else FMfreq -= 0.2;
        }
    }

    /**
     * Este metodo es el encargado de obtener el valor de la frecuencia FM actual
     * @return FMfreq la cual contiene el valor double de la frecuencia FM
     */
    @Override
    public double getFMActualStation() {
        return FMfreq;
    }

    /**
     * Este metodo es el encargado de obtener el valor de la frecuencia AM actual
     * @return AMfreq la cual contiene el valor integer de la frecuencia AM
     */
    @Override
    public int getAMActualStation() {
        return AMfreq;
    }

    /**
     *
     * @param actualStation
     */
    @Override
    public void setFMActualStation(double actualStation) {
        FMfreq = actualStation;
    }

    /**
     *
     * @param actualStation
     */
    @Override
    public void setAMActualStation(int actualStation) {
        AMfreq = actualStation;
    }

    /**
     * Este metodo guarda en una lista de doubles la estacion actual en el espacio que el usuario seleccione
     * Funciona para el radio en modo FM
     * @param actualStation la estacion FM actual en la radio
     * @param slot el boton de 1 a 12 que el usuario selecciono
     */
    @Override
    public void saveFMStation(double actualStation, int slot) {
        favFreqsfm[slot] = actualStation;
    }

    /**
     * Este metodo guarda en una lista de integers la estacion actual en el espacio que el usuario seleccione
     * Funciona para el radio en modo AM
     * @param actualStation la estacion AM actual en la radio
     * @param slot el boton de 1 a 12 que el usuario selecciono
     */
    @Override
    public void saveAMStation(int actualStation, int slot) {
        favFreqsam[slot] = actualStation;
    }

    /**
     * Metodo que obtiene la frecuencia FM guardada en alguno de los botones que el usuario seleccione
     * Si esta vacio, mostrara un mensaje de que el boton seleccionado esta vacio
     * @param slot  el espacio seleccionado por el usuario
     * @return  devuelve la frecuencia FM guardada en el espacio seleccionado, y si esta vacio devuelve 0
     */
    @Override
    public double getFMSlot(int slot) {
        if (favFreqsfm[slot]==0){
            return 0;
        }else return favFreqsfm[slot];
    }

    /**
     * Metodo que obtiene la frecuencia AM guardada en alguno de los botones que el usuario seleccione
     * Si esta vacio, mostrara un mensaje de que el boton seleccionado esta vacio
     * @param slot el espacio seleccionado por el usuario
     * @return devuelve la frecuencia AM guardada en el espacio seleccionado, y si esta vacio devuelve 0
     */
    @Override
    public int getAMSlot(int slot) {
        if (favFreqsam[slot] == 0){
            return 0;
        }else return favFreqsam[slot];
    }

}

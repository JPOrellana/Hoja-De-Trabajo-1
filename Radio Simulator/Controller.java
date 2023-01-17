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

    @Override
    public String getFrequence() {
        return stationType;
    }

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

    @Override
    public double getFMActualStation() {
        return FMfreq;
    }

    @Override
    public int getAMActualStation() {
        return AMfreq;
    }

    @Override
    public void setFMActualStation(double actualStation) {
        FMfreq = actualStation;
    }

    @Override
    public void setAMActualStation(int actualStation) {
        AMfreq = actualStation;
    }

    @Override
    public void saveFMStation(double actualStation, int slot) {
        favFreqsfm[slot] = actualStation;
    }

    @Override
    public void saveAMStation(int actualStation, int slot) {
        favFreqsam[slot] = actualStation;
    }

    @Override
    public double getFMSlot(int slot) {
        if (favFreqsfm[slot]==0){
            return 0;
        }else return favFreqsfm[slot];
    }

    @Override
    public int getAMSlot(int slot) {
        if (favFreqsam[slot] == 0){
            return 0;
        }else return favFreqsam[slot];
    }

}

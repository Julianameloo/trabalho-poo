package codigos;

import java.util.LinkedList;

public class HorarioMotorista extends Horario{
    private LinkedList passageiros;
    

    public LinkedList getPassageiros() {
        return passageiros;
    }

    public void setPassageiros(LinkedList passageiros) {
        this.passageiros = passageiros;
    }
}

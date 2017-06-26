package codigos;

import java.util.LinkedList;

public class Passageiro extends Usuario{
    private LinkedList horarios;
    private int id_passageiro;

    public LinkedList getHorarios() {
        return horarios;
    }

    public void setHorarios(LinkedList horarios) {
        this.horarios = horarios;
    }

    public int getId_passageiro() {
        return id_passageiro;
    }

    public void setId_passageiro(int id) {
        this.id_passageiro = id;
    }
    
}

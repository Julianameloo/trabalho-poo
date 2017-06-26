package codigos;

import java.util.LinkedList;

public class Motorista extends Usuario {
    private Veiculo veiculoDeTransporte;
    private LinkedList passageiros;
    private String regiao;
    private LinkedList horarios;
    private int id_motorista;    

    public LinkedList getPassageiros() {
        return passageiros;
    }

    public void setPassageiros(LinkedList passageiros) {
        this.passageiros = passageiros;
    }

    public LinkedList getHorarios() {
        return horarios;
    }

    public void setHorarios(LinkedList horarios) {
        this.horarios = horarios;
    }

    public int getId_motorista() {
        return id_motorista;
    }

    public void setId_motorista(int id) {
        this.id_motorista = id;
    }

    public Veiculo getVeiculoDeTransporte() {
        return veiculoDeTransporte;
    }

    public void setVeiculoDeTransporte(Veiculo veiculoDeTransporte) {
        this.veiculoDeTransporte = veiculoDeTransporte;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }
    
    
}

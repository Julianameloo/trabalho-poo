package codigos;

import java.util.Iterator;
import java.util.LinkedList;

public class Motorista extends Usuario {
    private Veiculo veiculoDeTransporte;
    private LinkedList passageiros;
    private String regiao;
    private LinkedList horarios;
    private int id_motorista;    

    public int getId_motorista() {
        return id_motorista;
    }

    public void setId_motorista(int id) {
        this.id_motorista = id;
    }

    public Motorista() {
    }

    public Motorista(Veiculo veiculoDeTransporte, String regiao) {
        this.veiculoDeTransporte = veiculoDeTransporte;
        this.regiao = regiao;
    }
    
    public void addPassageiro (Passageiro passageiro) {
        
    }
    
    public void removePassageiro (Passageiro passageiro) {
        
    }
    
    public void addHorario (HorarioMotorista horario) {
        
    }
    
    public void gerarRota (HorarioMotorista horario) {
        
    }
    
    public void mostrarHorarios (){
        
    }
    
    public void buscarPassageiro (Passageiro passageiro) {
        
    }
    
    public void buscarPassageiro (HorarioMotorista horario) {
        
    }
    
    public void buscarPassageiro (Instituicao instituicao) {
        
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

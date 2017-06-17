package codigos;

public class Horario {
    private boolean tipo;
    private Hora horaInicio;
    private Hora hotaFinal;

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public Hora getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Hora horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Hora getHotaFinal() {
        return hotaFinal;
    }

    public void setHotaFinal(Hora hotaFinal) {
        this.hotaFinal = hotaFinal;
    }
    
    
}

package codigos;

import java.util.Date;

public class Horario {
    private boolean tipo;
    private Date horaInicio;
    private Date horaFinal;
    private int dia;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Horario() {
    }

    public Horario(boolean tipo, Date horaInicio, Date horaFinal, int dia) {
        this.tipo = tipo;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.dia = dia;
    }
    
    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public boolean isTipo() {
        return tipo;
    }

    public void setTipo(boolean tipo) {
        this.tipo = tipo;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(Date hotaFinal) {
        this.horaFinal = hotaFinal;
    }
    
    
}

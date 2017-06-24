package codigos;

import java.sql.Time;
import java.util.Date;

public class Horario {
    private boolean tipo;
    private Time horaInicio;
    private Time horaFinal;
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

    public Horario(boolean tipo, Time horaInicio, Time horaFinal, int dia) {
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

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Time getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(Time horaFinal) {
        this.horaFinal = horaFinal;
    }
    
    
}

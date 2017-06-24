/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigos;

import java.sql.Time;
import java.util.Iterator;
import java.util.LinkedList;
import model.dao.HorarioDAO;
import model.dao.Mot_passDAO;
import model.dao.MotoristaDAO;
import model.dao.PassageiroDAO;
import model.dao.UsuarioDAO;
import model.dao.VeiculoDAO;

public class teste {

    public static void main(String[] args) {
        Horario h = new Horario();
        java.sql.Time t = java.sql.Time.valueOf("08:30:00");
        java.sql.Time t1 = java.sql.Time.valueOf("10:45:30");
        h.setHoraInicio(t);
        h.setHoraFinal(t1);
        h.setDia(2);
        h.setTipo(true);
        HorarioDAO hdao = new HorarioDAO();
        hdao.criar(h);
        h = hdao.buscar(1);
        System.out.println(h.getHoraInicio());
    }
    
}

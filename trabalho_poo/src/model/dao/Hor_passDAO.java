/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import codigos.Horario;
import codigos.HorarioPassageiro;
import codigos.Motorista;
import codigos.Passageiro;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JOptionPane;

public class Hor_passDAO {
   
    public void criar(int idHorMot, Passageiro p, boolean permanente){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO hor_pass (hor_mot, passageiro, confirmado, permanente)VALUES(?, ?, ?, ?)");
            stmt.setInt(1, idHorMot);
            stmt.setInt(2, p.getId_passageiro());
            stmt.setBoolean(3, false);
            stmt.setBoolean(4, permanente);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    public void excluir(int idHorMot, int id_passageiro){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM hor_pass WHERE hot_mot = ? AND passageiro = ?");
            stmt.setInt(1, idHorMot);
            stmt.setInt(2, id_passageiro);
            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir");
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
    }
    public void excluirTemporario(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;        
        try {
            stmt = con.prepareStatement("SELECT * FROM hor_pass WHERE permanente = ?");
            stmt.setBoolean(1, false);
            rs = stmt.executeQuery();
            while(rs.next()){
                Hor_passDAO hpdao = new Hor_passDAO();
                hpdao.excluir(rs.getInt("hor_mot"), rs.getInt("passageiro"));
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar no Banco de Dados: ", ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    public LinkedList mostrarHorarioPassageiro(int id_passageiro){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        LinkedList horarios = new LinkedList();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM hor_pass WHERE passageiro = ?");
            stmt.setInt(1, id_passageiro);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                HorarioDAO hdao = new HorarioDAO();
                Hor_motDAO hmdao = new Hor_motDAO();
                MotoristaDAO mdao = new MotoristaDAO();
                Horario h = new Horario();
                h = (hmdao.getHorario(rs.getInt("hor_mot")));
                HorarioPassageiro hp = new HorarioPassageiro();
                hp.setMotorista(hmdao.getMotorista(rs.getInt("hor_mot")));
                hp.setDia(h.getDia());
                hp.setHoraInicio(h.getHoraInicio());
                hp.setHoraFinal(h.getHoraFinal());
                hp.setTipo(h.isTipo());
                hp.setId(rs.getInt("id"));
                horarios.add(hp);
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar no Banco de Dados: ", ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return horarios;
    }
    public LinkedList passageirosHorario(int idHorMot){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        LinkedList passageiros = new LinkedList();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM hor_pass WHERE hor_mot = ?");
            stmt.setInt(1, idHorMot);
            rs = stmt.executeQuery();
            while(rs.next()){
                Passageiro p = new Passageiro();
                PassageiroDAO pdao = new PassageiroDAO();
                p = (pdao.buscar(rs.getInt("passageiro")));
                passageiros.add(p);
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar no Banco de Dados: ", ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return passageiros;
    }
    public LinkedList horariosParaConfirmar(int id_passageiro){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        LinkedList horarios = new LinkedList();
        try {
            stmt = con.prepareStatement("SELECT * FROM hor_pass WHERE passageiro = ?");
            stmt.setInt(1, id_passageiro);
            rs = stmt.executeQuery();
            while(rs.next()){
                Hor_motDAO hmdao = new Hor_motDAO();
                if(hmdao.estaAberto(rs.getInt("hor_mot")) == true){
                    Horario h = new Horario();
                    h = (hmdao.getHorario(rs.getInt("hor_mot")));
                    HorarioPassageiro hp = new HorarioPassageiro();
                    hp.setMotorista(hmdao.getMotorista(rs.getInt("hor_mot")));
                    hp.setDia(h.getDia());
                    hp.setHoraInicio(h.getHoraInicio());
                    hp.setHoraFinal(h.getHoraFinal());
                    hp.setTipo(h.isTipo());
                    hp.setId(rs.getInt("id"));
                    horarios.add(hp);
                }
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar no Banco de Dados: ", ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return horarios;
    }    
    public void confirmarHorario(int idHorMot, int id_passageiro){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE hor_pass SET confirmado = ? WHERE passageiro = ? and hor_mot = ?");
            stmt.setBoolean(1, true);
            stmt.setInt(2, id_passageiro);
            stmt.setInt(3, idHorMot);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Horário confirmado!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao confirmar horario");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    public void desconfirmarHorario(int idHorMot, int id_passageiro){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE hor_pass SET confirmado = ? WHERE passageiro = ? and hor_mot = ?");
            stmt.setBoolean(1, false);
            stmt.setInt(2, id_passageiro);
            stmt.setInt(3, idHorMot);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Horário desconfirmado!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao desconfirmar horario");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
}

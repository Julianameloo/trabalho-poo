/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import codigos.Horario;
import codigos.HorarioMotorista;
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
public class Hor_motDAO {
    public int criar(Horario h, Motorista m){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        int id = -1;
        try {
            stmt = con.prepareStatement("INSERT INTO hor_mot (horario, motorista, aberto)VALUES(?, ?, ?)");
            stmt.setInt(1, h.getId());
            stmt.setInt(2, m.getId_motorista());
            stmt.setBoolean(3, false);
            id = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return id;
    }
    public void excluir(int id_horario, int id_motorista){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM hor_mot WHERE horario = ? AND motorista = ?");
            stmt.setInt(1, id_horario);
            stmt.setInt(2, id_motorista);
            rs = stmt.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir");
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
    }
    public LinkedList mostrarHorarioMotorista(int id_motorista){ //retorna tipo HorarioMotorista
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        LinkedList horarios = new LinkedList();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM hor_mot WHERE motorista = ?");
            stmt.setInt(1, id_motorista);
            rs = stmt.executeQuery();
            while(rs.next()){
                HorarioDAO hdao = new HorarioDAO();
                HorarioMotorista hm = new HorarioMotorista();
                Horario h = new Horario();
                h = (hdao.buscar(rs.getInt("horario")));
                hm.setDia(h.getDia());
                hm.setHoraInicio(h.getHoraInicio());
                hm.setHoraFinal(h.getHoraFinal());
                hm.setTipo(h.isTipo());
                hm.setId(rs.getInt("id"));
                horarios.add(hm);
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar no Banco de Dados: ", ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return horarios;
    }
    
    public void abrirConfirmacao(int id_motorista, int id_horario){ //Pode colocar um botão tipo on/off, quando mudar pra on chama esse metodo, off o fechar
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE hor_mot SET aberto = ? WHERE motorista = ? and horario = ?");
            stmt.setBoolean(1, true);
            stmt.setInt(2, id_motorista);
            stmt.setInt(3, id_horario);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Horário aberto!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir horario");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    public void fecharConfirmacao(int id_motorista, int id_horario){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE hor_mot SET aberto = ? WHERE motorista = ? and horario = ?");
            stmt.setBoolean(1, false);
            stmt.setInt(2, id_motorista);
            stmt.setInt(3, id_horario);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Horário aberto!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir horario");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    public boolean estaAberto(int idHorMot){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("SELECT hor_mot WHERE id = ?");
            stmt.setInt(1, idHorMot);
            rs = stmt.executeQuery();
            if(rs.next()){
                return rs.getBoolean("aberto");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir horario");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return false;
    }
    public Motorista getMotorista(int idHorMot){
        Motorista m = new Motorista();
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("SELECT hor_mot WHERE id = ?");
            stmt.setInt(1, idHorMot);
            rs = stmt.executeQuery();
            MotoristaDAO mdao = new MotoristaDAO();
            m = mdao.buscar(rs.getInt("motorista"));
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir horario");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return m;
        
    }
    public Horario getHorario(int idHorMot){
        Horario h = new Horario();
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("SELECT hor_mot WHERE id = ?");
            stmt.setInt(1, idHorMot);
            rs = stmt.executeQuery();
            HorarioDAO hdao = new HorarioDAO();
            h = hdao.buscar(rs.getInt("horario"));
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir horario");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return h;
        
    }
    public LinkedList mostrarHorarioDisponivelMotorista(int id_motorista){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        LinkedList horarios = new LinkedList();
        LinkedList passageiros = new LinkedList();
        try {
            stmt = con.prepareStatement("SELECT * FROM hor_mot WHERE motorista = ?");
            stmt.setInt(1, id_motorista);
            rs = stmt.executeQuery();
            while(rs.next()){
                Hor_passDAO hpdao = new Hor_passDAO();
                passageiros = hpdao.passageirosHorario(rs.getInt("id"));
                MotoristaDAO mdao = new MotoristaDAO();
                if(passageiros.size() < mdao.buscar(id_motorista).getVeiculoDeTransporte().getCapacidade()){
                    HorarioDAO hdao = new HorarioDAO();
                    HorarioMotorista hm = new HorarioMotorista();
                    Horario h = new Horario();
                    h = (hdao.buscar(rs.getInt("horario")));
                    hm.setDia(h.getDia());
                    hm.setHoraInicio(h.getHoraInicio());
                    hm.setHoraFinal(h.getHoraFinal());
                    hm.setTipo(h.isTipo());
                    hm.setId(rs.getInt("id"));
                    horarios.add(hm);
                }
                
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar no Banco de Dados: ", ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return horarios;
    }
}

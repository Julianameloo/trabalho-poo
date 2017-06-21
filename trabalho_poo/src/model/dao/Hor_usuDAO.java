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
import java.util.LinkedList;
import javax.swing.JOptionPane;

public class Hor_usuDAO {
    public void criar(Horario h, Motorista m, Passageiro p, boolean permanente){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO hor_usu (horario, motorista, passageiro, confirmado, permanente, aberto)VALUES(?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, h.getId());
            stmt.setInt(2, m.getId_motorista());
            stmt.setInt(3, p.getId_passageiro());
            stmt.setBoolean(4, false);
            stmt.setBoolean(5, permanente);
            stmt.setBoolean(6, false);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastro feito com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    public void excluir(int id_horario, int id_motorista, int id_passageiro){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("SELECT FROM hor_usu WHERE horario = ? AND motorista = ? AND passageiro = ?");
            stmt.setInt(1, id_horario);
            stmt.setInt(2, id_motorista);
            stmt.setInt(3, id_passageiro);
            rs = stmt.executeQuery();
            
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
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
            stmt = con.prepareStatement("SELECT * FROM hor_usu WHERE permanente = ?");
            stmt.setBoolean(1, false);
            rs = stmt.executeQuery();
            while(rs.next()){
                Hor_usuDAO hudao = new Hor_usuDAO();
                hudao.excluir(rs.getInt("horario"), rs.getInt("motorista"), rs.getInt("passageiro"));
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar no Banco de Dados: ", ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    public LinkedList mostrarHorarioMotorista(int id_motorista){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        LinkedList horarios = new LinkedList();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM hor_usu WHERE motorista = ?");
            stmt.setInt(1, id_motorista);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                HorarioDAO hdao = new HorarioDAO();
                Hor_usuDAO hudao = new Hor_usuDAO();
                Horario h = new Horario();
                h = (hdao.buscar(rs.getInt("horario")));
                HorarioMotorista hm = new HorarioMotorista();
                hm.setPassageiros(hudao.passageirosHorario(id_motorista, h.getId()));
                horarios.add(hm);
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar no Banco de Dados: ", ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return horarios;
    }
    public LinkedList passageirosHorario(int id_motorista, int id_horario){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        LinkedList passageiros = new LinkedList();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM hor_usu WHERE motorista = ? and horario = ?");
            stmt.setInt(1, id_motorista);
            stmt.setInt(1, id_horario);
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
    public LinkedList mostrarHorarioPassageiro(int id_passageiro){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        LinkedList horarios = new LinkedList();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM hor_usu WHERE passageiro = ?");
            stmt.setInt(1, id_passageiro);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                HorarioDAO hdao = new HorarioDAO();
                MotoristaDAO mdao = new MotoristaDAO();
                Horario h = new Horario();
                h = (hdao.buscar(rs.getInt("horario")));
                HorarioPassageiro hp = new HorarioPassageiro();
                hp.setMotorista(mdao.buscar(rs.getInt("motorista")));
                horarios.add(hp);
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar no Banco de Dados: ", ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return horarios;
    }
    public void abrirConfirmacao(int id_motorista, int id_horario){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE hor_usu SET confirmado = ?, aberto = ? WHERE motorista = ? and horario = ?");
            stmt.setBoolean(1, false);
            stmt.setBoolean(2, true);
            stmt.setInt(3, id_motorista);
            stmt.setInt(4, id_horario);
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
            stmt = con.prepareStatement("UPDATE hor_usu SET aberto = ? WHERE motorista = ? and horario = ?");
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
    public LinkedList horariosParaConfirmar(int id_passageiro){
    Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        LinkedList horarios = new LinkedList();
        try {
            stmt = con.prepareStatement("SELECT * FROM hor_usu WHERE passageiro = ? and aberto = ?");
            stmt.setInt(1, id_passageiro);
            stmt.setBoolean(1, true);
            rs = stmt.executeQuery();
            while(rs.next()){
                HorarioDAO hdao = new HorarioDAO();
                MotoristaDAO mdao = new MotoristaDAO();
                Horario h = new Horario();
                h = (hdao.buscar(rs.getInt("horario")));
                HorarioPassageiro hp = new HorarioPassageiro();
                hp.setMotorista(mdao.buscar(rs.getInt("motorista")));
                horarios.add(hp);
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar no Banco de Dados: ", ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return horarios;
    }    
    public void confirmarHorario(int id_horario, int id_motorista, int id_passageiro){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE hor_usu SET confirmado = ? WHERE passageiro = ? and horario = ? and motorista = ?");
            stmt.setBoolean(1, true);
            stmt.setInt(2, id_motorista);
            stmt.setInt(3, id_horario);
            stmt.setInt(4, id_passageiro);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Horário aberto!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir horario");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    public void desconfirmarHorario(int id_horario, int id_motorista, int id_passageiro){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE hor_usu SET confirmado = ? WHERE passageiro = ? and horario = ? and motorista = ?");
            stmt.setBoolean(1, false);
            stmt.setInt(2, id_motorista);
            stmt.setInt(3, id_horario);
            stmt.setInt(4, id_passageiro);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Horário aberto!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir horario");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    
}

package model.dao;

import codigos.Horario;
import codigos.HorarioMotorista;
import codigos.Motorista;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Hor_motDAO {
    public int criar(Horario h, Motorista m){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        int id = -1;
        try {
            stmt = con.prepareStatement("INSERT INTO hor_mot (horario, motorista, aberto)VALUES(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, h.getId());
            stmt.setInt(2, m.getId_motorista());
            stmt.setBoolean(3, false);
            id = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()){
                id = rs.getInt(1);
            }
            JOptionPane.showMessageDialog(null, "Horário cadastrado!");
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
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Horário excluído!");
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
                Horario h;
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
    
    public int getIdHor (int id_usuario){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = -1;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM hor_mot WHERE id = ?");
            stmt.setInt(1, id_usuario);
            rs = stmt.executeQuery();
            
            if(rs.next()){
                id = rs.getInt("horario");
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar no Banco de Dados: ", ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return id;
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
    public LinkedList fecharConfirmacao(int id_motorista, int id_horario, int idHorMot){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        LinkedList confirmados = new LinkedList();
        try {
            stmt = con.prepareStatement("UPDATE hor_mot SET aberto = ? WHERE motorista = ? and horario = ?");
            stmt.setBoolean(1, false);
            stmt.setInt(2, id_motorista);
            stmt.setInt(3, id_horario);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Horário fechado!");
            Hor_passDAO hpdao = new Hor_passDAO();
            Hor_motDAO hmdao = new Hor_motDAO();
            confirmados = hpdao.passageirosHorarioConfirmados(idHorMot);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir horario");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return confirmados;
    }
    public boolean estaAberto(int idHorMot){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs;
        try {
            stmt = con.prepareStatement("SELECT * FROM hor_mot WHERE id = ?");
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
    public int getIdHorMot (int id_usuario){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = -1;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM hor_pass WHERE id = ?");
            stmt.setInt(1, id_usuario);
            rs = stmt.executeQuery();
            
            if(rs.next()){
                id = rs.getInt("hor_mot");
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar no Banco de Dados: ", ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return id;
    }
    public Motorista getMotorista(int idHorMot){
        Motorista m = new Motorista();
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs;
        try {
            stmt = con.prepareStatement("SELECT * FROM hor_mot WHERE id = ?");
            stmt.setInt(1, idHorMot);
            rs = stmt.executeQuery();
            if(rs.next()){
                MotoristaDAO mdao = new MotoristaDAO();
                m = mdao.buscar(rs.getInt("motorista"));
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar motorista: ");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return m;
        
    }
    public Horario getHorario(int idHorMot){
        Horario h = new Horario();
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs;
        try {
            stmt = con.prepareStatement("SELECT * FROM hor_mot WHERE id = ?");
            stmt.setInt(1, idHorMot);
            rs = stmt.executeQuery();
            if(rs.next()){
                HorarioDAO hdao = new HorarioDAO();
                h = hdao.buscar(rs.getInt("horario"));
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar horario");
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
        LinkedList passageiros;
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
                    Horario h;
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

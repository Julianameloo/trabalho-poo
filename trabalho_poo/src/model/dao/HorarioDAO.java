/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import codigos.Horario;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import javax.swing.JOptionPane;

public class HorarioDAO {
    public void criar(Horario h){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO horario (tipo, horaInicio, horaFinal, dia)VALUES(?, ?, ?, ?)");
            stmt.setBoolean(1, h.isTipo());
            stmt.setTime(2, (Time) h.getHoraInicio());
            stmt.setTime(3, (Time) h.getHoraFinal());
            stmt.setInt(4, h.getDia());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastro feito com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    
    public void atualizar(Horario h){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE horario SET tipo = ?, horaInicio = ?, horaFinal = ?, dia = ? WHERE  id = ?");
            stmt.setBoolean(1, h.isTipo());
            stmt.setTime(2, (Time) h.getHoraInicio());
            stmt.setTime(3, (Time) h.getHoraFinal());
            stmt.setInt(4, h.getDia());
            stmt.setInt(5, h.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualização feita com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    public void excluir(Horario h){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM horario WHERE  id = ?");
            stmt.setInt(1, h.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
}

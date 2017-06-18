/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import codigos.Horario;
import codigos.Motorista;
import codigos.Passageiro;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Hor_usuDAO {
    public void criar(Horario h, Motorista m, Passageiro p, boolean permanente){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO hor_usu (horario, motorista, passageiro, confirmado, permanente)VALUES(?, ?, ?, ?, ?)");
            stmt.setInt(1, h.getId());
            stmt.setInt(2, m.getId_motorista());
            stmt.setInt(3, p.getId_passageiro());
            stmt.setBoolean(4, false);
            stmt.setBoolean(5, permanente);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastro feito com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    public void excluir(Horario h, Motorista m, Passageiro p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement("SELECT FROM hor_usu WHERE horario = ? AND motorista = ? AND passageiro = ?");
            stmt.setInt(1, h.getId());
            stmt.setInt(2, m.getId_motorista());
            stmt.setInt(3, p.getId_passageiro());
            rs = stmt.executeQuery();
            
            JOptionPane.showMessageDialog(null, "Cadastro feito com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar");
        } finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
    }
}

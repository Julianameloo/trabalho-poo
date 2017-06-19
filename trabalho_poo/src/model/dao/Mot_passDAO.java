/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import codigos.Motorista;
import codigos.Passageiro;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;

public class Mot_passDAO {
    public void criar(Motorista m, Passageiro p, boolean permanente){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO mot_pass (motorista, passageiro, permanente)VALUES(?, ?, ?)");
            stmt.setInt(1, m.getId_motorista());
            stmt.setInt(2, p.getId_passageiro());
            stmt.setBoolean(3, permanente);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastro feito com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        } 
    }
    public void confirma(int id_motorista, int id_passageiro){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = -1;
        try {
            stmt = con.prepareStatement("SELECT * FROM mot_pass WHERE motorista = ? and passageiro = ?");
            stmt.setInt(1, id_motorista);
            stmt.setInt(2, id_passageiro);
            rs = stmt.executeQuery();
            if(rs.next()){
                id = rs.getInt("id");
            }
            if(id != -1){
                stmt = con.prepareStatement("UPDATE mot_pass SET permanente = ? WHERE id = ?");
                stmt.setBoolean(1, true);
                stmt.setInt(2, id);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Atualização feita com sucesso!");
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar no Banco de Dados: ", ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    public LinkedList solicitacao(int id_motorista){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        LinkedList passageiros = new LinkedList();
     
        try {
            stmt = con.prepareStatement("SELECT * FROM mot_pass WHERE motorista = ? and permanente = ?");
            stmt.setInt(1, id_motorista);
            stmt.setInt(2, 0);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                passageiros.add(rs.getInt("passageiro"));
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar no Banco de Dados: ", ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return passageiros;
    }
}

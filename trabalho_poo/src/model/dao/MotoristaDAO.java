/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import codigos.Motorista;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class MotoristaDAO {
    public void criar(Motorista m){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO motorista (usuario, regiao)VALUES(?, ?)");
            stmt.setInt(1, m.getId());
            stmt.setString(2, m.getRegiao());
            stmt.executeUpdate();
           JOptionPane.showMessageDialog(null, "Cadastro feito com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    public void atualizar(Motorista m){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE motorista SET regiao = ? WHERE id = ?");
            stmt.setInt(2, m.getId());
            stmt.setString(1, m.getRegiao());
            stmt.executeUpdate();
           JOptionPane.showMessageDialog(null, "Atualização feita com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    public void excluir(Motorista m){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM motorista WHERE id = ?)");
            stmt.setInt(1, m.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    public boolean verificaMotorista(int id_usuario){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean verifica = false;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM motorista WHERE usuario = ?");
            stmt.setInt(1, id_usuario);
            rs = stmt.executeQuery();
            
            if(rs.next()){
                verifica = true;
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar no Banco de Dados: ", ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return verifica;
    }
}

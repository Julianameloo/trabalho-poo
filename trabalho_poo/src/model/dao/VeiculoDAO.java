/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import codigos.Veiculo;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author lasaz_000
 */
public class VeiculoDAO {
    public void criar(Veiculo v){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO veiculo (modelo, placa, capacidade)VALUES(?, ?, ?)");
            stmt.setString(1, v.getModelo());
            stmt.setString(2, v.getPlaca());
            stmt.setInt(3, v.getCapacidade());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar");
            throw new RuntimeException("Falha ao cadastrar: ", ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        } 
    }
    public int buscarPlaca(String placa){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = -1;
        try {
            stmt = con.prepareStatement("SELECT * FROM veiculo WHERE placa = ?");
            stmt.setString(1, placa);
            rs = stmt.executeQuery();
            
            if(rs.next()){
                id = rs.getInt("id");
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar no Banco de Dados: ", ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return id;
    }
    
    public void atualizar(Veiculo v){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE veiculo SET modelo = ?, placa = ?, capacidade = ? WHERE id = ?");
            stmt.setString(1, v.getModelo());
            stmt.setString(2, v.getPlaca());
            stmt.setInt(3, v.getCapacidade());
            stmt.setInt(4, v.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualização feita com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    public void excluir(Veiculo v){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM veiculo WHERE id = ?");
            stmt.setInt(1, v.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    
    public Veiculo buscar(int id){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Veiculo v = new Veiculo();
        v.setId(-1);
        try {
            stmt = con.prepareStatement("SELECT * FROM veiculo WHERE id = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if(rs.next()){
                v.setId(rs.getInt("id"));
                v.setModelo(rs.getString("modelo"));
                v.setPlaca(rs.getString("placa"));
                v.setCapacidade(rs.getInt("capacidade"));
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar no Banco de Dados: ", ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return v;
    }
}

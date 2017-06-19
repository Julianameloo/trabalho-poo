/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import codigos.Motorista;
import codigos.Veiculo;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;

public class Mot_veiDAO {
    public void criar(Motorista m, Veiculo v){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO mot_vei (motorista, veiculo)VALUES(?, ?)");
            stmt.setInt(1, m.getId_motorista());
            stmt.setInt(2, v.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastro feito com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public LinkedList veiculos(int id_motorista){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        LinkedList veiculos = new LinkedList();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM mot_vei WHERE motorista = ?");
            stmt.setInt(1, id_motorista);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                VeiculoDAO vdao = new VeiculoDAO();
                Veiculo v = new Veiculo();
                v = (vdao.buscar(rs.getInt("veiculo")));
                veiculos.add(v);
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar no Banco de Dados: ", ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return veiculos;
    }
}

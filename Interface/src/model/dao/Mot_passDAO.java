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
        Mot_passDAO mpdao = new Mot_passDAO();
        if(mpdao.valida(m.getId_motorista(), p.getId_passageiro()) != false){
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
        else
            JOptionPane.showMessageDialog(null, "Já cadastrado");
        
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
    public LinkedList solicitacoes(int id_motorista){
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
                PassageiroDAO pdao = new PassageiroDAO();
                passageiros.add(pdao.buscar(rs.getInt("passageiro")));
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar no Banco de Dados: ", ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return passageiros;
    }
    public LinkedList passageiros(int id_motorista){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        LinkedList passageiros = new LinkedList();
     
        try {
            stmt = con.prepareStatement("SELECT * FROM mot_pass WHERE motorista = ?");
            stmt.setInt(1, id_motorista);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                PassageiroDAO pdao = new PassageiroDAO();
                passageiros.add(pdao.buscar(rs.getInt("passageiro")));
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar no Banco de Dados: ", ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return passageiros;
    }
    public LinkedList motoristas(int id_passageiro){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        LinkedList motoristas = new LinkedList();
     
        try {
            stmt = con.prepareStatement("SELECT * FROM mot_pass WHERE passageiro = ?");
            stmt.setInt(1, id_passageiro);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                MotoristaDAO mdao = new MotoristaDAO();
                motoristas.add(mdao.buscar(rs.getInt("motorista")));
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar no Banco de Dados: ", ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return motoristas;
    }
    public boolean valida(int id_motorista, int id_passageiro){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean v = true;
        try {
            stmt = con.prepareStatement("SELECT * FROM mot_pass WHERE motorista = ? and passageiro = ?");
            stmt.setInt(1, id_motorista);
            stmt.setInt(2, id_passageiro);
            rs = stmt.executeQuery();
            
            if(rs.next()){
                v = false;
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar no Banco de Dados: ", ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return v;
    }
}

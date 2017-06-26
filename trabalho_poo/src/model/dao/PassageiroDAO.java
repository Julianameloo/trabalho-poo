/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import codigos.Passageiro;
import codigos.Usuario;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class PassageiroDAO {
    public void criar(Passageiro p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO passageiro (usuario)VALUES(?)");
            stmt.setInt(1, p.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastro feito com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    public void excluir(Passageiro p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM passageiro WHERE id = ?");
            stmt.setInt(1, p.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir");
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }   
    }
    public int verificaPassageiro(int id_usuario){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = -1;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM passageiro WHERE usuario = ?");
            stmt.setInt(1, id_usuario);
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
    public Passageiro buscar(int id){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        UsuarioDAO udao = new UsuarioDAO();
        Usuario u = new Usuario();
        Passageiro p = new Passageiro();
        p.setId_passageiro(-1);
        try {
            stmt = con.prepareStatement("SELECT * FROM passageiro WHERE id = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if(rs.next()){
                p.setId_passageiro(rs.getInt("id"));
                u = udao.buscar(rs.getInt("usuario"));
                p.setId(u.getId());
                p.setCep(u.getCep());
                p.setCpf(u.getCpf());
                p.setDataDeNascimento(u.getDataDeNascimento());
                p.setEnderecoResidencia(u.getEnderecoResidencia());
                p.setLogin(u.getLogin());
                p.setNome(u.getNome());
                p.setSenha(u.getSenha());
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar no Banco de Dados: ", ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return p;
    }
    
}

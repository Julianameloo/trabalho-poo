package model.dao;

import codigos.Usuario;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import javax.swing.JOptionPane;

public class UsuarioDAO {
    public int criar(Usuario u){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        int id = -1;
        try {
            stmt = con.prepareStatement("INSERT INTO usuario (nome, login, senha, cpf, cep, enderecoResidencia, dataDeNascimento)VALUES(?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getLogin());
            stmt.setString(3, u.getSenha());
            stmt.setString(4, u.getCpf());
            stmt.setString(5, u.getCep());
            stmt.setString(6, u.getEnderecoResidencia());
            java.sql.Date datesql = new java.sql.Date(u.getDataDeNascimento().getTime());
            stmt.setDate(7, datesql);
            id = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar");           
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return id;
    }
    public LinkedList buscar(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        LinkedList usuarios = new LinkedList();
     
        try {
            stmt = con.prepareStatement("SELECT * FROM usuario");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setCep(rs.getString("cep"));
                u.setCpf(rs.getString("cpf"));
                u.setDataDeNascimento(rs.getDate("dataDeNascimento"));
                u.setEnderecoResidencia(rs.getString("enderecoResidencia"));
                u.setLogin(rs.getString("login"));
                u.setNome(rs.getString("nome"));
                u.setSenha(rs.getString("senha"));
                usuarios.add(u);
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar no Banco de Dados: ", ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return usuarios;
    }
    
    public LinkedList buscar(String nome){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        LinkedList usuarios = new LinkedList();
     
        try {
            stmt = con.prepareStatement("SELECT * FROM usuario WHERE nome = ?");
            stmt.setString(1, nome);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setCep(rs.getString("cep"));
                u.setCpf(rs.getString("cpf"));
                u.setDataDeNascimento(rs.getDate("dataDeNascimento"));
                u.setEnderecoResidencia(rs.getString("enderecoResidencia"));
                u.setLogin(rs.getString("login"));
                u.setNome(rs.getString("nome"));
                u.setSenha(rs.getString("senha"));
                usuarios.add(u);
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar no Banco de Dados: ", ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return usuarios;
    }
    
    public Usuario buscar(int id){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario u = new Usuario();
        u.setId(-1);
        try {
            stmt = con.prepareStatement("SELECT * FROM usuario WHERE id = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            
            if(rs.next()){
                u.setId(rs.getInt("id"));
                u.setCep(rs.getString("cep"));
                u.setCpf(rs.getString("cpf"));
                u.setDataDeNascimento(rs.getDate("dataDeNascimento"));
                u.setEnderecoResidencia(rs.getString("enderecoResidencia"));
                u.setLogin(rs.getString("login"));
                u.setNome(rs.getString("nome"));
                u.setSenha(rs.getString("senha"));
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar no Banco de Dados: ", ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return u;
    }
    
    public int buscarLogin(String login){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = -1;
        try {
            stmt = con.prepareStatement("SELECT * FROM usuario WHERE login = ?");
            stmt.setString(1, login);
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
    
    public void atualizar(Usuario u){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE usuario SET nome = ?, senha = ?, cpf = ?, cep = ?, enderecoResidencia = ?, dataDeNascimento = ? WHERE id = ?");
            stmt.setString(1, u.getNome());
            stmt.setString(2, u.getSenha());
            stmt.setString(3, u.getCpf());
            stmt.setString(4, u.getCep());
            stmt.setString(5, u.getEnderecoResidencia());
            stmt.setDate(6, (java.sql.Date) u.getDataDeNascimento());
            stmt.setInt(7, u.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualização feita com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar");
            throw new RuntimeException("Falha ao cadastrar: ", ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    public void excluir(Usuario u){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM usuario WHERE id = ?");
            stmt.setInt(1, u.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    public int verificarSenha(String login, String senha){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = -1;
        try {
            stmt = con.prepareStatement("SELECT * FROM usuario WHERE login = ? and senha = ?");
            stmt.setString(1, login);
            stmt.setString(2, senha);
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
}

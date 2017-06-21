/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import codigos.Motorista;
import codigos.Usuario;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
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
            throw new RuntimeException("Falha ao cadastrar: ", ex);
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
    public int verificaMotorista(int id_usuario){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int id = -1;
        
        try {
            stmt = con.prepareStatement("SELECT * FROM motorista WHERE usuario = ?");
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
    public Motorista buscar(int id){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        UsuarioDAO udao = new UsuarioDAO();
        Usuario u = new Usuario();
        Motorista m = new Motorista();
        m.setId_motorista(-1);
        try {
            stmt = con.prepareStatement("SELECT * FROM motorista WHERE id = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if(rs.next()){
                m.setId_motorista(rs.getInt("id"));
                m.setRegiao(rs.getString("regiao"));
                u = udao.buscar(rs.getInt("usuario"));
                m.setId(u.getId());
                m.setCep(u.getCep());
                m.setCpf(u.getCpf());
                m.setDataDeNascimento(u.getDataDeNascimento());
                m.setEnderecoResidencia(u.getEnderecoResidencia());
                m.setLogin(u.getLogin());
                m.setNome(u.getNome());
                m.setSenha(u.getSenha());
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar no Banco de Dados: ", ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return m;
    }
    public LinkedList buscar(String nome){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        UsuarioDAO udao = new UsuarioDAO();
        LinkedList usuarios = new LinkedList();
        LinkedList motoristas = new LinkedList();
        usuarios = udao.buscar(nome);
        Iterator i = usuarios.iterator();
        while(i.hasNext()){
            try {
                
                Usuario u = new Usuario();
                u = (Usuario) i.next();
                stmt = con.prepareStatement("SELECT * FROM motorista WHERE usuario = ?");
                stmt.setInt(1, u.getId());
                rs = stmt.executeQuery();
                if(rs.next()){
                    Motorista m = new Motorista();
                    MotoristaDAO mdao = new MotoristaDAO();
                    m = mdao.buscar(rs.getInt("id"));
                    motoristas.add(m);
                }

            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao buscar no Banco de Dados: ", ex);
            }finally{
                ConnectionFactory.closeConnection(con, stmt, rs);
            }
        }
        return motoristas;
    }
}

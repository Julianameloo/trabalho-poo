
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import codigos.Mensagem;
import codigos.Usuario;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;

public class MensagemDAO {
    public void criar(Mensagem m){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO mensagem (conteudo, destinatario, remetente, dataHora)VALUES(?, ?, ?, now())");
            stmt.setString(1, m.getConteudo());
            stmt.setInt(2, m.getDestinatario().getId());
            stmt.setInt(3, m.getRemetente().getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastro feito com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        } 
    }
    public LinkedList msgRecebidas(Usuario u){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        LinkedList mensagens = new LinkedList();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM mensagem WHERE destinatario = ?");
            stmt.setInt(1, u.getId());
            rs = stmt.executeQuery();
            
            while(rs.next()){
                UsuarioDAO udao = new UsuarioDAO();
                Usuario remetente = new Usuario();
                Mensagem m = new Mensagem();
                m.setConteudo(rs.getString("conteudo"));
                m.setDestinatario(u);
                m.setDataHora(rs.getDate("dataHora"));
                m.setId(rs.getInt("id"));
                remetente = udao.buscar(rs.getInt("remetente"));
                m.setRemetente(remetente);
                mensagens.add(m);
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar no Banco de Dados: ", ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return mensagens;
    }
    public LinkedList msgEnviadas(Usuario u){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        LinkedList mensagens = new LinkedList();
        
        try {
            stmt = con.prepareStatement("SELECT * FROM mensagem WHERE remetente = ?");
            stmt.setInt(1, u.getId());
            rs = stmt.executeQuery();
            
            while(rs.next()){
                UsuarioDAO udao = new UsuarioDAO();
                Usuario destinatario = new Usuario();
                Mensagem m = new Mensagem();
                m.setConteudo(rs.getString("conteudo"));
                m.setRemetente(u);
                m.setDataHora(rs.getDate("dataHora"));
                m.setId(rs.getInt("id"));
                destinatario = udao.buscar(rs.getInt("destinatario"));
                m.setDestinatario(destinatario);
                mensagens.add(m);
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar no Banco de Dados: ", ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return mensagens;
    }
}

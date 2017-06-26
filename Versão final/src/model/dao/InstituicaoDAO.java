package model.dao;

import codigos.Instituicao;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class InstituicaoDAO {
    public void criar(Instituicao i){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO intituicao (nome, endereco, tipo)VALUES(?, ?, ?)");
            stmt.setString(1, i.getNome());
            stmt.setString(2, i.getEndereco());
            stmt.setString(3, i.getTipo());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastro feito com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }  
    public void atualizar(Instituicao i){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE intituicao SET nome = ?, endereco = ?, tipo = ? WHERE id = ?");
            stmt.setString(1, i.getNome());
            stmt.setString(2, i.getEndereco());
            stmt.setString(3, i.getTipo());
            stmt.setInt(4, i.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualização feita com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    public void excluir(Instituicao i){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM intituicao WHERE id = ?");
            stmt.setInt(1, i.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    public Instituicao buscar(int id){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Instituicao i = new Instituicao();
        i.setId(-1);
        try {
            stmt = con.prepareStatement("SELECT * FROM instituicao WHERE id = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if(rs.next()){
                i.setId(rs.getInt("id"));
                i.setNome(rs.getString("nome"));
                i.setEndereco(rs.getString("endereco"));
                i.setTipo(rs.getString("tipo"));
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar no Banco de Dados: ", ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return i;
    }
}

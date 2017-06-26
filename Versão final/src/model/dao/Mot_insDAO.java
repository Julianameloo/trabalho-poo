package model.dao;

import codigos.Instituicao;
import codigos.Motorista;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;

public class Mot_insDAO {
    public void criar(Motorista m, Instituicao i){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO mot_ins (motorista, instituicao)VALUES(?, ?)");
            stmt.setInt(1, m.getId_motorista());
            stmt.setInt(2, i.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastro feito com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    public LinkedList instituicoes(int id_motorista){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        LinkedList instituicoes = new LinkedList();
        try {
            stmt = con.prepareStatement("SELECT * FROM mot_ins WHERE motorista = ?");
            stmt.setInt(1, id_motorista);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                InstituicaoDAO idao = new InstituicaoDAO();
                Instituicao i;
                i = (idao.buscar(rs.getInt("instituicao")));
                instituicoes.add(i);
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar no Banco de Dados: ", ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return instituicoes;
    }
}

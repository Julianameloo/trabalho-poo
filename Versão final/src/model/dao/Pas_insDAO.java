package model.dao;

import codigos.Instituicao;
import codigos.Passageiro;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.JOptionPane;

public class Pas_insDAO {
    public void criar(Passageiro p, Instituicao i){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO pas_ins (passageiro, instituicao)VALUES(?, ?)");
            stmt.setInt(1, p.getId_passageiro());
            stmt.setInt(2, i.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastro feito com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    public LinkedList instituicoes(int id_passageiro){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        LinkedList instituicoes = new LinkedList();
        try {
            stmt = con.prepareStatement("SELECT * FROM pas_ins WHERE passageiro = ?");
            stmt.setInt(1, id_passageiro);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                InstituicaoDAO idao = new InstituicaoDAO();
                Instituicao i = new Instituicao();
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

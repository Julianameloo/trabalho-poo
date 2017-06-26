package model.dao;

import codigos.Horario;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import javax.swing.JOptionPane;

public class HorarioDAO {
    public int criar(Horario h){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        int id = -1;
        try {
            stmt = con.prepareStatement("INSERT INTO horario (tipo, horaInicio, horaFinal, dia)VALUES(?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS);
            stmt.setBoolean(1, h.isTipo());
            stmt.setTime(2, (Time) h.getHoraInicio());
            stmt.setTime(3, (Time) h.getHoraFinal());
            stmt.setInt(4, h.getDia());
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
    public void atualizar(Horario h){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE horario SET tipo = ?, horaInicio = ?, horaFinal = ?, dia = ? WHERE  id = ?");
            stmt.setBoolean(1, h.isTipo());
            stmt.setTime(2, h.getHoraInicio());
            stmt.setTime(3, h.getHoraFinal());
            stmt.setInt(4, h.getDia());
            stmt.setInt(5, h.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualização feita com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    public void excluir(Horario h){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM horario WHERE  id = ?");
            stmt.setInt(1, h.getId());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir");
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        
    }
    public Horario buscar(int id){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Horario h = new Horario();
        h.setId(-1);
        try {
            stmt = con.prepareStatement("SELECT * FROM horario WHERE id = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if(rs.next()){
                h.setId(rs.getInt("id"));
                h.setDia(rs.getInt("dia"));
                h.setHoraFinal(rs.getTime("horaFinal"));
                h.setHoraInicio(rs.getTime("horaInicio"));
                h.setTipo(rs.getBoolean("tipo"));
            }
            
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar no Banco de Dados: ", ex);
            
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return h;
    }
}

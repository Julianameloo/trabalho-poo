/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigos;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



import model.dao.Hor_motDAO;
import model.dao.HorarioDAO;
import model.dao.MotoristaDAO;
import model.dao.PassageiroDAO;

public class teste {

    public static void main(String[] args) {
        Document document = new Document();
        Passageiro p = new Passageiro();
        PassageiroDAO pdao = new PassageiroDAO();
        Motorista m = new Motorista();
        MotoristaDAO mdao = new MotoristaDAO();
        p = pdao.buscar(1);
        m = mdao.buscar(37);
        String nome = p.getNome() + ".pdf";
        try {
            
            PdfWriter.getInstance(document, new FileOutputStream(nome));
            document.open();
            document.add(new Paragraph("Pelo presente instrumento particular de contrato de prestação de serviços de Transporte Escolar, de um lado o(a) Sr(a) " + m.getNome() + " CPF " + m.getCpf() + ", motorista de passageiros, residente em " + m.getEnderecoResidencia() + ", denominado CONTRATADO, e de outro o(a) Sr(a) " + p.getNome() + " CPF " + p.getCpf() + " , passageiro(a), residente em " + p.getEnderecoResidencia() + ", doravante denominado CONTRATANTE, tem entre si justo e contratado o seguinte: " + "O CONTRATADO compromete-se a transportar o(a) passageiro(a) cadastrado acima " + "de sua residência à sua Instituição de Ensino e vice-versa, durante o período normal de aulas do semestre letivo com exclusão de domingos ou feriados." + "\n\n\n\n" + m.getNome() + "\nCONTRATADO\n\n\n" + p.getNome() + "\nCONTRATANTE\n\n\nDATA"));
            
        } catch (FileNotFoundException | DocumentException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir documento");
        } finally{
            document.close();
        }
        try {
            Desktop.getDesktop().open(new File(nome));
        } catch (IOException ex) {
            Logger.getLogger(teste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

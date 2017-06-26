package views;

import codigos.Mensagem;
import codigos.Motorista;
import codigos.Passageiro;
import codigos.Usuario;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.DocumentFilter;
import model.dao.MensagemDAO;
import model.dao.MotoristaDAO;
import model.dao.PassageiroDAO;
import model.dao.UsuarioDAO;

public class NovaMensagemMotorista extends javax.swing.JFrame {
    telaPassageiro tp;
    int motoristaID;
    private DefaultStyledDocument doc;
    
    public NovaMensagemMotorista() {
        initComponents();
        
        doc = new DefaultStyledDocument();
        
        doc.setDocumentFilter(new DocumentSizeFilter(200));
        
        doc.addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                updateCount();
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateCount();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                updateCount();
            }
        });
        
        mensagem.setDocument(doc);
        updateCount();
        pack();
    }

    private void updateCount() {
        remaningLabel.setText((200 - doc.getLength()) + " caracteres restantes");
    }

        class DocumentSizeFilter extends DocumentFilter {

        int maxCharacters;
        boolean DEBUG = false;

        public DocumentSizeFilter(int maxChars) {
            maxCharacters = maxChars;
        }

        @Override
        public void insertString(DocumentFilter.FilterBypass fb, int offs,
                String str, AttributeSet a)
                throws BadLocationException {
            if (DEBUG) {
                System.out.println("in DocumentSizeFilter's insertString method");
            }

            
            if((fb.getDocument().getLength() + str.length()) <= maxCharacters) {
                super.insertString(fb, offs, str, a);
            } else {
                Toolkit.getDefaultToolkit().beep();
            }
        }

        public void replace(DocumentFilter.FilterBypass fb, int offs,
                int length,
                String str, AttributeSet a)
                throws BadLocationException {
            if (DEBUG) {
                System.out.println("in DocumentSizeFilter's replace method");
            }
           
            if((fb.getDocument().getLength() + str.length() - length) <= maxCharacters) {
                super.replace(fb, offs, length, str, a);
            } else {
                Toolkit.getDefaultToolkit().beep();
            }
        }

    }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        enviar = new javax.swing.JButton();
        PassageirosComboBox = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        buscaNome = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        mensagem = new javax.swing.JTextArea();
        remaningLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(245, 248, 249));

        jLabel1.setText("Destinatário");

        jLabel2.setText("Mensagem");

        enviar.setText("Enviar");
        enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarActionPerformed(evt);
            }
        });

        jLabel3.setText("Nome");

        buscaNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buscaNomeKeyPressed(evt);
            }
        });

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        jLabel4.setText("Escolha dentre as opções de login");

        mensagem.setColumns(20);
        mensagem.setRows(5);
        jScrollPane2.setViewportView(mensagem);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(63, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(buscaNome, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1)
                            .addComponent(PassageirosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(enviar)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(remaningLabel)
                        .addGap(26, 26, 26)))
                .addContainerGap(158, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(40, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(PassageirosComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buscaNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(remaningLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(enviar)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 663, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void enviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarActionPerformed
        Mensagem m = new Mensagem();
        Motorista mot;
        MotoristaDAO mdao = new MotoristaDAO();
        mot = mdao.buscar(motoristaID);
        m.setDataHora(new Date(System.currentTimeMillis()));
        m.setConteudo(mensagem.getText());
        m.setDestinatario((Passageiro)PassageirosComboBox.getSelectedItem());
        m.setRemetente(mot);
        MensagemDAO mendao = new MensagemDAO();
        mendao.criar(m);
    }//GEN-LAST:event_enviarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            PassageirosComboBox.removeAllItems();
            UsuarioDAO udao = new UsuarioDAO();
            LinkedList ll = udao.buscar(buscaNome.getText());
            Iterator i = ll.iterator();
            if (!ll.isEmpty()) {
                PassageiroDAO p = new PassageiroDAO();
                Usuario u;
                while (i.hasNext()) {
                    u = (Usuario) i.next();
                    if (p.verificaPassageiro(u.getId()) != -1) {
                        PassageiroDAO pdao = new PassageiroDAO();
                        Passageiro pa;
                        int id = pdao.verificaPassageiro(u.getId());
                        pa = pdao.buscar(id);
                        PassageirosComboBox.addItem(pa);
                    }
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Passageiro não encontrado!");
            }
    }//GEN-LAST:event_jButton1ActionPerformed

     public void recebeID (int id) {
        this.motoristaID = id;
    }
     
    
    private void buscaNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscaNomeKeyPressed
        
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            PassageirosComboBox.removeAllItems();
            UsuarioDAO udao = new UsuarioDAO();
            LinkedList ll = udao.buscar(buscaNome.getText());
            Iterator i = ll.iterator();
            if (!ll.isEmpty()) {
                PassageiroDAO p = new PassageiroDAO();
                Usuario u;
                while (i.hasNext()) {
                    u = (Usuario) i.next();
                    if (p.verificaPassageiro(u.getId()) != -1) {
                        PassageiroDAO pdao = new PassageiroDAO();
                        Passageiro pa;
                        int id = pdao.verificaPassageiro(u.getId());
                        pa = pdao.buscar(id);
                        PassageirosComboBox.addItem(pa);
                    }
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Passageiro não encontrado!");
            }
        }
    }//GEN-LAST:event_buscaNomeKeyPressed
   
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NovaMensagemMotorista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NovaMensagemMotorista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NovaMensagemMotorista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NovaMensagemMotorista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NovaMensagemMotorista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Object> PassageirosComboBox;
    private javax.swing.JTextField buscaNome;
    private javax.swing.JButton enviar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea mensagem;
    private javax.swing.JLabel remaningLabel;
    // End of variables declaration//GEN-END:variables
}

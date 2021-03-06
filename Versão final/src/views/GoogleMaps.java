package views;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class GoogleMaps extends javax.swing.JFrame {

    int zoom = 12;
    String tipo = "roadmap";
    String rota;
    
    public GoogleMaps() {
        super("Rota");
        initComponents();
        int rotaChoice;
        Random gerador = new Random();
        rotaChoice = gerador.nextInt(5);
        switch (rotaChoice) {
            case 0:
                rota = "-16.668209,-49.244135|-16.670197,-49.250580|-16.663809,-49.260212";
                break;
            case 1:
                rota = "-16.687414,-49.254100|-16.679057,-49.244316|-16.675366,-49.257954|-16.672156,-49.257877|-16.664699,-49.261119";
                break;
            case 2:
                rota = "-16.687414,-49.254100|-16.668209,-49.244135|-16.675366,-49.257954|-16.663809,-49.260212|-16.672156,-49.257877|-16.664699,-49.261119";
                break;
            case 3:
                rota = "-16.678816,-49.253991|-16.673450,-49.248479|-16.669950,-49.248749|-16.672188,-49.254799|-16.667425,-49.253122";
                break;
            case 4:
                rota = "-16.685702,-49.254679|-16.678041,-49.247731|-16.673077,-49.247012|-16.666277,-49.244166";
                break;
            default:
                rota = "-16.683406,-49.257106|-16.674971,-49.248749|-16.672733,-49.246353|-16.669692,-49.249618";
                break;
        }
        atualizaMapa();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        radioSatelite = new javax.swing.JRadioButton();
        radioRuas = new javax.swing.JRadioButton();
        botaoZoomMenos = new javax.swing.JButton();
        botaoZoomMais = new javax.swing.JButton();
        mapaLable = new javax.swing.JLabel();
        radioHibrido = new javax.swing.JRadioButton();
        radioTerreno = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        buttonGroup1.add(radioSatelite);
        radioRuas.setSelected(true);
        radioSatelite.setText("Satelite");
        radioSatelite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioSateliteActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioRuas);
        radioRuas.setText("Ruas");
        radioRuas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioRuasActionPerformed(evt);
            }
        });

        botaoZoomMenos.setText("-");
        botaoZoomMenos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoZoomMenosActionPerformed(evt);
            }
        });

        botaoZoomMais.setText("+");
        botaoZoomMais.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoZoomMaisActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioHibrido);
        radioHibrido.setText("Híbrido");
        radioHibrido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioHibridoActionPerformed(evt);
            }
        });

        buttonGroup1.add(radioTerreno);
        radioTerreno.setText("Terreno");
        radioTerreno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioTerrenoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(radioRuas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioSatelite)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioHibrido)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radioTerreno)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 292, Short.MAX_VALUE)
                        .addComponent(botaoZoomMais)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoZoomMenos, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(mapaLable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoZoomMenos)
                    .addComponent(botaoZoomMais)
                    .addComponent(radioSatelite)
                    .addComponent(radioRuas)
                    .addComponent(radioHibrido)
                    .addComponent(radioTerreno))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mapaLable, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>

    private void botaoZoomMenosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoZoomMenosActionPerformed
        zoom--;
        atualizaMapa();
    }

    private void botaoZoomMaisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoZoomMaisActionPerformed
        zoom++;
        atualizaMapa();
    }

    private void radioSateliteActionPerformed(java.awt.event.ActionEvent evt) {//evento de  radioSateliteActionPerformed
        mudaTipo();
    }

    private void radioRuasActionPerformed(java.awt.event.ActionEvent evt) {//evento de radioRuasActionPerformed
        mudaTipo();
    }

    private void radioHibridoActionPerformed(java.awt.event.ActionEvent evt) {//evento de radioHibridoActionPerformed
         mudaTipo();
    }

    private void radioTerrenoActionPerformed(java.awt.event.ActionEvent evt) {//evento de radioTerrenoActionPerformed
         mudaTipo();
    }

    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(GoogleMaps.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GoogleMaps.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GoogleMaps.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GoogleMaps.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GoogleMaps().setVisible(true);
            }
        });
    }
    // Declaração de variáveis (não modificar)
    private javax.swing.JButton botaoZoomMais;
    private javax.swing.JButton botaoZoomMenos;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel mapaLable;
    private javax.swing.JRadioButton radioRuas;
    private javax.swing.JRadioButton radioHibrido;
    private javax.swing.JRadioButton radioSatelite;
    private javax.swing.JRadioButton radioTerreno;
    // Fim de declaração de variáveis

    private void mudaTipo(){
        if (radioRuas.isSelected()){
            tipo="roadmap";
        }
        if (radioSatelite.isSelected()){
            tipo="satellite";
        }
        if (radioHibrido.isSelected()){
             tipo="hybrid";
        }
        if (radioTerreno.isSelected()){
             tipo="terrain";
        }
        atualizaMapa();
    }
    
    private void atualizaMapa() {
        
        String chave="AIzaSyAz2YDYmEQpdEbhPbsDB-Zm6K5dIVXWJhE";
        
        String endereco = "http://maps.googleapis.com/maps/api/staticmap?center=-16.671641,-49.237193&zoom=" + zoom + "&size=640x640&maptype=" + tipo + "&key="+chave+"&sensor=false&format=jpg&path=color:0xff0000ff|weight:5|-16.673378,-49.236957|"+rota+"|-16.601657,-49.258911";
        BufferedImage img = null;

        try {
            URL url = new URL(endereco);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            img = ImageIO.read(con.getInputStream());
        } catch (MalformedURLException ex) {
            Logger.getLogger(GoogleMaps.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GoogleMaps.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (img != null) {
            ImageIcon mapa = new ImageIcon(img);
            mapaLable.setIcon(mapa);
        }
    }

}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import Controller.ViewListener;
import Model.Station;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author pandi_000
 */
public class MainWindow extends javax.swing.JFrame {

   
     public enum PlanStatus
    {
        notWaiting,
        waitingForStationPosition,
        waitingForConvoyeurPosition,
        waitingForJonctionPosition
    }
    
    ArrayList<ViewListener> listeners;
    private PlanStatus planStatus;
    
    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        listeners = new ArrayList<ViewListener>();
        planStatus = PlanStatus.notWaiting;
    }

    public void addListener(ViewListener _newListener)
    {
        listeners.add(_newListener);
    }
    
    public void messageToUser(String _message)
    {
        jLabel1.setText(_message);
    }
    
     public void addStationToPlan(Station station) {
        
    }
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenuBar4 = new javax.swing.JMenuBar();
        jMenu7 = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jMenuBar5 = new javax.swing.JMenuBar();
        jMenu10 = new javax.swing.JMenu();
        jMenu11 = new javax.swing.JMenu();
        ButtonStation = new javax.swing.JButton();
        ButtonConvoyeur = new javax.swing.JButton();
        ButtonJonction = new javax.swing.JButton();
        ButtonEntree = new javax.swing.JButton();
        ButtonSortie = new javax.swing.JButton();
        ButtonRefaire = new javax.swing.JButton();
        ButtonAnnuler = new javax.swing.JButton();
        LabelInterface = new javax.swing.JLabel();
        PanelInterface = new javax.swing.JPanel();
        TextFieldNom = new javax.swing.JTextField();
        TextFieldPositionX = new javax.swing.JTextField();
        TextFieldPositionY = new javax.swing.JTextField();
        ScrollPaneMatrice = new javax.swing.JScrollPane();
        MatriceRecup = new javax.swing.JTable();
        LabelMatrice = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        plan = new View.Plan();
        jButton1 = new javax.swing.JButton();
        Menu = new javax.swing.JMenuBar();
        MenuFichier = new javax.swing.JMenu();
        MenuEnregistrer = new javax.swing.JMenuItem();
        MenuQuitter = new javax.swing.JMenuItem();
        MenuVision = new javax.swing.JMenu();
        MenuAide = new javax.swing.JMenu();

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        jMenu5.setText("File");
        jMenuBar3.add(jMenu5);

        jMenu6.setText("Edit");
        jMenuBar3.add(jMenu6);

        jMenu7.setText("File");
        jMenuBar4.add(jMenu7);

        jMenu8.setText("Edit");
        jMenuBar4.add(jMenu8);

        jMenu10.setText("File");
        jMenuBar5.add(jMenu10);

        jMenu11.setText("Edit");
        jMenuBar5.add(jMenu11);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ButtonStation.setText("Station");
        ButtonStation.setMaximumSize(new java.awt.Dimension(85, 23));
        ButtonStation.setMinimumSize(new java.awt.Dimension(85, 23));
        ButtonStation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonStationActionPerformed(evt);
            }
        });

        ButtonConvoyeur.setText("Convoyeur");
        ButtonConvoyeur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonConvoyeurActionPerformed(evt);
            }
        });

        ButtonJonction.setLabel("Jonction");
        ButtonJonction.setMaximumSize(new java.awt.Dimension(85, 23));
        ButtonJonction.setMinimumSize(new java.awt.Dimension(85, 23));

        ButtonEntree.setText("Entrée");
        ButtonEntree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonEntreeActionPerformed(evt);
            }
        });

        ButtonSortie.setLabel("Sortie");

        ButtonRefaire.setText("Refaire");
        ButtonRefaire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonRefaireActionPerformed(evt);
            }
        });

        ButtonAnnuler.setText("Annuler");
        ButtonAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAnnulerActionPerformed(evt);
            }
        });

        LabelInterface.setText("Interface");

        PanelInterface.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PanelInterface.setPreferredSize(new java.awt.Dimension(613, 275));

        TextFieldNom.setBackground(new java.awt.Color(240, 240, 240));
        TextFieldNom.setText("Nom:");
        TextFieldNom.setToolTipText("Nom:");
        TextFieldNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldNomActionPerformed(evt);
            }
        });

        TextFieldPositionX.setBackground(new java.awt.Color(240, 240, 240));
        TextFieldPositionX.setText("Position x: ");

        TextFieldPositionY.setBackground(new java.awt.Color(240, 240, 240));
        TextFieldPositionY.setText("Position y:");

        MatriceRecup.setBackground(new java.awt.Color(240, 240, 240));
        MatriceRecup.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Produit", "Sortie 1", "Sortie 3", "Sortie 4"
            }
        ));
        MatriceRecup.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ScrollPaneMatrice.setViewportView(MatriceRecup);
        MatriceRecup.getAccessibleContext().setAccessibleDescription("");

        LabelMatrice.setText("Matrice de récupération");

        javax.swing.GroupLayout PanelInterfaceLayout = new javax.swing.GroupLayout(PanelInterface);
        PanelInterface.setLayout(PanelInterfaceLayout);
        PanelInterfaceLayout.setHorizontalGroup(
            PanelInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInterfaceLayout.createSequentialGroup()
                .addComponent(ScrollPaneMatrice, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 27, Short.MAX_VALUE))
            .addGroup(PanelInterfaceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TextFieldNom, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(TextFieldPositionY, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(TextFieldPositionX, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
                    .addComponent(LabelMatrice, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelInterfaceLayout.setVerticalGroup(
            PanelInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInterfaceLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TextFieldNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(TextFieldPositionX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TextFieldPositionY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 215, Short.MAX_VALUE)
                .addComponent(LabelMatrice)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ScrollPaneMatrice, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        plan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                planMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout planLayout = new javax.swing.GroupLayout(plan);
        plan.setLayout(planLayout);
        planLayout.setHorizontalGroup(
            planLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 871, Short.MAX_VALUE)
        );
        planLayout.setVerticalGroup(
            planLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 521, Short.MAX_VALUE)
        );

        jButton1.setText("Grille On/Off");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        MenuFichier.setText("Fichier");

        MenuEnregistrer.setText("Enregistrer");
        MenuFichier.add(MenuEnregistrer);

        MenuQuitter.setText("Quitter");
        MenuFichier.add(MenuQuitter);

        Menu.add(MenuFichier);

        MenuVision.setText("Vision");
        Menu.add(MenuVision);

        MenuAide.setText("Aide");
        Menu.add(MenuAide);

        setJMenuBar(Menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(ButtonStation, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(ButtonConvoyeur)
                        .addGap(47, 47, 47)
                        .addComponent(ButtonJonction, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(ButtonEntree, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(ButtonSortie, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel1)
                        .addGap(6, 6, 6)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(plan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LabelInterface)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ButtonRefaire)
                        .addGap(18, 18, 18)
                        .addComponent(ButtonAnnuler))
                    .addComponent(PanelInterface, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ButtonAnnuler)
                            .addComponent(ButtonRefaire)
                            .addComponent(LabelInterface))
                        .addComponent(PanelInterface, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ButtonStation, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ButtonConvoyeur, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ButtonJonction, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ButtonEntree, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ButtonSortie, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jButton1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(plan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        ButtonStation.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonStationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonStationActionPerformed
        this.jLabel1.setText("Choisissez une place sur le plan pour la station");
        this.planStatus = PlanStatus.waitingForStationPosition;
    }//GEN-LAST:event_ButtonStationActionPerformed

    private void ButtonEntreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonEntreeActionPerformed
        
        System.exit(0);// TODO add your handling code here:
    }//GEN-LAST:event_ButtonEntreeActionPerformed

    private void ButtonConvoyeurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonConvoyeurActionPerformed

        System.exit(0);// TODO add your handling code here:
    }//GEN-LAST:event_ButtonConvoyeurActionPerformed

    private void TextFieldNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldNomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldNomActionPerformed

    private void ButtonRefaireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonRefaireActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonRefaireActionPerformed

    private void ButtonAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonAnnulerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonAnnulerActionPerformed

    private void planMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_planMouseClicked
        switch(planStatus){
            case waitingForStationPosition : 
                for (ViewListener listener : listeners) {
                    listener.addStation(evt.getPoint());
                }
                this.planStatus = PlanStatus.notWaiting;
        }
    }//GEN-LAST:event_planMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Graphics g = this.plan.getGraphics();
        this.plan.paintGrid(g);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>*/
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonAnnuler;
    private javax.swing.JButton ButtonConvoyeur;
    private javax.swing.JButton ButtonEntree;
    private javax.swing.JButton ButtonJonction;
    private javax.swing.JButton ButtonRefaire;
    private javax.swing.JButton ButtonSortie;
    private javax.swing.JButton ButtonStation;
    private javax.swing.JLabel LabelInterface;
    private javax.swing.JLabel LabelMatrice;
    private javax.swing.JTable MatriceRecup;
    private javax.swing.JMenuBar Menu;
    private javax.swing.JMenu MenuAide;
    private javax.swing.JMenuItem MenuEnregistrer;
    private javax.swing.JMenu MenuFichier;
    private javax.swing.JMenuItem MenuQuitter;
    private javax.swing.JMenu MenuVision;
    private javax.swing.JPanel PanelInterface;
    private javax.swing.JScrollPane ScrollPaneMatrice;
    private javax.swing.JTextField TextFieldNom;
    private javax.swing.JTextField TextFieldPositionX;
    private javax.swing.JTextField TextFieldPositionY;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuBar jMenuBar4;
    private javax.swing.JMenuBar jMenuBar5;
    private View.Plan plan;
    // End of variables declaration//GEN-END:variables
}

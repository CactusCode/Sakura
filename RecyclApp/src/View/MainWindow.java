/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

import Controller.RecyclApp;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JOptionPane;

/**
 *
 * @author pandi_000
 */
public class MainWindow extends javax.swing.JFrame {
    static RecyclApp app;
    static MainWindow window;
    static Grid grid;
    static Point start;
    
    public static void main(String[] args) {   
        app = new RecyclApp(window);
        grid = new Grid();
    }

    
    public void setContextInfo(String description, Point position, int NumberExits) 
    {
        this.TextFieldNom.setText("Description :"+description);
        this.TextFieldPositionX.setText("Position x :"+position.getX());
        this.TextFieldPositionY.setText("Position y :"+position.getY());
        this.TextFieldNbStation.setText("Nombre de sortie(s) :"+NumberExits);
    }

   
     public enum PlanStatus
    {
        notWaiting,
        waitingForStationPosition,
        waitingForConvoyeurPositionStart,
        waitingForConvoyeyrPositionEnd,
        waitingForJonctionPosition,
        waitingForEntrancePosition,
        waitingForExitPosition
    }
    
   
    private PlanStatus planStatus;
    
    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        planStatus = PlanStatus.notWaiting;
        start = null;
    }
    public void messageToUser(String _message)
    {
        jLabel1.setText(_message);
    }
    
     public void redrawPlan() {
        Graphics g = this.plan1.getGraphics();
        app.paintPanel(plan1, g);
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
        TextFieldNbStation = new javax.swing.JTextField();
        grilleOnOff = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        plan1 = new View.Plan();
        jButton2 = new javax.swing.JButton();
        Menu = new javax.swing.JMenuBar();
        MenuFichier = new javax.swing.JMenu();
        MenuEnregistrer = new javax.swing.JMenuItem();
        MenuQuitter = new javax.swing.JMenuItem();
        MenuVision = new javax.swing.JMenu();
        MenuGridSize = new javax.swing.JMenuItem();
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
        setResizable(false);

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
        ButtonJonction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonJonctionActionPerformed(evt);
            }
        });

        ButtonEntree.setText("Entrée");
        ButtonEntree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonEntreeActionPerformed(evt);
            }
        });

        ButtonSortie.setLabel("Sortie");
        ButtonSortie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonSortieActionPerformed(evt);
            }
        });

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
        TextFieldNom.setText("Description :");
        TextFieldNom.setToolTipText("Nom:");
        TextFieldNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldNomActionPerformed(evt);
            }
        });

        TextFieldPositionX.setBackground(new java.awt.Color(240, 240, 240));
        TextFieldPositionX.setText("Position x: ");
        TextFieldPositionX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldPositionXActionPerformed(evt);
            }
        });

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

        TextFieldNbStation.setBackground(new java.awt.Color(240, 240, 240));
        TextFieldNbStation.setText("Nombre de sortie(s): ");
        TextFieldNbStation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldNbStationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelInterfaceLayout = new javax.swing.GroupLayout(PanelInterface);
        PanelInterface.setLayout(PanelInterfaceLayout);
        PanelInterfaceLayout.setHorizontalGroup(
            PanelInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInterfaceLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(PanelInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelInterfaceLayout.createSequentialGroup()
                        .addGroup(PanelInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TextFieldNom, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PanelInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(TextFieldPositionY, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(TextFieldPositionX, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
                            .addComponent(LabelMatrice, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PanelInterfaceLayout.createSequentialGroup()
                        .addGroup(PanelInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ScrollPaneMatrice, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextFieldNbStation, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 11, Short.MAX_VALUE))))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TextFieldNbStation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 184, Short.MAX_VALUE)
                .addComponent(LabelMatrice)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ScrollPaneMatrice, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        grilleOnOff.setText("Grille On/Off");
        grilleOnOff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grilleOnOffActionPerformed(evt);
            }
        });

        plan1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        plan1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                plan1MouseDragged(evt);
            }
        });
        plan1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                plan1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                plan1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                plan1MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout plan1Layout = new javax.swing.GroupLayout(plan1);
        plan1.setLayout(plan1Layout);
        plan1Layout.setHorizontalGroup(
            plan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 995, Short.MAX_VALUE)
        );
        plan1Layout.setVerticalGroup(
            plan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 562, Short.MAX_VALUE)
        );

        jButton2.setText("Magnetique On/Off");

        MenuFichier.setText("Fichier");

        MenuEnregistrer.setText("Enregistrer");
        MenuFichier.add(MenuEnregistrer);

        MenuQuitter.setText("Quitter");
        MenuQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuQuitterActionPerformed(evt);
            }
        });
        MenuFichier.add(MenuQuitter);

        Menu.add(MenuFichier);

        MenuVision.setText("Vision");

        MenuGridSize.setText("Taille grille");
        MenuGridSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuGridSizeActionPerformed(evt);
            }
        });
        MenuVision.add(MenuGridSize);

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
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 871, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(plan1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ButtonStation, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(ButtonConvoyeur)
                                .addGap(47, 47, 47)
                                .addComponent(ButtonJonction, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(ButtonEntree, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(ButtonSortie, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                                .addComponent(grilleOnOff)))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LabelInterface)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ButtonRefaire)
                        .addGap(18, 18, 18)
                        .addComponent(ButtonAnnuler))
                    .addComponent(PanelInterface, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ButtonAnnuler)
                            .addComponent(ButtonRefaire)
                            .addComponent(LabelInterface))
                        .addComponent(PanelInterface, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ButtonStation, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ButtonConvoyeur, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ButtonJonction, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ButtonEntree, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ButtonSortie, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(grilleOnOff)
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(plan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        ButtonStation.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonStationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonStationActionPerformed
        this.jLabel1.setText("Choisissez une place sur le plan pour la station");
        this.planStatus = PlanStatus.waitingForStationPosition;
    }//GEN-LAST:event_ButtonStationActionPerformed

    private void ButtonEntreeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonEntreeActionPerformed
        this.jLabel1.setText("Choisissez une place sur le plan pour l'entrée de la station");
        this.planStatus = PlanStatus.waitingForEntrancePosition;
    }//GEN-LAST:event_ButtonEntreeActionPerformed

    private void ButtonConvoyeurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonConvoyeurActionPerformed
        this.jLabel1.setText("Choisissez une composante du plan comme point de départ");
        this.planStatus = PlanStatus.waitingForConvoyeurPositionStart;
     
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

    private void grilleOnOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grilleOnOffActionPerformed
grid.change();
        Graphics g = this.plan1.getGraphics();
        app.paintPanel(plan1, g);
    }//GEN-LAST:event_grilleOnOffActionPerformed

    private void plan1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plan1MouseClicked

       switch(planStatus){
            case waitingForStationPosition : 
                    String numberExitStation = (String)JOptionPane.showInputDialog(this, "Entrez le nombre de sortie(s) de la station", "Nombre de sortie(s)", JOptionPane.QUESTION_MESSAGE, null , null, getComponentCount()); 
                    int intNumberExitStation = Integer.parseInt(numberExitStation); //les trois lignes font seulement en sorte que le nombre de sorties écrit par l'utilisateur est stocké
                    if ((numberExitStation != null) && (intNumberExitStation > 0))
                    {                                                                      
                        app.addStation(evt.getPoint(), intNumberExitStation);
                        this.planStatus = PlanStatus.notWaiting;
                    }
                    else
                    {
                        this.messageToUser("La station doit avoir au moins une sortie!");   
                    }
                    break;
            case waitingForConvoyeurPositionStart :
                    this.start = evt.getPoint();
                    this.jLabel1.setText("Choisissez une composante du plan comme point d'arrivé");
                    this.planStatus = PlanStatus.waitingForConvoyeyrPositionEnd;
                    break;
            case waitingForConvoyeyrPositionEnd :
                    app.addConvoyeur(this.start,evt.getPoint());
                    break;
            case waitingForJonctionPosition :
                    app.addJunction(evt.getPoint());
                    this.planStatus = PlanStatus.notWaiting;
                    break;
            case waitingForEntrancePosition :
                    app.addEntrance(evt.getPoint());
                    this.planStatus = PlanStatus.notWaiting;
                    break;
            case waitingForExitPosition :
                    app.addExit(evt.getPoint());
                    this.planStatus = PlanStatus.notWaiting;
                    break;
            case notWaiting :
                    app.getContextInfo(new Point(evt.getPoint().x-plan1.fakeX, evt.getPoint().y-plan1.fakeY));
                    break;
        }
    }//GEN-LAST:event_plan1MouseClicked

    private void TextFieldPositionXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldPositionXActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldPositionXActionPerformed

    private void MenuQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuQuitterActionPerformed
        System.exit(0);
    }//GEN-LAST:event_MenuQuitterActionPerformed

    private void ButtonJonctionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonJonctionActionPerformed
        this.jLabel1.setText("Choisissez une place sur le plan pour la jonction");
        this.planStatus = PlanStatus.waitingForJonctionPosition;
    }//GEN-LAST:event_ButtonJonctionActionPerformed

    private void ButtonSortieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSortieActionPerformed
        this.jLabel1.setText("Choisissez une place sur le plan pour la sortie de la station");
        this.planStatus = PlanStatus.waitingForExitPosition;
    }//GEN-LAST:event_ButtonSortieActionPerformed

    private void TextFieldNbStationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldNbStationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldNbStationActionPerformed

    private void MenuGridSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuGridSizeActionPerformed
        String stringGridSize = (String)JOptionPane.showInputDialog(this, "Entrez la taille de chaque carrés", "Taille de la grille", JOptionPane.QUESTION_MESSAGE, null , null, grid.getSize()); 
        redrawPlan();
        int gridSize = Integer.parseInt(stringGridSize);
        
        if ((stringGridSize != null) && (gridSize > 0))
        {
            grid.setSize(gridSize);
            redrawPlan();
        }
    }//GEN-LAST:event_MenuGridSizeActionPerformed

    int count = 0;
    Point lastPosition;
    
    private void plan1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plan1MouseDragged
        count++;
        grid.moveGrid(lastPosition.x - evt.getPoint().x, lastPosition.y - evt.getPoint().y);
        
        plan1.fakeX -= lastPosition.x - evt.getPoint().x;
        plan1.fakeY -= lastPosition.y - evt.getPoint().y;
        
        if (count%20 == 0)
        {
            redrawPlan();
        }
        
        lastPosition = evt.getPoint();

    }//GEN-LAST:event_plan1MouseDragged

    private void plan1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plan1MousePressed
        lastPosition = evt.getPoint();
    }//GEN-LAST:event_plan1MousePressed

    private void plan1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plan1MouseReleased
        if (count != 0)
        {
            count = 0;
            redrawPlan();
        }
    }//GEN-LAST:event_plan1MouseReleased

    /**
     * @param args the command line arguments
     */


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
    private javax.swing.JMenuItem MenuGridSize;
    private javax.swing.JMenuItem MenuQuitter;
    private javax.swing.JMenu MenuVision;
    private javax.swing.JPanel PanelInterface;
    private javax.swing.JScrollPane ScrollPaneMatrice;
    private javax.swing.JTextField TextFieldNbStation;
    private javax.swing.JTextField TextFieldNom;
    private javax.swing.JTextField TextFieldPositionX;
    private javax.swing.JTextField TextFieldPositionY;
    private javax.swing.JButton grilleOnOff;
    private javax.swing.JButton jButton2;
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
    private View.Plan plan1;
    // End of variables declaration//GEN-END:variables
}

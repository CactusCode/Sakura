/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;


import Model.Basket;
import Model.Material;
import Model.RecoveryMatrix;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Controller.*;
import Model.PlantComponant;
import java.awt.Image;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
/**
 *
 * @author vale
 */
public class MainWindow extends javax.swing.JFrame{
       static RecyclApp app;
    static MainWindow window;
    public static Grid grid;
    static Point start;
    static ArrayList<RecyclApp> appsMemorized;
    static Serialize serializer;
    static Deserialize deserializer;
    
    public static void main(String[] args) {   
        app = new RecyclApp(window);
        grid = new Grid();
        appsMemorized = new ArrayList<>();
        File file = new File("save.ser");
        serializer = new Serialize(file);
        deserializer = new Deserialize(file);
        serializer.save(app);
        appsMemorized.add(deserializer.load());
        appsMemorized.add(deserializer.load());
      
       
    }
    private Object textArea;

    static public RecyclApp getApp()
    {
        return app;
    }
    
    public void clearMatrixV2()
    {
        DefaultTableModel model = new DefaultTableModel();
        
        matrixV2.setModel(model);
        
        clearEntryTable();
    }
    
    public void clearEntryTable()
    {
        DefaultTableModel model = new DefaultTableModel();
        
        entryTable.setModel(model);
    }
    
    private void print(Object _value)
    {
        System.out.println(_value);
    }
    
    public void setEditButtonEnable(boolean _value)
    {
        btnAddEntry.setEnabled(_value);
        btnRemoveEntry.setEnabled(_value);
    }
    
    public void setMatrixV2(PlantComponant _componant)
    {
        if (_componant.getDescription().equals("Entrée Usine"))
            setEditButtonEnable(true);

        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Produits");
        
        //column name
        for (int i = 0; i < _componant.getConvoyeurV2List().size(); i++)
        {
            model.addColumn(_componant.getConvoyeurV2List().get(i).getName());
        }
        
        Map diffMap = new HashMap();
        int diffIndex = 0;
        
        //row name
        for (int i = 0; i < _componant.getMatrixV2().getSize(); i++)
        {
            if (_componant.hasAnEntry(_componant.getMatrixV2().getLine(i).getName()))
                model.addRow(new Object[]{_componant.getMatrixV2().getLine(i).getName()});
            else
                diffIndex++;
            diffMap.put(i, diffIndex);
        }
        matrixV2.setModel(model);
        
        print("TESTS ARE HERE DUMB");
        print("DIFF INDEX " + diffIndex);
        print("SIZE " + _componant.getMatrixV2().getSize());
        print("SUBSTRACTED " + (_componant.getMatrixV2().getSize()-diffIndex));
        print("COLUMN SIZE " + _componant.getConvoyeurV2List().size());
        //table values
        for (int i = 0; i < _componant.getConvoyeurV2List().size(); i++)
        {
            for (int j = 0; j < _componant.getMatrixV2().getSize()-diffIndex; j++)
            {
                print("THE ROW INDEX " + j);
                print("THE DIFF VALUE " + (int)diffMap.get(j));
                print("TRYING TO ACCESS AT ROW " + (j+(int)diffMap.get(j)) + " COLUMN " + (i+1));
                matrixV2.setValueAt(_componant.getMatrixV2().getLine(j+(int)diffMap.get(j)).getValueAt(i), j, i+1);
            }
        }
        
        weightLabel.setText("Kg/h total: " +_componant.getTotalWeight());
        
        setEntryTable(_componant);
    }

    public void setEntryTable(PlantComponant _componant)
    {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Produits");
        model.addColumn("Kg/h");
        model.addColumn("Taux Récupération");
        model.addColumn("Taux pureté");
        
        Map diffMap = new HashMap();
        int diffIndex = 0;
        
        //adding row names
        for (int i = 0; i < _componant.getMaterialTable().size(); i++)
        {
            if (_componant.getMaterialTable().get(i).getQuantity() != 0)
            {
                model.addRow(new Object[]{_componant.getMaterialTable().get(i).getType()});
            }
            else
            {
                diffIndex++;
            }
            diffMap.put(i, diffIndex);
        }
        entryTable.setModel(model);
        
        
        for (int i = 0; i < _componant.getMaterialTable().size(); i++)
        {
            if (_componant.getMaterialTable().get(i).getQuantity() != 0)
            {
                entryTable.setValueAt(_componant.getMaterialTable().get(i).getQuantity(), i-(int)diffMap.get(i), 1);
                
                //taux de pureté
                float recup = _componant.getMaterialTable().get(i).getQuantity();
                recup /= PlantComponant.getTotalMaterial();
                recup *= 100;
                String recupCutOff = app.parseTableValue(recup);
                entryTable.setValueAt(recupCutOff + " %", i-(int)diffMap.get(i), 2);
                
                float purityValue = _componant.getPurityPercentage(_componant.getMaterialTable().get(i).getType());
                String purityEntry = app.parseTableValue(purityValue);
                entryTable.setValueAt(purityEntry + " %", i-(int)diffMap.get(i), 3);
            }
        }
        
        app.applyTableToComponant(matrixV2, entryTable);
    }
    
    public void setContextInfo(String description, Point position, int numberExits, String nom, float capMax, RecoveryMatrix recoveryMatrix, Basket basket, String _weight) 
    {
        clearMatrixV2();
        
        this.LabelMatrice.setText("");
        this.imageButton.setVisible(false);
        this.TextFieldDescription.setText(description);
        this.TextFieldPositionX.setText(String.valueOf(position.getX()));
        this.TextFieldPositionY.setText(String.valueOf(position.getY()));
        this.TextFieldNbStation.setText(String.valueOf(numberExits));
        this.TextFieldNom.setText(nom);
        this.TextFieldCapMax.setText(String.valueOf(capMax));
        this.TextFieldNom.setEditable(true);
        this.TextFieldCapMax.setEditable(true);     
        this.weightLabel.setText("Kg/h: " + _weight);
        if("Station".equalsIgnoreCase(description)){
            this.imageButton.setVisible(true);
            this.LabelMatrice.setText("Matrice de récupération");
            List<String> col = new ArrayList<>(Arrays.asList("Produits"));
            for (int i = 0;i <numberExits;i++){
               col.add("Sortie " + String.valueOf(i+1));
            }
            
            DefaultTableModel model = new DefaultTableModel(col.toArray(), 2);
        }
        else if("Entrée Usine".equalsIgnoreCase(description)){
            this.LabelMatrice.setText("Panier d'entrée");
            List<String> col = new ArrayList<>(Arrays.asList("Produits"));
            col.add("Quantité");
            col.add("Poid");
            col.add("Total Kg/h");
            DefaultTableModel model = new DefaultTableModel(col.toArray(), 2);
        }
    }

   
     public enum PlanStatus
    {
        notWaiting,
        waitingForStationPosition,
        waitingForConvoyeurPositionStart,
        waitingForConvoyeyrPositionEnd,
        waitingForJonctionPosition,
        waitingForEntrancePosition,
        waitingForExitPosition,
        isDraggingComponant
    }
    
   
    private PlanStatus planStatus;
    
    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        planStatus = PlanStatus.notWaiting;
        this.imageButton.setVisible(false);
       
    }
    public void messageToUser(String _message)
    {
        jLabel1.setText(_message);
    }
    
public void zoom(float _value)
    {
        float oldZoom = plan1.getZoomFactor();
        
        if (_value > 1 && oldZoom < 1)
                zoom(1);
        
        if (_value < 1 && oldZoom > 1)
            zoom(1);
        
        plan1.zoom(_value);
        
        /*
        Point gridPosition = grid.getStart();//convertScreenPoint(grid.getStart());
        
        System.out.println(plan1.getConterPoint());
        System.out.println(gridPosition);
        
        int xZoomMod = (int)(plan1.distanceX(plan1.getConterPoint(), gridPosition));
        int yZoomMod = (int)(plan1.distanceY(plan1.getConterPoint(), gridPosition));
        
        System.out.println(xZoomMod);
        System.out.println(yZoomMod);
        
        xZoomMod /= plan1.zoomFactor;
        yZoomMod /= plan1.zoomFactor;
        
        //int xZoomMod = plan1.getWidth()/2;
        //int yZoomMod = plan1.getHeight()/2;
        
        System.out.println(xZoomMod);
        System.out.println(yZoomMod);
        
        
        //xZoomMod /= plan1.zoomFactor;
        //yZoomMod /= plan1.zoomFactor;
        
        //grid.xStart = plan1.projectPoint(gridPosition).x;
        //grid.yStart = plan1.projectPoint(gridPosition).y;
        
        //grid.moveGrid(xZoomMod, yZoomMod, plan1.zoomFactor);
        //grid.xStart -= xZoomMod;
        //grid.yStart -= yZoomMod;
        */
        //float x = plan1.getConterPoint().x;
        
        //Zoom
        
        if (_value > 1)
        {
            System.out.println("zoom");
            Point gridPosition = grid.getStart();
        
            int xZoomMod = (int)(plan1.distanceX(plan1.getConterPoint(), gridPosition));
            int yZoomMod = (int)(plan1.distanceY(plan1.getConterPoint(), gridPosition));
        
            grid.moveGrid(xZoomMod, yZoomMod, plan1.getZoomFactor());
        }
        else if (_value < 1)
        {
            System.out.println("dezoom");
            Point gridPosition = grid.getStart();
        
            int xZoomMod = (int)(plan1.distanceX(plan1.getConterPoint(), gridPosition));
            int yZoomMod = (int)(plan1.distanceY(plan1.getConterPoint(), gridPosition));
        
            xZoomMod*=plan1.getZoomFactor();
            yZoomMod*=plan1.getZoomFactor();
        
            grid.moveGrid(-xZoomMod, -yZoomMod, _value); 
        }
        else if (_value == 1)
        {
            if (oldZoom > 1)
            {
                System.out.println("reset");
                Point gridPosition = grid.getStart();
        
                int xZoomMod = (int)(plan1.distanceX(plan1.getConterPoint(), gridPosition));
                int yZoomMod = (int)(plan1.distanceY(plan1.getConterPoint(), gridPosition));
        
                grid.moveGrid(-xZoomMod/2, -yZoomMod/2, plan1.getZoomFactor());
            }
            else if (oldZoom < 1)
            {
                System.out.println("reset");
                Point gridPosition = grid.getStart();

                int xZoomMod = (int)(plan1.distanceX(plan1.getConterPoint(), gridPosition));
                int yZoomMod = (int)(plan1.distanceY(plan1.getConterPoint(), gridPosition));

                xZoomMod/=plan1.getZoomFactor();
                yZoomMod/=plan1.getZoomFactor();

                grid.moveGrid(xZoomMod, yZoomMod, _value);
            }
        }
        //
        
        //System.out.println(plan1.getWidth()/plan1.zoomFactor);
        //System.out.println(plan1.getHeight()/plan1.zoomFactor);
        
        //grid.xStart -= xZoomMod;
        //grid.yStart -= yZoomMod;
        
        
        //dezoom
        /*
        

        
        System.out.println(grid.xStart);
        System.out.println(grid.yStart);
*/
        redrawPlan();
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
        TextFieldDescription = new javax.swing.JTextField();
        TextFieldPositionX = new javax.swing.JTextField();
        TextFieldPositionY = new javax.swing.JTextField();
        LabelMatrice = new javax.swing.JLabel();
        TextFieldNbStation = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        TextFieldNom = new javax.swing.JTextField();
        TextFieldCapMax = new javax.swing.JTextField();
        mousePositionLabel = new javax.swing.JLabel();
        weightLabel = new javax.swing.JLabel();
        imageButton = new javax.swing.JButton();
        changerCouleurButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        matrixV2 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        entryTable = new javax.swing.JTable();
        BtnAppliquer = new javax.swing.JButton();
        btnAddEntry = new javax.swing.JButton();
        btnRemoveEntry = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        grilleOnOff = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        plan1 = new View.Plan();
        jButton2 = new javax.swing.JButton();
        jButtonSupprimer = new javax.swing.JButton();
        Menu = new javax.swing.JMenuBar();
        MenuFichier = new javax.swing.JMenu();
        OuvrirFichier = new javax.swing.JMenuItem();
        MenuEnregistrer = new javax.swing.JMenuItem();
        MenuExportImage = new javax.swing.JMenuItem();
        MenuQuitter = new javax.swing.JMenuItem();
        MenuVision = new javax.swing.JMenu();
        MenuGridSize = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
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

        ButtonStation.setForeground(new java.awt.Color(0, 0, 255));
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

        ButtonJonction.setForeground(new java.awt.Color(255, 0, 0));
        ButtonJonction.setLabel("Jonction");
        ButtonJonction.setMaximumSize(new java.awt.Dimension(85, 23));
        ButtonJonction.setMinimumSize(new java.awt.Dimension(85, 23));
        ButtonJonction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonJonctionActionPerformed(evt);
            }
        });

        ButtonEntree.setBackground(new java.awt.Color(140, 140, 140));
        ButtonEntree.setForeground(new java.awt.Color(255, 175, 175));
        ButtonEntree.setText("Entrée");
        ButtonEntree.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonEntreeActionPerformed(evt);
            }
        });

        ButtonSortie.setBackground(new java.awt.Color(140, 140, 140));
        ButtonSortie.setForeground(new java.awt.Color(255, 200, 0));
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

        TextFieldDescription.setEditable(false);
        TextFieldDescription.setToolTipText("Nom:");

        TextFieldPositionX.setEditable(false);

        TextFieldPositionY.setEditable(false);

        TextFieldNbStation.setEditable(false);

        jLabel2.setText("Description :");

        jLabel5.setText("Nom :");

        jLabel3.setText("Pos X :");

        jLabel4.setText("Pos Y :");

        jLabel6.setText("Nb de sorties :");

        jLabel7.setText("Cap. Max. :");

        TextFieldNom.setEditable(false);
        TextFieldNom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TextFieldNomKeyPressed(evt);
            }
        });

        TextFieldCapMax.setEditable(false);
        TextFieldCapMax.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TextFieldCapMaxKeyPressed(evt);
            }
        });

        mousePositionLabel.setText("jLabel8");

        weightLabel.setText("Kg/h: 0");

        imageButton.setText("Image Station");
        imageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imageButtonActionPerformed(evt);
            }
        });

        changerCouleurButton.setText("couleur");
        changerCouleurButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changerCouleurButtonActionPerformed(evt);
            }
        });

        matrixV2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(matrixV2);

        entryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(entryTable);

        BtnAppliquer.setText("Appliquer");
        BtnAppliquer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAppliquerActionPerformed(evt);
            }
        });

        btnAddEntry.setText("Ajouter");
        btnAddEntry.setEnabled(false);
        btnAddEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddEntryActionPerformed(evt);
            }
        });

        btnRemoveEntry.setText("Retirer");
        btnRemoveEntry.setEnabled(false);
        btnRemoveEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveEntryActionPerformed(evt);
            }
        });

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelInterfaceLayout = new javax.swing.GroupLayout(PanelInterface);
        PanelInterface.setLayout(PanelInterfaceLayout);
        PanelInterfaceLayout.setHorizontalGroup(
            PanelInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInterfaceLayout.createSequentialGroup()
                .addGroup(PanelInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addGroup(PanelInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PanelInterfaceLayout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addGroup(PanelInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)))
                        .addGroup(PanelInterfaceLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel6))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelInterfaceLayout.createSequentialGroup()
                        .addComponent(TextFieldNom, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(changerCouleurButton, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PanelInterfaceLayout.createSequentialGroup()
                        .addGroup(PanelInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TextFieldNbStation, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextFieldCapMax, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PanelInterfaceLayout.createSequentialGroup()
                                .addGroup(PanelInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(TextFieldDescription, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TextFieldPositionY, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TextFieldPositionX, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(imageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(PanelInterfaceLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mousePositionLabel)
                .addGap(812, 812, 812))
            .addGroup(PanelInterfaceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelInterfaceLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)
                        .addComponent(LabelMatrice, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(weightLabel)
                    .addGroup(PanelInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(PanelInterfaceLayout.createSequentialGroup()
                            .addGroup(PanelInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnAddEntry, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRemoveEntry, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(PanelInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(BtnAppliquer)
                                .addComponent(jButton1)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelInterfaceLayout.setVerticalGroup(
            PanelInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInterfaceLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextFieldDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(imageButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(TextFieldNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(changerCouleurButton))
                .addGap(9, 9, 9)
                .addGroup(PanelInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextFieldPositionX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextFieldPositionY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(TextFieldNbStation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(TextFieldCapMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(PanelInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelInterfaceLayout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(LabelMatrice))
                    .addGroup(PanelInterfaceLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99)
                .addGroup(PanelInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnAppliquer)
                    .addComponent(btnAddEntry))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelInterfaceLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mousePositionLabel)
                    .addComponent(btnRemoveEntry)
                    .addComponent(jButton1))
                .addGap(3, 3, 3)
                .addComponent(weightLabel)
                .addContainerGap())
        );

        grilleOnOff.setText("Grille On/Off");
        grilleOnOff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grilleOnOffActionPerformed(evt);
            }
        });

        plan1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
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
        plan1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                plan1MouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                plan1MouseMoved(evt);
            }
        });

        javax.swing.GroupLayout plan1Layout = new javax.swing.GroupLayout(plan1);
        plan1.setLayout(plan1Layout);
        plan1Layout.setHorizontalGroup(
            plan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        plan1Layout.setVerticalGroup(
            plan1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 562, Short.MAX_VALUE)
        );

        jButton2.setText("Magnetique On/Off");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButtonSupprimer.setText("Supprimer");
        jButtonSupprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSupprimerActionPerformed(evt);
            }
        });

        MenuFichier.setText("Fichier");

        OuvrirFichier.setText("Ouvrir");
        OuvrirFichier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OuvrirFichierActionPerformed(evt);
            }
        });
        MenuFichier.add(OuvrirFichier);

        MenuEnregistrer.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        MenuEnregistrer.setText("Enregistrer");
        MenuEnregistrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuEnregistrerActionPerformed(evt);
            }
        });
        MenuFichier.add(MenuEnregistrer);

        MenuExportImage.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        MenuExportImage.setText("ExporterCommeImage");
        MenuExportImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuExportImageActionPerformed(evt);
            }
        });
        MenuFichier.add(MenuExportImage);

        MenuQuitter.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
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

        jMenu1.setText("Zoom");

        jMenuItem1.setText("Proche");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Normal");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Loin");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        MenuVision.add(jMenu1);

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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(grilleOnOff)))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LabelInterface)
                        .addGap(105, 105, 105)
                        .addComponent(jButtonSupprimer)
                        .addGap(18, 18, 18)
                        .addComponent(ButtonRefaire)
                        .addGap(18, 18, 18)
                        .addComponent(ButtonAnnuler))
                    .addComponent(PanelInterface, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
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
                        .addComponent(plan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ButtonAnnuler)
                            .addComponent(ButtonRefaire)
                            .addComponent(LabelInterface)
                            .addComponent(jButtonSupprimer))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelInterface, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
        Point pointInitialEntree = new Point(199, 201); 
        serializer.save(app);
        appsMemorized.add(0, deserializer.load());
        app.addEntrance(convertScreenPoint(pointInitialEntree));
        appsMemorized.add(1,app);
        this.planStatus = PlanStatus.notWaiting;
    }//GEN-LAST:event_ButtonEntreeActionPerformed

    private void ButtonConvoyeurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonConvoyeurActionPerformed
        this.jLabel1.setText("Choisissez une composante du plan comme point de départ");
        this.planStatus = PlanStatus.waitingForConvoyeurPositionStart;
     
    }//GEN-LAST:event_ButtonConvoyeurActionPerformed

    private void grilleOnOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grilleOnOffActionPerformed
        grid.change();
        Graphics g = this.plan1.getGraphics();
        app.paintPanel(plan1, g);

    }//GEN-LAST:event_grilleOnOffActionPerformed

    public Point convertScreenPoint(Point _point)
    {
        int xZoomMod = plan1.distanceX(_point, plan1.projectPoint(_point));
        int yZoomMod = plan1.distanceY(_point, plan1.projectPoint(_point));
        
        xZoomMod /= plan1.getZoomFactor();
        yZoomMod /= plan1.getZoomFactor();
        
        return new Point(_point.x-(int)(plan1.fakeX) + xZoomMod, _point.y-(int)(plan1.fakeY) + yZoomMod);
    }
    
    private void plan1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plan1MouseClicked

       switch(planStatus){
            case waitingForStationPosition : 
                    String numberExitStation = (String)JOptionPane.showInputDialog(this, "Entrez le nombre de sortie(s) de la station", "Nombre de sortie(s)", JOptionPane.QUESTION_MESSAGE, null , null, getComponentCount()); 
                    int intNumberExitStation = Integer.parseInt(numberExitStation); //les trois lignes font seulement en sorte que le nombre de sorties écrit par l'utilisateur est stocké
                    if ((numberExitStation != null) && (intNumberExitStation > 0))
                    {        
                        serializer.save(app);
                        appsMemorized.add(0, deserializer.load());
                  
                        app.addStation(convertScreenPoint(evt.getPoint()), intNumberExitStation);
                
                        appsMemorized.add(1,app);
                        this.planStatus = PlanStatus.notWaiting;
                    }
                    else
                    {
                        this.messageToUser("La station doit avoir au moins une sortie!");   
                    }
                    break;
            case waitingForConvoyeurPositionStart :
                    this.start = convertScreenPoint(evt.getPoint());
                    this.jLabel1.setText("Choisissez une composante du plan comme point d'arrivé");
                    this.planStatus = PlanStatus.waitingForConvoyeyrPositionEnd;
                    break;
            case waitingForConvoyeyrPositionEnd :
                    serializer.save(app);
                    appsMemorized.add(0, deserializer.load());
                    app.addConvoyeur(this.start,convertScreenPoint(evt.getPoint()));
                    appsMemorized.add(1,app);
                    this.planStatus = PlanStatus.notWaiting;
                    break;
            case waitingForJonctionPosition :
                    serializer.save(app);
                    appsMemorized.add(0, deserializer.load());
                    app.addJunction(convertScreenPoint(evt.getPoint()));
                    appsMemorized.add(1,app);
                    this.planStatus = PlanStatus.notWaiting;
                    break;
            case waitingForEntrancePosition :
                    serializer.save(app);
                    appsMemorized.add(0, deserializer.load());
                    app.addEntrance(convertScreenPoint(evt.getPoint()));
                    appsMemorized.add(1,app);
                    this.planStatus = PlanStatus.notWaiting;
                    break;
            case waitingForExitPosition :
                    serializer.save(app);
                    appsMemorized.add(0, deserializer.load());
                    app.addExit(convertScreenPoint(evt.getPoint()));
                    appsMemorized.add(1,app);
                    this.planStatus = PlanStatus.notWaiting;
                    break;
            case notWaiting :
                    app.getContextInfo(convertScreenPoint(evt.getPoint()));
                    break;
        }
       
       app.calculatePasingWeight();
    }//GEN-LAST:event_plan1MouseClicked

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
        if("".equals(TextFieldDescription.getText())|| null == TextFieldDescription.getText())
	{
            count++;
            grid.moveGrid(lastPosition.x - evt.getPoint().x, lastPosition.y - evt.getPoint().y, plan1.getZoomFactor());

            plan1.fakeX -= (lastPosition.x - evt.getPoint().x)/plan1.getZoomFactor();
            plan1.fakeY -= (lastPosition.y - evt.getPoint().y)/plan1.getZoomFactor();

            if (count%1 == 0)
            {
                    redrawPlan();
            }

            lastPosition = evt.getPoint();
	}
	else
	{
		this.planStatus = planStatus.isDraggingComponant;
	}

    }//GEN-LAST:event_plan1MouseDragged

    private void plan1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plan1MousePressed
        lastPosition = evt.getPoint();
    }//GEN-LAST:event_plan1MousePressed

    private void plan1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plan1MouseReleased
        if(this.planStatus == planStatus.isDraggingComponant)
	{
            Point newPoint = new Point(evt.getPoint().x - (int)plan1.fakeX, evt.getPoint().y - (int)plan1.fakeY);  
            serializer.save(app);
            appsMemorized.add(0, deserializer.load());
            this.app.setNewPosition(Float.parseFloat(TextFieldPositionX.getText()),
                                    Float.parseFloat(TextFieldPositionY.getText()), newPoint);
            appsMemorized.add(1,app);
            this.planStatus = planStatus.notWaiting;
	}
	
	if (count != 0)
	{
		count = 0;
		redrawPlan();
	}
    }//GEN-LAST:event_plan1MouseReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        grid.changeMagnet();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void TextFieldCapMaxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextFieldCapMaxKeyPressed
        float cap;
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            try {
                cap = Float.parseFloat(TextFieldCapMax.getText());
                serializer.save(app);
                appsMemorized.add(0, deserializer.load());
                app.setCapMax(Float.parseFloat(TextFieldPositionX.getText()),Float.parseFloat(TextFieldPositionY.getText()),cap);
                appsMemorized.add(1,app);
                } catch (NumberFormatException e) {
                    JOptionPane.showConfirmDialog(null, "Please enter numbers only", "naughty", JOptionPane.CANCEL_OPTION);
                }
        }
    }//GEN-LAST:event_TextFieldCapMaxKeyPressed

    private void TextFieldNomKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextFieldNomKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            serializer.save(app);
            appsMemorized.add(0, deserializer.load());
            app.setName(Float.parseFloat(TextFieldPositionX.getText()),Float.parseFloat(TextFieldPositionY.getText()),TextFieldNom.getText());
            appsMemorized.add(1,app);
        }
    }//GEN-LAST:event_TextFieldNomKeyPressed

    private void jButtonSupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSupprimerActionPerformed
        serializer.save(app);
        appsMemorized.add(0, deserializer.load());
        app.deleteFocus();// TODO add your handling code here:
        appsMemorized.add(1,app);
    }//GEN-LAST:event_jButtonSupprimerActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        zoom(2);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        zoom(1);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        zoom(0.5f);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void plan1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plan1MouseMoved
     
        mousePositionLabel.setText("x : " + String.valueOf(convertScreenPoint(evt.getPoint()).x) + " , " + "y : "+String.valueOf(convertScreenPoint(evt.getPoint()).y));
    }//GEN-LAST:event_plan1MouseMoved

    private void MenuEnregistrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuEnregistrerActionPerformed
        JFrame parentFrame = new JFrame();
 
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");   
 
        int userSelection = fileChooser.showSaveDialog(parentFrame);
 
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            Serialize serialize = new Serialize(fileToSave);
            serialize.save(app);
        }
        
        
        
    
    }//GEN-LAST:event_MenuEnregistrerActionPerformed

    private void MenuExportImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuExportImageActionPerformed
       // app.exportImage();
        BufferedImage bi = new BufferedImage(plan1.getSize().width, plan1.getSize().height, BufferedImage.TYPE_INT_ARGB); 
        Graphics g = bi.createGraphics();
        app.paintPanel(plan1,g);  //this == JComponent
        g.dispose();
        try
        {
           ImageIO.write(bi,"png",new File("test2.png"));
        }

        catch (Exception e) {}
    }//GEN-LAST:event_MenuExportImageActionPerformed

    private void OuvrirFichierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OuvrirFichierActionPerformed
          JFrame parentFrame = new JFrame();
 
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to load");   
 
        int userSelection = fileChooser.showOpenDialog(parentFrame);
 
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToLoad = fileChooser.getSelectedFile();
            
            Deserialize deserialize = new Deserialize(fileToLoad);
            app = deserialize.load();
            Graphics g = plan1.getGraphics();
            app.paintPanel(plan1, g);
        }
        
       
    }//GEN-LAST:event_OuvrirFichierActionPerformed

    private void ButtonAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonAnnulerActionPerformed
            app = appsMemorized.get(0);
            Graphics g = plan1.getGraphics();
            app.paintPanel(plan1, g);
    }//GEN-LAST:event_ButtonAnnulerActionPerformed

    private void ButtonRefaireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonRefaireActionPerformed
            app = appsMemorized.get(1);
            Graphics g = plan1.getGraphics();
            app.paintPanel(plan1, g);
    }//GEN-LAST:event_ButtonRefaireActionPerformed

    private void imageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imageButtonActionPerformed

        app.stationImage();
        Graphics g = plan1.getGraphics();
        app.paintPanel(plan1, g);
    }//GEN-LAST:event_imageButtonActionPerformed

    private void changerCouleurButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changerCouleurButtonActionPerformed
        
        app.getColor();
        Graphics g = plan1.getGraphics();
        app.paintPanel(plan1, g);
    }//GEN-LAST:event_changerCouleurButtonActionPerformed

    private void BtnAppliquerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAppliquerActionPerformed
        app.applyTableToComponant(matrixV2, entryTable);    
    }//GEN-LAST:event_BtnAppliquerActionPerformed

    private void btnAddEntryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddEntryActionPerformed
        String name = JOptionPane.showInputDialog("Entrez le type de produit");
        String quantity = JOptionPane.showInputDialog("Entrez la quantité du produit");
        app.addEntry(name, quantity);
        setMatrixV2(app.getFocusComponant());
    }//GEN-LAST:event_btnAddEntryActionPerformed

    private void btnRemoveEntryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveEntryActionPerformed
        String name = JOptionPane.showInputDialog("Entrez le type de produit");
        app.removeEntry(name);
        setMatrixV2(app.getFocusComponant());
    }//GEN-LAST:event_btnRemoveEntryActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        app.runTest();
    }//GEN-LAST:event_jButton1ActionPerformed
    
    
    
    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAppliquer;
    private javax.swing.JButton ButtonAnnuler;
    private javax.swing.JButton ButtonConvoyeur;
    private javax.swing.JButton ButtonEntree;
    private javax.swing.JButton ButtonJonction;
    private javax.swing.JButton ButtonRefaire;
    private javax.swing.JButton ButtonSortie;
    private javax.swing.JButton ButtonStation;
    private javax.swing.JLabel LabelInterface;
    private javax.swing.JLabel LabelMatrice;
    private javax.swing.JMenuBar Menu;
    private javax.swing.JMenu MenuAide;
    private javax.swing.JMenuItem MenuEnregistrer;
    private javax.swing.JMenuItem MenuExportImage;
    private javax.swing.JMenu MenuFichier;
    private javax.swing.JMenuItem MenuGridSize;
    private javax.swing.JMenuItem MenuQuitter;
    private javax.swing.JMenu MenuVision;
    private javax.swing.JMenuItem OuvrirFichier;
    private javax.swing.JPanel PanelInterface;
    private javax.swing.JTextField TextFieldCapMax;
    private javax.swing.JTextField TextFieldDescription;
    private javax.swing.JTextField TextFieldNbStation;
    private javax.swing.JTextField TextFieldNom;
    private javax.swing.JTextField TextFieldPositionX;
    private javax.swing.JTextField TextFieldPositionY;
    private javax.swing.JButton btnAddEntry;
    private javax.swing.JButton btnRemoveEntry;
    private javax.swing.JButton changerCouleurButton;
    private javax.swing.JTable entryTable;
    private javax.swing.JButton grilleOnOff;
    private javax.swing.JButton imageButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonSupprimer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
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
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable matrixV2;
    private javax.swing.JLabel mousePositionLabel;
    private View.Plan plan1;
    private javax.swing.JLabel weightLabel;
    // End of variables declaration//GEN-END:variables
}

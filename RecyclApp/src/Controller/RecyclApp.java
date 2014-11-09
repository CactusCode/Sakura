/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import View.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import recyclappl.gui.MainWindow;

   
/**
 *
 * @author Pascal
 */
public class RecyclApp implements ViewListener{
    
    static recyclAppUI ui;
    static RecyclApp app;
    static private MainWindow window;
    
    ArrayList <PlantComponant> plantComponantsList;
    Basket basket;
    PlantEntrance plantEntrance;
    PlantExit plantExit;
    /**
     * @param args the command line arguments
     */
    public RecyclApp() {
        window = new MainWindow();
        window.setVisible(true);
        this.plantComponantsList = new ArrayList<PlantComponant>();
        this.basket = new Basket();
        this.plantEntrance = new PlantEntrance();
        this.plantExit = new PlantExit();
    }
    
    public static void main(String[] args) {
   
        app = new RecyclApp();
        window.addListener(app);
    }

    @Override
    public void respondToAction()
    {
        System.out.println("EVENT CALLED");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addStation() {
        System.out.println("test");
    }

   
}

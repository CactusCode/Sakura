/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ComposanteUsine;
import java.util.ArrayList;
import View.recyclAppUI;
import recyclappl.gui.MainWindow;

   
/**
 *
 * @author Pascal
 */
public class RecyclApp implements ViewListener{
    
    static recyclAppUI ui;
    static RecyclApp app;
    static private MainWindow window;
    
    ArrayList <ComposanteUsine> listeComposanteUsine;
    /**
     * @param args the command line arguments
     */
    public RecyclApp() {
        //ui = new recyclAppUI();
        //ui.setVisible(true);
        listeComposanteUsine = new ArrayList<ComposanteUsine>();
    }
    
    public static void main(String[] args) {
        window = new MainWindow();
        window.setVisible(true);
        app = new RecyclApp();
        
        window.addListener(app);
        //ui.passControler(app);
        //recyclappl.gui.MainWindow mainWindow = new recyclappl.gui.MainWindow();
        //mainWindow.setVisible(true);
    }
    
    public void checkIfClickOk(){
        ui.changeLabel();
    }
    
    private void doAction()
    {
        
    }

    @Override
    public void respondToAction()
    {
        System.out.println("EVENT CALLED");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

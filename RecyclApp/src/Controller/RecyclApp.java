/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.ComposanteUsine;
import java.util.ArrayList;
import View.recyclAppUI;

   
/**
 *
 * @author Pascal
 */
public class RecyclApp {
    
    static recyclAppUI ui;
    static RecyclApp app;
    ArrayList <ComposanteUsine> listeComposanteUsine;
    /**
     * @param args the command line arguments
     */
    public RecyclApp() {
        ui = new recyclAppUI();
        ui.setVisible(true);
        listeComposanteUsine = new ArrayList<ComposanteUsine>();
    }
    
    public static void main(String[] args) {
        app = new RecyclApp();
        ui.passControler(app);
    }
    
    public void checkIfClickOk(){
        ui.changeLabel();
    }
    
}

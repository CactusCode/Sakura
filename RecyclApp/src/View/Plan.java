/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.RecyclApp;
import Model.PlantComponant;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Pascal
 */
public class Plan extends JPanel
{
    private final int composantH = 12;
    private final int composantW = 12;
    
        public boolean grilleActive;
  
        private MainWindow parent;
        
    public Plan()
    {
        
    }
    
    public void setParent(MainWindow _parent)
    {
        parent = _parent;
    }
    
    public void paintPanel(ArrayList<PlantComponant> _components, Graphics g)
    {
        paintComponent(g);
        
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);
        
        MainWindow.grid.draw(g, getWidth(), getHeight());
        
        for (PlantComponant _component : _components) {
            _component.draw(g);
        }     
    }
    
    
}

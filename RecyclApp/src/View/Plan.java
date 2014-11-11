/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Pascal
 */
public class Plan extends JPanel
{
    public boolean grilleActive;
    public Plan()
    {
        System.out.println("je suis le bon plan");
        this.grilleActive = false;
    }
    
    public void paintGrid(Graphics g)
    {
        if(!this.grilleActive)
        {
            this.grilleActive = true;
            super.paintComponents(g);
            g.setColor(Color.GRAY);
            for (int i = 0; i <= this.getWidth(); i+=10)
            {
                g.drawLine(i, 0, i, this.getHeight());
            }
            for (int i = 0; i <= this.getWidth(); i+=10)
            {
                g.drawLine(0, i, this.getWidth(), i);
            }
        }
        else {
            this.grilleActive = false;
            repaint();
        }
            
    }
    
}

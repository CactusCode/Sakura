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
import java.awt.Point;

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
  
    public Plan()
    {
        
    }
    public void paintStation(Point _position, Graphics g)
    {
        g.setColor(Color.red);
        g.drawOval((int)_position.getX()-composantW/2, (int)_position.getY()-composantH/2,  composantH, composantW);    
    }
    
}

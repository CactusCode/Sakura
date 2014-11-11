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
    private int WIDTH = 870;
    private int HEIGHT = 551;
    
    public Plan()
    {
        System.out.println("je suis le bon plan");
        setBounds(25, 92, WIDTH, HEIGHT);
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponents(g);
        g.setColor(Color.GRAY);
        for (int i = 0; i < WIDTH; i+=10)
        {
            g.drawLine(i, 0, i, HEIGHT);
        }
        for (int i = 0; i < HEIGHT; i+=10)
        {
            g.drawLine(0, i, WIDTH, i);
        }
    }
}

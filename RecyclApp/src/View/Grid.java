/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Pascal
 */
public class Grid
{
    private Boolean show;
    private int size;
    private int xStart;
    private int yStart;
    private Boolean magnet;
    
    Grid()
    {
        show = false;
        size = 50;
        xStart = 0;
        yStart = 0;
        magnet = false;
    }
    
    public Boolean isShown()
    {
        return show;
    }
    
    public void hide()
    {
        show = false;
    }
    
    public void show()
    {
        show = true;
    }
    
    public void change()
    {
        show = !show;
    }
    
    public Boolean isMagnet()
    {
        return magnet;
    }
    
    public void changeMagnet()
    {
        magnet = !magnet;
    }
    
    public void setSize(int _value)
    {
        size = _value;
    }
    
    public int getSize()
    {
        return size;
    }
    
    public void moveGrid(int _x, int _y)
    {
        xStart -= _x;
        xStart = xStart%size;
        
        yStart -= _y;
        yStart = yStart%size;
    }
    
    public void draw(Graphics g, int _width, int _height)
    {
        if(show)
        {
            g.setColor(Color.GRAY);
            for (int i = xStart; i <= _width; i+=size)
            {
                g.drawLine(i, 0, i, _height);
            }
            for (int i = yStart; i <= _height; i+=size)
            {
                g.drawLine(0, i, _width, i);
            }
        }
    }
}

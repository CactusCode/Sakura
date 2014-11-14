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
    
    Grid()
    {
        show = false;
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
    
    public void draw(Graphics g, int _width, int _height)
    {
        if(show)
        {
            g.setColor(Color.GRAY);
            for (int i = 0; i <= _width; i+=10)
            {
                g.drawLine(i, 0, i, _height);
            }
            for (int i = 0; i <= _width; i+=10)
            {
                g.drawLine(0, i, _width, i);
            }
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author Pascal
 */
public class Grid
{
    private Boolean show;
    private float size;
    private float xStart;
    private float yStart;
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
        return (int)size;
    }
    
    public Point getStart()
    {
        return new Point((int)xStart, (int)yStart);
    }
    
    public void moveGrid(float _x, float _y, float _zoom)
    {
        xStart -= _x;
        xStart = xStart%(int)(size*_zoom);
        
        yStart -= _y;
        yStart = yStart%(int)(size*_zoom);
    }
    
    public void draw(Graphics g, int _width, int _height, float _zoom)
    {
        if(show)
        {
            int temp = 0;
            g.setColor(Color.GRAY);
                                            //width*2 hack, a arranger plus tard
            for (int i = (int)xStart; i <= (_width*2)+xStart; i+=(size*_zoom))
            {
                /*if (temp == 0)
                {
                    g.setColor(Color.black);
                    temp++;
                }
                else if (temp == 1)
                {
                    g.setColor(Color.red);
                    temp++;
                }
                else if (temp == 2)
                {
                    g.setColor(Color.blue);
                    temp++;
                }
                else if (temp == 3)
                {
                    g.setColor(Color.orange);
                    temp++;
                }
                else
                {
                    g.setColor(Color.green);
                    temp = 0;
                }*/
                
                g.drawLine(i, 0, i, _height);
            }
             temp = 0;
            for (int i = (int)yStart; i <= (_height*2)+yStart; i+=(size*_zoom))
            {
                /*if (temp == 0)
                {
                    g.setColor(Color.black);
                    temp++;
                }
                else if (temp == 1)
                {
                    g.setColor(Color.red);
                    temp++;
                }
                else if (temp == 2)
                {
                    g.setColor(Color.blue);
                    temp++;
                }
                else if (temp == 3)
                {
                    g.setColor(Color.orange);
                    temp++;
                }
                else
                {
                    g.setColor(Color.green);
                    temp = 0;
                }*/
                g.drawLine(0, i, _width, i);
            }
        }
    }
}

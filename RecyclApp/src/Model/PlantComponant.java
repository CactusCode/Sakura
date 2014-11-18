/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author Pascal
 */
public abstract class PlantComponant
{   
    protected Point position;
    protected float maximalCapacity;
    protected String name;
    protected String description;
    protected Color planColor;
    protected int drawSize = 20;

    public PlantComponant()
    {
        
    }
  
    //Name accessors
    public void setName(String _name)
    {
        this.name = _name;
    }
    public String getName()
    {
        return this.name;
    }

    //Description accessors
    public void setDescription(String _description)
    {
        this.description = _description;
    }
    public String getDescription()
    {
        return description;
    }
    
    //positionX accessors
    public void setPosition(Point _position)
    {
        this.position = _position;
    }
    public Point getPosition()
    {
        return this.position;
    } 
    //maximalCapacity acccessors
    public void setMaximalCapacity(float _capacity)
    {
        this.maximalCapacity = _capacity;
    }
    public float getMaximalCapacity()
    {
        return this.maximalCapacity;
    }
    public int getDrawSize(){
        return this.drawSize;
    }
    public void draw(Graphics g) {
        g.setColor(planColor);
        g.fillOval((int)(position.x-drawSize/2), (int)(position.y-drawSize/2), drawSize, drawSize);
    }
}

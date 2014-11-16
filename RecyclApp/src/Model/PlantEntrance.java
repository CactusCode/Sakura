/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;

/**
 *
 * @author Vincent
 */
public class PlantEntrance extends PlantComponant{
    private int drawSize = 15;
    public PlantEntrance ()
    {
        this.setDescription("Entrée Usine");
        this.getPosition();
    }

     
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.blue);
        g.drawOval((int)(position.getX()-drawSize/2), (int)(position.getY()-drawSize/2), drawSize, drawSize);
    
    
    }
}

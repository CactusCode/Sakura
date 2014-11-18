/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;

/**
 *
 * @author Vale
 */
public class Convoyeur 
{
  
    private final PlantComponant startPoint;
    private final PlantComponant endPoint;
    private final int ARR_SIZE = 9;
    
    public Convoyeur(PlantComponant _start,PlantComponant _end)
    {
       this.startPoint = _start;
       this.endPoint = _end;
    }   
    public PlantComponant getStartComponant(){
        return this.startPoint;
    }
    public PlantComponant getEndComponant(){
        return this.endPoint;
    }
    
    public Point getStartPosition()
    {
        return this.startPoint.getPosition();
    }
     public Point getEndPosition()
    {
        return this.endPoint.getPosition();
    }
     public void draw(Graphics g) {
        g.setColor(Color.black);
        this.drawArrow(g, this.startPoint.getPosition().x,this.startPoint.getPosition().y,
                   this.endPoint.getPosition().x, this.endPoint.getPosition().y);
        
    }
     void drawArrow(Graphics g1, int x1, int y1, int x2, int y2) {
                Graphics2D g = (Graphics2D) g1.create();

                double dx = x2 - x1, dy = y2 - y1;
                double angle = Math.atan2(dy, dx);
                int len = (int) Math.sqrt(dx*dx + dy*dy);
                AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
                at.concatenate(AffineTransform.getRotateInstance(angle));
                g.transform(at);

                // Draw horizontal arrow starting in (0, 0)
                g.drawLine(0, 0, len, 0);
                g.fillPolygon(new int[] {len, len-ARR_SIZE, len-ARR_SIZE, len},
                              new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
            }
}

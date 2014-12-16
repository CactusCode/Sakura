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
public class Convoyeur implements java.io.Serializable
{
  
    private final PlantComponant startPoint;
    private final PlantComponant endPoint;
    private final Elbow elbow1;
    private final Elbow elbow2;
    private final int ARR_SIZE = 9;
    private int exitAssociated;
    private Color planColor;
    public Convoyeur(PlantComponant _start,PlantComponant _end)
    {
       this.startPoint = _start;
       this.endPoint = _end;
       this.elbow1 = new Elbow(startPoint.getPosition(),endPoint.getPosition(),this,3);
       this.elbow2 = new Elbow(elbow1.getPosition(),endPoint.getPosition(),this,2);
       this.exitAssociated = 0;
       this.planColor = Color.BLACK;
    }   
    public PlantComponant getStartComponant(){
        return this.startPoint;
    }
    public Elbow getElbow(int x){
        if(x == 1){
            return this.elbow1;
        }
        else return this.elbow2;
       
    }
    public void setExitAssociated(int _exitNumber){
        this.exitAssociated = _exitNumber;
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
     public void setColor(Color newColor)
    {
        this.planColor = newColor ;
    }
     public void draw(Graphics g, int _fakeX, int _fakeY, float _zoom, Point _start, Point _end) {
        g.setColor(planColor);
        g.drawLine(_start.x+(int)(_fakeX*_zoom),_start.y+(int)(_fakeY*_zoom),
                   elbow1.getPosition().x+(int)(_fakeX*_zoom), elbow1.getPosition().y+(int)(_fakeY*_zoom));

        elbow1.draw(g, _fakeX, _fakeY);
        g.setColor(planColor);
        g.drawLine(elbow1.getPosition().x+(int)(_fakeX*_zoom),elbow1.getPosition().y+(int)(_fakeY*_zoom),
                   elbow2.getPosition().x+(int)(_fakeX*_zoom), elbow2.getPosition().y+(int)(_fakeY*_zoom));
        elbow2.draw(g, _fakeX, _fakeY);
        g.drawString("Sortie #"+this.exitAssociated,(elbow1.getPosition().x+(int)(_fakeX*_zoom))-15,(elbow1.getPosition().y+(int)(_fakeY*_zoom))-10);
        this.drawArrow(g, elbow2.getPosition().x+(int)(_fakeX*_zoom),elbow2.getPosition().y+(int)(_fakeY*_zoom),
                   _end.x+(int)(_fakeX*_zoom), _end.y+(int)(_fakeY*_zoom));
         g.drawString("Sortie #"+this.exitAssociated,(elbow2.getPosition().x+(int)(_fakeX*_zoom))-15,(elbow2.getPosition().y+(int)(_fakeY*_zoom))-10);
        //elbow.draw(g, _fakeX, _fakeY);
    }
     void drawArrow(Graphics g1, int x1, int y1, int x2, int y2) {
                Graphics2D g = (Graphics2D) g1.create();
                g.setColor(planColor);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Convoyeur;
import Model.PlantComponant;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Pascal
 */
public class Plan extends JPanel
{
    private final int composantH = 12;
    private final int composantW = 12;
    
    public float fakeX = 0;
    public float fakeY = 0;
    
        public boolean grilleActive;
  
        private MainWindow parent;
        
    private Point centerPoint;
    private float zoomFactor = 1;
    
    private Point mousePosition = new Point();
        
    public Plan()
    {
        centerPoint = new Point(getWidth()/2, getHeight()/2);
    }
    
    public void setParent(MainWindow _parent)
    {
        parent = _parent;
    }
    
    public void setMousePosition(Point _position)
    {
        mousePosition = _position;
    }
    
    
    public void paintPanel(ArrayList<PlantComponant> _components, ArrayList<Convoyeur> _convoyeurs, Graphics g)
    {
        paintComponent(g);
        
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, this.getWidth()-1, this.getHeight()-1);
        
        //g.fillOval((getWidth()/2)-5, (getHeight()/2)-5, 10, 10);
        
        //MainWindow.grid.draw(g, getWidth(), getHeight());
        
        centerPoint = new Point((getWidth()/2), (getHeight()/2));
        
        MainWindow.grid.draw(g, getWidth(), getHeight(), zoomFactor);
        
        //Point temp;
        for (PlantComponant _component : _components) {
            
            /*
            //debug lines
            temp = projectPoint(_component.getPosition());
            g.setColor(Color.blue);
            g.drawLine(centerPoint.x, centerPoint.y, temp.x+(int)(fakeX*zoomFactor), temp.y+(int)(fakeY*zoomFactor));
            g.setColor(Color.green);
            g.drawLine(centerPoint.x, centerPoint.y, _component.getPosition().x+(int)fakeX, _component.getPosition().y+(int)fakeY);
            //debug lines
            */
            int xZoomMod = distanceX(_component.getPosition(), projectPoint(_component.getPosition()));
            int yZoomMod = distanceY(_component.getPosition(), projectPoint(_component.getPosition()));
            
            _component.draw(g, (int)(fakeX*zoomFactor) - xZoomMod, (int)(fakeY*zoomFactor) - yZoomMod);
            //_component.draw(g, (int)fakeX, (int)fakeY);
            
        }
        for (Convoyeur _convoyeur : _convoyeurs) {
            _convoyeur.draw(g, (int)fakeX, (int)fakeY, zoomFactor, projectPoint(_convoyeur.getStartPosition()), projectPoint(_convoyeur.getEndPosition()));
        }
        
    }
    
        public Point projectPoint(Point _end)
    {
        centerPoint = new Point((getWidth()/2), (getHeight()/2));
        
        double triangleH = (_end.x) - centerPoint.x;
        double triangleV = (_end.y) - centerPoint.y;
        
        Point projection = new Point(centerPoint.x + (int)(triangleH*zoomFactor), centerPoint.y + (int)(triangleV*zoomFactor));
        
        return projection;
    }
    
    public int distanceX(Point _start, Point _end)
    {
        return _start.x - _end.x;
    }
    
    public int distanceY(Point _start, Point _end)
    {
        return _start.y - _end.y;
    }
    
    public void zoom(float _factor)
    {
        zoomFactor = _factor;
    }
    
    public float getZoomFactor()
    {
        return zoomFactor;
    }
    
    public Point getConterPoint()
    {
        return centerPoint;
    }
}

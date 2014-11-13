/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.awt.Point;

/**
 *
 * @author Vale
 */
public class Convoyeur extends PlantComponant 
{
  
    private PlantComponant startPoint;
    private PlantComponant endPoint;
    
    public Convoyeur(PlantComponant _start,PlantComponant _end)
    {
       this.startPoint = _start;
       this.endPoint = _end;
    }   
    
    public Point getStartPosition()
    {
        return this.startPoint.getPosition();
    }
     public Point getEndPosition()
    {
        return this.endPoint.getPosition();
    }
}

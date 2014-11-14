/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.awt.Color;
import java.awt.Graphics;


/**
 *
 * @author Vincent
 */
public class Station extends PlantComponant
{
    public RecoveryMatrix recoveryMatrix;
    private int numberOfExits;
    private int drawSize = 10;
    
    public Station()
    {    
        this.setDescription("Station");
        this.recoveryMatrix = new RecoveryMatrix();
        this.numberOfExits = 0;
    }
    public void setNumberOfExits(int _numberOfExits)
    {
        this.numberOfExits = _numberOfExits;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.drawOval((int)(position.getX()-drawSize/2), (int)(position.getY()-drawSize/2), drawSize, drawSize);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

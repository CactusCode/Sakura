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
    private Color planColor = Color.BLUE;
    
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
        g.setColor(planColor);
        g.drawOval((int)(position.x-drawSize/2), (int)(position.y-drawSize/2), drawSize, drawSize);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.awt.geom.Point2D;

/**
 *
 * @author Vincent
 */
public class Station extends PlantComponant
{
    public RecoveryMatrix recoveryMatrix;
    
    public Station(Point2D _position)
    {
        this.setPosition(_position);
        this.setDescription("Station");
        this.recoveryMatrix = new RecoveryMatrix();
    }
}

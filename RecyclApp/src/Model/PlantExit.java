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
public class PlantExit extends PlantComponant
{
    public PlantExit ()
    {
        this.setDescription("Sortie Usine");
        this.planColor = Color.ORANGE;
        this.exitIsConnected = true;
        this.numberOfExits = 0;
    }

   
}

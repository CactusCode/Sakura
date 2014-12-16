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
 * @author vincentguillemette
 */
public class Elbow extends PlantComponant{
    private final Convoyeur convoyeur;
    public Elbow(Point start, Point end,Convoyeur convoyeur,int divideFactor){
        this.description = "Coude";
        this.planColor = Color.lightGray;
        this.position = new Point(start.x+((end.x-start.x)/divideFactor), start.y+((end.y-start.y)/divideFactor));
        this.convoyeur = convoyeur;
    }
    public Convoyeur getConvoyeurAssociated(){
        return convoyeur;
    }
    @Override
     public void setColor(Color newColor)
    {
        this.convoyeur.setColor(newColor);
    }
}

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
    public Elbow(Point start, Point end,Convoyeur convoyeur){
        this.description = "Coude";
        this.planColor = Color.lightGray;
        this.position = new Point((start.x+end.x)/2, (start.y+end.y)/2);
        this.convoyeur = convoyeur;
    }
    public Convoyeur getConvoyeurAssociated(){
        return convoyeur;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View.MainWindow;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Pascal
 */
public abstract class PlantComponant implements java.io.Serializable
{   
    protected Point position;
    protected float maximalCapacity;
    protected String name;
    protected String description;
    protected Color planColor;
    protected int drawSize = 20;
    protected boolean entranceIsConnected;
    protected boolean exitIsConnected;
    protected int numberOfExits;
    protected ArrayList<connectionConvoyeurExit> connectionsConvoyeursExits;
    protected int numberOfExitsConnected;
    protected float matterWeight = 0;
    public RecoveryMatrix recoveryMatrix;
    public boolean image;
    public MainWindow observer;
    
    public PlantComponant()
    {
        entranceIsConnected = false;
        exitIsConnected = false;
        name="";
        connectionsConvoyeursExits = new ArrayList<>();
        numberOfExitsConnected = 0;
        image =false;
        observer = null;
    }
    public boolean canConnectExit(){
        return numberOfExitsConnected < numberOfExits;
    }
    public void addExitConnection(){
            this.numberOfExitsConnected++;    
    }
    public void removeExitConnection()
    {
        this.numberOfExitsConnected--;
    }
    //Name accessors
    public void setName(String _name)
    {
        this.name = _name;
    }
    public String getName()
    {
        return this.name;
    }
    public boolean connectEntrance(){
        if(!entranceIsConnected){
            this.entranceIsConnected = true;
            return true;
        }
        else return false;
    }
    public void removeEntranceConnection(){
        this.entranceIsConnected = false;
    }
    public float getMatterWeight()
    {
        return matterWeight;
    }
    public void setMatterWeight(float _weight)
    {
         matterWeight = _weight;
    }
    public void addMatterWeight(float _add)
    {
        matterWeight += _add;
    }
   
    //Description accessors
    public void setDescription(String _description)
    {
        this.description = _description;
    }
    public String getDescription()
    {
        return description;
    }
    
    //positionX accessors
    public void setPosition(Point _position)
    {
        this.position = _position;
    }
    public Point getPosition()
    {
        return this.position;
    } 
    //maximalCapacity acccessors
    public void setMaximalCapacity(float _capacity)
    {
        this.maximalCapacity = _capacity;
    }
    public float getMaximalCapacity()
    {
        return this.maximalCapacity;
    }
    public void setNumberOfExists(int _numberExits)
    {
        this.numberOfExits = _numberExits;
    }
    public int getNumberOfExits()
    {
        return this.numberOfExits;
    }
    public int getDrawSize(){
        return this.drawSize;
    }
    public ArrayList<connectionConvoyeurExit> getConvoyeurList()
    {
        return connectionsConvoyeursExits;
    }
    public void draw(Graphics g, int _fakeX, int _fakeY) {
        if(image==true){
              Station station = (Station)this;
              station.drawWithImage(g,_fakeX,_fakeY);
        }
        else{
        g.setColor(planColor);
        g.fillOval((int)(position.x-drawSize/2)+_fakeX, (int)(position.y-drawSize/2)+_fakeY, drawSize, drawSize);
        g.setColor(Color.BLACK);
        g.drawString(name,(int)(position.x-drawSize/2)+_fakeX, (int)(position.y-drawSize/2)+_fakeY);
        }
        
    }
    public int linkConvoyeurWithExit(Convoyeur _convoyeur){
        this.connectionsConvoyeursExits.add(new connectionConvoyeurExit(this.connectionsConvoyeursExits.size()+1, _convoyeur));
        return this.connectionsConvoyeursExits.size();
    }
    public void reLinkConvoyeurWithExit(){
        for(int i = 1;i<=connectionsConvoyeursExits.size();i++){
            connectionsConvoyeursExits.get(i-1).exitNumber = i;
            connectionsConvoyeursExits.get(i-1).convoyeur.setExitAssociated(i)  ;
        }
    }
    public void unLinkConvoyeurWithExit(Convoyeur _convoyeur)
    {
        for(int i = 0;i<connectionsConvoyeursExits.size();i++){
            if(connectionsConvoyeursExits.get(i).convoyeur == _convoyeur){
                this.connectionsConvoyeursExits.remove(i);
            }
        }
        
    }
    public static class connectionConvoyeurExit implements Serializable{
        public int exitNumber;
        public Convoyeur convoyeur;
        public connectionConvoyeurExit(int _exitNumber, Convoyeur _convoyeur) {
            this.exitNumber= _exitNumber;
            this.convoyeur = _convoyeur;
        }
    }
    
    public RecoveryMatrix getRecoveryMatrix(){
        return this.recoveryMatrix;
    }
    public void setColor(Color newColor)
    {
        this.planColor = newColor ;
    }
}

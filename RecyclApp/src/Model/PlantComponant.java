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
    
    protected ArrayList<ConvoyeurV2> convoyeurV2List = new ArrayList<ConvoyeurV2>();
    protected ArrayList<MaterialV2> materialV2List = new ArrayList<MaterialV2>();
    protected int materialV2Received = 0;
    protected int materialV2Max = 0;
    protected RecoveryMatrixV2 matrixV2 = new RecoveryMatrixV2();
    protected int convoyeurIdentifier = 1;
    protected static float totalMaterial = 0;
    
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
    public void addMaterialV2Max()
    {
        materialV2Max++;
    }
    public void addMaterialV2Received()
    {
        materialV2Received++;
    }
    public void lowerMaterialV2Received()
    {
        materialV2Received--;
    }
    public void resetMaterialV2MaxReceived()
    {
        materialV2Received = 0;
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
    
    public void addConvoyeurV2(PlantComponant _start, PlantComponant _end)
    {
        System.out.println("adding convoyeurV2");
        ConvoyeurV2 newConvoyeurV2 = new ConvoyeurV2(_start, _end, "Sortie #" + convoyeurIdentifier);
        convoyeurV2List.add(newConvoyeurV2);
        convoyeurIdentifier++;
        _end.addMaterialV2Max();
        //matrixV2.addLine(newConvoyeurV2.getName() + "(M)");
        
    }
    
    public void removeConvoyeurV2(Point _start, Point _end)
    {
        for (int i = 0; i < convoyeurV2List.size(); i++)
        {
            if (convoyeurV2List.get(i).getStart() == _start && convoyeurV2List.get(i).getEnd() == _end)
            {
                convoyeurV2List.remove(i);
                matrixV2.removeColumn(i);
                break;
            }
        }
    }
    
    private void print(Object _value)
    {
        System.out.println(_value);
    }
    
    public void sendMaterialV2()
    {
        print("sendMaterialV2");
        for (int i = 0; i < convoyeurV2List.size(); i++)
        {
            for (int j = 0; j < matrixV2.getSize(); j++)
            {
                //if (matrixV2.getLine(j).getName().equals(convoyeurV2List.get(i).getName()))
                boolean moreToCome = true;
                if (j == matrixV2.getSize()-1)
                    moreToCome = false;
                sendLineToConvoyeur(matrixV2.getLine(j), convoyeurV2List.get(i), i, j, moreToCome);
            }
        }
    }
    
    private void sendLineToConvoyeur(RecoveryMatrixLine line, ConvoyeurV2 convoyeur, int _column, int _row, boolean moreToCome)
    {
        
        print("sendLineToConvoyeur");
        print("test");
        MaterialV2 data = new MaterialV2();
        data.setType(line.getName());
        float quantity = 0;
        print("test");
        for (int i = 0; i < materialV2List.size(); i++)
        {
            if (materialV2List.get(i).getType().equals(data.getType()))
                quantity = materialV2List.get(i).getQuantity() * (matrixV2.matrixLines.get(_row).getValueAt(_column)/100);
        }
        print("test");
        data.add(quantity);
        print("SENDING MATERIAL: " + data.getType() + " " + data.getQuantity() + " TO " + convoyeur.getName() + " FROM " + getDescription());
        convoyeur.sendData(data, moreToCome);
    }
    
    public void receiveMaterialV2(MaterialV2 _material, boolean moreToCome)
    {
        print("receiveMaterialV2");
        print ("MATERIAL RECEIVED: " + _material.getType() + " " + _material.getQuantity() + " AT " + getDescription());
        boolean added = false;
        print("THE GODDAM SIZE OF THE MATERIAL LIST " + materialV2List.size());
        for (int i = 0; i < materialV2List.size(); i++)
        {
            print("COMPARE THIS " + _material.getType());
            print("TO THIS " + materialV2List.get(i).getType());
            if (_material.getType().equals(materialV2List.get(i).getType()))
            {
                print("WAS I ALWAYS ALONE??");
                materialV2List.get(i).add(_material.getQuantity());
                added = true;
            }
        }
        
        if (!added)
        {
            print("Received new stuff");
            materialV2List.add(_material);
            matrixV2.addLine(_material.getType());
            print(_material.getType());
        }
        
        if (!moreToCome)
            addMaterialV2Received();
        if (shouldSendMaterialV2())
        {
            sendMaterialV2();
        }
    }
    
    private boolean shouldSendMaterialV2()
    {
        print("shouldSendMaterialV2");
        return materialV2Max == materialV2Received;
    }
    
    public RecoveryMatrixV2 getMatrixV2()
    {
        return matrixV2;
    }
    
    public ArrayList<ConvoyeurV2> getConvoyeurV2List()
    {
        return convoyeurV2List;
    }
    
    public void setMatrixLine(int _line, int _index, float _value)
    {
        matrixV2.setValueAt(_line, _index, _value);
    }
    
    public void setMatrixLineByName(String _name, int _index, float _value)
    {
        matrixV2.setLineByName(_name, _index, _value);
    }
    
    public float getTotalWeight()
    {
        float result = 0;
        
        for (int i = 0; i < materialV2List.size(); i++)
        {
            result += materialV2List.get(i).getQuantity();
        }
        print("total weight" + result);
        return result;
    }
    
    public float getWeightOf(String _name)
    {
        float result = 0;
        for (int i = 0; i < materialV2List.size(); i++)
        {
            if (materialV2List.get(i).getType().equals(_name))
                result = materialV2List.get(i).getQuantity();
        }
        
        return result;
    }
    
    public void emptyMaterials()
    {
        resetMaterialV2MaxReceived();
        materialV2List.clear();
    }
    
    public ArrayList<MaterialV2> getMaterialTable()
    {
        return materialV2List;
    }
    
    public void setMaterialQuantity(String _name, float _value)
    {
        for (int i = 0; i < materialV2List.size(); i++)
        {
            if (materialV2List.get(i).getType().equals(_name))
                materialV2List.get(i).setQuantity(_value);
        }
    }
    
    public boolean hasAnEntry(String _name)
    {
        boolean result = false;
        
        for (int i = 0; i < materialV2List.size(); i++)
        {
            if (materialV2List.get(i).getType().equals(_name) && materialV2List.get(i).getQuantity() != 0)
                result = true;
        }
        
        return result;
    }
    
    public static float getTotalMaterial()
    {
        System.out.println("HERES YOUR TOTAL BITCH " + totalMaterial);
        return totalMaterial;
    }
    
    public static void emptyTotalMaterial()
    {
        totalMaterial = 0.0f;
    }
    
    public void makeTotalMaterial()
    {
        if (getDescription().equals("Entrée Usine"))
            totalMaterial += getTotalWeight();
    }
    
    public float getPurityPercentage(String _name)
    {
        return (getWeightOf(_name)/getTotalWeight())*100;
    }
    
    public boolean addEntry(String _name, float _quantity)
    {
        MaterialV2 entry = new MaterialV2(_name, _quantity);
        materialV2List.add(entry);
        matrixV2.addLine(entry.getType());
        
        return true;
    }
    
    public void removeEntry(String _name)
    {
        for (int i = 0; i < materialV2List.size(); i++)
        {
            if (materialV2List.get(i).getType().equals(_name))
            {
                materialV2List.remove(i);
                break;
            }
            
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import View.*;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;


   
/**
 *
 * @author Pascal
 */

public class RecyclApp{
    
   
    static RecyclApp app;
    static MainWindow window;
    
    ArrayList <PlantComponant> plantComponantsList;
    ArrayList<Convoyeur> convoyeursList;
    Basket basket;

    /**
     * @param _window
     */
    public RecyclApp(MainWindow _window) {
        window = new MainWindow();
        window.setVisible(true);   
        this.plantComponantsList = new ArrayList<>();
        this.convoyeursList = new ArrayList<>();
        this.basket = new Basket();
      
    }
    
    public void paintPanel(Plan _plan, Graphics g)
    {
        _plan.paintPanel(plantComponantsList, convoyeursList, g);
    }
    
    public static void main(String[] args) {
   
    
    }
    public void addStation(Point _position, int _nombreSortie) {
        if(positionAvailable(_position))
        {   
            Station station = new Station();
            station.setPosition(_position);
            station.setNumberOfExits(_nombreSortie);
            this.plantComponantsList.add(station);
            window.messageToUser("Station Ajoutée");
            window.redrawPlan();
        }
        else window.messageToUser("Il existe déjà un élément à cet endroit!");
    }
    public void addJunction(Point _position) {
        if(positionAvailable(_position))
        {   
            Junction junction = new Junction();
            junction.setPosition(_position);
            this.plantComponantsList.add(junction);
            window.messageToUser("Jonction Ajoutée");
            window.redrawPlan();
        }
        else window.messageToUser("Il existe déjà un élément à cet endroit!");
    }
    public void addEntrance(Point _position) {
        for(PlantComponant planComponantsList1 : this.plantComponantsList){
            if("Entrée Usine".equals(planComponantsList1.getDescription())){
                window.messageToUser("Il ne peut y avoir qu'une entrée!");
                return;
            }
        } 
        if(positionAvailable(_position))
        {   
            PlantEntrance entrance = new PlantEntrance();
            entrance.setPosition(_position);
            this.plantComponantsList.add(entrance);
            window.messageToUser("Entrée Ajoutée");
            window.redrawPlan();
        }
        else window.messageToUser("Il existe déjà un élément à cet endroit!");
    }
    public void addExit(Point _position) {
        for(PlantComponant planComponantsList1 : this.plantComponantsList){
            if("Sortie Usine".equals(planComponantsList1.getDescription())){
                window.messageToUser("Il ne peut y avoir qu'une sortie!");
                return;
            }
        } 
        if(positionAvailable(_position))
        {   
            PlantExit exit = new PlantExit();
            exit.setPosition(_position);
            this.plantComponantsList.add(exit);
            window.messageToUser("Sortie Ajoutée");
            window.redrawPlan();
        }
        else window.messageToUser("Il existe déjà un élément à cet endroit!");
    }
    
    public void addConvoyeur(Point _start,Point _end){
        PlantComponant startComponant = null;
        PlantComponant endComponant = null;
        
        for (PlantComponant plantComponantsList1 : this.plantComponantsList) {
            if(occupiedPosition(plantComponantsList1, _start))
            {
               if(!plantComponantsList1.connectExit() && !"Station".equals(plantComponantsList1.getDescription())){
                   window.messageToUser("L'élement de départ ne peut plus prendre de convoyeur en sortie!");
                   return;
               }
               else if("Station".equals(plantComponantsList1.getDescription())){
                   Station station = (Station)plantComponantsList1;
                   if(!station.addExitConnection()){
                    window.messageToUser("L'élement de départ ne peut plus prendre de convoyeur en sortie!");
                    return;
                   }
               }
               startComponant = plantComponantsList1;
            }
        }
        for (PlantComponant plantComponantsList1 : this.plantComponantsList) {
            if(occupiedPosition(plantComponantsList1, _end))
            {
               if(!plantComponantsList1.connectEntrance() && !"Jonction".equals(plantComponantsList1.getDescription())){
                   window.messageToUser("L'élement d'arrivé ne peut plus prendre de convoyeur en entré!");
                   return;
               }
               endComponant = plantComponantsList1;
            }
        }
        if (startComponant == null || endComponant == null){
            window.messageToUser("Le point de départ ou d'arriver n'est pas un élément valide pour un convoyeur!");
        }
        else{
            Convoyeur convoyeur = new Convoyeur(startComponant, endComponant);
            this.convoyeursList.add(convoyeur);
            window.messageToUser("Convoyeur Ajoutée");
            window.redrawPlan();
        }
    }
    
    public void getContextInfo(Point _position) {
        for (PlantComponant plantComponantsList1 : this.plantComponantsList) {
            if(occupiedPosition(plantComponantsList1, _position))
            {
                window.setContextInfo(plantComponantsList1.getDescription(), plantComponantsList1.getPosition());
            }
        }
        
    }
    private boolean occupiedPosition(PlantComponant _componant, Point _position) {
        return Math.abs(_componant.getPosition().x-_position.getX())<=_componant.getDrawSize()/2 && Math.abs(_componant.getPosition().y-_position.getY())<=_componant.getDrawSize()/2;
    }
    
    public boolean positionAvailable(Point2D _position){
        for (PlantComponant plantComponantsList1 : this.plantComponantsList) {
            if (Math.abs(plantComponantsList1.getPosition().x-_position.getX())<=plantComponantsList1.getDrawSize() && Math.abs(plantComponantsList1.getPosition().y-_position.getY())<=plantComponantsList1.getDrawSize()) {
                return false;
            }
        }
        return true;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import View.*;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;


   
/**
 *
 * @author Pascal
 */
public class RecyclApp implements ViewListener{
    
   
    static RecyclApp app;
    static MainWindow window;
    
    ArrayList <PlantComponant> plantComponantsList;
    Basket basket;
    PlantEntrance plantEntrance;
    PlantExit plantExit;
    /**
     */
    public RecyclApp() {
        window = new MainWindow();
        window.setVisible(true);
        
        this.plantComponantsList = new ArrayList<>();
        this.basket = new Basket();
        this.plantEntrance = new PlantEntrance();
        this.plantExit = new PlantExit();
    }
    
    public static void main(String[] args) {
   
        app = new RecyclApp();
        window.addListener(app);
        
    }


    @Override
    public void addStation(Point _position) {
        if(positionAvailable(_position))
        {   
            Station station = new Station();
            station.setPosition(_position);
            this.plantComponantsList.add(station);
            window.messageToUser("Station Ajoutée");
            window.addStationToPlan(station.getPosition());
        }
        else window.messageToUser("Il existe déjà un élément à cet endroit!");
    }
    

    @Override
    public void placeExit(Point _position) {
        if(positionAvailable(_position))
        {   
            this.plantExit.setPosition(_position);
            window.messageToUser("Sortie positionnée");         
        }
        else window.messageToUser("Il existe déjà un élément à cet endroit!");   
    }

    @Override
    public void placeEntrance(Point _position) {
         if(positionAvailable(_position))
        {   
            this.plantEntrance.setPosition(_position);
            window.messageToUser("Entrée positionnée");         
        }
        else window.messageToUser("Il existe déjà un élément à cet endroit!"); 
       
    }
    public boolean positionAvailable(Point2D _position){
        for (PlantComponant plantComponantsList1 : this.plantComponantsList) {
            if (plantComponantsList1.getPosition() == _position) {
                return false;
            }
        }
        return true;
    }
}

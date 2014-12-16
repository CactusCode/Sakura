/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MaterialV2;
import Model.*;
import View.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.awt.Point;
import javax.swing.JColorChooser;
import javax.swing.JTable;


   
/**
 *
 * @author Pascal
 */

public class RecyclApp implements java.io.Serializable {
    
   
    static RecyclApp app;
    static MainWindow window;
    
    ArrayList<PlantComponant> plantComponantsList;
    ArrayList<Convoyeur> convoyeursList;
    Basket basket;
    int focusIndex;
    
        ArrayList<PlantComponant> componentCycleCheckList;
    
    boolean hasCircuit = false;

    /**
     * @param _window
     */
    public RecyclApp(MainWindow _window) {
        window = new MainWindow();
        window.setVisible(true);   
        this.plantComponantsList = new ArrayList<>();
        this.convoyeursList = new ArrayList<>();
        this.basket = new Basket();
        
        componentCycleCheckList = new ArrayList<PlantComponant>();
      
    }
    
    public void paintPanel(Plan _plan, Graphics g)
    {
        _plan.paintPanel(plantComponantsList, convoyeursList, g);
    }
    
    public static void main(String[] args) {
   
    
    }
    
    private Point checkMagnetPosition(Point _position)
    {
        if (window.grid.isMagnet())
        {
            int tempX = _position.x;
            int tempY = _position.y;
            
            tempX = tempX % window.grid.getSize();
            tempY = tempY % window.grid.getSize();
            
            if (tempX <= window.grid.getSize()/2)
            {
                _position.x -= tempX;
            }
            else
            {
                _position.x += window.grid.getSize() - tempX;
            }
            
            if (tempY <= window.grid.getSize()/2)
            {
                _position.y -= tempY;
            }
            else
            {
                _position.y += window.grid.getSize() - tempY;
            }
        }
        
        return _position;
    }
    
    public void addStation(Point _position, int _nombreSortie) {
        _position = checkMagnetPosition(_position);
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
        _position = checkMagnetPosition(_position);
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
        _position = checkMagnetPosition(_position);
        /*for(PlantComponant planComponantsList1 : this.plantComponantsList){
            if("Entrée Usine".equals(planComponantsList1.getDescription())){
                window.messageToUser("Il ne peut y avoir qu'une entrée!");
                return;
            }
        } */
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
        _position = checkMagnetPosition(_position);
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
    public void addElbow(Elbow elbow) {
        
            this.plantComponantsList.add(elbow);
            
    }
    
    public void addConvoyeur(Point _start,Point _end){
        PlantComponant startComponant = null;
        PlantComponant endComponant = null;
        
        for (PlantComponant plantComponantsList1 : this.plantComponantsList) {
            if(occupiedPosition(plantComponantsList1, _start))
            {
               if(!plantComponantsList1.canConnectExit()){
                   window.messageToUser("L'élément de départ ne peut plus prendre de convoyeur en sortie!");
                   return;
               }
             
               startComponant = plantComponantsList1;
            }
        }
        for (PlantComponant plantComponantsList1 : this.plantComponantsList) {
            if(occupiedPosition(plantComponantsList1, _end))
            {
               if(!plantComponantsList1.connectEntrance() && !"Jonction".equals(plantComponantsList1.getDescription())){
                   window.messageToUser("L'élément d'arrivé ne peut plus prendre de convoyeur en entrée!");
                   return;
               }
               endComponant = plantComponantsList1;
            }
        }
        if (startComponant == null || endComponant == null){
            window.messageToUser("Le point de départ ou d'arrivé n'est pas un élément valide pour un convoyeur!");
        }
        else if (startComponant ==endComponant){
            window.messageToUser("Le point de départ et d'arrivé est le même!");
        }
        else{
            startComponant.addExitConnection();
            Convoyeur convoyeur = new Convoyeur(startComponant, endComponant);
            int exitNumber = startComponant.linkConvoyeurWithExit(convoyeur);
            convoyeur.setExitAssociated(exitNumber);
            this.convoyeursList.add(convoyeur);
            this.plantComponantsList.add(convoyeur.getElbow(1));
            this.plantComponantsList.add(convoyeur.getElbow(2));
            if (circuitIsValid())
                window.messageToUser("Convoyeur Ajouté");
            else
            {
                this.convoyeursList.remove(convoyeur);
                startComponant.removeExitConnection();
                startComponant.unLinkConvoyeurWithExit(convoyeur);
                window.messageToUser("Un cycle est crée dans le circuit, opération annulé");
            }
            
            //convoyeurV2 method
            startComponant.addConvoyeurV2(startComponant, endComponant);
            
            window.redrawPlan();
        }
    }
    
    public void getContextInfo(Point _position) 
    {
                window.setEditButtonEnable(false);
        for (int i = 0; i<plantComponantsList.size(); i++)
        {
            if(occupiedPosition(plantComponantsList.get(i), _position))
            {
                if("Station".equals(plantComponantsList.get(i).getDescription())){
                    
                    Station station = (Station)plantComponantsList.get(i);
                    window.setContextInfo(station.getDescription(), station.getPosition(),station.getNumberOfExits(), station.getName(),station.getMaximalCapacity(),station.getRecoveryMatrix(),null, Float.toString(plantComponantsList.get(i).getMatterWeight()));
                    window.setMatrixV2(plantComponantsList.get(i));
                    focusIndex = i;
                    break;
                }
                if("Entrée Usine".equals(plantComponantsList.get(i).getDescription())){
                    
                    PlantEntrance planEntrance  = (PlantEntrance)plantComponantsList.get(i);
                    window.setContextInfo(planEntrance.getDescription(), planEntrance.getPosition(),planEntrance.getNumberOfExits(), planEntrance.getName(),planEntrance.getMaximalCapacity(),null,planEntrance.getBasket(), Float.toString(plantComponantsList.get(i).getMatterWeight()));
                    window.setMatrixV2(plantComponantsList.get(i));
                    focusIndex = i;
                    break;
                }
                else {
                    window.setContextInfo(plantComponantsList.get(i).getDescription(), plantComponantsList.get(i).getPosition(),plantComponantsList.get(i).getNumberOfExits(), plantComponantsList.get(i).getName(),plantComponantsList.get(i).getMaximalCapacity(),null,null, Float.toString(plantComponantsList.get(i).getMatterWeight()));
                    window.setMatrixV2(plantComponantsList.get(i));
                    focusIndex = i;
                    break;
                }
            }
            else
            {
                window.setContextInfo("", new Point(), 0, "", 0, new RecoveryMatrix(),null, "0");
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

    public void setCapMax(float _positionX, float _positionY, float _cap) {
      for(PlantComponant plantComponantList1 : this.plantComponantsList){
          if(plantComponantList1.getPosition().x == _positionX && plantComponantList1.getPosition().y == _positionY){
              plantComponantList1.setMaximalCapacity(_cap);
          }
      }
    }

    public void setName(float _positionX, float _positionY, String _nom) 
    {
        for(PlantComponant plantComponantList1 : this.plantComponantsList)
        {
          if(plantComponantList1.getPosition().x == _positionX && plantComponantList1.getPosition().y == _positionY){
              plantComponantList1.setName(_nom);
              window.redrawPlan();
        }
        /*if(plantComponantList1.getName()!= "")
        {
            //redraw;
        }*/
      }
    }
    public void setFocusIndex(int _focus)
    {
        focusIndex = _focus;
    }
    
    public int getFocusIndex()
    {
        return focusIndex;
    }
  
    public void deleteFocus()
    {
        for(int i=convoyeursList.size()-1;i >= 0;i--)
        {
  
            if (convoyeursList.get(i).getStartComponant()== plantComponantsList.get(focusIndex)||
                convoyeursList.get(i).getEndComponant()== plantComponantsList.get(focusIndex))      
            {
                //libérer la sortie qui de la station
                PlantComponant plantComp = convoyeursList.get(i).getStartComponant();
                plantComp.removeExitConnection();
                plantComp.unLinkConvoyeurWithExit(convoyeursList.get(i));
                plantComp = convoyeursList.get(i).getEndComponant();
                plantComp.removeEntranceConnection();
                
                this.plantComponantsList.remove(convoyeursList.get(i).getElbow(1));
                this.plantComponantsList.remove(convoyeursList.get(i).getElbow(2));
                convoyeursList.remove(i);
            }
            
         
        }
        if("Coude".equals(plantComponantsList.get(focusIndex).getDescription())){
            
            Elbow coude = (Elbow)plantComponantsList.get(focusIndex);
            PlantComponant plantComp = coude.getConvoyeurAssociated().getStartComponant();
            plantComp.removeExitConnection();
            plantComp.unLinkConvoyeurWithExit(coude.getConvoyeurAssociated());
            plantComp.reLinkConvoyeurWithExit();
            plantComp = coude.getConvoyeurAssociated().getEndComponant();
            plantComp.removeEntranceConnection();
            Convoyeur convoyeur = coude.getConvoyeurAssociated();
            this.convoyeursList.remove(convoyeur);
            
            plantComponantsList.remove(convoyeur.getElbow(1));
            plantComponantsList.remove(convoyeur.getElbow(2));
        }
        else{
            plantComponantsList.remove(focusIndex);
        }
        window.redrawPlan();
    }

    public void setMatrix(Material.MaterialType type, int exitNumber, float pourcentage, double posx, double posy) {
        for(PlantComponant plantComponantList1 : this.plantComponantsList){
          if(plantComponantList1.getPosition().x == posx && plantComponantList1.getPosition().y == posy){
             Station station = (Station) plantComponantList1;
             station.AddLineToMatrix(type,exitNumber,pourcentage);
          }
      } 
    }
    public void setNewPosition(float _positionX, float _positionY, Point _newPosition) //déplacer élément sur le plan
    {
	for(PlantComponant plantComponant1 : this.plantComponantsList)
	{
            if (! positionAvailable(_newPosition))
            {
                window.messageToUser("Position déjà occupé!");
            }
            if(plantComponant1.getPosition().x == _positionX && plantComponant1.getPosition().y == _positionY
                            && positionAvailable(_newPosition))
            {
                if (window.grid.isMagnet())								 // verifier que la grille est magnétique
                {
                        _newPosition = checkMagnetPosition(_newPosition);
                }
                plantComponant1.setPosition(_newPosition);
                this.getContextInfo(_newPosition);
                 window.messageToUser("Élément déplacé");
                 window.redrawPlan();
            }
	}
    }  

    public void clearMatrix(double posx, double posy) {
        for(PlantComponant plantComponant1 : this.plantComponantsList){
             if(plantComponant1.getPosition().x == posx && plantComponant1.getPosition().y == posy){
                 Station station = (Station)plantComponant1;
                 station.clearMatrix();
             }
        }
    }
    
    public void calculatePasingWeight()
    {
        componentCycleCheckList.clear();
        for (int i = 0; i < plantComponantsList.size(); i++)
        {
            if (plantComponantsList.get(i).getDescription() == "Entrée Usine")
            {
                componentCycleCheckList.add(plantComponantsList.get(i));
                break;
            }
        }
        
        if (componentCycleCheckList.size() != 0)
        {
            cleanDataPlan();
            
            componentCycleCheckList.clear();
            for (int i = 0; i < plantComponantsList.size(); i++)
            {
                if (plantComponantsList.get(i).getDescription() == "Entrée Usine")
                {
                    componentCycleCheckList.add(plantComponantsList.get(i));
                    break;
                }
            }
            System.out.println("LALALALALALA");
            sendDataThroughPlan(0);
        }
    }
    
    private void cleanDataPlan()
    {
        if (componentCycleCheckList.size()-1 != 0)
        {
            componentCycleCheckList.get(componentCycleCheckList.size()-1).setMatterWeight(0);
        }
        
            for (int i = 0; i < componentCycleCheckList.get(componentCycleCheckList.size()-1).getConvoyeurList().size(); i++)
            {
                componentCycleCheckList.add(componentCycleCheckList.get(componentCycleCheckList.size()-1).getConvoyeurList().get(i).convoyeur.getEndComponant());
                cleanDataPlan();
                componentCycleCheckList.remove(componentCycleCheckList.size()-1);
            }
    }
    
    private void sendDataThroughPlan(int _exitIndex)
    {
        System.out.println("function");
        if (componentCycleCheckList.size()-1 != 0)
        {
            if (componentCycleCheckList.get(componentCycleCheckList.size()-2).getDescription() == "Station")
            { 
                System.out.println("from station");
                float toAdd = componentCycleCheckList.get(componentCycleCheckList.size()-2).getMatterWeight();
                toAdd *= (componentCycleCheckList.get(componentCycleCheckList.size()-2).getRecoveryMatrix().getMatrix().get(_exitIndex).pourcentage/100);
                componentCycleCheckList.get(componentCycleCheckList.size()-1).addMatterWeight(toAdd);
                System.out.println(_exitIndex);
                System.out.println(toAdd);
            }
            else
            {

                float toAdd = componentCycleCheckList.get(componentCycleCheckList.size()-2).getMatterWeight();
                componentCycleCheckList.get(componentCycleCheckList.size()-1).addMatterWeight(toAdd);
                                System.out.println("NOT from station");
                System.out.println(toAdd);
            }
        }

        
        for (int i = 0; i < componentCycleCheckList.get(componentCycleCheckList.size()-1).getConvoyeurList().size(); i++)
        {
            componentCycleCheckList.add(componentCycleCheckList.get(componentCycleCheckList.size()-1).getConvoyeurList().get(i).convoyeur.getEndComponant());
            sendDataThroughPlan(i);
            componentCycleCheckList.remove(componentCycleCheckList.size()-1);
        }
    }
    
    public boolean circuitIsValid()
    {
        hasCircuit = false;
        
        componentCycleCheckList.clear();
        
        for (int i = 0; i < plantComponantsList.size(); i++)
        {
            if (plantComponantsList.get(i).getDescription() == "Entrée Usine")
            {
                componentCycleCheckList.add(plantComponantsList.get(i));
                break;
            }
        }
        
        if (componentCycleCheckList.size() != 0)
            checkCircuit();

        return !hasCircuit;
    }
    
    private void checkCircuit()
    {
        if (!hasCircuit)
        {
            checkForDuplicate();
            
            for (int i = 0; i < componentCycleCheckList.get(componentCycleCheckList.size()-1).getConvoyeurList().size(); i++)
            {
                componentCycleCheckList.add(componentCycleCheckList.get(componentCycleCheckList.size()-1).getConvoyeurList().get(i).convoyeur.getEndComponant());
                checkCircuit();
                componentCycleCheckList.remove(componentCycleCheckList.size()-1);
            }
        }
    }
    
    private void checkForDuplicate()
    {
        for (int i = 0; i < componentCycleCheckList.size(); i++)
        {
            if (!hasCircuit)
            {
                for (int j = i+1; j < componentCycleCheckList.size(); j++)
                {
                    if (componentCycleCheckList.get(i).getPosition() == componentCycleCheckList.get(j).getPosition())
                    {
                        hasCircuit = true;
                        break;
                    }
                }
            }
        }
    }

    public void stationImage() {
        
        for(PlantComponant plantComponant1 : this.plantComponantsList){
            if(plantComponant1 == plantComponantsList.get(focusIndex)){
                if("Station".equals(plantComponant1.getDescription())){
                    Station station = (Station)plantComponant1;
                    station.drawImage(window);
                }
            }
            
        }
    }
    public void getColor()
    {
        for (int i = 0; i<plantComponantsList.size(); i++)
        {
            if(plantComponantsList.get(i) ==plantComponantsList.get(focusIndex))
            {
               plantComponantsList.get(i).setColor(JColorChooser.showDialog(null, null, Color.BLUE));
            }
        }
    }

    private void print(Object _value)
    {
        System.out.println(_value);
    }
    
    public boolean sendMaterial(Point _identifier, MaterialV2 _material, boolean moreToCome)
    {
        
        boolean result = false;
        for (int i = 0; i < plantComponantsList.size(); i++)
        {
            print(_identifier);
            print(plantComponantsList.get(i).getPosition());
            if (equalPositions(plantComponantsList.get(i).getPosition(), _identifier))
            {
                plantComponantsList.get(i).receiveMaterialV2(_material, moreToCome);
                result = true;
                break;
            }
        }
        
        return result;
    }
    
    private boolean equalPositions(Point _pos1, Point _pos2)
    {
        boolean result = true;
        
        if (_pos1.x != _pos2.x || _pos1.y != _pos2.y)
            result = false;
        
        return result;
    }

    public void setFocusComponantMatrixLine(int _line, int _index, float _value)
    {
        plantComponantsList.get(focusIndex).setMatrixLine(_line, _index, _value);
    }
    
    public void setFocusComponantMatrixLineByName(String _name, int _index, float _value)
    {
        plantComponantsList.get(focusIndex).setMatrixLineByName(_name, _index, _value);
    }
    
    public void runTest()
    {
        PlantComponant.emptyTotalMaterial();
        
        for (int i = 0; i < plantComponantsList.size(); i++)
        {
            plantComponantsList.get(i).makeTotalMaterial();
        }
        
        for (int i = 0; i < plantComponantsList.size(); i++)
        {
           print("HHHH" + plantComponantsList.get(i).getDescription() + "HHHH");
           if (!plantComponantsList.get(i).getDescription().equals("Entrée Usine"))
           {
               print("HHHH" + plantComponantsList.get(i).getDescription() + "HHHH");
               plantComponantsList.get(i).emptyMaterials();
           }
        }
        for (int i = 0; i < plantComponantsList.size(); i++)
        {
            if (plantComponantsList.get(i).getDescription().equals("Entrée Usine"))
            {
                print (plantComponantsList.get(i).getDescription() + " IS SENDING STUFF");
                plantComponantsList.get(i).sendMaterialV2();
            }
        }
    }
    
    public PlantComponant getFocusComponant()
    {
        return plantComponantsList.get(focusIndex);
    }
    
    public void applyTableToComponant(JTable matrixV2, JTable entryTable)
    {
        boolean validLine = true;
        float lineTotal = 0.0f;
        
        for (int i = 0; i < matrixV2.getRowCount(); i++)
        {
            lineTotal = 0.0f;
            for (int j = 1; j < matrixV2.getColumnCount(); j++)
            {
                lineTotal += Float.parseFloat(matrixV2.getValueAt(i, j).toString());
            }
            if (lineTotal != 100.0f)
            {
                validLine = false;
            }
        }
        
        if (validLine && matrixV2.getColumnCount() > 1 && !getFocusComponant().getDescription().equals("Jonction") && !getFocusComponant().getDescription().equals("Entrée Usine"))
        {
            for (int i = 1; i < matrixV2.getColumnCount(); i++)
            {
                for (int j = 0; j < matrixV2.getRowCount(); j++)
                {
                    float matrixValue = Float.parseFloat(matrixV2.getValueAt(j, i).toString());
                    String name = matrixV2.getValueAt(j, 0).toString();
                    setFocusComponantMatrixLine(j, i, matrixValue);
                    //setFocusComponantMatrixLineByName(name, i, matrixValue);
                }
            }
        }
        if (!validLine)
        {
            print("invalid line");
        }
        
        if (getFocusComponant().getDescription().equals("Entrée Usine"))
        {
            for (int i = 0; i < entryTable.getRowCount(); i++)
            {
                String name = entryTable.getValueAt(i, 0).toString();
                float newQuantity = Float.parseFloat(entryTable.getValueAt(i, 1).toString());
                getFocusComponant().setMaterialQuantity(name, newQuantity);
            }
        }
    }
    
    public String parseTableValue(float _value)
    {
        String valueCutOff = Float.toString(_value);
        if  (valueCutOff.length() > 5)
            valueCutOff = valueCutOff.substring(0, 5);
        
        return valueCutOff;
    }
    
    public boolean addEntry(String _name, String _quantity)
    {
        float quantityFloat = Float.parseFloat(_quantity);
        
        getFocusComponant().addEntry(_name, quantityFloat);
        
        return true;
    }
    
    public void removeEntry(String _name)
    {
        getFocusComponant().removeEntry(_name);
    }
}

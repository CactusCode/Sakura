/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.util.ArrayList;

/**
 *
 * @author Vale
 */
public class Junction extends ComposanteUsine
{
   private ArrayList<String> inputArray;
   private String outputConvoyeur;
    //private static List ConvoyeursEntree = new ArrayList<Junction>();
    
    public Junction()
    {
        inputArray = new ArrayList<String>();
        outputConvoyeur = "";
    }
    public Junction(ArrayList<String> listConvoyeurs, String OneExitConvoyeur)
    {
        //inputArray = listConvoyeurs;
        // copier élément par élément ou simplement copier la structure ?
       for(int i = 0; i < listConvoyeurs.size(); i++)
       {
           inputArray.add(listConvoyeurs.get(i));
       }
       outputConvoyeur = OneExitConvoyeur;
    }
    
    public ArrayList<String> getInput()
    {
        return inputArray;
    }
    
    public String getOutput()
    {
        return outputConvoyeur;
    }
    
    public void setInput(ArrayList<String> _listConvoyeur)
    {   
        inputArray = _listConvoyeur;
    }
    
    public void setOutput(String _exit)
    {   
        outputConvoyeur = _exit;
    }

}

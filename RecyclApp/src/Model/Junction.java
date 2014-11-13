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
public class Junction extends PlantComponant
{
   private ArrayList<Convoyeur> inputConvoyeurs;
   private Convoyeur outputConvoyeur;
  
    public Junction()
    {
        this.setDescription("Jonction");
        this.inputConvoyeurs = new ArrayList<>();
    }
    public void addInputConvoyeur(Convoyeur _input)
    {
        this.inputConvoyeurs.add(_input);
    }
    public void addOutputConvoyeur(Convoyeur _output)
    {
        this.outputConvoyeur = _output;
    }
    public ArrayList<Convoyeur> getInputConvoyeurList()
    {
        return this.inputConvoyeurs;
    }
    public Convoyeur getOutputConvoyeur()
    {
        return this.outputConvoyeur;
    }
}

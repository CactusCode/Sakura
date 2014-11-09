/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Pascal
 */
public class ComposanteUsine
{   
    private float positionX;
    private float positionY;
    private float maximalCapacity;
    private String name;
    private String description;

    public ComposanteUsine()
    {
        
    }
    
    //Name accessors
    public void setName(String _value)
    {
        
    }
    public String getName()
    {
        return name;
    }

    //Description accessors
    public void setDescription(String _value)
    {
        
    }
    public String getDescription()
    {
        return description;
    }
    
    //positionX accessors
    public void setPositionX(float _value)
    {
        positionX = _value;
    }
    public float getPositionX()
    {
        return positionX;
    }
    
    //positionY accessors
    public void setPositionY(float _value)
    {
        positionY = _value;
    }
    public float getPositionY()
    {
        return positionY;
    }
    
    //maximalCapacity acccessors
    public void setMaximalCapacity(float _value)
    {
        maximalCapacity = _value;
    }
    public float getMaximalCapacity()
    {
        return maximalCapacity;
    }
}

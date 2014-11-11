/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

/**
 *
 * @author Vale
 */
public class Convoyeur extends ComposanteUsine 
{
    private float point;
    public Convoyeur(float positionX, float positionY)
    {
        this.getPositionX();
        this.getPositionY();
    }
    
    public float getPosX()
    {
        return this.getPositionX();
    }
    
    public void setPoX(float _value)
    {
        this.setPositionX(_value);
    }
    
    public float getPosY()
    {
        return this.getPositionY();
    }
    
    public void setPoxY(float _value)
    {
        this.setPositionY(_value);
    }
    
    public float getMax()
    {
        return this.getMaximalCapacity();
    }
    
    public void setMax(float _max)
    {
        this.setMaximalCapacity(_max);
    }
    
    public float PositionGrille(float positionX, float positionY)
    {
        return point;
    }
}

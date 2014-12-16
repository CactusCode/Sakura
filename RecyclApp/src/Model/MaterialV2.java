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
public class MaterialV2 implements java.io.Serializable
{
    private String type;
    private float quantity;
    
    public MaterialV2()
    {
        type = "";
        quantity = 0;
    }
    
    public MaterialV2(String _type, float _quantity)
    {
        type = _type;
        quantity = _quantity;
    }
    
    public String getType()
    {
        return type;
    }
    
    public void setType(String _value)
    {
        type = _value;
    }
    
    public float getQuantity()
    {
        return quantity;
    }
    
    public void add(float _value)
    {
        quantity += _value;
        if (quantity < 0)
            quantity = 0;
    }
    
    public void setQuantity(float _value)
    {
        quantity = _value;
    }
}

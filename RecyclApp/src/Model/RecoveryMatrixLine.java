/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Pascal
 */
public class RecoveryMatrixLine implements java.io.Serializable
{
    private String type;
    private ArrayList<Float> percentage;
    private String name;
    
    public RecoveryMatrixLine(String _type, float _percentage, String _name)
    {
        type = _type;
        percentage = new ArrayList<Float>();
        name = _name;
    }
    
    public String getType()
    {
        return type;
    }
    
    public void setType(String _value)
    {
        type = _value;
    }
    
    public ArrayList<Float> getPercentageList()
    {
        return percentage;
    }
    
    //public void setPercentage(float _value)
    //{
    //    percentage = _value;
    //}
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String _value)
    {
        name = _value;
    }
    
    public float getValueAt(int _index)
    {
        if (_index >= percentage.size())
        {
            for (int i = percentage.size(); i <= _index; i++)
            {
                float value = 0;
                if (i == 0 && percentage.size() == 0)
                {
                    System.out.println("LAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
                    value = 100;
                }
                
                percentage.add(value);
            }
        }
        
        return percentage.get(_index);
    }
    
    public void setValueAt(int _index, float _value)
    {
        percentage.set(_index-1, _value);
        if (percentage.get(_index-1) < 0)
            percentage.set(_index-1, 0.0f);
    }
    
    public void removeColumn(int _index)
    {
        percentage.remove(_index);
    }
}

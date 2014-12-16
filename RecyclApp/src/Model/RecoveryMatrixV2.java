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
public class RecoveryMatrixV2 implements java.io.Serializable
{
    ArrayList<RecoveryMatrixLine> matrixLines = new ArrayList<RecoveryMatrixLine>();
    private ArrayList<String> safeList = new ArrayList<String>();
    
    public RecoveryMatrixV2()
    {
        
    }
    
    public void addLine(String _name)
    {
        if (!isSafe(_name))
            matrixLines.add(new RecoveryMatrixLine("", 0, _name));
    }
    
    //needs testing
    public void removeLine()
    {
        int matrixSize = matrixLines.size();
        matrixLines.clear();
        for (int i = 0; i < matrixSize-1; i++)
        {
            //addLine();
        }
    }
    
    public int getSize()
    {
        return matrixLines.size();
    }
    
    public RecoveryMatrixLine getLine(int _index)
    {
        RecoveryMatrixLine result = null;
        if (_index < getSize() && _index >= 0)
        {
            result = matrixLines.get(_index);
        }
        
        return result;
    }
    
    public void setValueAt(int _line, int _index, float _value)
    {
        matrixLines.get(_line).setValueAt(_index, _value);
    }
    
    public void removeColumn(int _index)
    {
        for (int i = 0; i < matrixLines.size(); i++)
        {
            matrixLines.get(i).removeColumn(_index);
        }
    }
    
    public void removeType(String _name)
    {
        for (int i = 0; i < matrixLines.size(); i++)
        {
            if (matrixLines.get(i).getName().equals(_name))
            {
                matrixLines.remove(i);
                break;
            }
        }
    }
    
    public void startCleaningProcess()
    {
        safeList.clear();
    }
    
    public void keep(String _name)
    {
        safeList.add(_name);
    }
    
    public void purge()
    {
        for (int i = matrixLines.size()-1; i >= 0; i--)
        {
            if (!isSafe(matrixLines.get(i).getName()))
            {
                matrixLines.remove(i);
            }
        }
    }
    
    private boolean isSafe(String _name)
    {
        boolean result = false;
        for (int i = 0; i < matrixLines.size(); i++)
        {
            if (matrixLines.get(i).getName().equals(_name))
            {
                result = true;
                break;
            }
        }
        return result;
    }
    
    public void setLineByName(String _name, int _index, float _value)
    {
        for (int i = 0; i < matrixLines.size(); i++)
        {
            if (matrixLines.get(i).getName().equals(_name))
                matrixLines.get(i).setValueAt(_index, _value);
        }
    }
}

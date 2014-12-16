/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.RecyclApp;
import View.MainWindow;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Pascal
 */
public class ConvoyeurV2 implements java.io.Serializable
{
    private Point start;
    private Point end;
    private String name;
    
    public ConvoyeurV2(Point _start, Point _end, String _name)
    {
        start = _start;
        end = _end;
        name = _name;
    }
    
    public Point getStart(){
        return start;
    }
    
    public Point getEnd(){
        return end;
    }
    
    public void sendData(MaterialV2 _material, boolean moreToCome)
    {
        boolean result = MainWindow.getApp().sendMaterial(end, _material, moreToCome);
        if (result)
        {
            System.out.println("Data sent with succes");
        }
        else
        {
            System.out.println("WRONG WRONG WRONG");
        }
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String _value)
    {
        name = _value;
    }
}


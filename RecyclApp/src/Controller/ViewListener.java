/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.Point;
/**
 *
 * @author Pascal
 */
public interface ViewListener
{
    public void addStation(Point _point);
    public void placeExit(Point _point);
    public void placeEntrance (Point _point);
}

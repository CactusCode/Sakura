/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.awt.Color;
/**
 *
 * @author Vincent
 */
public class PlantEntrance extends PlantComponant{
    private Basket basket;
    
    public PlantEntrance ()
    {
        this.basket = new Basket();
        basket.addContentToBasketWithMaterialAndQuantity(new Material(Material.MaterialType.product1, 10), 100);
        basket.addContentToBasketWithMaterialAndQuantity(new Material(Material.MaterialType.product2, 5), 200);
        this.setDescription("Entrée Usine");
        this.planColor = Color.PINK;
        this.entranceIsConnected = true;
        this.numberOfExits = 1;
        this.matterWeight = 2000;
    }

    public Basket getBasket()
    {
        return basket;
    }
 
}

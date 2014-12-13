/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Vincent
 */
public class Basket implements java.io.Serializable{
    
    public class MaterialQty implements Serializable
    {
        public Material material;
        public float quantity;
        public MaterialQty(Material _material,float _quantity)
        {
            this.material = _material;
            this.quantity = _quantity;
        }
    }
    
    private final ArrayList<MaterialQty> basketContents;
    
    public Basket()
    {
        basketContents = new ArrayList<>();
    }
    public ArrayList<MaterialQty> getBasketContents()
    {
        return this.basketContents;
    }
    public void addContentToBasketWithMaterialAndQuantity(Material _material, float _quantity)
    {
        this.basketContents.add(new MaterialQty(_material, _quantity));
    }
    public float getQtyInBasketForMaterialType(Material.MaterialType _type)
    {
        for (MaterialQty basketContent : this.basketContents) {
            if (basketContent.material.materialType == _type) {
                return basketContent.quantity;
            }
        }
        return 0;
    }
}

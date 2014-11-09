/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.util.ArrayList;

/**
 *
 * @author Vincent
 */
public class Basket {
    
    public class MaterialQty 
    {
        public Material material;
        public float quantity;
        public MaterialQty(Material _material,float _quantity)
        {
            this.material = _material;
            this.quantity = _quantity;
        }
    }
    
    private ArrayList<MaterialQty> basketContents;
    
    public Basket()
    {
        basketContents = new ArrayList<MaterialQty>();
    }
    public ArrayList<MaterialQty> getBasketContents()
    {
        return this.basketContents;
    }
    public void addContentToBasketWithMaterialAndQuantity(Material _material, Float _quantity)
    {
        this.basketContents.add(new MaterialQty(_material, _quantity));
    }
    public float getPourcentageInBasketForMaterialType(Material.MaterialType _type)
    {
        for(int i=0;i<this.basketContents.size();i++)
        {
            if(this.basketContents.get(i).material.materialType==_type)
            {
                return this.basketContents.get(i).quantity;
            }
        }
        return 0;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

/**
 *
 * @author Vincent
 */

public class Material {
    
    public enum MaterialType 
    {
        product1,product2
    }
    
    MaterialType materialType;
    Float weight;
    
    public Material(MaterialType _type,float _weight)
    {
        this.materialType = _type;
        this.weight = _weight;
    }
    public MaterialType getMaterialType(){
        return this.materialType;
    }
    public float getWeight(){
        return this.weight;
    }
}

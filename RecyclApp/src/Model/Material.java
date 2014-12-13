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

public class Material implements java.io.Serializable {
    
    public enum MaterialType  implements java.io.Serializable 
    {
        product1,product2
    }
    
    MaterialType materialType;
    float weight;
    
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

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
public class RecoveryMatrix 
{
  
   public class MatrixLine
    {
        public Material.MaterialType type;
        public int exitNumber;
        public float pourcentage;
        public MatrixLine(Material.MaterialType _type,int _exitNumber, float _pourcentage)
        {
            this.type = _type;
            this.exitNumber = _exitNumber;
            this.pourcentage = _pourcentage;
        }
    }   
    private final ArrayList<MatrixLine> matrix;
    public RecoveryMatrix()
    {
        this.matrix = new ArrayList<>();
    }
    public void addLineToMatrix(Material.MaterialType _type, int _exitNumber, float _pourcentage)
    {
        MatrixLine line = new MatrixLine(_type, _exitNumber, _pourcentage);
        this.matrix.add(line);
    }
    public ArrayList<MatrixLine> getMatrix(){
        return this.matrix;
    }
    public void setPourcentageForMaterialType(Material.MaterialType _type, float _pourcentage)
    {
       for (MatrixLine matrix1 : this.matrix) {
           if (matrix1.type == _type) {
               matrix1.pourcentage = _pourcentage;
           }
       }
    }
    public void setExitNumberForMaterialType(Material.MaterialType _type, int _exitNumber)
    {
       for (MatrixLine matrix1 : this.matrix) {
           if (matrix1.type == _type) {
               matrix1.exitNumber = _exitNumber;
           }
       }
    }
  
}


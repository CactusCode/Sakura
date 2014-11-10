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
    private ArrayList<MatrixLine> matrix;
    public RecoveryMatrix()
    {
        this.matrix = new ArrayList<MatrixLine>();
    }
    public void addLineToMatrix(MatrixLine _line)
    {
        this.matrix.add(_line);
    }
    public void setPourcentageForMaterialType(Material.MaterialType _type, float _pourcentage)
    {
        for(int i=0; i<this.matrix.size();i++)
        {
            if(this.matrix.get(i).type==_type)
            {
                this.matrix.get(i).pourcentage = _pourcentage;
            }
        }
    }
    public void setExitNumberForMaterialType(Material.MaterialType _type, int _exitNumber)
    {
        for(int i=0; i<this.matrix.size();i++)
        {
            if(this.matrix.get(i).type==_type)
            {
                this.matrix.get(i).exitNumber = _exitNumber;
            }
        }
    }
    public ArrayList<MatrixLine> getMatrix()
    {
        return this.matrix;
    }
}


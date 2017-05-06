/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labirinto;

/**
 *
 * @author Ultron
 */
public class Cordenadas implements Cloneable{
    private int x;
    private int y;
    
    public Cordenadas(int x, int y) throws Exception{
        this.setY(y);
        this.setX(x);
    }
    public String toString(){
        return "("+this.x+","+this.y+")";
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public void setX(int x) throws Exception{
        /*if(x<0)
            throw new Exception("valor do X não pode ser negativo");*/
        this.x=x;
    }
    public void setY(int y) throws Exception{
        /*if(y<0)
            throw new Exception("valor do Y não pode ser negativo");*/
        this.y=y;
    }
    
    public int hashCode(){
        int ret=666;
        ret = ret * 7 + new Integer(this.x).hashCode();
        ret = ret * 7 + new Integer(this.y).hashCode();        
        return ret;
    }
    
    public boolean equals(Object obj){
        if(obj ==null)
            return false;
        if(obj==this)
            return false;
        if(obj.getClass() != this.getClass())
            return false;
        Cordenadas aux = (Cordenadas)obj;
        if(aux.x!=this.x)
            return false;
        if(aux.y!=this.y)
            return false;
        
        return true;
    }
    
    public Cordenadas clone(){
        Cordenadas ret =null;
        try{
            ret = new Cordenadas(this);
        }catch(Exception e){}
        
        return ret;
    }
    
    public Cordenadas(Cordenadas cor)throws Exception{
        if(cor==null)
            throw new Exception("objeto não pode ser nulo");
        this.x=cor.x;
        this.y=cor.y;
    }
    
}

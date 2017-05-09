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
public class Coordenadas implements Cloneable{
    private int x;
    private int y;
    
    public Coordenadas(int x, int y) throws Exception{
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
        if(obj == null)
            return false;
        if(obj==this)
            return true;
        if(obj.getClass() != this.getClass())
            return false;
        Coordenadas aux = (Coordenadas)obj;
        if(aux.x!=this.x)
            return false;
        if(aux.y!=this.y)
            return false;
        
        return true;
    }
    
    public Coordenadas clone(){
        Coordenadas ret = null;
        try{
            ret = new Coordenadas(this);
        }catch(Exception e){}
        
        return ret;
    }
    
    public Coordenadas(Coordenadas cor)throws Exception{
        if(cor==null)
            throw new Exception("objeto não pode ser nulo");
        this.x=cor.x;
        this.y=cor.y;
    }
    
}

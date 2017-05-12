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
    /**
     * Classe que serve para armazenar cordenadas inteiras
     * 
     * @param x cordenada da posição X
     * @param y cordenada da posição Y
     * @throws Exception 
     */
    public Coordenadas(int x, int y) throws Exception{
        this.setY(y);
        this.setX(x);
    }
    /**
     * 
     * @return Strring com o (X,Y) em suas exatas posições
     */
    public String toString(){
        return "("+this.x+","+this.y+")";
    }/**
     * Retorna o inteiro armazenado na variavel X
     * @return int X com as cordensdas do X
     */
    public int getX(){
        return this.x;
    }/**
     * Retorna o inteiro armazenado na variavel Y
     * @return int Y com as cordensdas do X
     */
    public int getY(){
        return this.y;
    }
    /**
     * Armazena o valor recebido em X
     * @param x integer com o valor
     * @throws Exception 
     */
    public void setX(int x) throws Exception{
        /*if(x<0)
            throw new Exception("valor do X não pode ser negativo");*/
        this.x=x;
    }
    /**
     * Armazena o valor recebido em Y
     * @param y integer com o valor
     * @throws Exception 
     */
    public void setY(int y) throws Exception{
        /*if(y<0)
            throw new Exception("valor do Y não pode ser negativo");*/
        this.y=y;
    }
    /**
     * 
     * @return Hash da classe
     */
    public int hashCode(){
        int ret=666;
        ret = ret * 7 + new Integer(this.x).hashCode();
        ret = ret * 7 + new Integer(this.y).hashCode();        
        return ret;
    }
    /**
     * compara um objeto com as cordanadas
     * 
     * @param obj qualquer objeto
     * @return Boolean true para igual e False para diferente
     */
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
    /**
     * Clona cordenadas
     * @return Cordenadas clone
     */
    public Coordenadas clone(){
        Coordenadas ret = null;
        try{
            ret = new Coordenadas(this);
        }catch(Exception e){}
        
        return ret;
    }
    /**
     * Construtor para clone
     * @param cor Coordendas a serem clonadas
     * @throws Exception Objeto não pode ser nullo
     */
    public Coordenadas(Coordenadas cor)throws Exception{
        if(cor==null)
            throw new Exception("objeto não pode ser nulo");
        this.x=cor.x;
        this.y=cor.y;
    }
    
}

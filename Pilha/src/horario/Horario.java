/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package horario;

import horario.ConstanteTempo;

/**
 *
 * @author Ultron
 */
public class Horario implements Comparable<Horario>, Cloneable{
    private final ConstanteTempo cons = new ConstanteTempo();
    private int hora,minutos,segundos;
    
    public Horario(int h,int m, int s) throws Exception{
        setHora(h);
        setMinutos(m);
        setSegundos(s);
    }
    public Horario(Horario horas) throws Exception{
        if(horas==null)
            throw new Exception("objeto não pode ser nulo");
        
        this.hora=horas.hora;
        this.minutos=horas.minutos;
        this.segundos=horas.segundos;
    }
    
    public void setHora(int h)throws Exception{
        if(h<0)
            throw new Exception("Hora não pode ser nula");
        this.hora = h;
    }
    public void setMinutos(int m)throws Exception{
        if(m<0)
            throw new Exception("Minutos não pode ser nula");
        this.minutos = m;
    }
    public void setSegundos(int s)throws Exception{
        if(s<0)
            throw new Exception("Segundos não pode ser nula");
        this.segundos = s;
    }
    public int convertSegMinutos(int s){
        int ret=0;
        int minutes = s / cons.segundosMinutos;
        int seconds = s % cons.segundosMinutos;
        return ret;
    }
    public String toString(){
        String texto= "";
        texto = this.hora+":"+this.minutos+":"+this.segundos;
        return texto;
    }
    public Horario clone(){
        Horario ret =null;
        try{
            ret = new Horario(this);
        }catch(Exception e){}
        
        return ret;
    }
    
    public int hash(){
        int ret = 666;
        ret = 7 * ret + new Integer (this.hora).hashCode();
        ret = 7 * ret + new Integer (this.minutos).hashCode();
        ret = 7 * ret + new Integer (this.segundos).hashCode();
        return ret;
    }
    
    public boolean equals(Object obj){
        if(obj ==null)
            return false;
        if(obj==this)
            return false;
        if(obj.getClass() != this.getClass())
            return false;
        
        Horario aux = (Horario)obj;
        
        if(aux.hora!=this.hora)
            return false;
        if(aux.minutos!=this.minutos)
            return false;
        if(aux.segundos!=this.segundos)
            return false;
        
        return true;
    }
    
    //compare this com h
    //retorna um nuemro negativo quando o this for menos que h
    public int compareTo(Horario h){
        if(this.hora>h.hora)
            return 666;
        if(this.hora<h.hora)
            return -666;
        if(this.minutos>h.minutos)
            return 666;
        if(this.minutos>h.minutos)
            return -666;
        if(this.segundos>h.segundos)
            return 666;
        if(this.segundos>h.segundos)
            return -666;
        return 0;
    }
}

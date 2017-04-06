/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pilha;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.*;
import java.lang.reflect.Method;

public class Pilha <X> implements Cloneable{
    private Object [] vector;
    private int topo;
    
    private X clonador(X x) throws Exception{//colocar static
        if(x instanceof Cloneable){
              Class <?> classe = x.getClass();
              Class <?>[] paramFormal = null;
              Method metodo = classe.getMethod("clone",paramFormal);
              Object[] paramReal = null;
              return (X)metodo.invoke(x, paramReal);//.clone();
        }
        return x;
    }

    //Construtor de Clone
    public Pilha(Pilha<X> modelo)throws Exception{
        if(modelo==null)
            throw new Exception("Não é aceito objetos null");
        
        for(int i=0;i<=this.topo;i++){
            this.vector[i] = modelo.vector[i];//.clone();
        }
        this.topo = modelo.topo;
        
    }
    
    public Pilha(int tamanhoVetor) throws Exception{
        if(tamanhoVetor < 2)
            throw new Exception ("Capacidade invalido, informe um numero maior que 2");
        this.vector = new Object[tamanhoVetor];
        this.topo = -1;
    }
    
    public void guarde(X x) throws Exception{
          if (x==null)
              throw new Exception("Valor insirido invalido, não pode ser nullo");
          if (this.topo==this.vector.length-1)
              throw new Exception("Valor maximo da pilha ja alcançado "+this.vector.length+"Tente remover alguns valores com o jogueFora()");
          this.topo++;
          this.vector[this.topo] = this.clonador(x);
          
     }

     public X recupere() throws Exception{
          if (this.topo <= -1)
              throw new Exception("A fila é vazia portanto tente adicionar novos valores");     
          return this.clonador((X) this.vector[this.topo]);
     }

     public void jogueFora() throws Exception{
          if (this.topo <= -1)
              throw new Exception("A fila é vazia portanto tente adicionar novos valores");
          this.topo--;
     }
    
    public boolean equals(Object obj){
        if(obj==null)
            return false;
        if(this==obj)
            return true;
        if(this.getClass()!=obj.getClass())
            return false;
        Pilha <X> p = (Pilha<X>)obj;
        if(this.topo!=p.topo)
            return false;
        for(int i=0; i<= this.topo; i++)
            if(!this.vector[i].equals(p.vector[i]))
                return false;
        
        return true;
    }
    
    public String listar() throws Exception{
          if (this.topo <= -1)
              throw new Exception("A fila é vazia portanto tente adicionar novos valores");
          String texto = "Fila completa: \n";
          for(int i=0;i<=this.topo;i++){
              int posicao = i;
              posicao++;
              texto += posicao + "º " + this.vector[i] + "\n";
          }
          return texto;
                  
    }
    
    public String toString(){
        String texto = "";
        texto += (this.topo+1)+" elementos(";
        
        if(this.topo>-1)
            texto += "Ultimo valor: " +this.vector[this.topo] + ")";  
        return texto;
    }
    
    public int hashCode(){
        //int ret = super.hashCode()// quando herdar de alguma classe
        int ret = 666; //qualquer numero, não zero e intero, desde que sua classe não herde de nenhuma classe
        
        ret = 7 * ret + new Integer(this.topo).hashCode();
        for (int i =0; i<=this.topo;i++){
            if(this.vector[i]!=null)
                ret = 7 * ret + this.vector[i].hashCode();
        }
        return ret;
    }
    
    //public 
    
    public Pilha clone(){
        Pilha<X> ret =null;
        try{
            ret = new Pilha<X> (this);
        }catch(Exception e){}//não vai acontecer
        return ret;
    }
    
}

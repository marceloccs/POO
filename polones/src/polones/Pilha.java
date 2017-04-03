/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polones;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.*;

public class Pilha <X> {
    private Object [] vector;
    private int topo = -1;

    /**
     * Instancia a pilha
     * 
     * @param tamanhoVetor, um inteiro que significa o total de elementos que vai ter na pilha;
     * @throws Exception capacidade da pilha não pode ser menor que 2;
     */
    public Pilha(int tamanhoVetor) throws Exception{
        if(tamanhoVetor < 2)
            throw new Exception ("Capacidade invalido, informe um numero maior que 2");
        this.vector = new Object[tamanhoVetor];
    }
    /**
     * guarda uma variavel do mesmo tipo da pilha dentro dela e armazena.
     * 
     * @param x
     * @throws Exception Valor não pode ser nullo
     * @throws Exception não pode ter alcançado o valor maximo da pilha
     */
    public void guarde(X x) throws Exception{
          if (x==null)
              throw new Exception("Valor insirido invalido, não pode ser nullo");
          if (this.topo==this.vector.length-1)
              throw new Exception("Valor maximo da pilha ja alcançado "+this.vector.length+"Tente remover alguns valores com o jogueFora()");
          this.topo++;
          this.vector[topo] = x;//.clone();
     }
    
     /**
     * função que retorna o ultimo valor da pilha.
     * 
     * @return null se não possui mais elemanto dentro da pilha ou retorna o valor do topo da pilha;
     */
     public X getValor(){
          if (this.topo <= -1)
              return null;
          
          return (X)this.vector[this.topo];//.clone();
     }
     
     /**
      * função que descarta o topo da pilha e retorna o mesmo.
      * 
      * @return null se a pilha tiver vazia, caso contrario retorna o topo da pilha;
      */
     public X jogueFora(){
        if(topo<=-1)
            return null;
        Object retorno = this.getValor();
        
        this.topo--;
        return (X)retorno;
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
    
  //Construtor de Clone
    public Pilha(Pilha<X> modelo)throws Exception{
        if(modelo==null)
            throw new Exception("N�o � aceito objetos null");
        
        for(int i=0;i<=this.topo;i++){
            this.vector[i] = modelo.vector[i];//.clone();
        }
        this.topo = modelo.topo;
        
    }
    
    public Pilha clone(){
    	Pilha<X> ret =null;
        try{
            ret = new Pilha<X> (this);
        }catch(Exception e){}//n�o vai acontecer
        return ret;
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
            if(!this.vector[i].equals(null))
                ret = 7 * ret + this.vector[i].hashCode();
        }
        return ret;
    }
    /**
     * Função que retorna se possui algum valor na pilha
     * 
     * @return true se tiver valores e False se não tiver nenhum valor
     */
    public boolean hasValore(){
        if(this.topo<=-1)
            return false;
        return true;
    }
    
    /**
     * Retorna o numero de valores na pilha
     * 
     * @return numero de vetores na pilha
     */
    public int getNumeroValores(){
        int ret = this.topo;
        ret ++;
        return ret;
    }
    
    public String imprimir(){
        String texto = "";
          for(int i=0;i<=this.topo;i++){
              int posicao = i;
              posicao++;
              texto +=this.vector[i]+" ";
          }
          return texto;
    }
}

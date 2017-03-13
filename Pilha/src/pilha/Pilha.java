/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pilha;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Pilha <X> {
    private Object [] vector;
    private int topo = -1;

    public Pilha(int tamanhoVetor) throws Exception{
        if(tamanhoVetor < 2)
            throw new Exception ("Capacidade invalido, informe um numero maior que 2");
        this.vector = new Object[tamanhoVetor];
    }
    
    public void guarde(X x) throws Exception{
          if (this.topo==this.vector.length-1)
              throw new Exception("Valor maximo da pilha ja alcançado "+this.vector.length+"Tente remover alguns valores com o jogueFora()");
          this.topo++;
          vector[topo]=x;
     }

     public X recupere() throws Exception{
          if (this.topo <= -1)
              throw new Exception("A fila é vazia portanto tente adicionar novos valores");     
          return (X)this.vector[this.topo];
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
    
}

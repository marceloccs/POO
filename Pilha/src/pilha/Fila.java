/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pilha;

public class Fila <X>{
    private Object [] vector;
    private int topo = -1;
    
    public Fila(int tamanhoVetor) throws Exception{
        if(tamanhoVetor < 2)
            throw new Exception ("Capacidade invalido, informe um numero maior que 2");
        this.vector = new Object [tamanhoVetor];
    }
    
    public void guarde(X x) throws Exception{
          if (this.topo==this.vector.length-1)
              throw new Exception("Valor maximo da fila ja alcançado "+this.vector.length+"Tente remover alguns valores com o jogueFora");
          this.topo++;
          vector[topo]=x;
     }

     public X recupere() throws Exception{
          if (this.topo <= -1)
              throw new Exception("A fila é vazia portanto tente adicionar novos valores");     
          return (X)this.vector[0];
     }

     public void jogueFora() throws Exception{
          if (this.topo <= -1)
              throw new Exception("A fila é vazia portanto tente adicionar novos valores");
          //this.vector[0]
          if (this.topo == 0){
              this.topo=-1;
          }
          else{
            for(int i=0;i<this.topo;i++){
                int y = i;
                y++;
                this.vector[i] = this.vector[y];
            }
          }
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
        
        if(this.topo>-1){
            texto += " Ultimo valor: " +this.vector[this.topo];  
            texto += " Primeiro valor: " +this.vector[0] + ")";  
        }
        return texto;
    }
     
     public boolean equals(Object obj){
        if(obj==null)
            return false;
        if(this==obj)
            return true;
        if(this.getClass()!=obj.getClass())
            return false;
        Fila <X> f = (Fila<X>)obj;
        
        if(this.topo!=f.topo)
            return false;
        for(int i=0; i<= this.topo; i++)
            if(!this.vector[i].equals(f.vector[i]))
                return false;
        
        return true;
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
}

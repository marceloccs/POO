/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labirinto;

import java.lang.reflect.Method;

public class Fila <X> implements Cloneable{
    private Object [] vector;
    private int topo = -1;
    
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
    
    /**
     * Instancia a Fila
     * 
     * @param tamanhoVetor, um inteiro que significa o total de elementos que vai ter na fila;
     * @throws Exception capacidade da pilha não pode ser menor que 2;
     */
    public Fila(int tamanhoVetor) throws Exception{
        if(tamanhoVetor < 2)
            throw new Exception ("Capacidade invalido, informe um numero maior que 2");
        this.vector = new Object[tamanhoVetor];
    }
    
    /**
     * guarda uma variavel do mesmo tipo da fila dentro dela e armazena.
     * 
     * @param x
     * @throws Exception Valor não pode ser nullo
     * @throws Exception não pode ter alcançado o valor maximo da fila
     */
    public void guarde(X x) throws Exception{
          if (x==null)
              throw new Exception("Valor insirido invalido, não pode ser nullo");
          if (this.topo==this.vector.length-1)
              throw new Exception("Valor maximo da pilha ja alcançado "+this.vector.length+"Tente remover alguns valores com o jogueFora()");
          this.topo++;
          this.vector[topo] = this.clonador(x);//.clone();
    }
     /**
     * função que retorna o primeiro valor da fila.
     * 
     * @return null se não possui mais elemanto dentro da pilha ou retorna o valor do topo da pilha;
     */
    
     public X getValor() throws Exception{
          if (this.topo <= -1)
              return null;
          return this.clonador((X)this.vector[0]);
     }

     
     /**
      * função que descarta o topo, o primeiro resultado, da fila e organiza a fila novamente e retorna o mesmo.
      * 
      * @return null se a pilha tiver vazia, caso contrario retorna o topo da pilha;
      */
     public X jogueFora() throws Exception{
         Object valor = this.getValor();
          if (this.topo <= -1)
              return null;
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
            this.topo--;
          }
          return this.clonador((X)valor);
     }
     
     /**
     * retona uma lista com os valores da pilha
     * 
     * @return String com todas as posições e os valores dentro da pilha
     * @throws Exception caso a pilha estiver vazia lança uma execção
     */
     /*private String listar() throws Exception{
          if (this.topo <= -1)
              throw new Exception("A fila é vazia portanto tente adicionar novos valores");
          String texto = "Fila completa: \n";
          for(int i=0;i<=this.topo;i++){
              int posicao = i;
              posicao++;
              texto += posicao + "º " + this.vector[i] + "\n";
          }
          return texto;
                  
    }*/
     
     
     //Construtor de Clone
    public Fila(Fila<X> modelo)throws Exception{
        if(modelo==null)
            throw new Exception("N�o � aceito objetos null");
        this.vector =new Object[modelo.vector.length];
        for(int i=0;i<=modelo.topo;i++){
            this.vector[i] = this.clonador((X)modelo.vector[i]);//.clone();
        }
        this.topo = (int)new Integer(modelo.topo);        
    }
    
    public Fila clone(){
    	Fila<X> ret =null;
        try{
            ret = new Fila<X> (this);
        }catch(Exception e){}//n�o vai acontecer
        return ret;
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
    
    /**
     * Função que retorna se possui algum valor na fila
     * 
     * @return true se tiver valores e False se não tiver nenhum valor
     */
    public boolean hasValore(){
        if(this.topo<=-1)
            return false;
        return true;
    }
    
    /**
     * Retorna o numero de valores na fila
     * 
     * @return numero de vetores na pilha
     */
    public int getNumeroValores(){
        int ret = this.topo;
        ret ++;
        return ret;
    }
    
    /*private String imprimir(){
        String texto = "";
          for(int i=0;i<=this.topo;i++){
              int posicao = i;
              posicao++;
              texto +=this.vector[i]+" ";
          }
          return texto;
    }*/
}

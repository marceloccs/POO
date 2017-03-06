/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pilha;

public class Pilha {
    private int[] vector = new int [10];
    private int topo = -1;

    public Pilha(int tamanhoVetor) throws Exception{
        if(tamanhoVetor < 2)
            throw new Exception ("Capacidade invalido, informe um numero maior que 2");
        this.vector = new int[tamanhoVetor];
    }
    
    public void guarde(int x) throws Exception{
          if (this.topo==this.vector.length-1)
              throw new Exception("Valor maximo da pilha ja alcançado "+this.vector.length+"Tente remover alguns valores com o jogueFora()");
          this.topo++;
          vector[topo]=x;
     }

     public int recupere() throws Exception{
          if (this.topo <= -1)
              throw new Exception("A fila é vazia portanto tente adicionar novos valores");     
          return this.vector[this.topo];
     }

     public void jogueFora() throws Exception{
          if (this.topo <= -1)
              throw new Exception("A fila é vazia portanto tente adicionar novos valores");
          this.topo--;
     }
}

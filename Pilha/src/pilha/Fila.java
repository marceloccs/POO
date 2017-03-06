/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pilha;

public class Fila {
    private int[] vector;
    private int topo = -1;
    
    public Fila(int tamanhoVetor) throws Exception{
        if(tamanhoVetor < 2)
            throw new Exception ("Capacidade invalido, informe um numero maior que 2");
        this.vector = new int[tamanhoVetor];
    }

    Fila() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void guarde(int x) throws Exception{
          if (this.topo==this.vector.length-1)
              throw new Exception("Valor maximo da fila ja alcançado "+this.vector.length+"Tente remover alguns valores com o jogueFora");
          this.topo++;
          vector[topo]=x;
     }

     public int recupere() throws Exception{
          if (this.topo <= -1)
              throw new Exception("A fila é vazia portanto tente adicionar novos valores");     
          return this.vector[0];
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
          String texto="Fila \n";
          for(int i=0;i<this.topo;i++){
              texto = texto + this.vector[i] + "\n";
          }
          return texto;
                  
     }
}

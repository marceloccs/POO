/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package busca;

/**
 *
 * @author Ultron
 */
public class BuscaBinaria {
    private int vector[];
    private int posicao;
    private int quantidade;
    
    public BuscaBinaria (int vector[]){
        int quantidade = vector.length;
        this.vector = vector.clone();
        this.quantidade = quantidade;
    }
    public int busca(int serBuscado){
        int inicio = 0;
        int meio;
        int fim = this.quantidade;
        if(inicio>serBuscado)
            return inicio++;
        if(fim<serBuscado)
            return fim++;
        boolean flag = false;
        while(inicio<=fim){
            meio = (inicio + fim)/2;
            int comp = serBuscado.compareTo(this.vector[meio]);
            if(comp===0)
                return meio+1;
            else
                if(comp<0)
                    fim=meio-1;
                else
                    inicio=meio+1;
        }
        return - (inicio+1);
    }
}

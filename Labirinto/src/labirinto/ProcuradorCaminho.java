/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labirinto;

import java.util.Vector;

/**
 * 
 * @author Ultron
 */
public class ProcuradorCaminho {
    private Mapa mapa;
    private Pilha<Cordenadas> caminho;
    private Pilha<Fila<Cordenadas>> possibilidades;
    
    public ProcuradorCaminho(Mapa mapa) throws Exception{
        this.setMapa(mapa);
        int mult = this.mapa.getColunas() * this.mapa.getLinhas();
        this.caminho = new Pilha<Cordenadas>(mult);
        this.possibilidades = new Pilha<Fila<Cordenadas>>(mult);
        this.achaCaminho();
    }
    private void achaCaminho() throws Exception{
        Cordenadas atual = this.mapa.getEntrada();
        this.mapa.getMovimentos(atual);
        do{
            Fila <Cordenadas> posLocal = new Fila(4);
            this.caminho.guarde(atual);
            this.mapa.setCaractere(atual, "*");
            posLocal = this.mapa.getMovimentos(atual);
            if(posLocal.getNumeroValores()==1){
                atual = posLocal.jogueFora();
                System.out.println(atual.toString());
            }
            else if(posLocal.getNumeroValores()==0){
                break;
            }
            else{
                atual = posLocal.jogueFora();
                this.possibilidades.guarde(posLocal);
            }
        }while(!this.mapa.getPosicao(atual).equals("S"));
    }
    public String toString(){
        String ret ="Mapa:\n";
        ret+=this.mapa.toString();
        ret+="\nCaminho: "+this.caminho.toString();
        ret+="\nPossibilidades: "+this.possibilidades.toString();
        return ret;
    }
    private void setMapa(Mapa mapa){
        this.mapa = mapa.clone();
    }
    
}
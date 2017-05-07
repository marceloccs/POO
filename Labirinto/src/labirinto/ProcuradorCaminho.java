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
            if(posLocal.getNumeroValores()!=0){
                try{
                    atual = posLocal.jogueFora();
                    this.possibilidades.guarde(posLocal.clone());
                    System.out.println(atual.toString());
                }
                catch(Exception erro){
                    throw new Exception ("Documento lido é incompativel");
                }
            }
            else{
                try{
                    do{
                        this.mapa.setCaractere(atual, " ");
                        this.possibilidades.jogueFora();
                        atual = this.mapa.cameBack(atual);
                        System.out.println(atual.toString());
                    }while(possibilidades.getValor().getNumeroValores()==0);
                    atual= this.possibilidades.getValor().jogueFora();
                }
                catch(Exception erro){
                    throw new Exception ("Documento lido é incompativel");
                }
            }
        }while(!this.mapa.getPosicao(atual).equals("S"));
        System.out.println("Achou poha na posição na posição: " + atual.toString());
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

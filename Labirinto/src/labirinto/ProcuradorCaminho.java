/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labirinto;

import java.util.Vector;
///////TODO documentação, hash, clone, e outros
/**
 * 
 * @author Ultron
 */
public class ProcuradorCaminho {
    private Mapa mapa;
    private Pilha<Cordenadas> caminho;
    private Pilha<Fila<Cordenadas>> possibilidades;
    private Cordenadas fim;
    
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
        try{
            do{
                Fila <Cordenadas> posLocal = new Fila(4);
                this.caminho.guarde(atual);
                this.mapa.setCaractere(atual, "*");
                posLocal = this.mapa.getMovimentos(atual);
                if(posLocal.getNumeroValores()!=0){
                    try{
                        atual = posLocal.jogueFora();
                        this.possibilidades.guarde(posLocal.clone());
                        //System.out.println(atual.toString());
                    }
                    catch(Exception erro){
                        throw new Exception ("Documento lido é incompativel, verifique se a quantidade de linhas e colunas estão certas e se o labirinto tem fim");
                    }
                }
                else{
                    try{
                        do{
                            this.mapa.setCaractere(atual, " ");
                            this.caminho.jogueFora();
                            this.possibilidades.jogueFora();
                            atual = this.mapa.cameBack(atual);
                            //System.out.println(atual.toString());
                        }while(possibilidades.getValor().getNumeroValores()==0);
                        atual= this.possibilidades.getValor().jogueFora();
                    }
                    catch(Exception erro){
                        throw new Exception ("Documento lido é incompativel, verifique se a quantidade de linhas e colunas estão certas e se o labirinto tem fim");
                    }
                }
            }while(!this.mapa.getPosicao(atual).equals("S"));

            if(!(this.mapa.getPosicao(atual).equals("S")))
                throw new Exception ("Não achou fim");
            this.fim = atual;
            //System.out.println(this.caminho.imprimir());
        
        }catch(Exception e){
            throw new Exception ("Documento lido é incompativel, verifique se a quantidade de linhas e colunas estão certas e se o labirinto tem fim");
        }
    }
    public String toString(){
        String ret ="";
        ret=ret+"\nCaminho:\n"+this.caminho.toString();
        ret=ret+"\nFim do labirinto: "+this.fim.toString();
        return ret;
    }
    private void setMapa(Mapa mapa){
        this.mapa = mapa.clone();
    }
    
    public boolean equals(Object obj) {
    	if(obj == null)
    		return false;
    	
    	if(obj == this)
    		return true;
    	
    	if(obj.getClass() != this.getClass())
    		return false;
    	
    	ProcuradorCaminho aux = (ProcuradorCaminho)obj;
    	
    	if(aux.mapa != this.mapa)
    		return false;
    	
    	if(aux.caminho != this.caminho)
    		return false;
    	
    	if(aux.possibilidades != this.possibilidades)
    		return false;
    	
    	if(aux.fim != this.fim)
    		return false;
    	
    	return true;
    }
    
}

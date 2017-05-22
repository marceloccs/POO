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
public class ProcuradorCaminho implements Cloneable{
    private Mapa mapa;
    private Pilha<Coordenadas> caminho;
    private Pilha<Fila<Coordenadas>> possibilidades;
    private Coordenadas fim;
    private Coordenadas atual;
    /**
     * Classe como objetico de achar o caminho de um mapa passado
     * @param mapa Mapa com o labirinto que deve ser procurado
     * @throws Exception não achou caminho pois o labirinto ta mal formulado
     */
    public ProcuradorCaminho(Mapa mapa) throws Exception{
        this.setMapa(mapa);
        int mult = this.mapa.getColunas() * this.mapa.getLinhas();
        this.caminho = new Pilha<Coordenadas>(mult);
        this.possibilidades = new Pilha<Fila<Coordenadas>>(mult);
        this.achaCaminho();
    }
    /**
     * Função que procura caminho e armazena no caminho e quando achado coloca no fim
     * @throws Exception Não acha caminho do labirinto
     */
    private void achaCaminho() throws Exception{
        this.atual = this.mapa.getEntrada();
        this.mapa.getMovimentos(this.atual);
        try{
            do{
                Fila <Coordenadas> posLocal = new Fila(4);
                this.caminho.guarde(this.atual);
                this.mapa.setCaractere(this.atual, "*");
                posLocal = this.mapa.getMovimentos(this.atual);
                if(posLocal.getNumeroValores()!=0){
                    try{
                        this.atual = posLocal.jogueFora().clone();
                        this.possibilidades.guarde(posLocal.clone());
                        //System.out.println(atual.toString());
                    }
                    catch(Exception erro){
                        throw new Exception ("Documento lido é incompativel, verifique se a quantidade de linhas e colunas estão certas e se o labirinto tem fim");
                    }
                }
                else{
                    try{
                        if (this.possibilidades.getValor().getNumeroValores()==0){
                            do{
                                System.out.println(this.mapa.setCaractere(this.atual, " "));
                                this.caminho.jogueFora();
                                this.possibilidades.jogueFora();
                                System.out.println(this.atual = this.mapa.cameBack(this.atual).clone());
                                System.out.println(this.atual);
                                //System.out.println(this.mapa.cameBack(this.atual));
                                //System.out.println(this.mapa.toString());
                            }while(possibilidades.getValor().getNumeroValores()==0);
                        }
                        this.mapa.setCaractere(this.atual, " ");
                        this.atual= this.possibilidades.getValor().jogueFora().clone();
                    }
                    catch(Exception erro){
                        throw new Exception ("Documento lido é incompativel, verifique se a quantidade de linhas e colunas estão certas e se o labirinto tem fim");
                    }
                }
            }while(!this.mapa.getPosicao(atual).equals("S"));

            if(!(this.mapa.getPosicao(atual).equals("S")))
                throw new Exception ("Não achou fim");
            this.fim = this.atual.clone();
            //System.out.println(this.caminho.imprimir());
        
        }catch(Exception e){
            throw new Exception ("Documento lido é incompativel, verifique se a quantidade de linhas e colunas estão certas e se o labirinto tem fim");
        }
    }/**
     * retorna uma string com as informações de comco achou o fim
     * @return String com o caminho que foi feito para achar o fim e a posição do fim
     */
    public String toString(){
        String ret ="";
        ret=ret+"\nCaminho:\n"+this.caminho.toString();
        ret=ret+"\nFim do labirinto: "+this.fim.toString();
        return ret;
    }
    /**
     * função que seta na classe o mapa
     * @param mapa mapa para achar o caminho
     */
    private void setMapa(Mapa mapa){
        this.mapa = mapa.clone();
    }
    /**
     * Compara o objeto passda com a classe
     * @param obj objeto para ser comparado 
     * @return true para iguais e false para diferentes
     */
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
    /**
     * 
     * @return hash da classe
     */
    public int hashCode() {
    	int ret = 666;
    	ret = 7 * ret + this.mapa.hashCode();
    	ret = 7 * ret + this.caminho.hashCode();
    	ret = 7 * ret + this.possibilidades.hashCode();
    	ret = 7 * ret + this.fim.hashCode();
    	
    	return ret;
    }
    /**
     * clona a classe
     * @return classe clonada
     */
    public ProcuradorCaminho clone() {
    	ProcuradorCaminho ret = null;
    	try {
    		ret = new ProcuradorCaminho(this);
    	}
    	catch(Exception e) {}
    	
    	return ret;
    }
    /**
     * construtor de clone
     * @param obj ProcuradorCaminho a ser clonado
     * @throws Exception 
     */
    public ProcuradorCaminho(ProcuradorCaminho obj) throws Exception {
    	if(obj == null)
    		throw new Exception("Objeto n�o pode ser nulo!");
    	
    	this.mapa = obj.mapa;
    	this.caminho = obj.caminho;
    	this.possibilidades = obj.possibilidades;
    	this.fim = obj.fim;
    }
}

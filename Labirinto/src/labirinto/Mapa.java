/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labirinto;

import java.util.Arrays;
import java.util.Vector;

/**
 *
 * @author Ultron
 */
public class Mapa implements Cloneable{
    private String mapa[][];
    private int linhas;
    private int colunas;
    private Coordenadas entrada;
    
    /**
     * constroi a classe de mapa
     * @param mapaNaoTratado um Vector do arquivo lido com as primeiras linhas com coluna e linhas e as proximas com o mapa
     * @throws Exception 
     */
    public Mapa(Vector <String> mapaNaoTratado) throws Exception{
        try {
            int linhaInt = Integer.parseInt(mapaNaoTratado.firstElement());
            mapaNaoTratado.remove(mapaNaoTratado.firstElement());
            int colunaInt = Integer.parseInt(mapaNaoTratado.firstElement());
            mapaNaoTratado.remove(mapaNaoTratado.firstElement());
            this.setColunas(colunaInt);
            this.setLinhas(linhaInt);
        }catch(NumberFormatException e){
            throw new Exception ("O mapa passado no arquivo de texto não esta compativel");
        }
        this.setMapa(mapaNaoTratado);
    }
    /**
     * Troca o caracter da posição das cordenadas que foram enviadas
     * 
     * @param cordenada cordenada que deseja ver o caracter
     * @return caracter que foi encontrado na cordenada
     */
    public String getPosicao(Coordenadas cordenada){
        return this.mapa[cordenada.getX()][cordenada.getY()];
    }
    /**
     * 
     * @return dados da classe que são utilixzadas
     */
    public String toString(){
        String ret = "mapa: \n";
        for(int i=0; i<this.colunas;i++){
            for(int y = 0; y<this.linhas;y++){
                ret += this.mapa[i][y];
            }
            ret += "\n";
        }
        ret += "\n";
        //ret +="Entrada: " +this.entrada.toString();
        //ret +="\nLinhas: " +this.linhas;
        //ret +="\nColunas: " +this.colunas;
        return ret;
    }
    /**
     * ele muda o caracter das cordenadas indicadas
     * 
     * @param cor cordenada da onde vai mudar o caracter
     * @param caracter caracter que vai ser mudado
     * @return cordenada da onde foi mudado
     * @throws CloneNotSupportedException 
     */
    public Coordenadas setCaractere(Coordenadas cor, String caracter) throws CloneNotSupportedException{
        this.mapa[cor.getX()][cor.getY()] = caracter;
        return cor;
    }
    /**
     * Função que procura o * mais em posições adacentes e retorna essa casa, ele retorna o primeiro encontrado
     * 
     * @param atual cordenadas da onde vc deseja rtornar
     * @return cordenadas que foir retornada
     * @throws Exception 
     */
    public Coordenadas cameBack(Coordenadas atual) throws Exception{
        Coordenadas auxCima = new Coordenadas(atual.getX(),(atual.getY() + 1));
        Coordenadas auxBaixo = new Coordenadas(atual.getX(),(atual.getY() - 1));
        Coordenadas auxEsquerda = new Coordenadas((atual.getX() - 1),atual.getY());
        Coordenadas auxDireita = new Coordenadas((atual.getX() + 1),(atual.getY()));
        
        /*if(!posicaoatual.equals(" "))
            throw new Exception("Posição invalida para movimentação");*/
        try{
            if (this.mapa[auxCima.getX()][auxCima.getY()].equals("*")){
                return auxCima.clone();}
        }catch(Exception e){}
        try{
        if (this.mapa[auxBaixo.getX()][auxBaixo.getY()].equals("*")){
            return auxBaixo.clone();}}catch(Exception e){}
        
        try{
        if (this.mapa[auxEsquerda.getX()][auxEsquerda.getY()].equals("*")){
            return auxEsquerda.clone();}}catch(Exception e){}
        
        try{
        if (this.mapa[auxDireita.getX()][auxDireita.getY()].equals("*")){
            return auxDireita.clone();}}catch(Exception e){}
        
        return null;
    }
    /**
     * Pega a posição desejada e retorna todas as posições possivies, caso não ache nehuma retorna uma fila nulla
     * @param cordenada cordenada atual ou que você deseja visualizar a movimentação
     * @return Fila de coordenadas possiveis
     * @throws Exception 
     */
    public Fila<Coordenadas> getMovimentos(Coordenadas cordenada) throws Exception{
        Fila <Coordenadas> ret = new Fila(4);
        //String posicaoatual = this.mapa[cordenada.getX()][cordenada.getY()];
        Coordenadas auxCima = new Coordenadas(cordenada.getX(),(cordenada.getY() + 1));
        Coordenadas auxBaixo = new Coordenadas(cordenada.getX(),(cordenada.getY() - 1));
        Coordenadas auxEsquerda = new Coordenadas((cordenada.getX() - 1),cordenada.getY());
        Coordenadas auxDireita = new Coordenadas((cordenada.getX() + 1),(cordenada.getY()));
        
        /*if(!posicaoatual.equals(" "))
            throw new Exception("Posição invalida para movimentação");*/
        try{
            if (this.mapa[auxCima.getX()][auxCima.getY()].equals(" ")||this.mapa[auxCima.getX()][auxCima.getY()].equals("S")){
                ret.guarde(auxCima.clone());}
        }catch(Exception e){}
        try{
        if (this.mapa[auxBaixo.getX()][auxBaixo.getY()].equals(" ")||this.mapa[auxBaixo.getX()][auxBaixo.getY()].equals("S")){
            ret.guarde(auxBaixo.clone());}}catch(Exception e){}
        
        try{
        if (this.mapa[auxEsquerda.getX()][auxEsquerda.getY()].equals(" ")||this.mapa[auxEsquerda.getX()][auxEsquerda.getY()].equals("S")){
            ret.guarde(auxEsquerda.clone());}}catch(Exception e){}
        
        try{
        if (this.mapa[auxDireita.getX()][auxDireita.getY()].equals(" ")||this.mapa[auxDireita.getX()][auxDireita.getY()].equals("S")){
            ret.guarde(auxDireita.clone());}}catch(Exception e){}
        
        return ret;
    }
    /**
     * Tranforma o objeto vector em array do mapa
     * 
     * @param mapa Vector do arquivo lido
     * @throws Exception 
     */
    private void setMapa(Vector <String> mapa) throws Exception{
        String linhas [] = new String[mapa.size()]; //split
        linhas = mapa.toArray(new String[mapa.size()]);
        this.mapa = new String[this.linhas][this.colunas];
        //this.mapa =
        for(int i=0;i<linhas.length;i++){
            String aux[];
            aux = linhas[i].split("(?!^)");
            this.mapa[i]=aux;
            //System.out.println(aux);
        }
        this.procuraEntrada();
    }
    /**
     * adiciona as linhas na classe
     * @param linhas 
     */
    private void setLinhas(int linhas){
        this.linhas=linhas;
    }
    /**
     * adiciona as colunas na classe
     * @param colunas 
     */
    private void setColunas(int colunas){
        this.colunas= colunas;
    }
    /**
     * produz hashcode da classe
     * @return int hash
     */
    public int hashCode(){
        int ret=666;
        ret = ret * 7 + new Integer(this.colunas).hashCode();
        ret = ret * 7 + this.mapa.hashCode();
        ret = ret * 7 + new Integer(this.linhas).hashCode();
        return ret;
    }
    /**
     * Compara um objeto com o a classe atual
     * @param obj
     * @return bollean true para igual e false para falso
     */
    public boolean equals(Object obj){
        if(obj ==null)
            return false;
        if(obj==this)
            return false;
        if(obj.getClass() != this.getClass())
            return false;
        Mapa aux = (Mapa)obj;
        if(!Arrays.equals(aux.mapa, this.mapa))
            return false;
        if(aux.linhas!=this.linhas)
            return false;
        if(aux.colunas!=this.colunas)
            return false;
        return true;
    }
    /**
     * Clona mapa
     * @return clone do mapa
     */
    public Mapa clone(){
        Mapa ret =null;
        try{
            ret = new Mapa(this);
        }catch(Exception e){}
        
        return ret;
    }
    /**
     * construtor de clone
     * @param map
     * @throws Exception objeto nullo
     */
    public Mapa(Mapa map)throws Exception{
        if(map==null)
            throw new Exception("objeto não pode ser nulo");
        this.colunas=map.colunas;
        this.linhas=map.linhas;
        this.mapa=map.mapa;
        this.entrada = map.entrada;
    }
    /**
     * Retorna as linhas lidas no arquivo
     * @return in linhas
     */
    public int getLinhas(){
        return this.linhas;
    }
    /**
     * retorna as colunas lidas no arquivo
     * @return int Coluna
     */
    public int getColunas(){
        return this.colunas;
    }
    /**
     * Retorna as cordenadas da entrda
     * @return Cordenadas da entrada
     */
    public Coordenadas getEntrada(){
        return this.entrada;
    }
    /**
     * Função que procura pelos cantos do programa uma entrada simbolizada pelo E
     * 
     * @throws Exception quando não acaha uma entrada
     */
    private void procuraEntrada() throws Exception{
        int i=0;
        int y=0;
        int limite = this.colunas;
        limite--;
        for(i=0;i<this.colunas;i++){
            if(i==limite||i==0){
                for(y=0; y<this.linhas;y++){
                    if(this.mapa[i][y].equals("E")){
                        this.entrada = new Coordenadas(i,y);
                        break;
                    }
                }
            }else{
                if(this.mapa[i][0].equals("E")){
                    this.entrada = new Coordenadas(i,y);
                    break;
                }
                if(this.mapa[i][limite].equals("E")){
                    this.entrada = new Coordenadas(i,y);
                    break;
                }
            }
        }
        if(this.entrada.equals(null)){
            throw new Exception("Não foi possivel achar entrada");
        }
    }
}

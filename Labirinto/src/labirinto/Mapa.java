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
    private Cordenadas entrada;
    
    public Mapa(Vector <String> mapaNaoTratado) throws CloneNotSupportedException, Exception{
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
    public String getPosicao(Cordenadas cordenada){
        return this.mapa[cordenada.getX()][cordenada.getY()];
    }
    
    public String toString(){
        String ret = "mapa: \n";
        for(int i=0; i<this.colunas;i++){
            for(int y = 0; y<this.linhas;y++){
                ret += this.mapa[i][y];
            }
            ret += "\n";
        }
        ret += "\n";
        ret +="Entrada: " +this.entrada.toString();
        ret +="\nLinhas: " +this.linhas;
        ret +="\nColunas: " +this.colunas;
        return ret;
    }
    
    public Cordenadas setCaractere(Cordenadas cor, String caracter) throws CloneNotSupportedException{
        this.mapa[cor.getX()][cor.getY()] = caracter;
        return cor;
    }
    
    public Cordenadas cameBack(Cordenadas atual) throws Exception{
        Cordenadas auxCima = new Cordenadas(atual.getX(),(atual.getY() + 1));
        Cordenadas auxBaixo = new Cordenadas(atual.getX(),(atual.getY() - 1));
        Cordenadas auxEsquerda = new Cordenadas((atual.getX() - 1),atual.getY());
        Cordenadas auxDireita = new Cordenadas((atual.getX() + 1),(atual.getY()));
        
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
    
    public Fila<Cordenadas> getMovimentos(Cordenadas cordenada) throws Exception{
        Fila <Cordenadas> ret = new Fila(4);
        //String posicaoatual = this.mapa[cordenada.getX()][cordenada.getY()];
        Cordenadas auxCima = new Cordenadas(cordenada.getX(),(cordenada.getY() + 1));
        Cordenadas auxBaixo = new Cordenadas(cordenada.getX(),(cordenada.getY() - 1));
        Cordenadas auxEsquerda = new Cordenadas((cordenada.getX() - 1),cordenada.getY());
        Cordenadas auxDireita = new Cordenadas((cordenada.getX() + 1),(cordenada.getY()));
        
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
    public void setMapa(Vector <String> mapa) throws Exception{
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
    public void setLinhas(int linhas){
        this.linhas=linhas;
    }
    public void setColunas(int colunas){
        this.colunas= colunas;
    }
    
    public int hashCode(){
        int ret=666;
        ret = ret * 7 + new Integer(this.colunas).hashCode();
        ret = ret * 7 + this.mapa.hashCode();
        ret = ret * 7 + new Integer(this.linhas).hashCode();
        return ret;
    }
    
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
    
    public Mapa clone(){
        Mapa ret =null;
        try{
            ret = new Mapa(this);
        }catch(Exception e){}
        
        return ret;
    }
    
    public Mapa(Mapa map)throws Exception{
        if(map==null)
            throw new Exception("objeto não pode ser nulo");
        this.colunas=map.colunas;
        this.linhas=map.linhas;
        this.mapa=map.mapa;
        this.entrada = map.entrada;
    }
    public int getLinhas(){
        return this.linhas;
    }
    public int getColunas(){
        return this.colunas;
    }
    public Cordenadas getEntrada(){
        return this.entrada;
    }
    private void procuraEntrada() throws Exception{
        int i=0;
        int y=0;
        int limite = this.colunas;
        limite--;
        for(i=0;i<this.colunas;i++){
            if(i==limite||i==0){
                for(y=0; y >this.linhas;y++){
                    if(this.mapa[i][y].equals("E")){
                        this.entrada = new Cordenadas(i,y);
                        break;
                    }
                }
            }else{
                if(this.mapa[i][0].equals("E")){
                    this.entrada = new Cordenadas(i,y);
                    break;
                }
                if(this.mapa[i][limite].equals("E")){
                    this.entrada = new Cordenadas(i,y);
                    break;
                }
            }
        }
        if(this.entrada.equals(null)){
            throw new Exception("Não foi possivel achar entrada");
        }
    }
}

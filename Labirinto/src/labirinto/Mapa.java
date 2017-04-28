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
public class Mapa {
    private String mapa[][];
    private int linhas;
    private int colunas;
    
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
    public Vector<Cordenadas> getMovimentos(Cordenadas cordenada) throws Exception{
        Vector <Cordenadas> ret = new Vector();
        int i=0;
        String posicaoatual = this.mapa[cordenada.getX()][cordenada.getY()];
        Cordenadas auxCima = new Cordenadas(cordenada.getX(),(cordenada.getY() + 1));
        Cordenadas auxBaixo = new Cordenadas(cordenada.getX(),(cordenada.getY() - 1));
        Cordenadas auxEsquerda = new Cordenadas((cordenada.getX() - 1),cordenada.getY());
        Cordenadas auxDireita = new Cordenadas((cordenada.getX() + 1),(cordenada.getY()));
        
        if(!posicaoatual.equals(" "))
            throw new Exception("Posição invalida para movimentação");
        
        if (!this.mapa[auxCima.getX()][auxCima.getY()].equals("#"))
            ret.add(auxCima);
        
        if (!this.mapa[auxBaixo.getX()][auxBaixo.getY()].equals("#"))
            ret.add(auxBaixo);
        
        if (!this.mapa[auxEsquerda.getX()][auxEsquerda.getY()].equals("#"))
            ret.add(auxEsquerda);
        
        if (!this.mapa[auxCima.getX()][auxCima.getY()].equals("#"))
            ret.add(auxDireita);
        
        return ret;
    }
    public void setMapa(Vector <String> mapa){
        this.mapa = (String[][]) mapa.toArray();
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
    }
}

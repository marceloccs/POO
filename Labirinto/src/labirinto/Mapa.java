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
public class Mapa {
    private Vector <String> mapa;
    private int linhas;
    private int colunas;
    
    public Mapa(Vector <String> mapaNaoTratado){
        
    }
    public void setMapa(Vector <String> mapa){
        this.mapa=mapa;
    }
    
    public void setLinhas(int linhas){
        this.linhas=linhas;
    }
    
    public void setColunas(int colunas){
        this.colunas=colunas;
    }
    
}

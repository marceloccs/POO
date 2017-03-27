/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polones;

import java.util.Arrays;

/**
 *
 * @author Ultron
 */
public class Tabela {
    private boolean [][] tabela = new boolean[][] {
            {false,false,false,false,false,false,true},//(
            {false,true,true,true,true,true,true},//^
            {false,false,true,true,true,true,true},//*
            {false,false,true,true,true,true,true},// /
            {false,false,false,false,true,true,true},//+
            {false,false,false,false,true,true,true},//-
            {false,false,false,false,false,false,false},//)
        };
    static String [] simbulos = new String[] {"(","^","*","/","+","-",")"};
    public Tabela(){}
    
    public boolean getValor(String linha, String coluna) throws Exception{
        int y = this.getNumero(coluna);
        int x = this.getNumero(linha);
        boolean valor; 
        //if (Arrays.asList(tabela).contains("whatever"))
        valor = tabela[x][y];
        return valor;
    }
    
    private int getNumero(String c) throws Exception{
        int retorno = -1;
        switch (c) {
            case "(":
                retorno = 0;
                break;
            case "^":
                retorno = 1;
                break;
            case "*":
                retorno = 2;
                break;
            case "/":
                retorno = 3;
                break;
            case "+":
                retorno = 4;
                break;
            case "-":
                retorno = 5;
                break;
             case ")":
                retorno = 6;
                break;
         }
        if (retorno==-1)
            throw new Exception ("simbulo não é numero e nem se encontra dentro dos valores correspondetes da tabela, consulte ela");
        return retorno;
    }
    
}

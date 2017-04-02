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
    private String [] simbulos = new String[] {"(","^","*","/","+","-",")"};
    public Tabela(){}
    
    public boolean getValor(String linha, String coluna) throws Exception{
        int y = this.getNumero(coluna);
        int x = this.getNumero(linha);
        boolean valor;
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
    
    public Fila clone(){}
     
    public String toString(){
        String texto = "Caracteres permitidos:";
        for(int i=0; i<=this.simbulos.length;i++)
            texto+= this.simbulos[i]+ " ";
        
    }
     
     public boolean equals(Object obj){
        if(obj==null)
            return false;
        if(this==obj)
            return true;
        if(this.getClass()!=obj.getClass())
            return false;
        return true;
    }
     
    public int hashCode(){
        //int ret = super.hashCode()// quando herdar de alguma classe
        int ret = 666; //qualquer numero, não zero e intero, desde que sua classe não herde de nenhuma classe
        
        //Fazer um loop com a matriz e com o vector;
        for (int i =0; i<=7;i++){
            ret = 7 * ret + this.simbulos[i].hashCode();
        }
        for (int i =0; i<=7;i++){
            for (int y =0; y<=7;y++){
                 ret = 7 * ret + new Boolean(this.tabela[i][y]).hashCode();
            }
        }
        return ret;
    }
    
}

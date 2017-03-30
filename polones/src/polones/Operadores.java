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
public class Operadores {
    private String[] validos = {"+","-","/","*","^"};
    
    public Operadores() {}
    
    public boolean isValid(String operador){
        if(operador.equals("*")){
            return true;
        }
        else if(operador.equals("/")){
            return true;
        }
        else if(operador.equals("+")){
            return true;
        }
        else if(operador.equals("-")){
            return true;
        }
        else if(operador.equals("^")){
            return true;
        }
        else if(operador.equals("(")){
            return true;
        }
        else if(operador.equals(")")){
            return true;
        }
	return false;
    }
    
    public double calcular(double a, String operador, double b) throws Exception{
        double resultado=0;
        
        if(operador.equals("*")){
            resultado = a*b;
        }
        else if(operador.equals("/")){
            resultado = a/b;
        }
        else if(operador.equals("+")){
            resultado = a+b;
        }
        else if(operador.equals("-")){
            resultado = a-b;
        }
        else if(operador.equals("^")){
            resultado = Math.pow(a, b);
        }
            
        return resultado;
    }
    
    public Fila clone(){}
     
    public String toString(){
        String texto = "";
        texto += "Valores validos: ";
        
        for (int i =0; i<=validos.length;i++){
            if(this.validos[i]!=null)
                texto += this.validos[i]+", ";
        }
        return texto;
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

        for (int i =0; i<=validos.length;i++){
            if(this.validos[i]!=null)
                ret = 7 * ret + this.validos[i].hashCode();
        }
        return ret;
    }
}

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
}

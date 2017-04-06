/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polones;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author Ultron
 */
public class Polones {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        BufferedReader teclado = new BufferedReader( new InputStreamReader(System.in));
        String linha = new String();
        Calculadora calc;
        do{
            try{
             System.out.println(""
                     + "Digite sua equanção que vai ser calculada (no formato infixo) e logo sera calculada:"
                     + "\n**caso deseje sair do programa digite sair");
             linha = teclado.readLine();
             System.out.println(linha.toString());
             if(!(linha.equals("sair"))){
                calc = new Calculadora(linha);
                System.out.println("Resultado:" + calc.toString());
             }
            }catch(Exception e){
                    System.out.println(e.getMessage());
            }
        }while(!(linha.equals("sair")));
        System.out.println("Obrigado por usar o nosso programa"
                + "\n Marcelo Custodio"
                + "\n Rodrigo Baptista");
    }
    
}

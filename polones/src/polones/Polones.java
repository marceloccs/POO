/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polones;

/**
 *
 * @author Ultron
 */
public class Polones {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        
        Calculadora calc = new Calculadora("1+4*3^2/3-4*(2-1)+9");
        System.out.println(calc.toString());
        //System.out.println(calc.getResultado());
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labirinto;

/**
 *
 * @author Ultron
 */
public class Programa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            LeitorArquivo reader = new LeitorArquivo("E:\\test.txt");
            System.out.println(reader.getConteudoString());
        }catch(Exception erro){
            System.err.println(erro);
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labirinto;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author Ultron
 */
public class Programa {
    /////// TODO: Ler teclado e loop;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String resposta ="";
        BufferedReader teclado = new BufferedReader( new InputStreamReader(System.in));
        do{
            try{
                System.out.println(""
                     + "O caminho com o nome de onde o seu mapa esta localizado dentro do computador:"
                     + "\n*Lembre de por a extenção .txt"
                     + "\n**Caso deseje sair do programa digite sair");
                resposta = teclado.readLine();
                if(!resposta.equals("sair")){
                    LeitorArquivo reader = new LeitorArquivo(resposta);
                    Mapa mapa = new Mapa(reader.getConteudo());
                    ProcuradorCaminho caminho = new ProcuradorCaminho(mapa);
                    System.out.println(caminho.toString());
                }
            }catch(Exception erro){
                System.err.println(erro.getMessage());
            }
        }while(!resposta.equals("sair"));
    }
    
}

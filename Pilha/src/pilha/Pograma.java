/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pilha;
import java.io.*;

/**
 *
 * @author Ultron
 */
public class Pograma {
    
    public static void main(String[] args) throws Exception {
        BufferedReader teclado = new BufferedReader( new InputStreamReader(System.in));
        int opcao = -1;
        int i=0;
        Fila fil = new Fila(12);
        boolean flag = false;
        do{
            try{
            
                System.out.println("Qual a capacidade da fila?");
                opcao = Integer.parseInt (teclado.readLine());
                fil = new Fila (opcao);
                flag=true;
            }catch(Exception e){
                    System.out.println(e);
            }
        }while(flag!=true);
        
        do{
            try{
             System.out.println("Digite sua opção \n 1 guarde novos valores \n 2 jogue fora o primeiro valor \n 3 obtenha o promeiro valor da fila \n 4 encerra o programa \n 5 lista Fila");
             opcao = Integer.parseInt (teclado.readLine());
             switch(opcao){
                  case 1:
                       System.out.println("Digite o valor para guardar");
                       int valor = Integer.parseInt (teclado.readLine());
                       fil.guarde(valor);
                       break;
                  case 2:
                       fil.jogueFora();
                       System.out.println("Valor Removido");
                       break;
                  case 3:
                       System.out.println("Valor Recuperado " + fil.recupere());
                       break;
                  case 4:
                       System.out.println("Obrigado por usar esse programa");//sair do programa;
                       break;
                  case 5:
                       System.out.println(fil.listar());
                       break;
                  default: System.out.println("Valor invalido") ;//besteira
             }
            }catch(Exception e){
                    System.out.println(e);
            }
        }while(opcao!=4);
     }
}
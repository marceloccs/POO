/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bingo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import bd.dbos.User;
import cliente.ClienteNovo;

/**
 *
 * @author Ultron
 */
public class Bingo {

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	try {
    		System.out.println("Escreva o IP do servidor: \n");
    		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    		String entrada = in.readLine();
    		System.out.println(entrada);
			new ClienteNovo(entrada);
			new HomePanel().setVisible(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
}

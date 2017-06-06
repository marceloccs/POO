package cliente;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import protocolo.ProtocoloPedido;

public class Cliente implements Runnable{
	  private String host = Constants.host;

	   private int porta = Constants.porta;
	   private boolean runner = true;
	   private ProtocoloPedido pedido;

	   public Cliente (ProtocoloPedido per) {
		   this.pedido = per;
	   }

	   

	   public void run(){
		 try{
	     Socket cliente = new Socket(this.host, this.porta);

	     System.out.println("O cliente se conectou ao servidor!");

	 

	     // thread para receber mensagens do servidor

	     Recebedor r = new Recebedor(cliente.getInputStream());

	     new Thread(r).start();

	     

	     // l� msgs do teclado e manda pro servidor

	     Scanner teclado = new Scanner(System.in);

	     PrintStream saida = new PrintStream(cliente.getOutputStream());

	     while (this.runner==true) {

	       saida.println(teclado.nextLine());

	     }

	     

	     saida.close();

	     teclado.close();

	     cliente.close();    

	   }catch(Exception e){
		   this.para();
	   }
	}
	
	   private void para() {
		this.runner = false;
		
	}

	 }
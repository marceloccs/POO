package server;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class TrataClientes implements Runnable {

	 

	   private ObjectInputStream cliente;

	   private Servidor servidor;

	 

	   public TrataClientes(ObjectInputStream cliente, Servidor servidor) {

	     this.cliente = cliente;

	     this.servidor = servidor;

	   }

	 

	   public void run() {

	     // quando chegar uma msg, distribui pra todos

	     Scanner s = new Scanner(this.cliente);

	     while (s.hasNextLine()) {
	    	 System.out.println(s.nextLine());

	       servidor.distribuiMensagem(s.nextLine());

	     }

	     s.close();

	   }
}

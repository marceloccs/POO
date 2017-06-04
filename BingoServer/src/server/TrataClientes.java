package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Scanner;

import protocolo.ProtocoloPedido;

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
	     try {
	    	 ProtocoloPedido proto = (ProtocoloPedido)this.cliente.readObject();
	    	 proto.toString();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     while (s.hasNextLine()) {
	    	 System.out.println(s.nextLine());
	    	 //s.nextLine()
	       servidor.distribuiMensagem();

	     }

	     s.close();

	   }
}

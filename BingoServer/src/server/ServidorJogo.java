package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import org.apache.commons.lang3.SerializationUtils;

import protocolo.ProtocoloResposta;

public class ServidorJogo implements Runnable{
	private int porta;

	   private ArrayList<ObjectInputStream> clientes;
	   private ArrayList<ObjectOutputStream> clientesRecebidores;
	   private Sorteio sorte;
	   private boolean runner = true;

	   

	   public ServidorJogo (int porta) {

	     this.porta = porta;

	     this.clientes = new ArrayList<ObjectInputStream>();
	     this.clientesRecebidores = new ArrayList<ObjectOutputStream>();
	     this.sorte = new Sorteio(this);
	     //ts = new Thread(sorte);
		 //this.ts.start();
	     this.sorte.start();

	   }

	   

	   public void run (){
		  try{
		     ServerSocket servidor = new ServerSocket(this.porta);
	
		     System.out.println("Porta"+ this.porta +"aberta!");
	
		     
	
		     while (this.runner=true) {
		       
		       // aceita um cliente
			   Socket cliente = servidor.accept();
			   System.out.println("Nova conexão com o cliente "+cliente.getInetAddress().getHostAddress());
			   
		       // adiciona saida do cliente à lista
		       ObjectOutputStream ps = new ObjectOutputStream(cliente.getOutputStream());
		       this.clientesRecebidores.add(ps);
		       // cria tratador de cliente numa nova thread
		       //TrataClientes tc = new TrataClientes(new ObjectInputStream(cliente.getInputStream()), this);
		       //new Thread(tc).start();
		       
		     }
		 }catch(Exception e){
			 System.out.println(e.getMessage());
			 //this.para();
		 }
		 }

	 

	   private void para() {
		   System.out.println("Para de rodar");
		   this.runner  = false;
	   }



	public void distribuiMensagem(Object obj) throws IOException {

	     // envia msg para todo mundo
		System.out.println(obj.toString());
	     for (ObjectOutputStream cliente : this.clientesRecebidores) {

	       try {
	    	 byte[] dataresposta = SerializationUtils.serialize((Serializable) obj);
	    	 cliente.writeObject(dataresposta);
	    	 cliente.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	     }
}
}

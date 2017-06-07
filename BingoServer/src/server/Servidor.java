package server;

import java.awt.List;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import org.apache.commons.lang3.SerializationUtils;

import protocolo.ProtocoloResposta;

public class Servidor implements Runnable{
	private int porta;

	   private ArrayList<ObjectInputStream> clientes;
	   private ArrayList<ObjectOutputStream> clientesRecebidores;

	private boolean runner = true;

	   

	   public Servidor (int porta) {

	     this.porta = porta;

	     this.clientes = new ArrayList<ObjectInputStream>();
	     this.clientesRecebidores = new ArrayList<ObjectOutputStream>();

	   }

	   

	   public void run (){
		  try{
		     ServerSocket servidor = new ServerSocket(this.porta);
	
		     System.out.println("Porta"+ this.porta +"aberta!");
	
		     
	
		     while (this.runner=true) {
	
		       // aceita um cliente
	
		       Socket cliente = servidor.accept();
	
		       System.out.println("Nova conexão com o cliente " +   
	
		         cliente.getInetAddress().getHostAddress()
	
		       );
	
		       
	
		       // adiciona saida do cliente à lista
	
		       ObjectOutputStream ps = new ObjectOutputStream(cliente.getOutputStream());
	
		       this.clientesRecebidores.add(ps);
	
		       
	
		       // cria tratador de cliente numa nova thread
	
		       TrataClientes tc = new TrataClientes(new ObjectInputStream(cliente.getInputStream()), this);
	
		       new Thread(tc).start();
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



	public void distribuiMensagem(ProtocoloResposta obj) throws IOException {

	     // envia msg para todo mundo
		 obj.getObjeto().toString();
	     for (ObjectOutputStream cliente : this.clientesRecebidores) {

	       try {
	    	 byte[] dataresposta = SerializationUtils.serialize(obj);
	    	 cliente.writeObject(dataresposta);
	    	 cliente.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	     }
}

	 }
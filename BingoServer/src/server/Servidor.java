package server;

import java.awt.List;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor implements Runnable{
	private int porta;

	   private ArrayList<PrintStream> clientes;

	private boolean runner = true;

	   

	   public Servidor (int porta) {

	     this.porta = porta;

	     this.clientes = new ArrayList<PrintStream>();

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
	
		       PrintStream ps = new PrintStream(cliente.getOutputStream());
	
		       this.clientes.add(ps);
	
		       
	
		       // cria tratador de cliente numa nova thread
	
		       TrataClientes tc = new TrataClientes(cliente.getInputStream(), this);
	
		       new Thread(tc).start();
		     }
		 }catch(Exception e){
			 this.para();
		 }
		 }

	 

	   private void para() {
		   this.runner  = false;
	   }



	public void distribuiMensagem(String msg) {

	     // envia msg para todo mundo

	     for (PrintStream cliente : this.clientes) {

	       cliente.println(msg);

	     }

	   }

	 }
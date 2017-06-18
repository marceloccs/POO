package server;

import java.awt.List;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

import org.apache.commons.lang3.SerializationUtils;

import protocolo.ProtocoloResposta;

public class ServidorNovo implements Runnable{
	private static int porta;
	private static Vector <Jogador> players = new Vector<Jogador>(30);
	private static Sorteio jogo;

	private boolean runner = true;

	   

	   public ServidorNovo (int porta) {
		 ServidorNovo.porta = porta;
		 ServidorNovo.jogo = new Sorteio(this);
	   }
	   
	   public void run (){
			  try{
			     ServerSocket servidor = new ServerSocket(ServidorNovo.porta);
			     System.out.println("Porta"+ ServidorNovo.porta +"aberta!");
			     
			     while (this.runner=true) {
			       Socket cliente = servidor.accept();
			       System.out.println("Nova conexão com o cliente " +  cliente.getInetAddress().getHostAddress());
			       Jogador player = new Jogador(cliente,this); 
			       ServidorNovo.players.add(player);
			       new Thread(player).start();
			     }
			 }catch(Exception e){
				 System.out.println(e.getMessage());
				 //this.para();
			 }
		 }
	   public void para() {
		   System.out.println("Para de rodar");
		   this.runner  = false;
	   }
	   public void encerraServidor() throws IOException{
		   for(int i=0;i<ServidorNovo.players.size();i++){
			   ServidorNovo.players.get(i).fechaConexao();
		   }
	   }
	   public static void enviaMensagem(ProtocoloResposta proto) throws IOException{
		   for(int i=0;i<ServidorNovo.players.size();i++){
			   ServidorNovo.players.get(i).enviaMensagem(proto);
		   }
	   }
	   public static void enviaMensagemJogando(ProtocoloResposta resp) throws IOException{
		   for(int i=0;i<ServidorNovo.players.size();i++){
			   if(ServidorNovo.players.get(i).isPlaying())
				   ServidorNovo.players.get(i).enviaMensagem(resp);
		   }
	   }
	   public boolean iniciaJogo(){
		   if (ServidorNovo.jogo.getIniciado()==false){
			   ServidorNovo.jogo.start();
			   return true;
		   }else{
			   if(ServidorNovo.jogo.getLiberado()==true)
				   return true;
		   }
		   return false;
	   }
	   @Override
	public String toString() {
		return "ServidorNovo [runner=" + runner + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (runner ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServidorNovo other = (ServidorNovo) obj;
		if (runner != other.runner)
			return false;
		return true;
	}

	public static void pausaJogo(){
		   ServidorNovo.jogo.pausa();
	   }
	   public static void voltaJogo(){
		   ServidorNovo.jogo.volta();
	   }
	   public static void terminaJogo(){
		   ServidorNovo.jogo.para();
	   }
	 }
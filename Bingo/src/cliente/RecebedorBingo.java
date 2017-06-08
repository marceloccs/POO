package cliente;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.apache.commons.lang3.SerializationUtils;

import bingo.BingoPanel;
import protocolo.AcaoResposta;
import protocolo.ProtocoloPedido;
import protocolo.ProtocoloResposta;

public class RecebedorBingo implements Runnable{
	
	   private ObjectInputStream servidor;
	   private BingoPanel view;
	   private boolean finaliza=false;
	   public RecebedorBingo(InputStream servidor, BingoPanel view) throws Exception {
	     try {
			this.servidor = new ObjectInputStream(servidor);
			this.view = view;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new Exception ("Erro ao receber objeto");
		}
	   }
	   public void run(){
	     // recebe msgs do servidor e imprime na tela
		   ProtocoloResposta ret = null;
		   int tempo=0;
		   int tempoLimite=90000000;
		   while(1>0){
			   //System.out.println(this.servidor.readObject().getClass().toString());
			   byte[] ser = null;
			try {
				ser = (byte[]) this.servidor.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   ProtocoloResposta proto = (ProtocoloResposta) SerializationUtils.deserialize(ser);
			   Constants cont = null;
			try {
				cont = new Constants();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   if(proto.getIP().equals(cont.getIP())||(tempo!=tempoLimite)){
				   if((proto.getSucesso()==true)&&(proto.getStatus()==AcaoResposta.IniciarJogo)){
					   this.view.habilitaButton();
				   }else{
					   this.view.printaInfo("Erro ao comunicar ao servidor");
					   this.finaliza=true;
				   }
				   break;
			   }
			   tempo++;
		   }
		   while(finaliza=false){
			   byte[] ser = null;
			try {
				ser = (byte[]) this.servidor.readObject();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   this.view.printaInfo((String)SerializationUtils.deserialize(ser));
		   }
		   if(ret==null){
			   try {
				throw new Exception("Time Out");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }
	   }
	 }
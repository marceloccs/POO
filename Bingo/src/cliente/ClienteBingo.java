package cliente;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.apache.commons.lang3.SerializationUtils;

import com.sun.org.apache.xml.internal.security.encryption.Serializer;

import bingo.BingoPanel;
import protocolo.ProtocoloPedido;
import protocolo.ProtocoloResposta;

public class ClienteBingo implements Runnable{
	  private String host = Constants.host;

	   private int porta = Constants.portaJogo;
	   private ProtocoloPedido pedido;
	   private BingoPanel view;

	   public ClienteBingo (ProtocoloPedido per, BingoPanel view) throws Exception {
		   this.pedido = per;
		   this.view = view;
	   }

	   public void run(){
		 try{
	     Socket cliente = new Socket(this.host, this.porta);
	     
	     // lê msgs do teclado e manda pro servidor
	     ObjectOutputStream  saida = new ObjectOutputStream(cliente.getOutputStream());
	     byte[] dataPedido = SerializationUtils.serialize(pedido);
	     saida.writeObject(dataPedido);
	     saida.flush();
	     // thread para receber mensagens do servidor
	     RecebedorBingo r = new RecebedorBingo(cliente.getInputStream(),this.view);
	     Thread t = new Thread(r);
	     t.start();
	     //retorno = r.pegaObjeto();
	     //cliente.close();
	     //saida.close();
	     //return retorno;
	     
	   }catch(Exception e){
		   System.err.println(e.getStackTrace());
		   System.err.println(e.getMessage());
		   try {
			throw new Exception("erro ao se comunicar com o servidor");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
	   }
	}

}
package cliente;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import Protocolo.ProtocoloPedido;

public class ClienteNormal{
	  private String host = Constants.host;

	   private int porta = Constants.porta;
	   private ProtocoloPedido pedido;

	   public ClienteNormal (ProtocoloPedido per) throws Exception {
		   this.pedido = per;
	   }

	   public Object realizapedido() throws Exception{
		 Object retorno = null;
		 try{
	     Socket cliente = new Socket(this.host, this.porta);
	     
	     // lê msgs do teclado e manda pro servidor
	     ObjectOutputStream  saida = new ObjectOutputStream(cliente.getOutputStream());
	     saida.writeObject(pedido);
	     
	     // thread para receber mensagens do servidor
	     RecebedorNormal r = new RecebedorNormal(cliente.getInputStream());
	     retorno = r.pegaObjeto();
	     saida.close();
	     cliente.close();    
	     return retorno;
	     
	   }catch(Exception e){
		   throw new Exception("erro ao se comunicar com o servidor"); 
	   }
	}

}
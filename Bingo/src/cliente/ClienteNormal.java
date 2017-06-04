package cliente;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.apache.commons.lang3.SerializationUtils;

import com.sun.org.apache.xml.internal.security.encryption.Serializer;

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
	     
	     // l� msgs do teclado e manda pro servidor
	     ObjectOutputStream  saida = new ObjectOutputStream(cliente.getOutputStream());
	     byte[] dataPedido = SerializationUtils.serialize(pedido);
	     System.out.println(dataPedido.toString());
	     saida.writeObject(dataPedido);
	     saida.flush();
	     saida.close();
	     // thread para receber mensagens do servidor
	     RecebedorNormal r = new RecebedorNormal(cliente.getInputStream());
	     retorno = r.pegaObjeto();
	     cliente.close();    
	     return retorno;
	     
	   }catch(Exception e){
		   System.err.println(e.getStackTrace());
		   System.err.println(e.getMessage());
		   throw new Exception("erro ao se comunicar com o servidor"); 
	   }
	}

}
package cliente;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.apache.commons.lang3.SerializationUtils;

import com.sun.org.apache.xml.internal.security.encryption.Serializer;

import protocolo.ProtocoloPedido;
import protocolo.ProtocoloResposta;

public class ClienteNormal{
	  private String host = Constants.host;

	   private int porta = Constants.porta;
	   private ProtocoloPedido pedido;

	   public ClienteNormal (ProtocoloPedido per) throws Exception {
		   this.pedido = per;
	   }

	   public ProtocoloResposta realizapedido() throws Exception{
		  ProtocoloResposta retorno = null;
		 try{
	     Socket cliente = new Socket(this.host, this.porta);
	     
	     // lê msgs do teclado e manda pro servidor
	     ObjectOutputStream  saida = new ObjectOutputStream(cliente.getOutputStream());
	     byte[] dataPedido = SerializationUtils.serialize(pedido);
	     saida.writeObject(dataPedido);
	     saida.flush();
	     // thread para receber mensagens do servidor
	     RecebedorNormal r = new RecebedorNormal(cliente.getInputStream());
	     retorno = r.pegaObjeto();
	     cliente.close();
	     saida.close();
	     return retorno;
	     
	   }catch(Exception e){
		   System.err.println(e.getStackTrace());
		   System.err.println(e.getMessage());
		   throw new Exception("erro ao se comunicar com o servidor"); 
	   }
	}

}
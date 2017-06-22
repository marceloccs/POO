package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.apache.commons.lang3.SerializationUtils;

import protocolo.ProtocoloPedido;
import protocolo.ProtocoloResposta;

public class ClienteNovo {
	   private static Socket socket;
	   private static ObjectOutputStream saida;
	   private static ObjectInputStream entrada;
	   
	   public ClienteNovo (String entrada2) throws Exception {
		   String host ="";
		   if(entrada2!=null)
			   host=entrada2;
		   else
			   host = Constants.host;
		   
		   ClienteNovo.socket = new Socket(host, Constants.porta);
		   ClienteNovo.saida = new ObjectOutputStream(ClienteNovo.socket.getOutputStream());
		   ClienteNovo.entrada = new ObjectInputStream(ClienteNovo.socket.getInputStream());
	   }

	   @Override
	public String toString() {
		return "Socket porta: "+this.socket.getPort()+", host: "+this.socket.getRemoteSocketAddress();
	}

	public static ProtocoloResposta realizapedido(ProtocoloPedido per) throws Exception{
		 ProtocoloResposta retorno = null;
		 try{
	     
	     // lê msgs do teclado e manda pro servidor
	     byte[] dataPedido = SerializationUtils.serialize(per);
	     System.out.println("inicia envio");
	     ClienteNovo.saida.writeObject(dataPedido);
	     ClienteNovo.saida.flush();
	     System.out.println("envia");
	     // thread para receber mensagens do servidor
	     retorno = ClienteNovo.esperaMensagem();
	     return retorno;
	     
	   }catch(Exception e){
		   System.err.println(e.getStackTrace());
		   System.err.println(e.getMessage());
		   throw new Exception("erro ao se comunicar com o servidor"); 
	   }
	}
	   
	private static ProtocoloResposta esperaMensagem() throws Exception{
		ProtocoloResposta ret = null;
		   int tempo=0;
		   int tempoLimite=90000000;
		   while(1>0){
			   //System.out.println(this.servidor.readObject().getClass().toString());
			   System.out.println("inicia recebimento");
			   Object obj = ClienteNovo.entrada.readObject();
			   System.out.println(obj);
			   byte[] ser = (byte[]) obj;
			   System.out.println("recebe");
			   ProtocoloResposta proto = (ProtocoloResposta) SerializationUtils.deserialize(ser);
			   System.out.println("converte recebimento");
			   Constants cont = new Constants();
			   if(proto.getIP().equals(cont.getIP())){
				   ret=proto;
				   break;
			   }
			   tempo++;
		   }
		   if(ret==null){
			   throw new Exception("Time Out");
		   }
		 return ret;
	}
	public static ObjectInputStream getEntrada(){
		return ClienteNovo.entrada;
	}
}

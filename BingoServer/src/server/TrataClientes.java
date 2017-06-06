package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Scanner;

import org.apache.commons.lang3.SerializationUtils;

import protocolo.AcaoPedido;
import protocolo.ProtocoloPedido;
import protocolo.ProtocoloResposta;

public class TrataClientes implements Runnable {

	 

	   private ObjectInputStream cliente;

	   private Servidor servidor;

	 

	   public TrataClientes(ObjectInputStream cliente, Servidor servidor) {

	     this.cliente = cliente;

	     this.servidor = servidor;

	   }

	 

	   public void run() {

	     // quando chegar uma msg, distribui pra todos

	     //Scanner s = new Scanner(this.cliente);
	     //ProtocoloPedido proto = (ProtocoloPedido) SerializationUtils.deserialize(this.cliente.readByte());
		 try {
			Object serializado = this.cliente.readObject();
			byte[] ser = (byte[]) serializado;
			ProtocoloPedido proto = (ProtocoloPedido) SerializationUtils.deserialize(ser);
			AcaoPedido ac = proto.getAcao();
			ProtocoloResposta obj = ac.acionarAcao(proto);
			//System.out.println(obj.toString());
			servidor.distribuiMensagem(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}
}

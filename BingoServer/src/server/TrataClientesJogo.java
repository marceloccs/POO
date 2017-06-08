package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Scanner;

import org.apache.commons.lang3.SerializationUtils;

import bd.dbos.Cartela;
import programa.IniciadorJogo;
import protocolo.AcaoPedido;
import protocolo.AcaoResposta;
import protocolo.ProtocoloPedido;
import protocolo.ProtocoloResposta;

public class TrataClientesJogo implements Runnable {

	 

	   private ObjectInputStream cliente;

	   private Servidor servidor;

	 

	   public TrataClientesJogo(ObjectInputStream cliente, Servidor servidor) {

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
			if(proto.getAcao()==AcaoPedido.Ganhou){
				//IniciadorJogo jogoIniciado = new IniciadorJogo();
				//jogoIniciado.getSorteio().para();
				Cartela car = (Cartela)proto.getObj();
				//ProtocoloResposta resp;
				//if(car.verificaNumeros(jogoIniciado.getSorteio().getNumeros())==true){
					//resp = new ProtocoloResposta(car.getDono(), AcaoResposta.JogoAcabou, "Ja possui um ganhador", true, null);
				//}else{
					//jogoIniciado.getSorteio().run();
				//}
			}
			//AcaoPedido ac = proto.getAcao();
			//ProtocoloResposta obj = ac.acionarAcao(proto);
			//System.out.println(obj.toString());
			//servidor.distribuiMensagem(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

}
}

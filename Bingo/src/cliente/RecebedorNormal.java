package cliente;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Scanner;

import org.apache.commons.lang3.SerializationUtils;

import protocolo.ProtocoloPedido;
import protocolo.ProtocoloResposta;

public class RecebedorNormal{
	
	   private ObjectInputStream servidor;
	   public RecebedorNormal(InputStream servidor) throws Exception {
	     try {
			this.servidor = new ObjectInputStream(servidor);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new Exception ("Erro ao receber objeto");
		}
	   }
	   public ProtocoloResposta pegaObjeto() throws Exception {
	     // recebe msgs do servidor e imprime na tela
		   ProtocoloResposta ret = null;
		   int tempo=0;
		   int tempoLimite=100000;
		   while(1>0){
			   //System.out.println(this.servidor.readObject().getClass().toString());
			   byte[] ser = (byte[]) this.servidor.readObject();
			   ProtocoloResposta proto = (ProtocoloResposta) SerializationUtils.deserialize(ser);
			   Constants cont = new Constants();
			   if(proto.getIP().equals(cont.getIP())||(tempo!=tempoLimite)){
				   ret=proto;
				   break;
			   }
			   Thread.sleep(1000);
			   tempo++;
		   }
		   if(ret==null){
			   throw new Exception("Time Out");
		   }
		 return ret;
	   }
	 }
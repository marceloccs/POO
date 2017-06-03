package cliente;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Scanner;

import Protocolo.ProtocoloPedido;
import Protocolo.ProtocoloResposta;

public class RecebedorNormal{
	
	   private ObjectInputStream servidor;
	   public RecebedorNormal(InputStream servidor) {
	     this.servidor = (ObjectInputStream) servidor;
	   }
	   public Object pegaObjeto() throws ClassNotFoundException, IOException {
	     // recebe msgs do servidor e imprime na tela
		   ProtocoloResposta obj;
		   while(1>0){
			   System.out.println(this.servidor.readObject().toString());
			   obj = (ProtocoloResposta)this.servidor.readObject();
			   if(obj.getIP()==Constants.ipProprio){
				   break;
			   }
		   }
		 return obj;
	   }
	 }
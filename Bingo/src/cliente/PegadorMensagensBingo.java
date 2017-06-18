package cliente;

import java.io.ObjectInputStream;

import org.apache.commons.lang3.SerializationUtils;

import bingo.BingoPanel;
import bingo.HomePanel;
import protocolo.AcaoPedido;
import protocolo.AcaoResposta;
import protocolo.ProtocoloResposta;

public class PegadorMensagensBingo implements Runnable{
	private ObjectInputStream receptor;
	private boolean runner =true;
	private BingoPanel view;
	public PegadorMensagensBingo(ObjectInputStream r, BingoPanel view){
		this.receptor = r;
		this.view = view;
	}
	
	public PegadorMensagensBingo(PegadorMensagensBingo pB) {
		this.runner = pB.runner;
		this.receptor = pB.receptor;
		this.view = pB.view;
	}

	@Override
	public void run() {
		while(this.runner==true){
			try{
				byte[] ser = (byte[]) this.receptor.readObject();
				ProtocoloResposta proto = (ProtocoloResposta) SerializationUtils.deserialize(ser);
				if(proto.getStatus().equals(AcaoResposta.NumeroSortiado)){
					this.view.printaInfo((String)proto.getObjeto());
				}else if(proto.getStatus().equals(AcaoResposta.ParaJogo)){
					this.view.desabilitaButton();
					this.view.printaInfo("um momento por favor");
				}else if(proto.getStatus().equals(AcaoResposta.VoltaJogo)){
					this.view.habilitaButton();
					this.view.printaInfo("Obrigado pela espera");
				}else if(proto.getStatus().equals(AcaoResposta.FinalizaJogo)){
					this.view.desabilitaButton();
					this.view.printaInfo(proto.getMensagem());
					this.wait(5000);
					new HomePanel().setVisible(true);
				}
			}catch(Exception e){}
		}
	}
	
	public void parar(){
		this.runner=false;
	}
	
	public Object clone(){
		return new PegadorMensagensBingo(this);
	}
	public boolean equals(Object obj){
		try{
			if(this == obj)
				return true;
			PegadorMensagensBingo ls = (PegadorMensagensBingo)obj;
			if(!ls.runner==this.runner){
				return false;
			}
			if(!ls.receptor.equals(this.receptor)){
				return false;
			}
			if(!ls.view.equals(this.view)){
				return false;
			}
			return true;
		}catch(Exception e){
			return false;
		}
	}
	public String toString(){
		return "Recebe mensagens e printa no painel: "+this.view.getClass();
	}
	public int hashCode(){
		int ret = 666; //qualquer numero, não zero e intero, desde que sua classe não herde de nenhuma classe
	       
        ret = 7 * ret + this.view.hashCode();
        ret = 7 * ret + this.receptor.hashCode();
        ret = 7 * ret + new Boolean(this.runner).hashCode();
        return ret;
	}

}

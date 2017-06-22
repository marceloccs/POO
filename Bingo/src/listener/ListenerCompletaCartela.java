package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import bd.dbos.Cartela;
import bd.dbos.User;
import bingo.BingoPanel;
import bingo.CadastrarPanel;
import bingo.HomePanel;
import cliente.ClienteNovo;
import cliente.Constants;
import protocolo.AcaoPedido;
import protocolo.AcaoResposta;
import protocolo.ProtocoloPedido;
import protocolo.ProtocoloResposta;

public class ListenerCompletaCartela implements ActionListener {
	protected BingoPanel view;
	
	public ListenerCompletaCartela(BingoPanel view){
		this.view=view;
	}
	
	public ListenerCompletaCartela(ListenerCompletaCartela listenerCompletaCartela) {
		this.view = listenerCompletaCartela.view;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			Cartela cartela = this.view.getCartela();
			this.view.paraThread();
			this.view.desabilitaButton();
			Constants cons = new Constants();
			ProtocoloPedido protocolo = new ProtocoloPedido(cartela, AcaoPedido.Ganhou, cons.getIP());
			
			ProtocoloResposta ret = ClienteNovo.realizapedido(protocolo);
			//ret.getIP();
			this.view.printaInfo(ret.getMensagem());
			if(ret.getStatus()==AcaoResposta.Ganhador){
				this.view.desabilitaButton();
				//new HomePanel().setVisible(false);
			}
			if(ret.getStatus()==AcaoResposta.Errou){
				//new HomePanel().setVisible(false);
				//this.view.printaInfo("mano!!!");
				this.view.habilitaButton();
			}
			//this.view.bloqueiCaixas();
		} catch (Exception e) {
			this.view.habilitaButton();
			this.view.printaInfo(e.getMessage());
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}

	}
	public String toString(){
		return "referece a pagina: "+this.view.getClass();
	}
	public Object clone(){
		return new ListenerCompletaCartela(this);
	}
	public boolean equals(Object obj){
		try{
			if(this == obj)
				return true;
			ListenerCompletaCartela ls = (ListenerCompletaCartela)obj;
			if(!ls.view.equals(this.view)){
				return false;
			}
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public int hashCode(){
		int ret = 666; //qualquer numero, não zero e intero, desde que sua classe não herde de nenhuma classe
	       
        //ret = 7 * ret + this.view.hashCode();
        return ret;
	}
}

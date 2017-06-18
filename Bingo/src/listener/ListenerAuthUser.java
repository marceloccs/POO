package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import bd.dbos.User;
import bingo.BingoPanel;
import bingo.CadastrarPanel;
import bingo.HomePanel;
import cliente.ClienteNovo;
import cliente.Constants;
import protocolo.AcaoPedido;
import protocolo.ProtocoloPedido;
import protocolo.ProtocoloResposta;

public class ListenerAuthUser implements ActionListener {
	protected HomePanel view;
	
	public ListenerAuthUser(HomePanel view){
		this.view=view;
	}
	
	public ListenerAuthUser(ListenerAuthUser listenerAuthUser) {
		this.view = listenerAuthUser.view;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//this.view.bloqueiCaixas();
		try {
			User user = view.createUser();
			System.out.println(user.toString());
			Constants cons = new Constants();
			ProtocoloPedido protocolo = new ProtocoloPedido(user, AcaoPedido.Ath, cons.getIP());
			//ClienteNormal cli = new ClienteNormal(protocolo);
			ProtocoloResposta ret = ClienteNovo.realizapedido(protocolo);
			if(ret.getSucesso()==true){
				ProtocoloPedido protocoloJogo = new ProtocoloPedido(null, AcaoPedido.IniciarJogo, cons.getIP());
				//ClienteNormal cliJogo = new ClienteNormal(protocoloJogo);
				ProtocoloResposta retJogo = ClienteNovo.realizapedido(protocoloJogo);
				if(retJogo.getSucesso()==true){
					new BingoPanel((User)ret.getObjeto()).setVisible(true);
					this.view.dispose();
				}else{
					this.view.printa("Atualmente ja existe um jogo Rodando tente novamente mais tarde");
					this.view.clear();
				}
			}else{
				this.view.printa(ret.getMensagem());
				this.view.clear();
			}
			//ret.getIP();
			this.view.printa(ret.getMensagem());
		} catch (Exception e) {
			this.view.printa(e.getMessage());
		}

	}
	public String toString(){
		return "classe que authfica user";
	}
	public Object clone(){
		return new ListenerAuthUser(this);
	}
	public boolean equals(Object obj){
		try{
			if(this == obj)
				return true;
			ListenerAuthUser ls = (ListenerAuthUser)obj;
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
	       
        ret = 7 * ret + this.view.hashCode();
        return ret;
	}
}

package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import bd.dbos.User;
import bingo.CadastrarPanel;
import bingo.HomePanel;
import cliente.ClienteNormal;
import cliente.Constants;
import protocolo.AcaoPedido;
import protocolo.ProtocoloPedido;
import protocolo.ProtocoloResposta;

public class ListenerAuthUser implements ActionListener {
	protected HomePanel view;
	
	public ListenerAuthUser(HomePanel view){
		this.view=view;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.view.bloqueiCaixas();
		try {
			User user = view.createUser();
			Constants cons = new Constants();
			ProtocoloPedido protocolo = new ProtocoloPedido(user, AcaoPedido.Ath, cons.getIP());
			ClienteNormal cli = new ClienteNormal(protocolo);
			ProtocoloResposta ret = cli.realizapedido();
			//ret.getIP();
			this.view.printa(ret.getMensagem());
		} catch (Exception e) {
			this.view.printa(e.getMessage());
		}

	}
}

package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import bd.dbos.Cartela;
import bd.dbos.User;
import bingo.BingoPanel;
import bingo.CadastrarPanel;
import cliente.ClienteNormal;
import cliente.Constants;
import protocolo.AcaoPedido;
import protocolo.ProtocoloPedido;
import protocolo.ProtocoloResposta;

public class ListenerCompletaCartela implements ActionListener {
	protected BingoPanel view;
	
	public ListenerCompletaCartela(BingoPanel view){
		this.view=view;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			Cartela cartela = this.view.getCartela();
			Constants cons = new Constants();
			ProtocoloPedido protocolo = new ProtocoloPedido(cartela, AcaoPedido.Ganhou, cons.getIP());
			ClienteNormal cli = new ClienteNormal(protocolo);
			//ProtocoloResposta ret = cli.realizapedido();
			//ret.getIP();
			//this.view.printa(ret.getMensagem());
			//this.view.bloqueiCaixas();
		} catch (Exception e) {
			//this.view.printa(e.getMessage());
		}

	}
}

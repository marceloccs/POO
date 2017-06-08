package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import bd.dbos.User;
import bingo.BingoPanel;
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
		//this.view.bloqueiCaixas();
		try {
			User user = view.createUser();
			System.out.println(user.toString());
			Constants cons = new Constants();
			ProtocoloPedido protocolo = new ProtocoloPedido(user, AcaoPedido.Ath, cons.getIP());
			ClienteNormal cli = new ClienteNormal(protocolo);
			ProtocoloResposta ret = cli.realizapedido();
			if(ret.getSucesso()==true){
				ProtocoloPedido protocoloJogo = new ProtocoloPedido(null, AcaoPedido.IniciarJogo, cons.getIP());
				ClienteNormal cliJogo = new ClienteNormal(protocoloJogo);
				ProtocoloResposta retJogo = cliJogo.realizapedido();
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
}

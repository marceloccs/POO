package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import bd.dbos.User;
import bingo.CadastrarPanel;
import cliente.ClienteNormal;
import cliente.Constants;
import protocolo.AcaoPedido;
import protocolo.ProtocoloPedido;
import protocolo.ProtocoloResposta;

public class ListenerCreateUser implements ActionListener {
	protected CadastrarPanel view;
	
	public ListenerCreateUser(CadastrarPanel view){
		this.view=view;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			User user = view.creatUser();
			Constants cons = new Constants();
			ProtocoloPedido protocolo = new ProtocoloPedido(user, AcaoPedido.NewUser, cons.getIP());
			ClienteNormal cli = new ClienteNormal(protocolo);
			ProtocoloResposta ret = cli.realizapedido();
			//ret.getIP();
			this.view.printa(ret.getMensagem());
			this.view.bloqueiCaixas();
		} catch (Exception e) {
			this.view.printa(e.getMessage());
		}

	}

}

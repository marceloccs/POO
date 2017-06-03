package business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Protocolo.AcaoPedido;
import Protocolo.ProtocoloPedido;
import bingo.CadastrarPanel;
import cliente.ClienteNormal;
import cliente.Constants;

public class ListenerCreateUser implements ActionListener {
	protected CadastrarPanel view;
	
	public ListenerCreateUser(CadastrarPanel view){
		this.view=view;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			User cadastrado;
			User user = view.creatUser();
			ProtocoloPedido protocolo = new ProtocoloPedido(user, AcaoPedido.NewUser, Constants.ipProprio);
			ClienteNormal cli = new ClienteNormal(protocolo);
			cadastrado = (User)cli.realizapedido();
		} catch (Exception e) {
			this.view.printa(e.getMessage());
		}

	}

}

package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import bd.dbos.User;
import bingo.CadastrarPanel;
import cliente.ClienteNovo;
import cliente.Constants;
import protocolo.AcaoPedido;
import protocolo.ProtocoloPedido;
import protocolo.ProtocoloResposta;

public class ListenerCreateUser implements ActionListener {
	protected CadastrarPanel view;
	
	public ListenerCreateUser(CadastrarPanel view){
		this.view=view;
	}
	
	public ListenerCreateUser(ListenerCreateUser listenerCreateUser) {
		this.view = listenerCreateUser.view;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			User user = view.creatUser();
			Constants cons = new Constants();
			ProtocoloPedido protocolo = new ProtocoloPedido(user, AcaoPedido.NewUser, cons.getIP());
			//ClienteNormal cli = new ClienteNormal(protocolo);
			ProtocoloResposta ret = ClienteNovo.realizapedido(protocolo);
			//ret.getIP();
			this.view.printa(ret.getMensagem());
			if(ret.getSucesso()){
				this.view.bloqueiCaixas();
				this.view.trocaNomeCancelar();
			}
		} catch (Exception e) {
			this.view.printa(e.getMessage());
		}

	}
	public String toString(){
		return "referece ao panel do: "+this.view.getClass();
	}
	public Object clone(){
		return new ListenerCreateUser(this);
	}
	public boolean equals(Object obj){
		try{
			if(this == obj)
				return true;
			ListenerCreateUser ls = (ListenerCreateUser)obj;
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

package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JToggleButton;

import bd.dbos.User;
import bingo.BingoPanel;
import bingo.HomePanel;
import cliente.ClienteNormal;
import cliente.Constants;
import protocolo.AcaoPedido;
import protocolo.ProtocoloPedido;
import protocolo.ProtocoloResposta;

public class ListenerSelecionaDeselecionaBotao implements ActionListener {
	protected BingoPanel view;
	protected JToggleButton button;
	
	public ListenerSelecionaDeselecionaBotao(BingoPanel view, JToggleButton button){
		this.view=view;
		this.button=button;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		//this.view.bloqueiCaixas();
		try {
			if(this.button.isSelected()){
				this.view.selecioneBotao(this.button.getText());
			}else{
				this.view.deSelecioneBotao(this.button.getText());
			}
		} catch (Exception e) {}

	}

}

package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Closeable;
import java.util.concurrent.TimeUnit;

import javax.swing.JToggleButton;

import bd.dbos.User;
import bingo.BingoPanel;
import bingo.HomePanel;
import cliente.Constants;
import protocolo.AcaoPedido;
import protocolo.ProtocoloPedido;
import protocolo.ProtocoloResposta;

public class ListenerSelecionaDeselecionaBotao implements ActionListener, Cloneable {
	protected BingoPanel view;
	protected JToggleButton button;
	
	public ListenerSelecionaDeselecionaBotao(BingoPanel view, JToggleButton button){
		this.view=view;
		this.button=button;
	}
	
	public ListenerSelecionaDeselecionaBotao(ListenerSelecionaDeselecionaBotao listenerSelecionaDeselecionaBotao) {
		this.view = listenerSelecionaDeselecionaBotao.view;
		this.button = listenerSelecionaDeselecionaBotao.button;
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
	public String toString(){
		return "seleciona e deseleciona os botoes da : "+this.view.getClass();
	}
	public Object clone(){
		return new ListenerSelecionaDeselecionaBotao(this);
	}
	public boolean equals(Object obj){
		try{
			if(this == obj)
				return true;
			ListenerSelecionaDeselecionaBotao ls = (ListenerSelecionaDeselecionaBotao)obj;
			if(!ls.view.equals(this.view)){
				return false;
			}if(!ls.button.equals(this.button)){
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
        ret = 7 * ret + this.button.hashCode();
        return ret;
	}

}

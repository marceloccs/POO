package protocolo;

import java.io.Serializable;

public class ProtocoloPedido implements Serializable{
	private static final long serialVersionUID = -6777723843926337458L;
	private Object obj;
	private AcaoPedido acao;
	private String ip;
	
	public ProtocoloPedido(Object obj, AcaoPedido status, String ip){
		this.obj = obj;
		this.acao = status;
		this.ip = ip;
	}
	public String getIP(){
		return this.ip;
	}
	public Object getObj(){
		return this.obj;
	}
	public AcaoPedido getAcao(){
		return this.acao;
	}
	public String toString(){
		return "A ação foi: "+this.getAcao()+" Para o ip: "+this.getIP()+". E a classe de resposta foi: "+this.getObj().getClass()+" "
						+ "Com a seguinte informação dentro: "+this.getObj();
	}
}
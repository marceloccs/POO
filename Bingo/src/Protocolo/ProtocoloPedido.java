package Protocolo;

public class ProtocoloPedido {
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
}
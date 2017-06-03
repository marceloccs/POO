package protocolo;

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
	public Object getObj(){
		return this.obj;
	}
	public AcaoPedido getAcao(){
		return this.acao;
	}
}

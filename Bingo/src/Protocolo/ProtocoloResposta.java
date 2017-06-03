package Protocolo;

public class ProtocoloResposta {
	private Object obj;
	private AcaoResposta status;
	private String mensagem;
	private boolean sucesso; 
	private String ip;
	
	public ProtocoloResposta(Object obj, AcaoResposta status, String mensagem, boolean sucesso){
		this.obj = obj;
		this.status = status;
		this.mensagem = mensagem;
		this.sucesso = sucesso;
	}
	public String getIP(){
		return this.ip;
	}
}

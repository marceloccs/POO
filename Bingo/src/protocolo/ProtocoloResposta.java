package protocolo;

import java.io.Serializable;

public class ProtocoloResposta implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2361681314645605386L;
	private Object obj;
	private AcaoResposta status;
	private String mensagem;
	private boolean sucesso; 
	private String ip;
	
	public ProtocoloResposta(Object obj, AcaoResposta status, String mensagem, boolean sucesso, String ip){
		this.obj = obj;
		this.status = status;
		this.mensagem = mensagem;
		this.sucesso = sucesso;
		this.ip = ip;
	}
	public String getIP(){
		return this.ip;
	}
	public Object getObjeto(){
		return this.obj;
	}
	public String getMensagem(){
		return this.mensagem;
	}
	public boolean getSucesso(){
		return this.sucesso;
	}
	public AcaoResposta getStatus(){
		return this.status;
	}
	public String toString(){
		return "A ação foi: "+this.getStatus()+". A messagem de resposta foi: "
				+ this.getMensagem()+" Para o ip: "+this.getIP()+". E a classe de resposta foi: "+this.getObjeto().getClass()+" "
						+ "Com a seguinte informação dentro: "+this.getObjeto();
	}
}


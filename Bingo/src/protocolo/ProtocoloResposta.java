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
	public void setObeject(Object obj){
		this.obj = obj;
	}
	public String getIP(){
		if(this.ip==null)
			return "0.0.0.0";
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((mensagem == null) ? 0 : mensagem.hashCode());
		result = prime * result + ((obj == null) ? 0 : obj.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + (sucesso ? 1231 : 1237);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProtocoloResposta other = (ProtocoloResposta) obj;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (mensagem == null) {
			if (other.mensagem != null)
				return false;
		} else if (!mensagem.equals(other.mensagem))
			return false;
		if (this.obj == null) {
			if (other.obj != null)
				return false;
		} else if (!this.obj.equals(other.obj))
			return false;
		if (status != other.status)
			return false;
		if (sucesso != other.sucesso)
			return false;
		return true;
	}
}

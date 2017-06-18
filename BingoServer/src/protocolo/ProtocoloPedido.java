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
	public void setObject(Object obj){
		this.obj=obj;
	}
	public String getIP(){
		if(this.ip!=null)
			return this.ip;
		return "0";
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acao == null) ? 0 : acao.hashCode());
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((obj == null) ? 0 : obj.hashCode());
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
		ProtocoloPedido other = (ProtocoloPedido) obj;
		if (acao != other.acao)
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (this.obj == null) {
			if (other.obj != null)
				return false;
		} else if (!this.obj.equals(other.obj))
			return false;
		return true;
	}
}

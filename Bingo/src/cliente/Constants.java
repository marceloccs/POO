package cliente;

import java.net.InetAddress;
import java.net.UnknownHostException;

import listener.ListenerAbreFechaTelas;

public class Constants implements Cloneable{
	static public int porta = 9999;
	static public String host = "192.168.0.106";
	private String ipProprio;
	
	public Constants() throws UnknownHostException{
		this.ipProprio = InetAddress.getLocalHost().getHostAddress();
	}
	public Constants(Constants constants) {
		this.ipProprio = constants.ipProprio;
	}
	public String getIP(){
		return this.ipProprio;
	}
	
	public static void setHost(String host){
		Constants.host = host;
	}
	
	public Object clone(){
		return new Constants(this);
	}
	public boolean equals(Object obj){
		try{
			if(this == obj)
				return true;
			Constants ls = (Constants)obj;
			if(!ls.ipProprio.equals(this.ipProprio)){
				return false;
			}
			return true;
		}catch(Exception e){
			return false;
		}
	}
	public String toString(){
		return "Ip da maquina atual é: "+this.ipProprio;
	}
	public int hashCode(){
		int ret = 666; //qualquer numero, não zero e intero, desde que sua classe não herde de nenhuma classe
	       
        ret = 7 * ret + this.ipProprio.hashCode();
        ret = 7 * ret + Constants.host.hashCode();
        ret = 7 * ret + new Integer(Constants.porta).hashCode();
        return ret;
	}
	
}

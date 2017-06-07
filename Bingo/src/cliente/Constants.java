package cliente;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Constants {
	static public int porta = 9999;
	static public String host = "192.168.0.103";
	private String ipProprio;
	
	public Constants() throws UnknownHostException{
		this.ipProprio = InetAddress.getLocalHost().getHostAddress();
	}
	public String getIP(){
		return this.ipProprio;
	}
}

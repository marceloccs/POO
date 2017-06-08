package cliente;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Constants {
	static public int porta = 9999;
	static public String host = "172.16.231.86";
	public static int portaJogo = 6666;
	private String ipProprio;
	
	public Constants() throws UnknownHostException{
		this.ipProprio = InetAddress.getLocalHost().getHostAddress();
	}
	public String getIP(){
		return this.ipProprio;
	}
}

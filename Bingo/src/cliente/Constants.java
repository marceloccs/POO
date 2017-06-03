package cliente;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Constants {
	static public int porta = 6666;
	static public String host = "125.12.1.100";
	static public String ipProprio;
	
	public Constants() throws UnknownHostException{
		this.ipProprio = InetAddress.getLocalHost().getHostAddress();
	}
}

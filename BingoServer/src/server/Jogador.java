package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import org.apache.commons.lang3.SerializationUtils;

import protocolo.AcaoPedido;
import protocolo.AcaoResposta;
import protocolo.ProtocoloPedido;
import protocolo.ProtocoloResposta;

public class Jogador implements Runnable{
	private ObjectOutputStream envio;
	private ObjectInputStream recebimento;
	//private Socket socket;
	private String ip;
	private boolean runner=true;
	private boolean jogando = false;
	private ServidorNovo servidor;
	
	public Jogador(Socket server, ServidorNovo servidor) throws IOException{
		this.envio = new ObjectOutputStream(server.getOutputStream());
		this.recebimento = new ObjectInputStream(server.getInputStream());
		this.ip = server.getInetAddress().getHostAddress();
		this.servidor = servidor;
	}
	public void enviaMensagem(ProtocoloResposta obj) throws IOException{
		//System.out.println(obj.toString());
		byte[] dataresposta = SerializationUtils.serialize(obj);
   	 	envio.writeObject(dataresposta);
   	 	envio.flush();
	}

	@Override
	public void run() {
		while(this.runner==true){
			Object serializado;
			try {
				serializado = this.recebimento.readObject();
				byte[] ser = (byte[]) serializado;
				ProtocoloPedido proto = (ProtocoloPedido) SerializationUtils.deserialize(ser);
				if(proto.getAcao()!=AcaoPedido.IniciarJogo){
					AcaoPedido ac = proto.getAcao();
					ProtocoloResposta obj = ac.acionarAcao(proto);
					//System.out.println(obj.toString());
					this.enviaMensagem(obj);
				}else{
					if(this.servidor.iniciaJogo()){
						this.jogando=true;
						AcaoPedido ac = proto.getAcao();
						proto.setObject(this.servidor);
						ProtocoloResposta obj = ac.acionarAcao(proto);
						//System.out.println(obj.toString());
						this.enviaMensagem(obj);
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				try {
					this.enviaMensagem(new ProtocoloResposta(null, AcaoResposta.Erro, "Erro no servidor", false, this.ip));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (SocketException e){
				try {
					this.fechaConexao();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (IOException e){
				
			}
		}
		
	}
	
	public void pare(){
		this.runner=false;
	}
	public boolean isPlaying(){
		return this.jogando;
	}
	
	public void jogar(){
		this.jogando =true;
	}
	public void naoJogar(){
		this.jogando =false;
	}
	
	public void fechaConexao() throws IOException{
		this.recebimento.close();
		this.envio.flush();
		this.envio.close();
		this.pare();
		System.out.println("Conexão encerrada com o cliente: "+this.ip);
	}
	@Override
	public String toString() {
		return "Jogador [envio=" + envio + ", recebimento=" + recebimento + ", ip=" + ip + ", runner=" + runner
				+ ", jogando=" + jogando + ", servidor=" + servidor + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((envio == null) ? 0 : envio.hashCode());
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + (jogando ? 1231 : 1237);
		result = prime * result + ((recebimento == null) ? 0 : recebimento.hashCode());
		result = prime * result + (runner ? 1231 : 1237);
		result = prime * result + ((servidor == null) ? 0 : servidor.hashCode());
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
		Jogador other = (Jogador) obj;
		if (envio == null) {
			if (other.envio != null)
				return false;
		} else if (!envio.equals(other.envio))
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (jogando != other.jogando)
			return false;
		if (recebimento == null) {
			if (other.recebimento != null)
				return false;
		} else if (!recebimento.equals(other.recebimento))
			return false;
		if (runner != other.runner)
			return false;
		if (servidor == null) {
			if (other.servidor != null)
				return false;
		} else if (!servidor.equals(other.servidor))
			return false;
		return true;
	}

}

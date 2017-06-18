package server;

import java.util.Random;
import java.util.Vector;

import org.apache.commons.lang3.SerializationUtils;

import protocolo.AcaoResposta;
import protocolo.ProtocoloResposta;

public class Sorteio extends Thread{
	
	private static Vector <String> valoresSortiados = new Vector<String>(100);
	private boolean runner=false;
	private ServidorNovo servidor;
	private boolean iniciado = false;
	private boolean liberado = true;
	
	public Sorteio(ServidorNovo servidor){
		this.servidor=servidor;
	}
	
	public void run() {
		int max = 99;
		int min = 0;
		int i=0;
		this.runner=true;
		this.iniciado=true;
		try{
			if(this.liberado==true)
				super.sleep(20000);
			while(this.runner==true){
				if(i>=100)
					this.para();
				this.liberado=false;
				Random rand = new Random();
				Integer randomNum = rand.nextInt((max - min) + 1) + min;
				if(!Sorteio.valoresSortiados.contains(randomNum.toString())){
					Sorteio.valoresSortiados.add(randomNum.toString());
					ProtocoloResposta resp = new ProtocoloResposta(randomNum.toString(), AcaoResposta.NumeroSortiado, "Novo Numero Sorteado", true, null);
					ServidorNovo.enviaMensagemJogando(resp);
					System.out.println(randomNum.toString());
					super.sleep(5000);
				}else{
					i--;
				}
				i++;
			}
		}catch(Exception e){
			this.para();
		}
	}
	
	public void para(){
		this.runner=false;
		this.iniciado=false;
		this.liberado=true;
		Sorteio.valoresSortiados = new Vector<String>(30);
	}
	public void pausa(){
		this.runner=false;
	}
	public void volta(){
		this.runner=true;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (iniciado ? 1231 : 1237);
		result = prime * result + (liberado ? 1231 : 1237);
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
		Sorteio other = (Sorteio) obj;
		if (iniciado != other.iniciado)
			return false;
		if (liberado != other.liberado)
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

	@Override
	public String toString() {
		return "Sorteio [runner=" + runner + ", servidor=" + servidor + ", iniciado=" + iniciado + ", liberado="
				+ liberado + "]";
	}

	public boolean getIniciado(){
		return this.iniciado;
	}
	public boolean getLiberado(){
		return this.liberado;
	}
	
	public static Vector<String> getNumeros(){
		return Sorteio.valoresSortiados;
	}

}

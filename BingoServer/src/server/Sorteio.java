package server;

import java.util.Random;
import java.util.Vector;

import protocolo.AcaoResposta;
import protocolo.ProtocoloResposta;

public class Sorteio extends Thread implements Runnable{
	
	private Vector <String> valoresSortiados = new Vector<String>(99);
	private boolean runner=false;
	private ServidorJogo servidor;
	public Sorteio(ServidorJogo servidor){
		this.servidor=servidor;
	}
	
	@Override
	public void start() {
		int max = 99;
		int min = 1;
		this.runner=true;
		try{
			//this.wait(5000);
			do{

				super.sleep(5000);
				while(true){
					Random rand = new Random();
					Integer randomNum = rand.nextInt((max - min) + 1) + min;
					if(!this.valoresSortiados.contains(randomNum.toString())){
						this.valoresSortiados.add(randomNum.toString());
						ProtocoloResposta resp = new ProtocoloResposta(randomNum.toString(), AcaoResposta.NumeroSortiado, "Novo Numero Sorteado", true, null);
						servidor.distribuiMensagem(resp);
						//System.out.println(randomNum.toString());
					}
				}
			}while(this.runner==true);
		}catch(Exception e){
			this.para();
		}
	}
	public void para(){
		this.runner=false;
	}
	public Vector<String> getNumeros(){
		return this.valoresSortiados;
	}

}

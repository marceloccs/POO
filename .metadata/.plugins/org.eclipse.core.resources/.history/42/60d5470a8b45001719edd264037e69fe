package bd.dbos;

import bd.dbos.Jogo;
import java.util.Vector;

public class TabelaJogos {
	private Vector <Jogo> jogos = new Vector <Jogo> ();
	
	public TabelaJogos(){}
	
	public void pushJogo(Jogo jg){
		this.jogos.add(jg);
	}
	public String toString(){
		String retorno = "";
		for(int i =0; i<this.jogos.size();i++){
			retorno += 
					"Nome Ganhador: " + this.jogos.get(i).getUser().getNome() +"\t "+
					"Data de Termino do jogo: "+this.jogos.get(i).getDataTermino().toString()+
					"\n";
		}
		return retorno;
	}

}

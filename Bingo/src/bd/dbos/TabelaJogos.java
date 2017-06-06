package bd.dbos;

import java.io.Serializable;
import java.util.Vector;

import bd.dbos.Jogo;

public class TabelaJogos implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3062678996049843423L;
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

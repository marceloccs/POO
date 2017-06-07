package bd.dbos;

import bd.dbos.Jogo;

import java.io.Serializable;
import java.util.Vector;

public class TabelaJogos implements Serializable {
	private Vector <Jogo> jogos = new Vector <Jogo> ();
	private static final long serialVersionUID = 3062678996049843423L;
	
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

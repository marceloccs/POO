package bd.dbos;

import java.io.Serializable;
import java.sql.Date;
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
	public String returnHtml(){
		String retorno = "<html>";
		for(int i =0; i<this.jogos.size();i++){
			retorno += 
					"<p>Nome Ganhador: " + this.jogos.get(i).getUser().getNome() +"&emsp;"+
					"Data de Termino do jogo: "+this.jogos.get(i).getDataTermino().toString()+
					"</p>";
		}
		retorno += "</html>";
		return retorno;
	}
	public int hashCode(){
        int ret = 666; //qualquer numero, não zero e intero, desde que sua classe não herde de nenhuma classe
       
        ret = 7 * ret + this.jogos.hashCode();
        return ret;
    }
	public Object clone(){
        return new TabelaJogos(this);
    }
    public TabelaJogos (TabelaJogos j){
        try{
        	this.jogos = (Vector<Jogo>) j.jogos.clone();
        }catch (Exception e){}
    }
    public boolean equals(Object obj){
    	try{
    		TabelaJogos c = (TabelaJogos)obj;
    		if(!c.jogos.equals(this.jogos))
    			return false;
    		return true;
    	}catch(Exception e){
    		return false;
    	}
    }

}

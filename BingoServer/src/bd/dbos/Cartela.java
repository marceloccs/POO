package bd.dbos;

import java.util.Vector;

public class Cartela {
	private User dono;
	private String cartela[][] = new String[5][5];
	private Vector <String> selecionados = new Vector <String> (25);
	//private int quantidadeSelecionados=-1;
	
	public Cartela(String[][] cart, User dono) throws Exception{
		this.setCartela(cart);
		if(dono==null)
			throw new Exception("A dono é nulla");

		this.dono = dono;
	}

	private void setCartela(String[][] cart) throws Exception {
		if(cart==null)
			throw new Exception("A cartela é nulla");
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
				if(cart[i][j]==null)
					throw new Exception("A cartela tem valores nullos");
			}
		}
		this.cartela = cart;
	}
	public void seleciona(String valor){
		this.selecionados.add(valor);
	}
	public void deSeleciona(String valor) throws Exception{
		if(!this.selecionados.contains(valor))
			throw new Exception("Impossivel remover valor selecionado");
		this.selecionados.remove(valor);
	}
	public boolean verificaNumeros(Vector<String>numeros){
		if((numeros.size()<5)||(this.selecionados.size()<5)){
			return false;
		}
		for(int i=0; i<this.selecionados.size();i++){
			if(!numeros.contains(this.selecionados.get(i))){
				return false;
			}
		}
		return true;
	}
	public String toString(){
		String ret = "TABELA: \n";
		for(int i =0;i<5;i++){
			for(int j=0;j<5;j++){
				ret += this.cartela[i][j] + ", ";
			}
			ret += "\n";
		}
		ret += "SELECIONADOS: \n";
		for(int i=0;i<this.selecionados.size();i++){
			ret += this.selecionados.get(i) + ", ";
		}
		return ret;
	}
	public User getDono(){
		return this.dono;
	}
	public int hashCode(){
        int ret = 666; //qualquer numero, não zero e intero, desde que sua classe não herde de nenhuma classe
       
        ret = 7 * ret + this.cartela.hashCode();
        ret = 7 * ret + this.dono.hashCode();
        ret = 7 * ret + this.selecionados.hashCode();
        return ret;
    }
	public Object clone(){
        return new Cartela(this);
    }
    public Cartela (Cartela c){
        try{
        	this.cartela = c.cartela.clone();
        	this.dono = c.dono.clone();
        	this.selecionados = (Vector<String>) c.selecionados.clone();
        }catch (Exception e){}
    }
    public boolean equals(Object obj){
    	try{
    		Cartela c = (Cartela)obj;
    		if(!c.cartela.equals(this.cartela))
    			return false;
    		if(!c.dono.equals(this.dono))
    			return false;
    		if(!c.selecionados.equals(this.selecionados))
    			return false;
    		return true;
    	}catch(Exception e){
    		return false;
    	}
    }

}

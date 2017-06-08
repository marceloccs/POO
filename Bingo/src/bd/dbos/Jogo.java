package bd.dbos;

import java.io.Serializable;
import java.sql.Date;
import java.util.Vector;

public class Jogo implements Cloneable, Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2910239556884228850L;
	private int id;
	private Date dataTermino;
	private User user;
	
	public void setID(int id) throws Exception{
		this.id = id;
	}
	public void setDataTermino(Date dataTermino) throws Exception{
		if(dataTermino==null)
			throw new Exception ("dataTermino do jogo invalido");
		this.dataTermino = dataTermino;
	}
	public void setUsers(User user) throws Exception{
		if(user==null)
			throw new Exception ("Jogador do jogo invalido");
		this.user = user;
	}
	public int getID(){
		return this.id;
	}
	public User getUser(){
		return this.user;
	}
	public Date getDataTermino(){
		return this.dataTermino;
	}
	
	public Jogo(int id, Date dataTermino,User user) throws Exception{
		this.setID(id);
		this.setDataTermino(dataTermino);
		this.setUsers(user);
	}
	
	public Jogo( Date dataTermino,User user) throws Exception{
		this.setID(-1);
		this.setDataTermino(dataTermino);
		this.setUsers(user);
	}
	
	public Jogo( Date dataTermino) throws Exception{
		this.setID(-1);
		this.setDataTermino(dataTermino);
	}
	public boolean hasUser(){
		if(this.user==null)
			return false;
		return true;
	}
	public boolean hasID(){
		if(this.id==-1)
			return false;
		return true;
	}
	
	public String toString(){
		return "ID: "+this.id+" Data de termino: "+this.dataTermino+ " User ID: "+this.user.getID()+" Nome User: "+ this.user.getNome();
	}
	
	public int hashCode(){
        int ret = 666; //qualquer numero, não zero e intero, desde que sua classe não herde de nenhuma classe
       
        ret = 7 * ret + new Integer(this.id).hashCode();
        ret = 7 * ret + this.dataTermino.hashCode();
        ret = 7 * ret + this.user.hashCode();
        return ret;
    }
	public Object clone(){
        return new Jogo(this);
    }
    public Jogo (Jogo j){
        try{
        	this.id = j.id;
        	this.dataTermino = (Date) j.dataTermino.clone();
        	this.user=j.user;
        }catch (Exception e){}
    }
    public boolean equals(Object obj){
    	try{
    		Jogo c = (Jogo)obj;
    		if(!(c.id==this.id))
    			return false;
    		if(!c.dataTermino.equals(this.dataTermino))
    			return false;
    		if(!c.user.equals(this.user))
    			return false;
    		return true;
    	}catch(Exception e){
    		return false;
    	}
    }

}

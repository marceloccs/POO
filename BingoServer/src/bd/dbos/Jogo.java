package bd.dbos;

import java.sql.Date;

public class Jogo implements Cloneable {
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
	

}
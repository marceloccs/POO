package bd.dbos;

import java.io.Serializable;
import java.util.Vector;

public class User implements Cloneable,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3116530670969802298L;
	private int id;
	private String nome;
	private String email;
	private String password;
	
	public void setID(int id) throws Exception{
		this.id = id;
	}
	public void setNome(String nome) throws Exception{
		//if((nome==null)||(nome.equals("")))
			//throw new Exception ("nome do usuario invalido");
		this.nome = nome;
	}
	public void setEmail(String email) throws Exception{
		if((email==null)||(email.equals("")))
			throw new Exception ("email do usuario invalido");
		this.email = email;
	}
	public void setPassword(String password) throws Exception{
		if((password==null)||(password.equals("")))
			throw new Exception ("password do usuario invalido");
		this.password = password;
	}
	public int getID(){
		return this.id;
	}
	public String getNome(){
		return this.nome;
	}
	public String getEmail(){
		return this.email;
	}
	public User(int id, String nome, String email,String password) throws Exception{
		this.setID(id);
		this.setEmail(email);
		this.setNome(nome);
		this.setPassword(password);
	}
	public User(String nome, String email,String password) throws Exception{
		this.setID(-1);
		this.setEmail(email);
		this.setNome(nome);
		this.setPassword(password);
	}
	public User(String nome, String email) throws Exception{
		this.setID(-1);
		this.setEmail(email);
		this.setNome(nome);
	}
	public User (Object obj) throws Exception {
		try{
			User user = (User) obj;
			this.id=user.getID();
			this.nome=user.getNome();
			this.email=user.getEmail();
			this.password=user.getPassword();
		}catch (Exception e){
			throw new Exception("A classe não é compativel com um user");
		}
	}
	public boolean equals(Object obj){
		if(obj == null)
            return false;
        if(obj==this)
            return true;
        if(obj.getClass() != this.getClass())
            return false;
    	User user = (User) obj;
    	if(user.getID() != this.id)
    		return false;
    	if(!(user.getEmail().equals(this.email)))
    		return false;
    	if(!(user.getNome().equals(this.nome)))
    		return false;
    	if(!(user.getPassword().equals(this.email)))
    		return false;
    	return true;
	}
	public String getPassword() {
		return this.password;
	}
	public String toString(){
		return "ID: "+this.getID()+" Nome: "+this.getNome()+" Email:"+this.getEmail()+" Password: "+this.getPassword();
	}
	public User clone(){
		return new User(this);
	}
	public User (User u){
		try{
			this.id=u.id;
			this.nome=u.email;
			this.email=u.getEmail();
			this.password=u.password;
		}catch (Exception e){}
	}
	public int hashCode(){
        int ret = 666; //qualquer numero, não zero e intero, desde que sua classe não herde de nenhuma classe
       
        ret = 7 * ret + this.nome.hashCode();
        ret = 7 * ret + new Integer(this.id).hashCode();
        ret = 7 * ret + this.password.hashCode();
        ret = 7 * ret + this.email.hashCode();
        return ret;
    }

}

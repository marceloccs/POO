package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import com.sun.xml.internal.ws.api.pipe.Fiber;
import com.sun.xml.internal.ws.api.pipe.Fiber.Listener;

public class ListenerAbreFechaTelas implements ActionListener {
	private JFrame fecha;
	private String abre;
	public ListenerAbreFechaTelas(JFrame fecha, String abre){
		this.fecha = fecha;
		this.abre = abre;
	}
	public ListenerAbreFechaTelas(ListenerAbreFechaTelas listenerAbreFechaTelas) {
		this.abre=listenerAbreFechaTelas.abre;
		this.fecha=listenerAbreFechaTelas.fecha;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			fecha.dispose();
			JFrame j= (JFrame) Class.forName ("bingo."+this.abre).newInstance();
			j.setVisible(true);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//abre.setVisible(true);
	}
	
	public Object clone(){
		return new ListenerAbreFechaTelas(this);
	}
	public boolean equals(Object obj){
		try{
			if(this == obj)
				return true;
			ListenerAbreFechaTelas ls = (ListenerAbreFechaTelas)obj;
			if(!ls.abre.equals(this.abre)){
				return false;
			}
			if(!ls.fecha.equals(this.fecha)){
				return false;
			}
			return true;
		}catch(Exception e){
			return false;
		}
	}
	public String toString(){
		return "abre: "+this.abre + " e fecha: " + this.fecha.getClass();
	}
	public int hashCode(){
		int ret = 666; //qualquer numero, não zero e intero, desde que sua classe não herde de nenhuma classe
	       
        ret = 7 * ret + this.abre.hashCode();
        ret = 7 * ret + this.fecha.hashCode();
        return ret;
	}

}


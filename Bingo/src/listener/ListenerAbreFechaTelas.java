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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((abre == null) ? 0 : abre.hashCode());
		//result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListenerAbreFechaTelas other = (ListenerAbreFechaTelas) obj;
		if (abre == null) {
			if (other.abre != null)
				return false;
		} else if (!abre.equals(other.abre))
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		return true;
	}
	public String toString(){
		return "abre: "+this.abre + " e fecha: " + this.fecha.getClass();
	}
	

}


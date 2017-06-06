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

}


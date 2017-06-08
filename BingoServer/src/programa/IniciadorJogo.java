package programa;

import server.ServidorJogo;
import server.Sorteio;

public class IniciadorJogo {
	private static boolean jogoCorrendo=false;
	private static Sorteio sorte;
	public int iniciaJogo(){
    	int portaJogo = 6666;
    	if(IniciadorJogo.jogoCorrendo==false){
	    	ServidorJogo servidor = new ServidorJogo(portaJogo); 
	    	Thread t1 = new Thread (servidor);
	    	t1.start();
	    	this.sorte = new Sorteio(servidor);
	    	Thread t2= new Thread(this.sorte);
	    	t2.start();
	    	IniciadorJogo.jogoCorrendo=true;
	    	return portaJogo;
    	}else{
    		return portaJogo;
    	}
    }
	public Sorteio getSorteio(){
		return IniciadorJogo.sorte;
	}
}

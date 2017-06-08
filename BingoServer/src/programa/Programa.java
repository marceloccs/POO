package programa;

import bd.*;
import bd.dbos.*;
import server.Servidor;
import server.ServidorJogo;
import server.Sorteio;

public class Programa
{
    public static void main(String[] args)
    {
        try
        {
            //User user = new User (11,"johns","j.zinho@gmail.com","jocker");
            //BD.USERS.excluir(11);
            //System.out.println(BD.JOGOS.pegarUltimoMes().toString());
        	Servidor s = new Servidor(9999); 
        	Thread t = new Thread (s);
        	t.start();
        	ServidorJogo sj = new ServidorJogo(6666); 
        	Thread tj = new Thread (sj);
        	tj.start();
        	
        }
        catch (Exception erro)
        {
            System.err.println (erro.getStackTrace());
            System.err.println (erro.getCause());
        }
    }
}
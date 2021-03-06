package bd;

import bd.core.*;
import bd.daos.*;

public class BD
{
    public static final MeuPreparedStatement COMANDO;
    public static final Users              	 USERS;//um como esse para cada classe DAO
    public static final Jogos                JOGOS;//um como esse para cada classe DAO

    static
    {
    	MeuPreparedStatement comando = null;
     	Users              	 users  = null; //um como esse para cada classe DAO
     	Jogos              	 jogos  = null; //um como esse para cada classe DAO

    	try
        {
            comando =
            		new MeuPreparedStatement (
            				"com.mysql.jdbc.Driver",
            				"jdbc:mysql://localhost:3306/mydb",
            				"root", "");

            jogos = new Jogos (); //um como esse para cada classe DAO
            users = new Users (); //um como esse para cada classe DAO
        }
        catch (Exception erro)
        {
            System.err.println ("Problemas de conexao com o BD");
            System.exit(0); // aborta o programa
        }
        
        COMANDO = comando;
        USERS  = users; //um como esse para cada classe DAO
        JOGOS  = jogos; //um como esse para cada classe DAO
    }
}
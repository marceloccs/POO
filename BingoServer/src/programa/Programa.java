package programa;

import bd.*;
import bd.dbos.*;

public class Programa
{
    public static void main(String[] args)
    {
        try
        {
            //User user = new User (11,"johns","j.zinho@gmail.com","jocker");
            //BD.USERS.excluir(11);
            System.out.println(BD.JOGOS.pegarUltimoMes().toString());
        }
        catch (Exception erro)
        {
            System.err.println (erro.getStackTrace());
            System.err.println (erro.getCause());
        }
    } 
}
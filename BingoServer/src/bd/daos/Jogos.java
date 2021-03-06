package bd.daos;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Vector;

import bd.BD;
import bd.core.MeuResultSet;
import bd.dbos.Jogo;
import bd.dbos.TabelaJogos;
import bd.dbos.User;

public class Jogos implements Cloneable {

	public boolean cadastrado (int codigo) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM Jogo " +
                  "WHERE ID = ?";

            BD.COMANDO.prepareStatement (sql);

            BD.COMANDO.setInt (1, codigo);

            MeuResultSet resultado = (MeuResultSet)BD.COMANDO.executeQuery ();

            retorno = resultado.first();

            /* // ou, se preferirmos,

            String sql;

            sql = "SELECT COUNT(*) AS QUANTOS " +
                  "FROM LIVROS " +
                  "WHERE CODIGO = ?";

            BD.COMANDO.prepareStatement (sql);

            BD.COMANDO.setInt (1, codigo);

            MeuResultSet resultado = (MeuResultSet)BD.COMANDO.executeQuery ();

            resultado.first();

            retorno = resultado.getInt("QUANTOS") != 0;

            */
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar Jogo");
        }

        return retorno;
    }

    public void incluir (Jogo jogo) throws Exception
    {
        if (jogo==null)
            throw new Exception ("Jogo nao fornecido");

        try
        {
            String sql;
            Vector <Integer> values = new Vector(); 
            
            sql = "INSERT INTO Jogos " +
                  "(DataTermino"; 
            
            /*+
                  "VALUES " +
                  "(?,?,?)";*/
            if(jogo.hasID()){
            	sql+=", ID";
            	values.add(jogo.getID());
            }
            if(jogo.hasUser()){
            	sql+=", USER";
            	values.add(jogo.getUser().getID());
            }
            sql+=") VALUES(";
            sql+="?";
            BD.COMANDO.prepareStatement (sql);
            
            for(int i=0;i<values.size();i++){
            	sql+=",?";
            	BD.COMANDO.setInt (i, values.get(i));
            }
            
            BD.COMANDO.setDate (1, jogo.getDataTermino());
            
            BD.COMANDO.executeUpdate ();
            BD.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao inserir Jogo");
        }
    }
    
    public TabelaJogos pegarUltimoMes() throws Exception{
    	Date hj;
    	TabelaJogos tabela = null;
    	try{
    		String sql = "SELECT User.Nome,User.Email, Jogo.DataTermino "
    				+ "FROM Jogo "
    				+ "INNER JOIN User ON User.ID=Jogo.User_ID "
    				+ "WHERE YEAR(Jogo.DataTermino) = YEAR(now()) "
    				+ "AND MONTH(Jogo.DataTermino) = MONTH(now())";
    		BD.COMANDO.prepareStatement (sql);

            MeuResultSet resultado = (MeuResultSet)BD.COMANDO.executeQuery ();
            tabela = new TabelaJogos();
            while(resultado.next()==true){
            	User user = new User (
                		resultado.getString("Nome"),
                		resultado.getString("Email"));
                
                Jogo jogo = new Jogo (
                        resultado.getDate("DataTermino"),
                        user);
                tabela.pushJogo(jogo);
                
            }
    	}catch(Exception e){
    		//throw new Exception (e.printStackTrace());
    		System.out.println(e);
    	}
		return tabela;
    }

    public void excluir (int id) throws Exception
    {
        if (!cadastrado (id))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "DELETE FROM Jogo " +
                  "WHERE ID=?";

            BD.COMANDO.prepareStatement (sql);

            BD.COMANDO.setInt (1, id);

            BD.COMANDO.executeUpdate ();
            BD.COMANDO.commit        ();        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao excluir Jogo");
        }
    }

    public void alterar (Jogo jogo) throws Exception
    {
        if (jogo==null)
            throw new Exception ("Jogo nao fornecido");

        if (!cadastrado (jogo.getID()))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "UPDATE LIVROS " +
                  "SET ID=? " +
                  "SET DataTermino=? " +
                  "WHERE User_ID = ?";

            BD.COMANDO.prepareStatement (sql);

            BD.COMANDO.setInt (1, jogo.getID());
            BD.COMANDO.setDate  (2, jogo.getDataTermino());
            BD.COMANDO.setInt    (3, jogo.getUser().getID());

            BD.COMANDO.executeUpdate ();
            BD.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao atualizar dados do Jogo");
        }
    }

    public Jogo getJogo (int id) throws Exception
    {
        Jogo jogo = null;
        User user = null;

        try
        {
            String sql;

            sql = "SELECT Jogo.ID AS loja_ID, Jogo.DataTermino AS loja_dataTermino, "+ 
            	  "User.ID AS user_ID,User.Nome AS user_nome, User.Password AS user_password, User.Email AS user_email " +
                  "FROM Jogo "+ 
                  "Inner join User ON User.ID=Jogo.User_ID " +
                  "WHERE Jogo.ID = ?";

            BD.COMANDO.prepareStatement (sql);

            BD.COMANDO.setInt (1, id);

            MeuResultSet resultado = (MeuResultSet)BD.COMANDO.executeQuery ();

            if (!resultado.first())
                throw new Exception ("Nao cadastrado");
            user = new User (
            		resultado.getInt("User_ID"),
            		resultado.getString("User_nome"),
            		resultado.getString("User_email"),
            		resultado.getString("User_password"));
            
            jogo = new Jogo (
            		resultado.getInt("loja_ID"),
                    resultado.getDate("loja_dataTermino"),
                    user);
        }
        catch (SQLException erro)
        {
            throw new Exception (erro.getMessage());
        }

        return jogo;
    }

    public MeuResultSet getJogos () throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM Jogos";

            BD.COMANDO.prepareStatement (sql);

            resultado = (MeuResultSet)BD.COMANDO.executeQuery ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar livros");
        }

        return resultado;
    }

	@Override
	public String toString() {
		try {
			return "Jogos [pegarUltimoMes()=" + pegarUltimoMes() + ", getJogos()=" + getJogos() + "]";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
    
}

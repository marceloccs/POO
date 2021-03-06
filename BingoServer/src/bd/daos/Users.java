package bd.daos;

import java.sql.SQLException;

import bd.BD;
import bd.core.MeuResultSet;
import bd.dbos.User;

public class Users implements Cloneable {
	public boolean cadastrado (int codigo) throws Exception
    {
        boolean retorno = false;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM User " +
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
            throw new Exception ("Erro ao procurar user");
        }

        return retorno;
    }
	public User auth(String login,String password) throws Exception{
		User ret =null;
		
		if (login==null)
            throw new Exception ("login enviado � invalido");
		if (password==null)
            throw new Exception ("password enviado � invalido");
		String sql;
		sql = "SELECT * FROM User WHERE Email = ? AND Password = ?";
		
        BD.COMANDO.prepareStatement (sql);

        BD.COMANDO.setString    (1, login);
        BD.COMANDO.setString (2, password);
        
        MeuResultSet resultado = (MeuResultSet)BD.COMANDO.executeQuery ();

        if (!resultado.first())
            throw new Exception ("Login e senha n�o encontrados");
        ret = new User(
        		resultado.getInt("ID"),
        		resultado.getString("Nome"),
        		resultado.getString("Email"),
        		resultado.getString("Password")
        		);
		return ret;
	}

    public void incluir (User user) throws Exception
    {
        if (user==null)
            throw new Exception ("user enviado � invalido");

        try
        {
            String sql;
            if(user.getID()!= -1){
	            sql = "INSERT INTO User " +
	                  "(ID,Nome,Email,Password) " +
	                  "VALUES " +
	                  "(?,?,?,?)";
	            
	            BD.COMANDO.prepareStatement (sql);

	            BD.COMANDO.setInt    (1, user.getID ());
	            BD.COMANDO.setString (2, user.getNome ());
	            BD.COMANDO.setString  (3, user.getEmail ());
	            BD.COMANDO.setString  (4, user.getPassword ());
	            
            }else{
            	sql = "INSERT INTO User " +
  	                  "(Nome,Email,Password) " +
  	                  "VALUES " +
  	                  "(?,?,?)";
            	
                BD.COMANDO.prepareStatement (sql);

                BD.COMANDO.setString  (3, user.getPassword ());
                BD.COMANDO.setString  (1, user.getNome ());
                BD.COMANDO.setString  (2, user.getEmail ());
            }

            BD.COMANDO.executeUpdate ();
            BD.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao inserir livro");
        }
    }

    public void excluir (int id) throws Exception
    {
        if (!cadastrado (id))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "DELETE FROM User " +
                  "WHERE ID=?";

            BD.COMANDO.prepareStatement (sql);

            BD.COMANDO.setInt (1, id);

            BD.COMANDO.executeUpdate ();
            BD.COMANDO.commit        ();        
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao excluir User");
        }
    }

    public void alterar (User user) throws Exception
    {
        if (user==null)
            throw new Exception ("User nao fornecido");

        if (!cadastrado (user.getID()))
            throw new Exception ("Nao cadastrado");

        try
        {
            String sql;

            sql = "UPDATE User " +
                  "SET Nome=? " +
                  "SET Email=? " +
                  "SET Password=? " +
                  "WHERE ID = ?";

            BD.COMANDO.prepareStatement (sql);

            BD.COMANDO.setString 	(1, user.getNome ());
            BD.COMANDO.setString  	(2, user.getEmail ());
            BD.COMANDO.setString    (3, user.getPassword ());
            BD.COMANDO.setInt    	(4, user.getID ());

            BD.COMANDO.executeUpdate ();
            BD.COMANDO.commit        ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao atualizar dados do user");
        }
    }

    public User getUser (int id) throws Exception
    {
        User user = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM User " +
                  "WHERE ID = ?";

            BD.COMANDO.prepareStatement (sql);

            BD.COMANDO.setInt (1, id);

            MeuResultSet resultado = (MeuResultSet)BD.COMANDO.executeQuery ();

            if (!resultado.first())
                throw new Exception ("Nao cadastrado");

            user = new User   (resultado.getInt   ("Id"),
                               resultado.getString("Nome"),
                               resultado.getString ("Email"),
                               resultado.getString ("Password")
                               );
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao procurar User");
        }

        return user;
    }

    public MeuResultSet getUsers () throws Exception
    {
        MeuResultSet resultado = null;

        try
        {
            String sql;

            sql = "SELECT * " +
                  "FROM User";

            BD.COMANDO.prepareStatement (sql);

            resultado = (MeuResultSet)BD.COMANDO.executeQuery ();
        }
        catch (SQLException erro)
        {
            throw new Exception ("Erro ao recuperar livros");
        }

        return resultado;
    }

}

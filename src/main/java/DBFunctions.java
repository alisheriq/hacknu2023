import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.mindrot.jbcrypt.BCrypt;
public class DBFunctions
{
    public Connection connect_to_sql(String dbname, String user, String pass)
    {
        Connection conn = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+ dbname,user,pass);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return conn;
    }
    public void createTable(Connection conn, String table_name)
    {
        Statement statement;
        try
        {
            String query="create table "+table_name+"(id SERIAL,login varchar(200),iin varchar(200), password varchar(200),primary key(id))";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table created.");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public void update_password(Connection conn,String table_name,String new_pass, String login)
    {
        Statement statement;
        try
        {
            String hashedd = BCrypt.hashpw(new_pass, BCrypt.gensalt());
            String query=String.format("update %s set password ='%s' where login = '%s'",table_name, hashedd, login);
            statement = conn.createStatement();
            statement.executeUpdate(query);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public boolean search_db(Connection conn,String table_name,String login)
    {
        Statement statement;
        ResultSet rs=null;
        String result = "";
        try
        {
            String query=String.format("select * from %s",table_name);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                if (login.equals(rs.getString("login")))
                {
                    return true;
                }
            }
        }
        catch(Exception e)
        {
            return false;
        }
        return false;
    }
}

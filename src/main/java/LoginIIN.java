import org.mindrot.jbcrypt.BCrypt;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginIIN extends Login
{
    private boolean loggedIn;
    @Override
    public boolean login(String iin,String password)
    {
        Statement statement;
            ResultSet rs=null;
            try
            {
                String query=String.format("select * from %s","users");
                statement = conn.createStatement();
                rs = statement.executeQuery(query);
                loggedIn = false;
                while (rs.next())
                {
                    if (iin.equals(rs.getString("iin")) && BCrypt.checkpw(password, rs.getString("password")))
                    {
                        notification.sendMessage("User logged into account using IIN.");
                        return true;
                    }
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        notification.sendMessage("User failed login using IIN.");
        return false;
    }
    }

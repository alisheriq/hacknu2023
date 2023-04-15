import org.mindrot.jbcrypt.BCrypt;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Login implements LoginOperation{
    DBFunctions db = new DBFunctions();
    NotificationSender notification = new NotificationSender();
    Connection conn = db.connect_to_sql("authmail", "postgres", "qwerty");
    private boolean loggedIn;
    @Override
    public boolean login(String login,String password)
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
                if (login.equals(rs.getString("login")) && BCrypt.checkpw(password, rs.getString("password")))
                {
                    notification.sendMessage("User logged into account using email.");
                    return true;
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        notification.sendMessage("User failed login using email.");
        return false;
    }
    @Override
    public void output(String message)
    {
        System.out.println(message);
    };

}

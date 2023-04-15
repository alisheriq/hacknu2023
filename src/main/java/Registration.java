import org.mindrot.jbcrypt.BCrypt;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.Statement;

public class Registration implements RegOperation {
    NotificationSender notification = new NotificationSender();
    DBFunctions db = new DBFunctions();
    Connection conn = db.connect_to_sql("authmail", "postgres", "qwerty");
    private String password_s;
    private char[] password;
    boolean check1, check2, check3;
    @Override
    public boolean registration(String login, String iin, String password_ss)
    {
        password = password_ss.toCharArray();
        Statement statement;
        try
        {
            password_s = "";
            check1 = false;
            check2 = false;
            check3 = false;
            if (password.length > 6)
            {
                for (int k = 0; k < password.length; k++)
                {
                    if (password[k] >= 48 && password[k] <= 57)
                    {
                        check1 = true;
                    } else if (password[k] >= 65 && password[k] <= 90)
                    {
                        check2 = true;
                    } else if (password[k] >= 97 && password[k] <= 122)
                    {
                        check3 = true;
                    }
                }
                if ((check1) && (check2) && (check3))
                {
                    for (int l = 0; l < password.length; l++) {
                        password_s += password[l];
                    }
                    String hashed = BCrypt.hashpw(password_s, BCrypt.gensalt());
                    String query=String.format("insert into %s(login, iin, password) values('%s','%s','%s');","users",login,iin,hashed);
                    statement = conn.createStatement();
                    statement.executeUpdate(query);
                    notification.sendMessage("New user registered.");
                    return true;
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        notification.sendMessage("User failed registration.");
        return false;
    }
    public void output(String message)
    {
        System.out.println(message);
    }
}

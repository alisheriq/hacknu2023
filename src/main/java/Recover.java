import java.sql.Connection;
import java.util.Scanner;

public class Recover implements RecovOperation {
    NotificationSender notification = new NotificationSender();
    DBFunctions db = new DBFunctions();
    EmailSender es = new EmailSender();
    Connection conn = db.connect_to_sql("authmail", "postgres", "qwerty");
    @Override
    public boolean recover(String login)
    {
        boolean isUserFound = db.search_db(conn, "users", login);
        notification.sendMessage(isUserFound ? "User requested code to change password." : "User tried to change password with unknown email.");
        return isUserFound;
    }/*
    public boolean recover(String login){
        if (db.search_db(conn, "users", login) == true)
        {
            notification.sendMessage("User requested code to change password.");
            return true;
        }
        else
        {
            notification.sendMessage("User tried to change password with unknown email.");
            return false;
        }
    }*/
    public void change(String login, String new_pass)
    {
        db.update_password(conn, "users", new_pass, login);
        notification.sendMessage("User changed password.");
    }
    @Override
    public void output(String message)
    {
        System.out.println(message);
    }
}

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Scanner;
public class EmailSender
{
    Scanner input = new Scanner(System.in);
    private String code = String.valueOf((int)(100000 + Math.random() * 899999));
    public void EmailSender(String email)
    {
        String to = email;
        String from = "recovery23728@gmail.com";
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(properties, new javax.mail.Authenticator()
        {

            protected PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication("recovery23728@gmail.com", "emjvtxpcfyzusshc");
            }

        });
        session.setDebug(false);


        try
        {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Password recovery");
            message.setText("The recovery code is:" + code);
            Transport.send(message);
        }
        catch (MessagingException mex)
        {
            System.out.println(" ");
        }
    }
    public boolean CheckCode(String check)
    {
        if(check.equals(code))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecoverForm extends JFrame {
    NotificationSender notification = new NotificationSender();
    private JTextField textField1;
    private JButton sendCodeButton;
    private JTextField textField2;
    private JButton checkButton;
    private JPasswordField passwordField1;
    private JButton changePasswordButton;
    private JPanel RecoverPanel;
    private JButton cancelButton;
    Recover recover = new Recover();
    EmailSender emailSender = new EmailSender();
    private boolean check1, check2;

    RecoverForm(JFrame parent)
    {
        check1 = check2 = false;
        setTitle("Login to account");
        setContentPane(RecoverPanel);
        setMinimumSize(new Dimension(600, 600));
        setLocationRelativeTo(parent);
        sendCodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(recover.recover(textField1.getText()))
                {
                    check1 = true;
                    emailSender.EmailSender(textField1.getText());
                    JOptionPane.showMessageDialog(parent, "Code send to email.", "Success", JOptionPane.PLAIN_MESSAGE);
                }
                else
                {
                    check1 = false;
                    JOptionPane.showMessageDialog(parent, "Email not found.", "Fail", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(check1)
                {
                    if(emailSender.CheckCode(textField2.getText())){
                        check2 = true;
                        notification.sendMessage("User inputted correct recovery code. Password change permitted.");
                        JOptionPane.showMessageDialog(parent, "Code matches. Now create new password.", "Success", JOptionPane.PLAIN_MESSAGE);
                    }
                    else{
                        check2 = false;
                        notification.sendMessage("User inputted wrong recovery code.");
                        JOptionPane.showMessageDialog(parent, "Wrong code.", "Fail", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(check1 & check2)
                {
                    recover.change(textField1.getText(), String.valueOf(passwordField1.getPassword()));
                    JOptionPane.showMessageDialog(parent, "Password changed.", "Success", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
        setVisible(true);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}

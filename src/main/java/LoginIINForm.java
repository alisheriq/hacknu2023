import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginIINForm extends JFrame {
    LoginIIN loginIIN = new LoginIIN();
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JPanel LoginIINPanel;
    private JButton loginButton;
    private JButton cancelButton;

    public LoginIINForm(JFrame parent)
    {
        setTitle("Login to account");
        setContentPane(LoginIINPanel);
        setMinimumSize(new Dimension(450, 474));
        setLocationRelativeTo(parent);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(loginIIN.login(textField1.getText(),String.valueOf(passwordField1.getPassword())))
                {
                    JOptionPane.showMessageDialog(parent, "Login successful!", "Success", JOptionPane.PLAIN_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(parent, "Wrong IIN or password.", "Fail", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }
}

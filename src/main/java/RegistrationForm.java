import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationForm extends JDialog{
    Registration reg = new Registration();
    private JTextField textField1;
    private JTextField textField2;
    private JPasswordField passwordField1;
    private JButton registerButton;
    private JButton cancelButton;
    private JPanel registerPanel;
    RegistrationForm(JFrame parent)
    {
        super(parent);
        setTitle("Create a new account");
        setContentPane(registerPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(reg.registration(textField1.getText(), textField2.getText(), String.valueOf(passwordField1.getPassword())))
                {
                    JOptionPane.showMessageDialog(parent, "Registration successful!", "Success", JOptionPane.PLAIN_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(parent, "Registration failed!", "Fail", JOptionPane.ERROR_MESSAGE);
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

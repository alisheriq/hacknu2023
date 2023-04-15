import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame{
    private JButton registerButton;
    private JButton loginWithEmailButton;
    private JButton loginWithIINButton;
    private JButton recoverButton;
    private JButton cancelButton;
    private JPanel Menu;

public Menu(JFrame parent) {
    setTitle("Login to account");
    setContentPane(Menu);
    setMinimumSize(new Dimension(450, 474));
    setLocationRelativeTo(parent);

    registerButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new RegistrationForm(null);
        }
    });
    loginWithEmailButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new LoginForm(null);
        }
    });
    loginWithIINButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new LoginIINForm(null);
        }
    });
    recoverButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) { new RecoverForm(null);
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

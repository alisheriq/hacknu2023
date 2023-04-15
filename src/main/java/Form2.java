import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Form2 extends JDialog{
    private JPanel Form2;
    private JTextField textField2;
    private JButton OKButton;
    private JButton cancelButton;
    private JTextField textField1;

    public Form2(JFrame parent, String number, String phone) {
        setTitle("2");
        setContentPane(Form2);
        setMinimumSize(new Dimension(600, 400));
        setLocationRelativeTo(parent);
        textField1.setText(number);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Form3(null, textField2.getText(), number, phone);
            }
        });
        setVisible(true);
    }
}

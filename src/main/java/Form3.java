import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form3 extends JFrame{

    SmsSender smsSender = new SmsSender();
    PersonData personData = new PersonData();
    private JPanel Form3;
    private JLabel full_name;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField zTextField;
    private JButton OKButton;
    private JButton cancelButton;
    private JLabel last_name;
    private JLabel first_name;
    private JLabel order;
    private JLabel iin1;
    private JRadioButton яПринимаюУсловияПубличногоRadioButton;
    private JRadioButton яОзнакомленИСогласенRadioButton;
    private JButton договорButton;
    private JButton политикаButton;

    public Form3(JFrame parent, String iin, String number, String phone) {
        setTitle("3");
        setContentPane(Form3);
        setMinimumSize(new Dimension(800, 800));
        setLocationRelativeTo(parent);
        order.setText(number);
        iin1.setText(iin);
        textField1.setText("+"+phone);

        try
        {
            String name = personData.getFullName(iin, smsSender.getToken());
            full_name.setText(name);
            String[] parts = name.split(" ", 2);
            first_name.setText(parts[0]);
            last_name.setText(parts[1]);
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex);
        }
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        setVisible(true);
        договорButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(parent, "Тут должен быть договор", "Success", JOptionPane.PLAIN_MESSAGE);
            }
        });
        политикаButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(parent, "Тут должна быть политика", "Success", JOptionPane.PLAIN_MESSAGE);
            }
        });
    }
}

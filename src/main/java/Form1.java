import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Form1 extends JDialog {
    SmsSender smsSender = new SmsSender();
    private JTextField textField1;
    private JButton OKButton;
    private JButton cancelButton;
    private JPanel Form1;

    private String number = "000"+String.valueOf((int) (100000000 + (Math.random() * 899999999)));
    private String message = "Сіздің #"+number+" құжатыңыз дайын.Пайда болған терезеде жеткізу үшін ЖСН енгізіңіз. Құжатты жеткізу курьерлік қызметтің жеткізу мерзімдеріне сәйкес жүзеге асырылады. /Ваш документ #"+number+" готов. В появившемся окне введите свой ИИН для оформления доставки. Доставка осуществляется в соответствии со сроками доставки курьерской службы.";

    public Form1(JFrame parent) {
        setTitle("1");
        setContentPane(Form1);
        setMinimumSize(new Dimension(600, 400));
        setLocationRelativeTo(parent);


        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Form2(null, number, textField1.getText());
                try
                {
                    smsSender.sendSms(textField1.getText(),message,smsSender.getToken());
                }

                catch (IOException ex)
                {
                    throw new RuntimeException(ex);
                }
                catch (InterruptedException ex)
                {
                    throw new RuntimeException(ex);
                }
            }
        });

        setVisible(true);
    }
}


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Login {
    public JPanel panelMain;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JButton seConnecterButton;
    private JButton retourButton;

    public Login() {
        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Display.frame.dispose();
                Display.main();
            }
        });

        seConnecterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Display.test();
                Users actualUser = new Users();
                actualUser.setMail(textField1.getText());
                actualUser.setPwd(Arrays.toString(passwordField1.getPassword()));
                System.out.println(actualUser.getMail() + " " + actualUser.getPwd());
                actualUser.validUser();
            }
        });
    }
}

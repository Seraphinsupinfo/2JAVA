import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUp {
    public static Object nom;
    public JPanel panelMain;
    private JButton continuerButton;
    private JPasswordField confirmerMotDePassePasswordField;
    private JTextField nomDUtilisateurTextField;
    private JTextField emailTextField;
    private JButton retourButton;
    private JTextField textField1;
    private JTextField textField2;
    private JPasswordField passwordField1;
    public static String email;
    public SignUp() {
        continuerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                email = nomDUtilisateurTextField.getText();
                System.out.println(email);
            }
        });
        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Display.frame.dispose();
                Display.main();
            }
        });
    }
}

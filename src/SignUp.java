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
    public static String email;
    public SignUp() {
        continuerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                email = nomDUtilisateurTextField.getText();
                WhiteListsEmails.setEmail(email);
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

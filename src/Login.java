import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Objects;

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
                Users actualUser = new Users();
                String checkUser = textField1.getText();
                String checkPassword = String.valueOf(passwordField1.getPassword());
                boolean userOk = actualUser.validUser(checkUser, checkPassword);
                String role = actualUser.getRole();
                if (!userOk){
                    Display.errorPopUp("Identifiants incorrects");
                } else if (Objects.equals(role, "admin")){
                    System.out.println("connecté en tant qu'admin");
                    Display.homeAdmin();
                } else if (Objects.equals(role, "seller")) {
                    System.out.println("connecté en tant que vendeur");
                    Display.homeSeller();
                } else if (Objects.equals(role, "default")) {
                    System.out.println("connecté en tant qu'utilisateur");
                    Display.homeUser();
                } else {
                    Display.errorPopUp("Une erreur est survenue... rôle incorrect");
                }
            }
        });
    }
}

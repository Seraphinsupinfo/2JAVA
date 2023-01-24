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

                Users actualUser = new Users();
                actualUser.setMail(textField1.getText());
                actualUser.setPwd(Arrays.toString(passwordField1.getPassword()));
                System.out.println(actualUser.getMail() + " " + actualUser.getPwd());
                if (actualUser.validUser()){
                    Display.errorPopUp("Identifiants incorrects");
                } else if (actualUser.getRole() == "admin"){
                    System.out.println("connecté en tant qu'admin");
                    Display.homeAdmin();
                } else if (actualUser.getRole() == "vendeur") {
                    System.out.println("connecté en tant que vendeur");
                    Display.homeSeller();
                } else if (actualUser.getRole() == "user") {
                    System.out.println("connecté en tant qu'utilisateur");
                    Display.homeUser();
                } else {
                    Display.errorPopUp("Une erreur est survenue... rôle incorrect");
                }
            }
        });
    }
}

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;

public class SignUp {
    public static Object nom;
    public JPanel panelMain;
    private JButton continuerButton;
    private JPasswordField motDePasseField;
    private JTextField nomDUtilisateurTextField;
    private JTextField emailTextField;
    private JButton retourButton;
    private JTextField nameTextField;
    private JTextField surnameTextField;
    private JPasswordField confirmerMotDePasseField;
    public static String email;
    public SignUp() {
        continuerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (newUser()){
                    Login.connectionUser(email, String.valueOf(motDePasseField.getPassword()));
                    Display.errorPopUp("Votre compte a bien été créé. Vous êtes maintenant connecté.");
                }
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
    public boolean newUser(){
        email = emailTextField.getText();
        String name = nameTextField.getText();
        String surname = surnameTextField.getText();
        String userName = nomDUtilisateurTextField.getText();
        String password1 = String.valueOf(motDePasseField.getPassword());
        String password2 = String.valueOf(confirmerMotDePasseField.getPassword());
        if (email.isEmpty()) {
            Display.errorPopUp("Veuillez entrer votre adresse mail");
        } else if (!email.contains("@") || !email.contains(".") || email.length() < 12) {
            Display.errorPopUp("Veuillez entrer une adresse mail valide");
        } else if (name.isEmpty()){
            Display.errorPopUp("Veillez entrer votre nom");
        } else if (surname.isEmpty()) {
            Display.errorPopUp("Veuillez entrer votre prénom");
        } else if (userName.isEmpty()) {
            Display.errorPopUp("Veuillez entrer votre pseudo");
        } else if (password1.length() < 8) {
            Display.errorPopUp("Veuillez entrer un mot de passe plus sécurisé");
        } else if (password2.length() < 8) {
            Display.errorPopUp("Veuillez écrire le même mot de passe");
        }else if (main.getWhiteList().isInWhiteListInsert(email)) {
            if (!isAlreadyUsed(email)) {
                if (password1.equals(password2)) {
                    try (PreparedStatement preparedStatement = main.getConnectionDB().get().prepareStatement("INSERT INTO users (first_name, last_name, email, role, pseudo, password) VALUES (?, ?, ?, ?, ? ,?)")) {
                        preparedStatement.setString(1, surname);
                        preparedStatement.setString(2, name);
                        preparedStatement.setString(3, email);
                        preparedStatement.setString(4, "client");
                        preparedStatement.setString(5, userName);
                        preparedStatement.setString(6, PasswordHasher.hashPassword(password1));
                        preparedStatement.executeUpdate();
                        return true;
                    } catch (SQLException | NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    Display.errorPopUp("Un compte existant possède déjà cette adresse mail");
                }
            }
        } else {
            Display.errorPopUp("Votre adresse mail n'est pas autorisée à s'inscrire");
        }
        return false;
    }

    public boolean isAlreadyUsed(String email){
        try (PreparedStatement preparedStatement = main.getConnectionDB().get().prepareStatement("SELECT email FROM users")) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                if (Objects.equals(rs.getString(1), email)){
                    Display.errorPopUp("Un compte existant possède déjà cette adresse mail");
                    return true;
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return false;
    }
}

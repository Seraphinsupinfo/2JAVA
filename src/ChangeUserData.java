import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ChangeUserData {
    private JButton retourButton;
    protected JPanel panelMain;
    private JTextField nomTextField;
    private JTextField prenomTextField;
    private JTextField pseudoTextField;
    private JButton mettreÀJourButton;
    private JPasswordField ancienMDP;
    private JPasswordField nouveauMDP;
    private JPasswordField ConfirmerMDP;
    private JButton majPwdButton;
    private JLabel emailLabel;

    protected String email = main.getActualUser().getMail();
    protected String nom = main.getActualUser().getLastName();
    protected String prenom = main.getActualUser().getFirstName();
    protected String pseudo = main.getActualUser().getPseudo();
    ChangeUserData() {

        emailLabel.setText("Email : " + email);
        nomTextField.setText(nom);
        prenomTextField.setText(prenom);
        pseudoTextField.setText(pseudo);

        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(Objects.equals(main.getActualUser().getRole(), "seller")){
                    Display.homeSeller();
                }
                else if(Objects.equals(main.getActualUser().getRole(), "client")){
                    Display.homeUser();
                }
            }
        });

        mettreÀJourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.getActualUser().updateData(prenomTextField.getText(), nomTextField.getText(), pseudoTextField.getText());
            }
        });

        majPwdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String lastPassword = String.valueOf(ancienMDP.getPassword());
                String newPassword = String.valueOf(nouveauMDP.getPassword());
                String newPassword2 = String.valueOf(ConfirmerMDP.getPassword());
                if (newPassword.equals(newPassword2)){
                    main.getActualUser().updatePassword(lastPassword, newPassword);
                } else {
                    Display.errorPopUp("Vos nouveaux mots de passe ne sont pas identiques");
                }
            }
        });
    }
}

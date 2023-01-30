import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeUserData {
    private JButton retourButton;
    protected JPanel panelMain;
    private JTextField nomTextField;
    private JTextField prénomTextField;
    private JTextField pseudoTextField;
    private JButton mettreÀJourButton;
    private JPasswordField ancienMDP;
    private JPasswordField nouveauMDP;
    private JPasswordField ConfirmerMDP;
    private JButton majPwdButton;
    private JLabel emailLabel;

    protected String email = "ton adresse@mail.test";
    protected String nom = "ton nom";
    protected String prenom = "ton prénom";
    protected String pseudo = "ton pseudo";
    ChangeUserData() {

        emailLabel.setText("Email : " + email);
        nomTextField.setText(nom);
        prénomTextField.setText(prenom);
        pseudoTextField.setText(pseudo);

        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Display.homeUser();
            }
        });

        mettreÀJourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ta fonction ici
            }
        });

        majPwdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               //ta fonction ici
            }
        });
    }
}

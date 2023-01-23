package Interfaces;

import javax.swing.*;

public class HomeAdmin {
    private JButton gérerLesUtilisateursButton;
    protected JPanel panelMain;
    private JButton gérerLesMagasinsButton;
    private JButton gérerLesEMailsButton;
    private JLabel labelText;

    private String firstName = "[ta variable nom]";

    HomeAdmin(){
        labelText.setText("Bonjour "+ firstName + ", que voulez-vous faire ?");
    }
}

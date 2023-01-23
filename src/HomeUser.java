import javax.swing.*;

public class HomeUser {
    protected JPanel panelMain;
    private JButton accederÀUnMagasinButton;
    private JButton monCompteButton;

    private JLabel labelText;

    private String firstName = "[ta variable nom]";

    HomeUser(){
        labelText.setText("Bonjour "+ firstName + ", que voulez-vous faire ?");
    }
}

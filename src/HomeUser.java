import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeUser {
    protected JPanel panelMain;
    private JButton accederÀUnMagasinButton;
    private JButton monCompteButton;

    private JLabel labelText;
    private JButton seDéconnecterButton;

    private String firstName = "[ta variable nom]";

    HomeUser(){

        labelText.setText("Bonjour "+ firstName + ", que voulez-vous faire ?");

        accederÀUnMagasinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               //ryan
            }
        });
        monCompteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Display.changeUserData();
            }
        });
        seDéconnecterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Display.frame.dispose();
                Display.main();
            }
        });
    }
}

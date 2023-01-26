import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeAdmin {
    private JButton gérerLesUtilisateursButton;
    protected JPanel panelMain;
    private JButton gérerLesMagasinsButton;
    private JButton gérerLesEMailsButton;
    private JLabel labelText;

    private String firstName = "[ta variable nom]";

    HomeAdmin(){
        labelText.setText("Bonjour "+ firstName + ", que voulez-vous faire ?");

        gérerLesEMailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Display.whiteList();
            }
        });
    }
}

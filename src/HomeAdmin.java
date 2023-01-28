import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeAdmin {
    private static WhiteListsEmails whiteListsEmails;
    private JButton gérerLesUtilisateursButton;
    protected JPanel panelMain;
    private JButton gérerLesMagasinsButton;
    private JButton gérerLesEMailsButton;
    private JLabel labelText;
    private String firstName = main.getActualUser().getFirstName();

    public static WhiteListsEmails getWhiteListsEmails() {
        return whiteListsEmails;
    }

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

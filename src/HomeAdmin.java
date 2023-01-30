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
    private JButton gérerLesStocksButton;
    private JButton seDéconnecterButton;
    private String firstName = main.getActualUser().getFirstName();

    public static WhiteListsEmails getWhiteListsEmails() {
        return whiteListsEmails;
    }

    HomeAdmin(){
        labelText.setText("Bonjour "+ firstName + ", que voulez-vous faire ?");

        gérerLesUtilisateursButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Display.userManagement();
            }
        });

        gérerLesEMailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Display.whiteList();
            }
        });

        gérerLesMagasinsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Display.shopManagement();
            }
        });

        gérerLesStocksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Display.stockAdmin();
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

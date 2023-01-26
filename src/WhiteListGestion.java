import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WhiteListGestion {
    private JButton retourButton;
    private JTextField entrezMailOuIDTextField;
    private JButton supprimerButton;
    private JButton ajouterButton;
    private JTable table1;
    protected JPanel panelMain;

    WhiteListGestion(){

        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Display.homeAdmin();
            }
        });

        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ta fonction
            }
        });

        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ta fonction
            }
        });
    }
}

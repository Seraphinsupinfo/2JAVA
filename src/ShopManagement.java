import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopManagement {
    private JButton retourButton;
    private JPasswordField passwordField1;
    private JButton supprimerButton;
    private JPasswordField passwordField2;
    private JTextField textField1;
    private JTextField textField2;
    private JButton mettreÀJourButton;
    private JTextField textField3;
    private JTextField textField4;
    private JButton ajouterButton;
    private JTable table1;
    private JScrollBar scrollBar1;
    protected JPanel panelMain;

    public ShopManagement() {


        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Display.homeAdmin();
            }
        });

        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ton code ici
            }
        });

        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ton code ici
            }
        });

        mettreÀJourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ton code ici
            }
        });
    }
}

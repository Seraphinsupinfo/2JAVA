import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StockAdmin {
    private JButton retourButton;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton supprimerButton;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton ajouterButton;
    private JTable tableArticles;
    private JScrollBar scrollBar1;
    protected JPanel panelMain;

    public StockAdmin() {


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

    }
}

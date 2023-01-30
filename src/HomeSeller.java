import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeSeller {
    public JPanel panelMain;
    private JButton supprimerDesArticlesButton;
    private JButton ajouterDesArticlesButton;
    private JButton voirLesStocksButton;
    private JLabel labelText;
    private JButton seDéconnecterButton;

    private String firstName = main.getActualUser().getFirstName();
    private String shop = Login.getActualShop().getName() + " " + Login.getActualShop().getLocation();


    HomeSeller(){
        labelText.setText("Bonjour "+ firstName + ", vous gérez le magasin " + shop);

        voirLesStocksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Display.stockSeller();
            }
        });
        ajouterDesArticlesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //ryan
            }
        });
        supprimerDesArticlesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ryan
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


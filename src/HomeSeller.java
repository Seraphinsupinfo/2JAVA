import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class HomeSeller {
    public JPanel panelMain;
    private JButton supprimerDesArticlesButton;
    private JButton ajouterDesArticlesButton;
    private JButton voirLesStocksButton;
    private JLabel labelText;

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
    }
}


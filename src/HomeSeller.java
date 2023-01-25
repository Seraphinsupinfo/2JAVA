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

    private String firstName = "[ta variable nom]";
    private String shop = "[ta variable shop]";


    HomeSeller(){
        labelText.setText("Bonjour "+ firstName + ", vous êtes assignés au magasin " + shop);

        voirLesStocksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Display.stockSeller();
            }
        });
    }
}


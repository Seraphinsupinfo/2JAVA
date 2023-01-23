import javax.swing.*;

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
    }
}

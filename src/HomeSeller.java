import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeSeller {
    public JPanel panelMain;
    private JButton monCompteButton;
    private JButton gérerLesStocksButton;
    private JLabel labelText;
    private JButton seDéconnecterButton;

    private String firstName = main.getActualUser().getFirstName();
    private String shop = Login.getActualShop().getName() + " " + Login.getActualShop().getLocation();


    HomeSeller(){
        labelText.setText("Bonjour "+ firstName + ", vous gérez le magasin " + shop);

        gérerLesStocksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Display.stockSeller();
            }
        });

        monCompteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Display.changeUserData();
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


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayMain {
    public JPanel panelMain;
    public JButton créerUnCompteButton;
    public JButton seConnecterButton;
public DisplayMain() {
    créerUnCompteButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Display.signUp();
        }
    });
    seConnecterButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Display.logIn();
        }
    });
}
}
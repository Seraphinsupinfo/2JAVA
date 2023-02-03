import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionPopUp {
    protected JPanel panelMain;
    private JButton ouiButton;
    private JButton nonButton;
    private JLabel labelText;

    protected static String varText;
    ActionPopUp(){
        labelText.setText(varText);

        nonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Display.Actionframe.dispose();
            }
        });

        ouiButton.addActionListener(new ActionListener() {
            @Override
            ///action ici
            public void actionPerformed(ActionEvent e) {
                Display.Actionframe.dispose();
            }
        });
    }
}

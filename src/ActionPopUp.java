import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ActionPopUp {
    protected JPanel panelMain;
    private JButton ouiButton;
    private JButton nonButton;
    private JLabel labelText;

    protected static String varText;

    protected static String action;
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
            public void actionPerformed(ActionEvent e) {

                if(action.equals("user")){
                    // action si t'es sur la page user
                }
                if (action.equals("shop")){
                    // action si t'es sur la page shop
                }

                Display.Actionframe.dispose();
            }
        });
    }
}

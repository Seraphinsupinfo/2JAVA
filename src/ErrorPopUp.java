import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorPopUp {
    private JButton OKButton;
    protected JPanel panelMain;
    private JLabel errorLabel;

    protected static String varText;
    ErrorPopUp(){
        errorLabel.setText(varText);

        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Display.frame.dispose();
            }
        });
    }

}


import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ShopManagement {
    private JButton retourButton;
    private JButton supprimerButton;
    private JTextField nameUpdateField;
    private JTextField locationUpdateField;
    private JButton mettreÀJourButton;
    private JTextField shopNameAdd;
    private JTextField shopLocationAdd;
    private JButton ajouterButton;
    private JTable tableShops;

    protected JPanel panelMain;
    private JTextField IDUpdateField;
    private JTextField NameOrIDDel;

    public ShopManagement() {

        this.tableShops.setModel(new ModelDeTableShops(getAllShops()));

        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Display.homeAdmin();
            }
        });

        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNewShop(shopNameAdd.getText(), shopLocationAdd.getText());
            }
        });

        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ton code ici
            }
        });

        mettreÀJourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ton code ici
            }
        });
    }
}


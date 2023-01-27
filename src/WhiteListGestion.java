import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WhiteListGestion {
    private JButton retourButton;
    private JTextField entrezMailOuIDTextField;
    private JButton supprimerButton;
    private JButton ajouterButton;
    private JTable table1;
    protected JPanel panelMain;
    private JScrollBar scrollBar1;


    WhiteListGestion(){

        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Display.homeAdmin();
            }
        });

        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ta fonction
            }
        });

        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.getWhiteList().refreshList();
                WhiteMail newWhiteMail = new WhiteMail(entrezMailOuIDTextField.getText());
                newWhiteMail.insertWhiteList();
            }
        });

    }

}
class ModelDeTableWhiteList extends AbstractTableModel {
    private ArrayList<WhiteMail> whiteMails;
    private String[] columns = {"ID", "Email"};

    public ModelDeTableWhiteList(ArrayList<WhiteMail> whiteMails) {
        this.whiteMails = whiteMails;
    }
    @Override
    public int getRowCount() {
        return whiteMails.size();
    }
    @Override
    public int getColumnCount() {
        return columns.length;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        WhiteMail whiteMail = whiteMails.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return whiteMail.getID();
            case 1:
                return whiteMail.getEmail();
            default:
                return null;
        }
    }
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
}

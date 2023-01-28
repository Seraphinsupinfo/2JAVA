import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        this.table1.setModel(new ModelDeTableWhiteList(main.getWhiteList().getWhiteList()));

        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Display.homeAdmin();
            }
        });

        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (StockSeller.verifInt(entrezMailOuIDTextField.getText())) {
                    WhiteMail delWhiteMail = new WhiteMail(Integer.parseInt(entrezMailOuIDTextField.getText()));
                    delWhiteMail.deleteWhiteMail();
                } else {
                    WhiteMail delWhiteMail = new WhiteMail(entrezMailOuIDTextField.getText());
                    delWhiteMail.deleteWhiteMail();
                }
            }
        });

        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WhiteMail newWhiteMail = new WhiteMail(entrezMailOuIDTextField.getText());
                newWhiteMail.insertWhiteMail();
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

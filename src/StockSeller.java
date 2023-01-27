import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JTable;

public class StockSeller {
    private JTable tableArticles;

    protected JPanel panelMain;
    private JButton retourButton;
    private JTextField fieldId;
    private JTextField fieldQtt;
    private JButton validerButton;

    public boolean isModified(Items data) {
        return false;
    }

    private static boolean verifInt(String s) {
        boolean isInt = true;
        try{ Integer.parseInt(s); }
        catch(NumberFormatException nfe){ isInt = false; }
        return isInt;
    }
    public StockSeller() {

        this.tableArticles.setModel(new ModelDeTableDItems(Login.getActualShop().getItems()));

        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Display.homeSeller();
            }
        });

        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (verifInt(fieldQtt.getText())){
                    //ton code qtt OK

                    if (verifInt(fieldId.getText())){
                        //ton code ID OK
                    }
                    else {
                        Display.errorPopUp("ID invalide");
                    }
                }
                else {
                    if (!verifInt(fieldId.getText())){
                        Display.errorPopUp("ID et Quantité invalide");
                    }
                    else {
                        Display.errorPopUp("Quantité invalide");
                    }
                }

            }
        });
    }



}

class ModelDeTableDItems extends AbstractTableModel {
    private ArrayList<Items> items;
    private String[] columns = {"shopID", "ID", "name", "price", "quantity"};

    public ModelDeTableDItems(ArrayList<Items> items) {
        this.items = items;
    }
    @Override
    public int getRowCount() {
        return items.size();
    }
    @Override
    public int getColumnCount() {
        return columns.length;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Items item = items.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return item.getShopID();
            case 1:
                return item.getID();
            case 2:
                return item.getName();
            case 3:
                return item.getPrice();
            case 4:
                return item.getQuantity();
            default:
                return null;
        }
    }
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
}

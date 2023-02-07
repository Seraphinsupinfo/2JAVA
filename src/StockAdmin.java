import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockAdmin {
    private JButton retourButton;
    private JTextField ShopIDAdd;
    private JTextField IDDelField;
    private JButton supprimerButton;
    private JTextField IDAddField;
    private JTextField NameFieldAdd;
    private JTextField PriceAddField;
    private JButton ajouterButton;
    private JTable tableArticles;
    private JScrollBar scrollBar1;
    protected JPanel panelMain;
    private JTextField QuantityAddField;

    public StockAdmin() {

        tableArticles.setModel(new ModelDeTableStock(getAllStocks()));
        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Display.homeAdmin();
            }
        });

        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newItem();
            }
        });

        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delItem();
            }
        });

    }

    public ArrayList<Items> getAllStocks() {
        ArrayList<Items> items = new ArrayList<Items>();
        if (main.getConnectionDB().isPresent()) {
            try (PreparedStatement preparedStatement = main.getConnectionDB().get().prepareStatement("SELECT * FROM items")) {
                ResultSet rs = preparedStatement.executeQuery();
                while(rs.next()) {
                    items.add(new Items(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5)));
                }
                return items;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return items;
    }

    public void newItem(){
        String name = NameFieldAdd.getText();
        String price = PriceAddField.getText();
        String quantity = QuantityAddField.getText();
        String shopID = ShopIDAdd.getText();
        if (name.length() <= 3){
            Display.errorPopUp("Veuillez entrer un nom contenant au minimum 3 caractères pour votre article");
        } else if (!StockSeller.verifInt(price)){
            Display.errorPopUp("Veuillez entrer un prix au bon format.");
        } else if (!StockSeller.verifInt(quantity)){
            Display.errorPopUp("Veuillez entrer une quantité au bon format");
        } else if (!StockSeller.verifInt(shopID)){
            Display.errorPopUp("Veuillez entrer un shop ID sous un bon format.");
        } else {
            int intPrice = Integer.parseInt(price);
            int intQuantity = Integer.parseInt(quantity);
            int intShopID = Integer.parseInt(shopID);
            try (PreparedStatement preparedStatement = main.getConnectionDB().get().prepareStatement("INSERT INTO items (name, price, quantity, shop_ID) VALUES (?, ?, ?, ?)")) {
                preparedStatement.setString(1, name);
                preparedStatement.setInt(2, intPrice);
                preparedStatement.setInt(3, intQuantity);
                preparedStatement.setInt(4, intShopID);
                preparedStatement.executeUpdate();
                Display.stockAdmin();
                Display.errorPopUp("Item ajouté avec succès");
            } catch (SQLException e) {
                Display.errorPopUp("Une erreur est survenue... Création impossible");
                throw new RuntimeException(e);
            }
        }
    }

    public void delItem(){
        if (!IDDelField.getText().isEmpty()){
            if (StockSeller.verifInt(IDDelField.getText())){
                int ID = Integer.parseInt(IDDelField.getText());
                if (getAllStocksID().contains(ID)){
                    try (PreparedStatement preparedStatement = main.getConnectionDB().get().prepareStatement("DELETE FROM items WHERE ID = ?")) {
                        preparedStatement.setInt(1, ID);
                        preparedStatement.executeUpdate();
                        Display.stockAdmin();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    Display.errorPopUp("Aucun item ne portant cet ID n'a été trouvé.");
                }
            } else {
                Display.errorPopUp("Veuillez entrer un nombre dans le champs 'ID");
            }
        } else {
            Display.errorPopUp("Veuillez entrer un nombre dans le champs 'ID");
        }

    }

    public ArrayList<Integer> getAllStocksID(){
        ArrayList<Integer> ID = new ArrayList<Integer>();
        if (main.getConnectionDB().isPresent()) {
            try (PreparedStatement preparedStatement = main.getConnectionDB().get().prepareStatement("SELECT ID FROM items")) {
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    ID.add(rs.getInt(1));
                }
                return ID;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return ID;
    }
}

class ModelDeTableStock extends AbstractTableModel {
    private ArrayList<Items> items;
    private String[] columns = {"ID", "Nom", "Prix", "Quantité", "Shop_ID"};

    public ModelDeTableStock(ArrayList<Items> items) {
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
        Items items1 = items.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return items1.getShopID();
            case 1:
                return items1.getID();
            case 2:
                return items1.getName();
            case 3:
                return items1.getPrice();
            case 4:
                return items1.getQuantity();
            default:
                return null;
        }
    }
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
}

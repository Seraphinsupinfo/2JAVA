import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ShopManagement {
    protected JPanel panelMain;
    private JButton retourButton;
    private JTable ShopTables;
    private JTextField nameAdd;
    private JTextField LocationAdd;
    private JButton ajouterButton;
    private JTextField nameOrIDDel;
    private JButton supprimerButton;
    private JTextField IDUpdate;
    private JTextField nameUpdate;
    private JTextField locationUpdate;
    private JButton updateButton;

    public ShopManagement() {

        this.ShopTables.setModel(new ModelDeTableShops(getAllShops()));

        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Display.homeAdmin();
            }
        });

        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNewShop(nameAdd.getText(), LocationAdd.getText());
                Display.shopManagement();
            }
        });

        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Display.shopManagement();
            }
        });
    }

    class ModelDeTableShops extends AbstractTableModel {
        private ArrayList<Shops> shops;
        private String[] columns = {"ID", "Name", "Location"};

        public ModelDeTableShops(ArrayList<Shops> shops) {
            this.shops = shops;
        }
        @Override
        public int getRowCount() {
            return shops.size();
        }
        @Override
        public int getColumnCount() {
            return columns.length;
        }
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Shops shops1 = shops.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return shops1.getID();
                case 1:
                    return shops1.getName();
                case 2:
                    return shops1.getLocation();
                default:
                    return null;
            }
        }
        @Override
        public String getColumnName(int column) {
            return columns[column];
        }
    }

    public ArrayList<Shops> getAllShops() {
        ArrayList<Shops> shops = new ArrayList<Shops>();
        if (main.getConnectionDB().isPresent()){
            try (Statement st = main.getConnectionDB().get().createStatement()){
                try (ResultSet rs = st.executeQuery("SELECT * FROM shops")){
                    while (rs.next()) {
                        shops.add(new Shops(rs.getInt(1), rs.getString(2), rs.getString(3)));
                    }
                    return shops;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return shops;
    }

    public void createNewShop(String shopName, String location) {
        if (shopName.isEmpty()) {
            Display.errorPopUp("Veuillez entrer le nom de votre magasin");
        } else if (location.isEmpty()) {
            Display.errorPopUp("Veuillez entrer la location de votre magasin");
        }
        shopName.toUpperCase();
        try (PreparedStatement preparedStatement = main.getConnectionDB().get().prepareStatement("INSERT INTO shops (name, location) VALUES (?, ?)")) {
            preparedStatement.setString(1, shopName);
            preparedStatement.setString(2, location);
            preparedStatement.executeUpdate();
            Display.errorPopUp("Magasin créé avec succès");
        } catch (SQLException e) {
            Display.errorPopUp("Une erreur est survenue... Création impossible");
            throw new RuntimeException(e);
        }
    }

    public boolean isInShops(int checkID) {
        ArrayList<Shops> shops= new ArrayList<Shops>();
        shops = getAllShops();
        for (Shops shop : shops) {
            if (checkID == shop.getID()) {
                return true;
            }
        }
        return false;
    }
}

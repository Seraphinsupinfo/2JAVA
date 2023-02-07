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
    protected JPanel panelMain;
    private JButton retourButton;
    private JTable ShopTables;
    private JTextField nameAdd;
    private JTextField LocationAdd;
    private JButton ajouterButton;
    private JTextField IDDel;
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
            }
        });

        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (StockSeller.verifInt(IDDel.getText())){
                    if (isInShops(Integer.parseInt(IDDel.getText()))) {
                        deleteShop(Integer.parseInt(IDDel.getText()));
                    } else {
                        Display.errorPopUp("Veuillez entrer un ID présent dans la liste");
                    }
                } else {
                    Display.errorPopUp("Veuillez entrer un ID valide");
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (StockSeller.verifInt(IDUpdate.getText())){
                    if (isInShops(Integer.parseInt(IDUpdate.getText()))){
                        updateShop(Integer.parseInt(IDUpdate.getText()), nameUpdate.getText(), locationUpdate.getText());
                    } else {
                        Display.errorPopUp("L'ID entré ne correspond à aucun magasin.");
                    }
                } else {
                    Display.errorPopUp("Veuillez entrer un ID correct");
                }
            }
        });

    }

    static class ModelDeTableShops extends AbstractTableModel {
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
        public String getColumnName(int column) {
            return columns[column];
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
    }

    public static ArrayList<Shops> getAllShops() {
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
        try (PreparedStatement preparedStatement = main.getConnectionDB().get().prepareStatement("INSERT INTO shops (name, location) VALUES (?, ?)")) {
            preparedStatement.setString(1, shopName);
            preparedStatement.setString(2, location);
            preparedStatement.executeUpdate();
            Display.shopManagement();
            Display.errorPopUp("Magasin créé avec succès");
        } catch (SQLException e) {
            Display.errorPopUp("Une erreur est survenue... Création impossible");
            throw new RuntimeException(e);
        }
    }

    public void deleteShop(int ID){
        try (PreparedStatement preparedStatement = main.getConnectionDB().get().prepareStatement("DELETE FROM shops WHERE ID = ?")) {
            preparedStatement.setInt(1, ID);
            preparedStatement.executeUpdate();
            Display.shopManagement();
            Display.errorPopUp("Magasin supprimé avec succès");
        } catch (SQLException e) {
            Display.errorPopUp("Une erreur est survenue... Suppression impossible");
            throw new RuntimeException(e);
        }
    }

    public void updateShop(int ID, String newName, String newLocation){
        boolean newNameOK = false;
        boolean newLocationOK = false;
        boolean doRequest = true;
        if (!newName.isEmpty()){
            if (newName.length() >= 5){
                newNameOK = true;
            } else {
                Display.errorPopUp("Le nom doit faire au moins 5 caractères");
                doRequest =  false;
            }
        }
        if (!newLocation.isEmpty()){
            if (newLocation.length() >= 3){
                newLocationOK = true;
            } else {
                Display.errorPopUp("Le nom doit faire au moins 5 caractères");
                doRequest =  false;
            }
        }
        if (doRequest){
            if (newNameOK && newLocationOK){
                if (main.getConnectionDB().isPresent()) {
                    try (PreparedStatement preparedStatement = main.getConnectionDB().get().prepareStatement("UPDATE shops SET name = ?, location = ? WHERE ID = ?")) {
                        preparedStatement.setString(1, newName);
                        preparedStatement.setString(2, newLocation);
                        preparedStatement.setInt(3, ID);
                        preparedStatement.executeUpdate();
                        Display.shopManagement();
                        Display.errorPopUp("La mise à jour du magasin a bien été prise en compte");
                    } catch (SQLException e) {
                        Display.errorPopUp("Une erreur est survenue... Mise à jour impossible");
                        throw new RuntimeException(e);
                    }
                }
            } else if (newNameOK){
                if (main.getConnectionDB().isPresent()) {
                    try (PreparedStatement preparedStatement = main.getConnectionDB().get().prepareStatement("UPDATE shops SET name = ? WHERE ID = ?")) {
                        preparedStatement.setString(1, newName);
                        preparedStatement.setInt(2, ID);
                        preparedStatement.executeUpdate();
                        Display.shopManagement();
                        Display.errorPopUp("La mise à jour du magasin a bien été prise en compte");
                    } catch (SQLException e) {
                        Display.errorPopUp("Une erreur est survenue... Mise à jour impossible");
                        throw new RuntimeException(e);
                    }
                }
            } else if (newLocationOK){
                if (main.getConnectionDB().isPresent()) {
                    try (PreparedStatement preparedStatement = main.getConnectionDB().get().prepareStatement("UPDATE shops SET location = ? WHERE ID = ?")) {
                        preparedStatement.setString(1, newLocation);
                        preparedStatement.setInt(2, ID);
                        preparedStatement.executeUpdate();
                        Display.shopManagement();
                        Display.errorPopUp("La mise à jour du magasin a bien été prise en compte");
                    } catch (SQLException e) {
                        Display.errorPopUp("Une erreur est survenue... Mise à jour impossible");
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public boolean isInShops(int checkID) {
        ArrayList<Shops> shops = new ArrayList<Shops>();
        shops = getAllShops();
        for (Shops shop : shops) {
            if (checkID == shop.getID()) {
                return true;
            }
        }
        return false;
    }
}

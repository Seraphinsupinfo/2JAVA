import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserManagement {
    protected JPanel panelMain;
    private JButton retourButton;
    private JTextField IDField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField firstNameField;
    private JTextField pseudoField;
    private JTextField passwordField;
    private JButton ajouterButton;
    private JButton supprimerButton;
    private JButton mettreÀJourButton;
    private JTable tableUser;
    private JScrollBar scrollBar1;
    private JComboBox role;
    private JTextField shopIDField;

    public UserManagement() {
        ArrayList<Users> users = getAllUsers();
        tableUser.setModel(new ModelDeTableUsers(users));

        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Display.homeAdmin();
            }
        });

        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newrole = (String) role.getSelectedItem();
                if (newrole == "Admin"){
                    newrole = "admin";
                } else if (newrole == "Vendeur"){
                    newrole = "seller";
                } else if (newrole == "Client"){
                    newrole = "client";
                }
                newUser(newrole);
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

    public ArrayList<Users> getAllUsers(){
        ArrayList<Users> users= new ArrayList<Users>();
        if (main.getConnectionDB().isPresent()) {
            try (PreparedStatement preparedStatement = main.getConnectionDB().get().prepareStatement("SELECT * FROM users")) {
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    users.add(new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getString(7)));
                }
                return users;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return users;
    }

    public void newUser(String role){
        String lastName = lastNameField.getText();
        String firstName = firstNameField.getText();
        String pseudo = pseudoField.getText();
        String email = emailField.getText();
        String password = String.valueOf(passwordField.getText());
        if (lastName.length() <= 3){
            Display.errorPopUp("Veuillez entrer un nom contenant au minumum 3 caractères.");
        } else if (firstName.length() <= 3){
            Display.errorPopUp("Veuillez entrer un prénom contenant au minumum 3 caractères.");
        } else if (pseudo.length() <= 3){
            Display.errorPopUp("Veuillez entrer un pseudo contenant au minumum 3 caractères.");
        } else if (email.length() <= 12 && email.contains("@") && email.contains(".")){
            Display.errorPopUp("Veuillez entrer un email sous un bon format.");
        } else if (password.length() < 8){
            Display.errorPopUp("Veuillez entrer un mot de passe plus sécurisé.");
        } else {
            if (main.getWhiteList().isInWhiteListInsert(email)){
                Display.errorPopUp("Adresse mail déjà présente dans la liste");
            } else {
                if (role == "seller"){
                    if (StockSeller.verifInt(shopIDField.getText())){
                        int shopID = Integer.parseInt(shopIDField.getText());
                        if (haveAllShops().contains(shopID)){
                            try (PreparedStatement preparedStatement = main.getConnectionDB().get().prepareStatement("INSERT INTO users (first_name, last_name, email, role, shop_id, pseudo, password) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
                                preparedStatement.setString(1, firstName);
                                preparedStatement.setString(2, lastName);
                                preparedStatement.setString(3, email);
                                preparedStatement.setString(4, role);
                                preparedStatement.setInt(5, shopID);
                                preparedStatement.setString(6, pseudo);
                                preparedStatement.setString(7, PasswordHasher.hashPassword(password));
                                preparedStatement.executeUpdate();
                                Display.userManagement();
                                Display.errorPopUp("Adresse mail ajoutée avec succès");
                            } catch (SQLException | NoSuchAlgorithmException e) {
                                Display.errorPopUp("Une erreur est survenue... Création impossible");
                                throw new RuntimeException(e);
                            }
                        } else {
                            Display.errorPopUp("L'ID de shop entré n'existe pas.");
                        }
                    } else {
                    Display.errorPopUp("Veuillez entrer un ID de magasin valide");
                    }
                } else {
                    try (PreparedStatement preparedStatement = main.getConnectionDB().get().prepareStatement("INSERT INTO users (first_name, last_name, email, role, pseudo, password) VALUES (?, ?, ?, ?, ?, ?)")) {
                        preparedStatement.setString(1, firstName);
                        preparedStatement.setString(2, lastName);
                        preparedStatement.setString(3, email);
                        preparedStatement.setString(4, role);
                        preparedStatement.setString(5, pseudo);
                        preparedStatement.setString(6, PasswordHasher.hashPassword(password));
                        preparedStatement.executeUpdate();
                        Display.userManagement();
                        Display.errorPopUp("Adresse mail ajoutée avec succès");
                    } catch (SQLException | NoSuchAlgorithmException e) {
                        Display.errorPopUp("Une erreur est survenue... Création impossible");
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public ArrayList<Integer> haveAllShops(){
        ArrayList<Integer> allID = new ArrayList<Integer>();
        if (main.getConnectionDB().isPresent()) {
            try (PreparedStatement preparedStatement = main.getConnectionDB().get().prepareStatement("SELECT ID FROM shops")) {
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()){
                    allID.add(rs.getInt(1));
                }
                return allID;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return allID;
    }
}

class ModelDeTableUsers extends AbstractTableModel {
    private ArrayList<Users> users;
    private String[] columns = {"ID", "Prénom", "Nom", "email", "role", "shop_ID", "Pseudo"};

    public ModelDeTableUsers(ArrayList<Users> users) {
        this.users = users;
    }
    @Override
    public int getRowCount() {
        return users.size();
    }
    @Override
    public int getColumnCount() {
        return columns.length;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Users users1 = users.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return users1.getID();
            case 1:
                return users1.getFirstName();
            case 2:
                return users1.getLastName();
            case 3:
                return users1.getMail();
            case 4:
                return users1.getRole();
            case 5:
                return users1.getShopID();
            case 6:
                return users1.getPseudo();
            default:
                return null;
        }
    }
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
}
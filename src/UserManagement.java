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
    private JPasswordField passwordField;
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
                delUser();
            }
        });

        mettreÀJourButton.addActionListener(new ActionListener() {
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
                updateUser();
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
        String password = String.valueOf(passwordField.getPassword());
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

    public void delUser() {
        if (main.getConnectionDB().isPresent()) {
            if (StockSeller.verifInt(IDField.getText())){
                int ID = Integer.parseInt(IDField.getText());
                if (getAllUsers().contains(ID)){
                    try (PreparedStatement preparedStatement = main.getConnectionDB().get().prepareStatement("DELETE FROM users WHERE id = ?")) {
                        preparedStatement.setInt(1, ID);
                        preparedStatement.executeUpdate();
                        Display.userManagement();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    Display.errorPopUp("Aucun user ne portant cet ID n'a été trouvé.");
                }
            } else {
                Display.errorPopUp("La valeur du champs ID est incorrecte.");
            }
        }
    }

    public void updateUser() {
        if (StockSeller.verifInt(IDField.getText())) {
            int ID = Integer.parseInt(IDField.getText());
            try (PreparedStatement preparedStatement = main.getConnectionDB().get().prepareStatement("SELECT * FROM users WHERE id = ?")) {
                preparedStatement.setInt(1, ID);
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    String firstName = rs.getString(2);
                    String lastName = rs.getString(3);
                    String email = rs.getString(4);
                    Integer shopID = rs.getInt(6);
                    String pseudo = rs.getString(7);
                    String password = rs.getString(8);
                    if (lastNameField.getText().length() >= 3) {
                        lastName = lastNameField.getText();
                    } else if (!lastNameField.getText().isEmpty()){
                        Display.errorPopUp("Veuillez entrer un nom contenant au minumum 3 caractères.");
                    }
                    if (firstNameField.getText().length() >= 3) {
                        firstName = firstNameField.getText();
                    } else if (!firstNameField.getText().isEmpty()){
                        Display.errorPopUp("Veuillez entrer un prénom contenant au minumum 3 caractères.");
                    }
                    if (pseudoField.getText().length() >= 3) {
                        pseudo = pseudoField.getText();
                    } else if (!pseudoField.getText().isEmpty()){
                        Display.errorPopUp("Veuillez entrer un pseudo contenant au minumum 3 caractères.");
                    }
                    if (emailField.getText().length() > 12 && emailField.getText().contains("@") && emailField.getText().contains(".")) {
                        email = emailField.getText();
                    } else if (!emailField.getText().isEmpty()){
                        Display.errorPopUp("Veuillez entrer un email sous un bon format.");
                    }
                    if (String.valueOf(passwordField.getPassword()).length() > 8) {
                        password = PasswordHasher.hashPassword(String.valueOf(passwordField.getPassword()));
                    } else if (!passwordField.getText().isEmpty()) {
                        Display.errorPopUp("Veuillez entrer un mot de passe plus sécurisé.");
                    }
                    if (shopID != null) {
                        if (StockSeller.verifInt(shopIDField.getText())) {
                            shopID = Integer.parseInt(shopIDField.getText());
                        }
                    }
                    try (PreparedStatement preparedStatement2 = main.getConnectionDB().get().prepareStatement("UPDATE users SET first_name = ?, last_name = ?, email = ?, shop_id = ?, pseudo = ?, password = ? WHERE ID = ?")) {
                        preparedStatement2.setString(1, firstName);
                        preparedStatement2.setString(2, lastName);
                        preparedStatement2.setString(3, email);
                        preparedStatement2.setInt(4, shopID);
                        preparedStatement2.setString(5, pseudo);
                        preparedStatement2.setString(6, password);
                        preparedStatement2.setInt(7, ID);
                        preparedStatement2.executeUpdate();
                        Display.userManagement();
                    } catch (SQLException e) {
                        Display.errorPopUp("Impossible de mettre à jour l'utilisateur, veuillez réessayer.");
                        throw new RuntimeException(e);
                    }
                }
            } catch (SQLException | NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
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
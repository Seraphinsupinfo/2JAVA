import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Users {
    private int ID;
    private String firstName;
    private String lastName;
    private String pseudo;
    private String mail;
    private String role;
    private String pwd;
    private int shopID;

    public String getRole() {return role;}
    public String getFirstName() {return firstName;}
    public int getShopID() {return shopID;}
    public String getLastName() {return lastName;}
    public String getPseudo() {return pseudo;}
    public String getMail() {return mail;}

    public boolean validUser(String checkMail, String checkPassword){
        if (main.getConnectionDB().isPresent()){
            try (PreparedStatement preparedStatement = main.getConnectionDB().get().prepareStatement("SELECT * FROM users WHERE email = ?")){
                preparedStatement.setString(1, checkMail);
                ResultSet rs = preparedStatement.executeQuery();
                boolean loginOk = false;
                if (rs.next()){
                    mail = rs.getString(4);
                    pwd = rs.getString(8);
                    if (Objects.equals(mail, checkMail) && PasswordHasher.verifyPassword(checkPassword, pwd)) {
                        ID = rs.getInt(1);
                        firstName = rs.getString(2);
                        lastName = rs.getString(3);
                        role = rs.getString(5);
                        shopID = rs.getInt(6);
                        pseudo = rs.getString(7);
                        loginOk = true;
                    }
                }
                return loginOk;
            } catch (SQLException | NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    return false;
    }

    public void updateData(String newFirstName, String newLastName, String newPseudo){
        boolean doRequest = true;
        if (newFirstName.length() >= 3){
            this.firstName = newFirstName;
        } else {
            Display.errorPopUp("Votre prénom doit faire au minimum 3 caractères");
            doRequest = false;
        }
        if (newLastName.length() >= 3){
            this.lastName = newLastName;
        } else {
            Display.errorPopUp("Votre nom de famille doit faire au minimum 3 caractères");
            doRequest = false;
        }
        if (newPseudo.length() >= 5){
            this.pseudo = newPseudo;
        } else {
            Display.errorPopUp("Votre pseudo doit faire au minimum 5 caractères");
            doRequest = false;
        }
        if (doRequest){
            if (main.getConnectionDB().isPresent()) {
                try (PreparedStatement preparedStatement = main.getConnectionDB().get().prepareStatement("UPDATE users SET first_name = ?, last_name = ?, pseudo = ? WHERE ID = ?")) {
                    preparedStatement.setString(1, firstName);
                    preparedStatement.setString(2, lastName);
                    preparedStatement.setString(3, pseudo);
                    preparedStatement.setInt(4, ID);
                    preparedStatement.executeUpdate();
                    Display.changeUserData();
                    Display.errorPopUp("La mise à jour de votre profile a bien été prise en compte");
                } catch (SQLException e) {
                    Display.errorPopUp("Une erreur est survenue... Mise à jour impossible");
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void updatePassword(String lastPassword, String newPassword){
        String checkDBPassword;
        if (newPassword.length() >= 8) {
            if (main.getConnectionDB().isPresent()) {
                try (PreparedStatement preparedStatement = main.getConnectionDB().get().prepareStatement("SELECT password FROM users WHERE ID = ?")) {
                    preparedStatement.setInt(1, ID);
                    ResultSet rs = preparedStatement.executeQuery();
                    if (rs.next()) {
                        checkDBPassword = rs.getString(8);
                        if (PasswordHasher.verifyPassword(lastPassword, checkDBPassword)) {
                            try (PreparedStatement preparedStatement2 = main.getConnectionDB().get().prepareStatement("UPDATE users SET password = ? WHERE ID = ?")) {
                                preparedStatement2.setString(1, PasswordHasher.hashPassword(newPassword));
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        } else {
                            Display.errorPopUp("Votre mot de passe actuel est érroné.");
                        }
                    }
                } catch (SQLException | NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            Display.errorPopUp("Veuillez entrer un mot de passe plus sécurisé.");
        }
    }
}
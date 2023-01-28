import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WhiteMail {
    private int ID;
    private String email;

    public WhiteMail(int ID, String email) {
        this.ID = ID;
        this.email = email;
    }

    public WhiteMail(String email) {
        this.ID = 0;
        this.email = email;
    }

    public WhiteMail(int ID) {
        this.email = "";
        this.ID = ID;
    }

    public int getID() {return ID;}
    public String getEmail() {return email;}

    public void insertWhiteMail(){
        //Fonction servant à mettre de nouvelles adresses dans la table WhiteList
        if (email.contains("@") && email.contains(".") && email.length() > 8) {
            if (main.getWhiteList().isInWhiteListInsert(email)){
                Display.errorPopUp("Adresse mail déjà présente dans la liste");
            } else {
                try (PreparedStatement preparedStatement = main.getConnectionDB().get().prepareStatement("INSERT INTO white_list (email) VALUES (?)")) {
                    preparedStatement.setString(1, email);
                    preparedStatement.executeUpdate();
                    main.getWhiteList().refreshList();
                    Display.whiteList();
                    Display.errorPopUp("Adresse mail ajoutée avec succès");
                } catch (SQLException e) {
                    Display.errorPopUp("Une erreur est survenue... Création impossible");
                    throw new RuntimeException(e);
                }
            }
        } else {
            Display.errorPopUp("Adresse mail invalide");
        }
    }

    public void deleteWhiteMail(){
        if (main.getWhiteList().isInWhiteListDelete(email, ID)){
            try (PreparedStatement preparedStatement = main.getConnectionDB().get().prepareStatement("DELETE FROM white_list WHERE email = ? OR ID = ?")) {
                preparedStatement.setString(1, email);
                preparedStatement.setInt(2, ID);
                preparedStatement.executeUpdate();
                main.getWhiteList().refreshList();
                Display.whiteList();
                Display.errorPopUp("Adresse mail supprimée avec succès");
            } catch (SQLException e) {
                Display.errorPopUp("Une erreur est survenue... Suppression impossible");
                throw new RuntimeException(e);
            }
        } else {
            Display.errorPopUp("Adresse mail non présente dans la liste");
        }
    }
}
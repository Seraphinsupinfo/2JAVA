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
        this.email = email;
    }

    public int getID() {return ID;}
    public String getEmail() {return email;}

    public void insertWhiteList(){
        //Fonction servant à mettre de nouvelles adresses dans la table WhiteList
        if (email.contains("@") && email.contains(".") && email.length() > 8) {
            if (main.getWhiteList().isInWhiteList(email)){
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
}
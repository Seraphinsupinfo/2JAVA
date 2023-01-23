import java.sql.*;
import java.util.*;

public class WhiteListsEmails {
    private static ArrayList<String> whiteList = new ArrayList<String>();
    private static String email;
    private static Optional<Connection> connectionDB;

    public WhiteListsEmails(Optional<Connection> connection) throws SQLException {
        //Le constructeur qui charge toutes les valeurs de la table des adresses mails dans la variable grace àla fonction returnWhiteList
        connectionDB = connection;
        whiteList = returnWhitelist();
        email = "";
    }

    public static ArrayList<String> getWhiteList() {
        return whiteList;
    }

    public static void setEmail(String email) {
        WhiteListsEmails.email = email;
    }

    public void insertWhitelist () throws SQLException {
        //Fonction servant à mettre de nouvelles adresses dans la table WhiteList
        if (email != "") {
            String sql = "INSERT INTO white_list (email) VALUES(?)";
            PreparedStatement statement = connectionDB.get().prepareStatement(sql);
            statement.setString(1, email);
            statement.executeUpdate();
        }
    }

    public void deleteWhitelist() throws SQLException{
        email = SignUp.email;
    }

    private ArrayList<String> returnWhitelist() throws SQLException {
        //Pour toutes les adresses on ajoute dans la liste
        ArrayList<String> whitelist = new ArrayList<>();
        if (connectionDB.isPresent()){
            try (Statement st = connectionDB.get().createStatement()){
                try (ResultSet rs = st.executeQuery("SELECT email FROM white_list")){
                    while (rs.next()) {
                        whitelist.add(rs.getString("email"));
                    }
                }
            }
        }
        //On la return logique
        return whitelist;
    }
}
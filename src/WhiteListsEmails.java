import java.sql.*;
import java.util.*;

public class WhiteListsEmails {
    private static ArrayList<String> whiteList = new ArrayList<String>();

    public WhiteListsEmails(Optional<Connection> connection) throws SQLException {
        //Le constructeur qui charge toutes les valeurs de la table des adresses mails dans la variable grace àla fonction returnWhiteList
        whiteList = returnWhitelist(connection);
    }

    public static ArrayList<String> getWhiteList() {
        return whiteList;
    }

    private ArrayList<String> returnWhitelist(Optional<Connection> connection) throws SQLException {
        //Pour toutes les adresses on ajoute dans la liste
        ArrayList<String> whitelist = new ArrayList<>();
        if (connection.isPresent()){
            try (Statement st = connection.get().createStatement()){
                try (ResultSet rs = st.executeQuery("SELECT email FROM white_list")){
                    while (rs.next()) {
                        System.out.println(rs.getString("email"));
                        whitelist.add(rs.getString("email"));
                    }
                }
            }
        }
        //On la return logique
        return whitelist;
    }
}
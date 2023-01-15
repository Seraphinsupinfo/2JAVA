import java.sql.*;
import java.util.*;

public class WhiteListsEmails {
    private static ArrayList<String> whiteList = new ArrayList<String>();

    public WhiteListsEmails(Optional<Connection> connection) throws SQLException {
        whiteList = returnWhitelist(connection);
    }

    private ArrayList<String> returnWhitelist(Optional<Connection> connection) throws SQLException {
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
        return whitelist;
    }
}

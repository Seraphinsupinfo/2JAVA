import java.sql.*;
import java.util.*;

public class WhiteListsEmails {
    private static ArrayList<WhiteMail> whiteList = new ArrayList<WhiteMail>();

    public WhiteListsEmails() {
        if (main.getConnectionDB().isPresent()){
            try (Statement st = main.getConnectionDB().get().createStatement()){
                try (ResultSet rs = st.executeQuery("SELECT * FROM white_list")){
                    while (rs.next()) {
                        whiteList.add(new WhiteMail(rs.getInt(1), rs.getString(2)));
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static ArrayList<WhiteMail> getWhiteList() {
        return whiteList;
    }

    public boolean isInWhiteList(String checkEmail){
        System.out.println(whiteList);
        for (WhiteMail whiteMail : whiteList) {
            System.out.println(whiteMail.getEmail());
        }
        return true;
    }

    public ArrayList<WhiteMail> refreshList() {
        whiteList = new ArrayList<WhiteMail>();
        if (main.getConnectionDB().isPresent()){
            try (Statement st = main.getConnectionDB().get().createStatement()){
                try (ResultSet rs = st.executeQuery("SELECT * FROM white_list")){
                    while (rs.next()) {
                        whiteList.add(new WhiteMail(rs.getInt(1), rs.getString(2)));
                        System.out.println(rs.getInt(1) + " " + rs.getString(2));
                    }
                return whiteList;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return whiteList;
    }
}
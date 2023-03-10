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

    public static ArrayList<WhiteMail> getWhiteList() {return whiteList;}

    public boolean isInWhiteListInsert(String checkEmail) {
        for (WhiteMail whiteMail : whiteList) {
            if (checkEmail.equals(whiteMail.getEmail())) {
                return true;
            }
        }
        return false;
    }

    public boolean isInWhiteListDelete(String checkEmail, int checkID) {
        for (WhiteMail whiteMail : whiteList) {
            if (checkEmail.equals(whiteMail.getEmail()) || checkID == whiteMail.getID()) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<WhiteMail> refreshList() {
        whiteList = new ArrayList<WhiteMail>();
        if (main.getConnectionDB().isPresent()){
            try (Statement st = main.getConnectionDB().get().createStatement()){
                try (ResultSet rs = st.executeQuery("SELECT * FROM white_list")){
                    while (rs.next()) {
                        whiteList.add(new WhiteMail(rs.getInt(1), rs.getString(2)));
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
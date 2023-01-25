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

    public boolean validUser(String checkMail, String checkPassword){
        if (main.getConnectionDB().isPresent()){
            try (PreparedStatement preparedStatement = main.getConnectionDB().get().prepareStatement("SELECT * FROM users WHERE email = ?")){
                preparedStatement.setString(1, checkMail);
                ResultSet rs = preparedStatement.executeQuery();
                boolean loginOk = false;
                if (rs.next()){
                    mail = rs.getString(4);
                    pwd = rs.getString(8);
                    if (Objects.equals(mail, checkMail) && Objects.equals(pwd, checkPassword)) {
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
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    return false;
    }
}
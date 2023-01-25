import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Users {
    private int ID;
    private String firstName;
    private String lastName;
    private String pseudo;
    private String mail;
    private String role;
    private String pwd;
    private int shopID;

    public void setMail(String mail) {
        this.mail = mail;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMail() {
        return mail;
    }

    public String getPwd() {
        return pwd;
    }

    public String getRole() {return role;}

    public boolean validUser(){
        if (main.getConnectionDB().isPresent()){
            try (PreparedStatement preparedStatement = main.getConnectionDB().get().prepareStatement("SELECT * FROM users WHERE email = ?")){
                preparedStatement.setString(1, mail);
                ResultSet rs = preparedStatement.executeQuery();
                boolean loginOk = false;
                if (rs.next()){
                    ID = rs.getInt(1);
                    firstName = rs.getString(2);
                    lastName = rs.getString(3);
                    mail = rs.getString(4);
                    role = rs.getString(5);
                    shopID = rs.getInt(6);
                    pseudo = rs.getString(7);
                    pwd = rs.getString(8);
                    System.out.println(role);
                    System.out.println(pwd);
                    loginOk = true;
                }

                if (loginOk){
                    return true;
                } else {
                    return false;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    return false;
    }
}
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
            try (PreparedStatement preparedStatement = main.getConnectionDB().get().prepareStatement("SELECT role, password FROM users WHERE email = ?")){
                preparedStatement.setString(1, mail);
                ResultSet rs = preparedStatement.executeQuery();
                boolean loginOk = rs.next();
                if (rs.next()){
                    role = rs.getString(1);
                    System.out.println(role);
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
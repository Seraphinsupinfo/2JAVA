import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.*;

public class main{
    //Nos variables pour pouvoir se connecter à la DB
    private static String URL = "jdbc:mysql://89.116.147.204:3306/u788104185_2JAVA?autoReconnect=true";
    private static String USERNAME = "u788104185_Dev";
    private static String PASSWORD = "Supinfo123??";
    private static Optional<Connection> connectionDB = null;
    private static Users actualUser;
    private static WhiteListsEmails whiteList;

    //Nos getters pour pouvoir les utiliser vu qu'ils sont en privés (pas besoin de setters)
    public static Optional<Connection> getConnectionDB() {return connectionDB;}
    public static Users getActualUser() {return actualUser;}
    public static WhiteListsEmails getWhiteList() {return whiteList;}


    public static void main(String[] args) throws SQLException {
        //Chargement des drivers et connection
        loadDriver();

        //Initialisation connection
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            connectionDB = Optional.ofNullable(DriverManager.getConnection(URL, USERNAME, PASSWORD));
            actualUser = new Users();
            whiteList = new WhiteListsEmails();
            Display.main();

        } catch (SQLException e) {
            Display.errorPopUp("Impossible d'établir une connexion  à la base de données.");
            e.printStackTrace();
        }
    }
    private static void loadDriver(){
        //Charge juste les drivers
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
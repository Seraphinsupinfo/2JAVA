import java.sql.*;
import java.util.*;

public class main{
    //Nos variables pour pouvoir se connecter à la DB
    private static String URL = "jdbc:mysql://54.37.31.19:3306/u788104185_2JAVA?autoReconnect=true";
    private static String USERNAME = "u788104185_Dev";
    private static String PASSWORD = "Supinfo123??";
    private static Optional<Connection> connectionDB = null;
    private static Users actualUser = new Users();
    private static Shops actualShop = new Shops();

    //Nos getters pour pouvoir les utiliser vu qu'ils sont en privés (pas besoin de setters)
    public static Optional<Connection> getConnectionDB() {return connectionDB;}
    public static Users getActualUser() {return actualUser;}

    public static Shops getActualShop() {return actualShop;}

    public static void main(String[] args) throws SQLException {
        //Chargement des drivers et connection
        loadDriver();


        //Initialisation connection
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            System.out.println("DB connection OK");
            connectionDB = Optional.ofNullable(DriverManager.getConnection(URL, USERNAME, PASSWORD));


        } catch (SQLException e) {
            System.out.println("DB connection not OK");
            e.printStackTrace();
        }
        Display.main();
    }
    private static void loadDriver(){
        //Charge juste les drivers
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers OK");
        } catch (Exception ex){
            System.err.println("Drivers not OK");
            ex.printStackTrace();
        }
    }
}
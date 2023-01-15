import java.sql.*;
import javax.swing.*;
import java.util.*;

public class main{
    //Nos variables pour pouvoir se connecter à la DB
    private static String URL = "jdbc:mysql://54.37.31.19:3306/u788104185_2JAVA";
    private static String USERNAME = "u788104185_Dev";
    private static String PASSWORD = "Supinfo123??";

    //Nos getters pour pouvoir les utiliser vu qu'ils sont en privés (pas besoin de setters)
    public static String getURL() {
        return URL;
    }

    public static String getUSERNAME() {
        return USERNAME;
    }

    public static String getPASSWORD() {
        return PASSWORD;
    }

    public static void main(String[] args) throws SQLException {
        //Chargement des drivers et connection
        loadDriver();
        Optional<Connection> connectionDB = null;

        //Initialisation connection
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            System.out.println("DB connection OK");
            connectionDB = Optional.ofNullable(DriverManager.getConnection(URL, USERNAME, PASSWORD));

            //Liste des adresses stockées dans la seule variable de la classe
            WhiteListsEmails whiteList = new WhiteListsEmails(connectionDB);

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
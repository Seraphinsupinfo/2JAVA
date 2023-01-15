import java.sql.*;
import javax.swing.*;
import java.util.*;

public class main{
    private static String URL = "jdbc:mysql://54.37.31.19:3306/u788104185_2JAVA";
    private static String USERNAME = "u788104185_Dev";
    private static String PASSWORD = "Supinfo123??";


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

        loadDriver();
        Optional<Connection> connectionDB = null;

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            System.out.println("DB connection OK");
            connectionDB = Optional.ofNullable(DriverManager.getConnection(URL, USERNAME, PASSWORD));


        JFrame frame = new JFrame("Istore");
        frame.setContentPane(new Login().panelMain);
        frame.setSize(1200,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


        WhiteListsEmails whiteList = new WhiteListsEmails(connectionDB);




        } catch (SQLException e) {
            System.out.println("DB connection not OK");
            e.printStackTrace();
        }
    }
    private static void loadDriver(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers OK");
        } catch (Exception ex){
            System.err.println("Drivers not OK");
            ex.printStackTrace();
        }
    }
}
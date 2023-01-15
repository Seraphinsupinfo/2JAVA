import java.sql.*;
import javax.swing.*;

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

    public static void main(String[] args) {

        loadDriver();
        connectDB(getURL(), getUSERNAME(), getPASSWORD());

        JFrame frame = new JFrame("Istore");
        frame.setContentPane(new Login().panelMain);
        frame.setSize(250,100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

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
    private static void connectDB(String URL, String USERNAME, String PASSWORD){
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            System.out.println("DB connection OK");
        } catch (SQLException e) {
            System.out.println("DB connection not OK");
            e.printStackTrace();
        }
    }
}
import java.sql.*;

public class main {
    public static void main(String[] args) {
        String URL = "jdbc:mysql://54.37.31.19:3306/u788104185_2JAVA";
        String USERNAME = "u788104185_Dev";
        String PASSWORD = "Supinfo123??";
        loadDriver();
        connectDB(URL, USERNAME, PASSWORD);

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
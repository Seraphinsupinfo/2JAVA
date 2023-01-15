import java.util.Scanner;
import java.sql.*;
import java.util.Optional;
public class test {
    private static final String URL = "jdbc:mysql://localhost:8889/supinfo_voyage";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) throws SQLException {
        loadDriver();
        Optional<Connection> connection = getConnection();
        if (connection.isPresent()){
            try (Statement stmt = connection.get().createStatement()) {
                try (ResultSet rs = stmt.executeQuery("SELECT * FROM place")) {
                    while (rs.next()) {
                        System.out.println(rs.getLong("pl_id"));
                        System.out.println(rs.getString("pl_name"));
                    }
                    home();
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private static Optional<Connection> getConnection(){
        try{
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return Optional.of(connection);
        } catch (SQLException ex){
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    private static void loadDriver(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception ex){
            System.err.println("Impossible de charger le Driver");
            ex.printStackTrace();
        }
    }

    public static void home(){
        Scanner input_number = new Scanner(System.in);
        Boolean boucle = true;
        while (boucle){
            System.out.println("1/Créer une destination");
            System.out.println("2/Créer un voyage");
            System.out.println("3/Rechercher une destination");
            System.out.println("4/Rechercher un voyage");
            System.out.println("5/Supprimer un voyage");
            System.out.println("6/Quiter");
            System.out.print("Entrer un nombre pour executer une action : ");
            int choice = input_number.nextInt();

            switch (choice){
                case 1:
                    CreatePlace();
                    break;
                case 2:
                    System.out.println("2");
                    break;
                case 3:
                    SearchPlace();
                    break;
                case 4:
                    System.out.println("4");
                    break;
                case 5:
                    DeletePlace();
                    break;
                case 6:
                    System.out.println("Quiter...");
                    boucle = false;
                    break;
            }
        }
    }
    public static void CreatePlace(){
        Scanner input = new Scanner(System.in);

        System.out.print("Entrer un nom de la nouvelle ville à ajouter : ");
        String city = input.nextLine();

    }

    public static void SearchPlace(){
        Scanner input = new Scanner(System.in);

        System.out.print("Entrer un nom de la ville à rechercher : ");
        String city = input.nextLine();

    }

    public static void DeletePlace(){
        Scanner input = new Scanner(System.in);

        System.out.print("Entrer un nom de la ville à supprimer : ");
        String city = input.nextLine();

    }

}

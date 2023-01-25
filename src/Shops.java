import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Shops {
    private int ID;
    private String name;
    private String location;
    ArrayList items = new ArrayList();

    public Shops() {
        this.items = getStock(ID);
    }



    public ArrayList getItems() {return items;}

    public ArrayList getStock(int shopID) {
        if (main.getConnectionDB().isPresent()) {
            try (PreparedStatement preparedStatement = main.getConnectionDB().get().prepareStatement("SELECT * FROM items WHERE shop = ?")) {
                preparedStatement.setInt(1, shopID);
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    items.add(new Items(rs.getInt(1), rs.getString(1), rs.getInt(3), rs.getInt(4)));
                    System.out.println(items);
                }
                return items;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    return items;
    }
}

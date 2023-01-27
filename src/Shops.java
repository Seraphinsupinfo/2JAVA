import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Shops {
    private int ID;
    private String name;
    private String location;
    ArrayList<Items> items;

    public ArrayList<Items> getItems() {
        return items;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getID() {
        return ID;
    }

    public Shops(int ID) {
        this.ID = ID;
        if (main.getConnectionDB().isPresent()) {
            try (PreparedStatement preparedStatement = main.getConnectionDB().get().prepareStatement("SELECT * FROM shops WHERE ID = ?")) {
                preparedStatement.setInt(1, ID);
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next())
                    this.name = rs.getString(2);
                    this.location = rs.getString(3);
                this.items = new ArrayList<>();
                items = getStock(ID);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public ArrayList<Items> getStock(int shopID) {
        if (main.getConnectionDB().isPresent()) {
            try (PreparedStatement preparedStatement = main.getConnectionDB().get().prepareStatement("SELECT * FROM items WHERE shop_id = ?")) {
                preparedStatement.setInt(1, shopID);
                ResultSet rs = preparedStatement.executeQuery();
                boolean boucle = true;
                while(rs.next()) {
                    items.add(new Items(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5)));
                }
                return items;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return items;
    }
}
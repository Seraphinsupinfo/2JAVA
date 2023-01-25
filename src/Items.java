import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class Items {
    private int ID;
    private String name;
    private int price;
    private int shopID;

    public Items(int ID, String name, int price, int shopID) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.shopID = shopID;
    }
}

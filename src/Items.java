import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class Items {
    private int ID;
    private String name;
    private int price;
    private int quantity;
    private int shopID;

    public Items(int ID, String name, int price, int quantity, int shopID) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.shopID = shopID;
    }

    public int getID() {return ID;}
    public String getName() {return name;}
    public int getPrice() {return price;}
    public int getQuantity() {return quantity;}
    public int getShopID() {return shopID;}
}

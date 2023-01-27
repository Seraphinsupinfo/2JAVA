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

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getShopID() {
        return shopID;
    }

    public static void actualiserItemQuantity(int inputID, int newQuantity) {
        if (main.getConnectionDB().isPresent()) {
            if (newQuantity >= 0) {
                try (PreparedStatement preparedStatement = main.getConnectionDB().get().prepareStatement("UPDATE items SET quantity = ? WHERE ID = ?")) {
                    preparedStatement.setInt(1, newQuantity);
                    preparedStatement.setInt(2, inputID);
                    preparedStatement.executeUpdate();
                    Display.errorPopUp("La mise à jour de la quantité a bien été prise en compte");
                } catch (SQLException e) {
                    Display.errorPopUp("Une erreur est survenue... Mise à jour impossible");
                    throw new RuntimeException(e);
                }
            } else {
                Display.errorPopUp("Veuillez entrer une quantité supérieur ou égale à 0");
            }
        }
    }
}

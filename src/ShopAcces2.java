import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShopAcces2 {
    JPanel panelMain;
    private JButton retourButton;
    private JTable table1;

    public ShopAcces2(int ID) {
        int shopID = ID;
        table1.setModel(new ModelDeTableStock(getAllItems(shopID)));

        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Display.shopAcces();
            }
        });
    }

    public ArrayList<Items> getAllItems(int shopID) {
        ArrayList<Items> items = new ArrayList<>();
        if (main.getConnectionDB().isPresent()) {
            try (PreparedStatement preparedStatement = main.getConnectionDB().get().prepareStatement("SELECT * FROM items WHERE shop_id = ?")) {
                preparedStatement.setInt(1, shopID);
                ResultSet rs = preparedStatement.executeQuery();
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
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShopAcces {
    JPanel panelMain;
    private JButton retourButton;
    private JComboBox<String> comboBox1;
    private JButton accéderButton;

    public ShopAcces() {
        ArrayList<Shops> shops = ShopManagement.getAllShops();
        for (Shops shop : shops) {
            String newShop = shop.getName() + " - " + shop.getLocation();
            comboBox1.addItem(newShop);
        }

        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Display.homeUser();
            }
        });

        accéderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Display.shopAcces2(getShopID());
            }
        });
    }

    public int getShopID(){
        String input = (String) comboBox1.getSelectedItem();
        String[] parts = input.split(" - ");
        String name = parts[0];
        String location = parts[1];
        if (main.getConnectionDB().isPresent()) {
            try (PreparedStatement preparedStatement = main.getConnectionDB().get().prepareStatement("SELECT ID FROM shops WHERE name = ? AND location = ?")) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, location);
                ResultSet rs = preparedStatement.executeQuery();
                if(rs.next()) {
                    return rs.getInt(1);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return 0;
    }
}

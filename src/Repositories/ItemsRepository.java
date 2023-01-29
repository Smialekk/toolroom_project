package Repositories;

import Database.Database;
import Models.ItemModel;
import Models.UserModel;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class ItemsRepository {

    public List<ItemModel> getAllItems() {
        List<ItemModel> items = new LinkedList<ItemModel>();

        try {
            ResultSet result = Database.getStatement().executeQuery("SELECT * FROM items");

            int id;
            String name, image;

            while(result.next()) {
                id = result.getInt("id");
                name = result.getString("name");
                image = result.getString("image");

                items.add(new ItemModel(id, name, image));
            }

            return items;
        } catch (Exception e) {
            return items;
        }
    }

    public List<ItemModel> getAllItemsByUser(UserModel user) {
        List<ItemModel> items = new LinkedList<ItemModel>();

        try {
            ResultSet result = Database.getStatement().executeQuery("SELECT i.* FROM items as i LEFT JOIN users_items as ui ON i.id = ui.item_id WHERE ui.user_id = " + user.getId());

            int id;
            String name, image;

            while(result.next()) {
                id = result.getInt("id");
                name = result.getString("name");
                image = result.getString("image");

                items.add(new ItemModel(id, name, image));
            }

            return items;
        } catch (Exception e) {
            return items;
        }
    }
}

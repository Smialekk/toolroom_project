package Repositories;

import Database.Database;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemsRepositoryTest {

    @Test
    void getAllItems() {
        Database.connect();

        ItemsRepository itemsRepository = new ItemsRepository();
        var items = itemsRepository.getAllItems();

        assertEquals(32, items.size());
    }

    @Test
    void getAllItemsByUser() {
        Database.connect();

        ItemsRepository itemsRepository = new ItemsRepository();
        var items = itemsRepository.getAllItemsByUser(null);

        assertEquals(0, items.size());
    }

}
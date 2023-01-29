package Repositories;

import Database.Database;
import Models.ItemModel;
import Models.UserModel;

import static org.junit.jupiter.api.Assertions.*;

class UsersRepositoryTest {

    @org.junit.jupiter.api.Test
    void getAllUsers() {
        Database.connect();

        UsersRepository usersRepository = new UsersRepository();
        var users = usersRepository.getAllUsers();

        assertEquals(10, users.size());
    }

    @org.junit.jupiter.api.Test
    void createUser() {
        Database.connect();

        UsersRepository usersRepository = new UsersRepository();
        var user = usersRepository.createUser(new UserModel(91,"Janusz","Kowal","operator","9119","qqq","user"));

        assertEquals(false,user);
    }

    @org.junit.jupiter.api.Test
    void editUser() {
        Database.connect();

        UsersRepository usersRepository = new UsersRepository();
        var user = usersRepository.editUser(null);

        assertEquals(false,user);

    }

    @org.junit.jupiter.api.Test
    //DirtiesContext
    void deleteUserById() {
        Database.connect();

        UsersRepository usersRepository = new UsersRepository();
        usersRepository.deleteUserById(90);

        assertNotNull(90);
    }

    @org.junit.jupiter.api.Test
    void login() {
        Database.connect();

        UsersRepository usersRepository = new UsersRepository();
        var user = usersRepository.login("kapek", "qqq");

        assertNotNull(user);
    }

    @org.junit.jupiter.api.Test
    void selectItem() {
        Database.connect();

        UsersRepository usersRepository = new UsersRepository();
        var userItem = usersRepository.selectItem(new UserModel(null,"Janusz","Kowal","operator","9119","qqq","user"),
                new ItemModel(3,"Piła","saw.jpg"));


        assertEquals(false,userItem);
    }

    @org.junit.jupiter.api.Test
    void unselectItem() {
        Database.connect();

        UsersRepository usersRepository = new UsersRepository();
        var userItem = usersRepository.unselectItem(new UserModel(null,"Janusz","Kowal","operator","9119","qqq","user"),
                new ItemModel(1,"Młotek","hammer.jpg"));


        assertEquals(false,userItem);
    }
}
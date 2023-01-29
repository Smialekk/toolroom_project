package Repositories;

import Database.Database;
import Models.ItemModel;
import Models.UserModel;

import javax.xml.crypto.Data;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UsersRepository {
    /**
     * Pobranie wszystkich użytkowników z bazy danych w formie listy obiektów
     * Robimy zapytanie do tabeli users która pobiera nam wszystkie rekordy z tabelki i mapuje na liste obiektów
     * @return List<UserModel>
     */
    public List<UserModel> getAllUsers() {
        List<UserModel> users = new LinkedList<UserModel>();

        try {
            ResultSet result = Database.getStatement().executeQuery("SELECT * FROM users");

            int id;
            String name, surname, position, login, password, role;

            while(result.next()) {
                id = result.getInt("id");
                name = result.getString("name");
                surname = result.getString("surname");
                position = result.getString("position");
                login = result.getString("login");
                password = result.getString("password");
                role = result.getString("role");

                users.add(new UserModel(id, name, surname, position, login, password, role));
            }

            return users;
        } catch (Exception e) {
            return users;
        }
    }

    /**
     * Dodajemy nowego uzytkownika
     * @param user
     */
    public boolean createUser(UserModel user) {
        try {
            PreparedStatement prepStmt = Database.getConnection().prepareStatement(
                    "INSERT into users values (NULL, ?, ?, ?, ?, ?, ?);");
            prepStmt.setString(1, user.getName()); // Bo przeslalismy obiekt uzytkownika (1 elem to name)
            prepStmt.setString(2, user.getSurname());
            prepStmt.setString(3, user.getPosition());
            prepStmt.setString(4, user.getLogin());
            prepStmt.setString(5, user.getPassword());
            prepStmt.setString(6, user.getRole());
            prepStmt.execute();

            return true;
        } catch(Exception e) {
            System.err.println("Error while create user.");
            e.printStackTrace();

            return false;
        }
    }

    public boolean editUser(UserModel user) {
        try {
            PreparedStatement prepStmt = Database.getConnection().prepareStatement(
                    "UPDATE users SET name = ?, surname = ?, position = ?, login = ?, password = ?, role = ? WHERE id = ?");
            prepStmt.setString(1, user.getName());
            prepStmt.setString(2, user.getSurname());
            prepStmt.setString(3, user.getPosition());
            prepStmt.setString(4, user.getLogin());
            prepStmt.setString(5, user.getPassword());
            prepStmt.setString(6, user.getRole());
            prepStmt.setInt(7, user.getId());
            prepStmt.execute();

            return true;
        } catch(Exception e) {
            System.err.println("Error while edit user.");
            e.printStackTrace();

            return false;
        }
    }

    /**
     * Usuwamy uzytkownika po id
     * @param id
     */
    public void deleteUserById(int id) {
        try {
            Database.getStatement().execute("DELETE from users WHERE id =" + id);
        }catch(Exception e) {
            System.err.println("Error while delete user by id.");
            e.printStackTrace();
        }
    }

    /**
     * Metoda loguje uzytkownika, pobiera jego dane na podstawie loginu i hasla
     * @param login
     * @param password
     * @return
     */
    public UserModel login(String login, String password) {
        try {
            ResultSet result = Database.getStatement().executeQuery("SELECT * FROM users WHERE login = '" + login + "' AND password = '" + password + "'");

            int id;
            String name, surname, position, role;

            id = result.getInt("id");
            name = result.getString("name");
            surname = result.getString("surname");
            position = result.getString("position");
            login = result.getString("login");
            role = result.getString("role");

            return new UserModel(id, name, surname, position, login, password, role);
        }catch(Exception e) {
            System.err.println("Error while login.");
            e.printStackTrace();

            return null;
        }
    }

    public boolean selectItem(UserModel user, ItemModel item) {
        try {
            PreparedStatement prepStmt = Database.getConnection().prepareStatement(
                    "INSERT into users_items values (NULL, ?, ?);");
            prepStmt.setInt(1, user.getId());
            prepStmt.setInt(2, item.getId());
            prepStmt.execute();

            return true;
        } catch(Exception e) {
            System.err.println("Error while select item.");
            e.printStackTrace();

            return false;
        }
    }

    public boolean unselectItem(UserModel user, ItemModel item) {
        try {
            PreparedStatement prepStmt = Database.getConnection().prepareStatement(
                    "DELETE from users_items WHERE user_id = ? AND item_id = ?");
            prepStmt.setInt(1, user.getId());
            prepStmt.setInt(2, item.getId());
            prepStmt.execute();

            return true;
        }catch(Exception e) {
            System.err.println("Error while unselect item.");
            e.printStackTrace();

            return false;
        }
    }
}

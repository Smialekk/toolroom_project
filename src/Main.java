import Database.Database;
import Panels.LoginPanel;

import java.sql.*;

public class Main {
    public static final String DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL = "jdbc:sqlite:baza.db";
    private static Connection conn;
    private static Statement stat;
    public static void main(String[] args) {
        /**
         * Połaczenie z bazą danych
         */
        Database.connect();


        LoginPanel loginPanel = new LoginPanel();
        loginPanel.setVisible(true);
    }
}


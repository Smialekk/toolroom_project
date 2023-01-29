package Database;

import java.sql.*;

public class Database {
    private static final String DRIVER = "org.sqlite.JDBC";
    private static final String DB_URL = "jdbc:sqlite:database.db";
    private  static Connection connection;

    public static Connection getConnection() {
        return connection;
    }

    public static Statement getStatement() {
        return statement;
    }

    private static Statement statement;
    public static void connect() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC not found.");
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(DB_URL);
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.err.println("Error while connect to database");
            e.printStackTrace();
        }
    }


}

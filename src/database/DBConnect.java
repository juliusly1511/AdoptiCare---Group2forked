package database;

import java.sql.*;

public class DBConnect {

    private static final String URL = "jdbc:mysql://localhost:3306/adopticaredb";

    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection()
            throws SQLException {

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }
}

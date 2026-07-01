package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DbConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/adopticaredb";

    private static final String USER = "root";
    private static final String PASSWORD = "";
    
    public static Connection getConnection()
            throws SQLException {

        Connection con = DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );

        return con;
    }
}

package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection getMySQLConnection() {
        String userName = "root";
        String password = "aLINOID07";
        String connectionURL = "jdbc:mysql://localhost:3306/user";
        try {
            return DriverManager.getConnection(connectionURL, userName,
                    password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

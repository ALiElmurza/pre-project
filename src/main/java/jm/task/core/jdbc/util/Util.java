package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    // реализуйте настройку соеденения с БД

    public static Connection getMySQLConnection()
            throws ClassNotFoundException, SQLException {
        String hostName = "localhost";
        String dbName = "user";
        String userName = "root";
        String password = "aLINOID07";
        return getMySQLConnection(hostName, dbName, userName, password);
    }

    public  static Connection getSQLConnection()
            throws SQLException {
        String hostName = "localhost";
        String userName = "root";
        String password = "aLINOID07";
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/";
        Connection conn = DriverManager.getConnection(connectionURL, userName,
                password);
        return conn;
    }

    public static Connection getMySQLConnection(String hostName, String dbName,
                                                String userName, String password) throws SQLException,
            ClassNotFoundException {
        //Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

        Class.forName("com.mysql.cj.jdbc.Driver");

        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

        Connection conn = DriverManager.getConnection(connectionURL, userName,
                password);
        return conn;
    }



}
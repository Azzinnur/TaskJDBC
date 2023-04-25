package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest?useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Root1234";
    private static Connection connection;

    public static Connection getConnection() {

        if (connection == null) {
            try {
                Driver driver = new com.mysql.cj.jdbc.Driver();
                DriverManager.registerDriver(driver);
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                System.out.println("Ошибка соединения");
            }
        }
        return connection;
    }
    public static void close() {
        try{
            if (!connection.isClosed()) {connection.close();}
        } catch(SQLException e) {
            System.out.println("Cannot close connection");
        }
    }
}


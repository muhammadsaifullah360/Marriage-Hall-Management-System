package database;

import java.sql.*;

public class DBService {
    private static Connection connection;
    public static Statement statement;
    
    private DBService() {
    }
    
    public static void createConnection() {
        if (connection == null) {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                String username = "Saifullah";
                String url = "jdbc:oracle:thin:@localhost:1521:xe";
                String password = "hr";
                Connection connection = DriverManager.getConnection(url, username, password);
                statement = connection.createStatement();
            } catch (SQLException | ClassNotFoundException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
    
    public static ResultSet executeQuery(String query) {
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return resultSet;
    }
}
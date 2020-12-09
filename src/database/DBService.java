package database;

import java.sql.*;

public class DBService {
    public static Statement statement;
    
    public DBService() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String username = "Saifullah";
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String password = "hr";
            Connection connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
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
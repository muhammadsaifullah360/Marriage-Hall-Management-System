package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBService {
    public static Statement statement;
    
    public DBService() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            String username = "HR";
            String url = "jdbc:oracle:thin:@localhost:1521:xe";
            String password = "mypassword";
            Connection connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
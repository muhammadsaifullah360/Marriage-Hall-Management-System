package database;

import org.h2.tools.Server;

import java.sql.*;

public class DBService {
    private static Connection connection;
    public static Statement statement;
    
    private DBService() {
    }
    
    public static void createConnection() {
        String port = "9092";
        startTCPServer(port);
        try {
            Class.forName("org.h2.Driver");
            String url = String.format("jdbc:h2:tcp://localhost:%s/./HMS", port);
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    private static void startTCPServer(String port) {
        try {
            String[] params = {"-tcpPort", port, "-ifExists", "-baseDir", "./src/database/h2/"};
            Server server = Server.createTcpServer(params).start();
            System.out.println(server.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
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
    
    public static int executeUpdate(String query) {
        int rowsUpdated = 0;
        try {
            rowsUpdated = statement.executeUpdate(query);
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return rowsUpdated;
    }
    
    public static void close() throws SQLException {
        if (!connection.isClosed())
            connection.close();
    }
}
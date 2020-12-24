package main;

import database.DBService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//import javax.mail.MessagingException;

public class Main extends Application {
    
    public static void main(String[] args)  {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        DBService.createConnection();
        Parent root = FXMLLoader.load(getClass().getResource("/dashboard/Dashboard.fxml"));
        primaryStage.setTitle("Marriage Hall Management System.");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}

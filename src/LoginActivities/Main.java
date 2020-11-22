package LoginActivities;

import Database.DBService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
         new DBService();
        Parent root = FXMLLoader.load(getClass().getResource("/Dashboard/Dashboard.fxml"));
        primaryStage.setTitle("Hall Management System.");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

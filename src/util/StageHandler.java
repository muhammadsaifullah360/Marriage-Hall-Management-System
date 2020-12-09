package util;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StageHandler {
    static public Stage createStage(String fxmlPath, String title) {
        Parent root = null;
        try {
            root = FXMLLoader.load(StageHandler.class.getResource("/login/ForgetPassword.fxml"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("FXML Loading Error!!!");
        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
        return stage;
    }
    
    public static Stage getStage(ActionEvent event) {
        return (Stage) ((Node) event.getSource()).getScene().getWindow();
    }
}

package dashboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;


public class DashboardController {
    
    
    @FXML
    private BorderPane MainBorderPane;
    
    public void onclickDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/dashboard/Screens/Dash.fxml"));
        MainBorderPane.setCenter(root);
    }
    
    public void onclickBooking(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/dashboard/Screens/Booking.fxml"));
        MainBorderPane.setCenter(root);
    }
    
    public void onclickMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/dashboard/Screens/Menu.fxml"));
        MainBorderPane.setCenter(root);
    }
    
    public void onclickEmployee(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/dashboard/Screens/Employee.fxml"));
//        scrolpanemain.setContent(root);
        MainBorderPane.setCenter(root);
    }
    
    public void onclickBilling(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/dashboard/Screens/Billing.fxml"));
        MainBorderPane.setCenter(root);
    }
    
    public void onclickLogout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/login/activities/LoginPage.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    public void onClickSetting(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dashboard/Screens/setting.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Settings");
        stage.setScene(new Scene(root1));
        stage.show();
    }
    
}
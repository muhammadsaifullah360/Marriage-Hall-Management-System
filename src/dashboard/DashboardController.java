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
    private BorderPane mainBorderPane;
    
    
    
    public void onclickDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/dashboard/screens/Dash.fxml"));
        mainBorderPane.setCenter(root);
    }
    
    public void onclickBooking(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/dashboard/screens/Booking.fxml"));
        mainBorderPane.setCenter(root);
    }
    
    public void onclickMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/dashboard/screens/Menu.fxml"));
        mainBorderPane.setCenter(root);
    }
    
    public void onclickEmployee(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/dashboard/screens/Employee.fxml"));
//        scrolpanemain.setContent(root);
        mainBorderPane.setCenter(root);
    }
    
    public void onclickBilling(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/dashboard/screens/Billing.fxml"));
        mainBorderPane.setCenter(root);
    }
    
    public void onclickLogout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/login/LoginPage.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    public void onClickSetting(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/login/Setting.fxml"));
        Parent root1 = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Settings");
        stage.setScene(new Scene(root1));
        stage.show();
    }
    
    public void loadCustomerPane() throws IOException {
        Parent root = FXMLLoader.load(DashboardController.class.getResource("/dashboard/screens/Customer.fxml"));
        mainBorderPane.setCenter(root);

    }
    
}
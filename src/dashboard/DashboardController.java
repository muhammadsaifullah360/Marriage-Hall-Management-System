package dashboard;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import util.StageHandler;

import java.io.IOException;


public class DashboardController {
    @FXML
    private JFXButton logout;
    @FXML
    private BorderPane mainBorderPane;
    
    public void loadDashboard() throws IOException {
        setCenterPane("/dashboard/screens/Dash.fxml");
    }
    
    private void setCenterPane(String path) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(path));
        mainBorderPane.setCenter(root);
    }
    
    public void loadBooking(ActionEvent event) throws IOException {
        
        setCenterPane("/dashboard/screens/Booking.fxml");
    }
    
    public void loadMenu(ActionEvent event) throws IOException {
        setCenterPane("/dashboard/screens/Menu.fxml");
    }
    
    public void onclickEmployee(ActionEvent event) throws IOException {
        setCenterPane("/dashboard/employee/Employee.fxml");
    }
    
    public void loadBilling(ActionEvent event) throws IOException {
        setCenterPane("/dashboard/screens/Billing.fxml");
    }
    
    public void logout(ActionEvent event) {
        Stage stage = (Stage) logout.getScene().getWindow();
        stage.close();
        StageHandler.createStage("Logout", "/login/LoginPage.fxml");
    }
    
    public void loadSettings(ActionEvent event) {
        StageHandler.createStage("Settings", "/login/Setting.fxml");
    }
    
    public void onClickStock(ActionEvent actionEvent) throws IOException {
        setCenterPane("/dashboard/screens/Stock.fxml");
    }
}
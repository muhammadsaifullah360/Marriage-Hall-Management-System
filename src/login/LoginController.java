package login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import database.DBService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import util.StageHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private JFXButton forgetPasswordBtn;
    
    @FXML
    private JFXButton loginButton;
    @FXML
    private JFXTextField username_field;
    @FXML
    private JFXPasswordField password_field;
    @FXML
    private Label loginMessage_label;
    
    @FXML
    private void login() {
        String username = username_field.getText();
        String password = password_field.getText();
        if (username.isBlank() || password.isBlank())
            loginMessage_label.setText("Please Enter Username and Password!");
        else if (exists(username, password)) {
    
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.close();
            String fxmlPath = "/dashboard/Dashboard.fxml";
            String title = "Dashboard Of Hall Management System";
            StageHandler.createStage(title, fxmlPath).show();
            
        } else loginMessage_label.setText("Username or Password Incorrect!");
    }
    
    
    private boolean exists(String username, String password) {
        String query = String.format("Select * From SignUp Where username='%s' AND password='%s'", username, password);
        ResultSet rs = DBService.executeQuery(query);
        try {
            return rs.next();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return false;
    }
    
    @FXML
    private void forgetPassword() {
        Stage stage = (Stage) forgetPasswordBtn.getScene().getWindow();
        stage.close();
        String fxmlPath = "/login/ForgetPassword.fxml";
        String title = "Forget Password";
        StageHandler.createStage(title, fxmlPath).show();
    }
}

package main;

import database.DBService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    
    @FXML
    private JFXButton forgetpasswordbtn;
    
    @FXML
    private JFXTextField username_txt;
    
    @FXML
    private JFXPasswordField password_txt;
    
    @FXML
    private Label loginMessage;
    
    @FXML
    public void loginButton_txt(ActionEvent event) throws IOException {
        if (username_txt.getText().isBlank() && password_txt.getText().isBlank()) {
            loginMessage.setText("Please enter the username and password!");
        } else if (validateLogin()) {
            Parent root = FXMLLoader.load(getClass().getResource("/dashboard/Dashboard.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Dashboard Of Hall Management System");
            stage.show();
        }
    }
    
    public boolean validateLogin() {
        boolean ok = false;
        try {
            String query = String.format("select * from signup where username = '%s' AND password = '%s'", username_txt.getText(), password_txt.getText());
            ResultSet rs = DBService.statement.executeQuery(query);
            if (rs.next())
                ok = true;
            else
                ok = false;
            loginMessage.setText("Wrong Username and Password! ");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ok;
    }
    
    
    public void forgetpasswordbtn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/forget/password/ForgetPassword.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Forget Password");
        stage.setScene(scene);
        stage.show();
    }
}

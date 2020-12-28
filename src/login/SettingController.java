package login;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import database.DBService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingController {
    
    @FXML
    private JFXTextField c_username_txt;
    
    @FXML
    private Label MessageLabelOfChangePassword;
    
    @FXML
    private Label confirmPassL;
    
    @FXML
    private JFXPasswordField c_old_pas;
    
    @FXML
    private JFXPasswordField confirmPassword_t;
    
    @FXML
    private JFXPasswordField confirmPassword_t1;
    
    @FXML
    private JFXTextField name_txt;
    
    @FXML
    private JFXPasswordField password_txt;
    
    @FXML
    private JFXPasswordField password_txt1;
    
    @FXML
    private JFXTextField username_txt;
    @FXML
    private JFXTextField email_txt;
    
    @FXML
    private JFXTextField phone_txt;
    
    @FXML
    private Label confirmPassLabel;
    
    @FXML
    private Label MessageLabelOfSignup;
    
    public void onclickSignup(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/login/SignUp.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Create New Account");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    
    public void onClickSignup(ActionEvent event) {
        if (password_txt.getText().equals(password_txt1.getText())) {
            registerUser();
            confirmPassLabel.setText("Password Matched!");
        } else
            confirmPassLabel.setText("Password does not Match! ");
    }
    
    public void registerUser() {
        
        
        String query = String.format("INSERT  INTO signup (Name,username,password,email,phone_No ,date_time)VALUES('%s','%s','%s','%s','%s',to_date( sysdate,'yyyy-mm-dd'))",
                name_txt.getText(),
                username_txt.getText(),
                password_txt.getText(),
                email_txt.getText(),
                phone_txt.getText()
        );
        
        DBService.executeUpdate(query);
        MessageLabelOfSignup.setText("You have been registered!");
        clearFields();
    }
    
    public void clearFields() {
        
        name_txt.clear();
        password_txt.clear();
        password_txt1.clear();
        username_txt.clear();
        email_txt.clear();
        phone_txt.clear();
        confirmPassLabel.setText(null);
        confirmPassword_t.setText(null);
        c_username_txt.setText(null);
        c_old_pas.setText(null);
    }
    
    public void closeSignup(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/login/Setting.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    public void ChangePasswordBtn(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/login/ChangePassword.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Create New Account");
        stage.setScene(scene);
        stage.show();
    }
    
    public void changePassword(ActionEvent event) {
        if (confirmPassword_t.getText().equals(confirmPassword_t1.getText())) {
            changePass();
            confirmPassL.setText("Password Matched!");
        } else
            confirmPassL.setText("Password does not Match! ");
    }
    
    public void changePass() {
        String query = String.format("update  signup set password = '%s' where username ='%s'And password = '%s' ",
                confirmPassword_t.getText(), c_username_txt.getText(), c_old_pas.getText());
        
        DBService.executeUpdate(query);
        MessageLabelOfChangePassword.setText("Password Changed!");
        clearFields();
    }
    
    public void BackToTools(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/login/Setting.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}

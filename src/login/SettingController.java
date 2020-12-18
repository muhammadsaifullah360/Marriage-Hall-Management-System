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
    private JFXPasswordField c_cnfrm_password_t;
    
    @FXML
    private JFXPasswordField c_cnfrm_pas_t1;
    
    @FXML
    private JFXTextField lastname_txt;
    
    @FXML
    private JFXPasswordField password_txt;
    
    @FXML
    private JFXPasswordField password_txt1;
    
    @FXML
    private JFXTextField username_txt;
    
    @FXML
    private JFXTextField firstname_txt;
    
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
    
        String firstName = firstname_txt.getText();
        String lastName = lastname_txt.getText();
        String username = username_txt.getText();
        String password = password_txt.getText();
        String email = email_txt.getText();
        String phoneNo = phone_txt.getText();
    
        String query = "INSERT  INTO signup (firstName,lastName,username,password,email,phoneNo)VALUES('" + firstName + "','" + lastName + "','" + username + "','" + password + "','" + email + "','" + phoneNo + "')";
    
        DBService.executeUpdate(query);
        MessageLabelOfSignup.setText("You have been registered!");
        clearFields();
    }
    
    public void clearFields() {
        
        lastname_txt.clear();
        password_txt.clear();
        password_txt1.clear();
        username_txt.clear();
        firstname_txt.clear();
        email_txt.clear();
        phone_txt.clear();
        confirmPassLabel.setText(null);
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
        if (c_cnfrm_password_t.getText().equals(c_cnfrm_pas_t1.getText())) {
            changePass();
            confirmPassL.setText("Password Matched!");
        } else
            confirmPassL.setText("Password does not Match! ");
    }
    
    public void changePass() {
        String query = String.format("update  signup set password = '%s' where username ='%s'And password = '%s' ",
                c_cnfrm_password_t.getText(), c_username_txt.getText(), c_old_pas.getText());
    
        DBService.executeUpdate(query);
        MessageLabelOfChangePassword.setText("Password Changed!");
        clearChangepassFields();
    }
    
    public void clearChangepassFields() {
        c_cnfrm_password_t.setText(null);
        c_username_txt.setText(null);
        c_old_pas.setText(null);
    }
    
    public void BackToTools(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/login/Setting.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}

package login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import database.DBService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Random;


public class ForgetPasswordController {
    
    public static String code;
    @FXML
    private JFXTextField usernameTxt;
    @FXML
    private JFXButton otpPageBack;
    @FXML
    private JFXTextField emailTxt;
    @FXML
    private JFXTextField otpTxt;
    @FXML
    private JFXTextField passwordTxt;
    
    public void backToLoginPage(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/login/LoginPage.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    public void onOk(ActionEvent actionEvent) throws Exception {
        if (usernameTxt.getText().isBlank() || emailTxt.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning!");
            alert.setHeaderText("Error");
            alert.setContentText("Please Enter Your Email And Username");
            alert.showAndWait();
        } else {
            String query = String.format("select * from signup where username = '%s' AND Email = '%s' ",
                    usernameTxt.getText(),
                    emailTxt.getText()
            );
            ResultSet rs = DBService.statement.executeQuery(query);
            while (rs.next()){
                
                System.out.println(rs.getString("Email"));
            }
            sendMail("importan0987@gmail.com");
            
        }
    }
    
    public static void sendMail(String recipient) throws Exception {
        
        System.out.println("Checking Network Connectivity...");
        
        Properties properties = new Properties();
        
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "587");///Some smtp ports are 25,465,587
        
        
        String myEmail = "importan0987@gmail.com";
        String myPass = "importan365365";
        
        System.out.println("-----Network Connection is Ok-----");
        System.out.println("Preparing to send Mail...");
        
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmail, myPass);
            }
        });
        
        Message message = prepareMessage(session, myEmail, recipient);
        assert message != null;
        Transport.send(message);
        
        System.out.println("Mail Successfully Sent...");
    }
    
    private static Message prepareMessage(Session session, String myEmail, String recipient) {
        Message message = null;
        try {
            
            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject("Verification OTP Code");
            code = String.valueOf(OTP());
            String htmlCode = "<h1> Your OTP Verification code: </h1> <br/> <h2><b>Next Line </b></h2>";
            message.setContent(htmlCode, "text/html");
            message.setText(code);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Done");
            alert.setHeaderText("OTP Sent On Your Mail");
            alert.setContentText("Check Your Email And Verify It To Show Your Password!");
            alert.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }
    
    static char[] OTP() {
        System.out.println("Generating OTP....");
        int length = 6;
        
        String numbers = "0123456789abcdefg";
        
        Random random = new Random();
        char[] otp = new char[length];
        for (int i = 0; i < length; i++) {
            otp[i] = numbers.charAt(random.nextInt(numbers.length()));
        }
        return otp;
    }
    
    public void onVerify(ActionEvent actionEvent) throws Exception {
        System.out.println(code);
        
        if (code.equals(otpTxt.getText())) {
            System.out.println("DOne");
            String query = String.format("select * from signup where username = '%s' AND Email = '%s' ",
                    usernameTxt.getText(),
                    emailTxt.getText()
            );
            ResultSet rs = DBService.statement.executeQuery(query);
            if (rs.next()) {
                passwordTxt.setText(rs.getString("password"));
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Warning!");
                alert.setHeaderText("Error");
                alert.setContentText("The Email You Enter Is Wrong.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning!");
            alert.setHeaderText("Error");
            alert.setContentText("PLEASE ENTER A CORRECT OTP Code");
            alert.showAndWait();
        }
    }
}

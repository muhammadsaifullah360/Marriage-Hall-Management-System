package dashboard.screens;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import util.StageHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BookingController implements Initializable {
    
    @FXML
    private BorderPane pane;
    @FXML
    private JFXTextField Id;
    @FXML
    private JFXTextField durationField;
    @FXML
    private JFXComboBox<String> hallNo;
    @FXML
    private JFXTimePicker eventStartTime;
    @FXML
    private JFXTimePicker eventEndTime;
    @FXML
    private JFXDatePicker eventDate;
    @FXML
    private JFXTextField noOfPersons;
    @FXML
    private Label MessageLabelOfEventSaved;
    @FXML
    private JFXComboBox<String> eventType;
    
    ///////////////Customer Detail////
    
    @FXML
    private JFXTextField nameOfCustomer;
    @FXML
    private JFXTextField phoneOfCustomer;
    @FXML
    private JFXTextField emailOfCustomer;
    @FXML
    private JFXTextField customerAddress;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList("Wedding", "Seminar", "Party", "Other", "Mehfill");
        eventType.setItems(list);
        
        ObservableList<String> list1 = FXCollections.observableArrayList("Hall 1 (MaxCap 500)", "Hall 2 (MaxCap 300)", "Hall 3 (MaxCap 700)");
        hallNo.setItems(list1);
    }
    
    public void onCheck(ActionEvent actionEvent) {
        String fxmlPath = "/dashboard/screens/BookingSearch.fxml";
        String title = "Booking Availability";
        StageHandler.createStage(fxmlPath, title);
    }
    
    public void onNext(ActionEvent actionEvent) throws IOException {
        bookingDetail();
        nextFxml();
    }
    
    public void bookingDetail() {
        
        /// todo  implementation if empty query not run
//        try {
//
//            String query = String.format("Insert Into Event_Booking( EventType, EventStartTime,EventDate ,NoOfPerson, NameOfCustomer, PhoneNoOfCustomer, EmailOfCustomer,EventEndingTime, HallNo, INVOICENO) Values ('%s', to_date('2020-12-12 %s',  'yyyy-mm-dd hh24:mi:ss'), to_date('%s','yyyy-mm-dd'), '%s', '%s', '%s', '%s',to_date('2020-12-12 %s',  'yyyy-mm-dd hh24:mi:ss'), '%s', %d )",
//                    event_type_box.getValue(),
//                    event_time.getValue(),
//                    event_date.getValue(),
//                    no_of_persons.getText(),
//                    name_of_customer.getText(),
//                    phone_no_of_customer.getText(),
//                    email_of_customer.getText(),
//                    event_ending_time.getValue(),
//                    hall_no.getValue(),
//                    Integer.parseInt(invoiceNoTxT.getText()));
//
//            DBService.statement.executeUpdate(query);
//            MessageLabelOfEventSaved.setText("Saved!");
//        clearFields();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
    }
    
    private void nextFxml() throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("/dashboard/screens/Customer.fxml"));
//        pane.setCenter(root);
        String fxmlPath = "/dashboard/screens/Customer.fxml";
        String title = "Booking Availability";
        StageHandler.createStage(fxmlPath, title);
    }
    
    public void clearFields() {
        
        hallNo.setValue(null);
        eventType.setValue(null);
        eventStartTime.setValue(null);
        eventDate.setValue(null);
        eventEndTime.setValue(null);
        noOfPersons.clear();
//        nameOfCustomer.clear();
        phoneOfCustomer.clear();
        emailOfCustomer.clear();
        customerAddress.setText(null);
    }
    
    public void onCheck(ActionEvent event) {
    }
    
    public void onNext(ActionEvent event) {
    
    }
}

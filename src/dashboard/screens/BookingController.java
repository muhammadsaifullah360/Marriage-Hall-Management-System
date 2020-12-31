package dashboard.screens;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import database.DBService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import util.StageHandler;

import java.sql.SQLException;

public class BookingController {
    
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
    private JFXTextField idOfCustomer;
    @FXML
    private JFXTextField nameOfCustomer;
    @FXML
    private JFXTextField phoneOfCustomer;
    @FXML
    private JFXTextField emailOfCustomer;
    @FXML
    private JFXTextField customerAddress;
    
    
    public void initialize() {
        ObservableList<String> list = FXCollections.observableArrayList("Wedding", "Seminar", "Party", "Other", "Mehfill");
        eventType.setItems(list);
        
        ObservableList<String> list1 = FXCollections.observableArrayList("Hall 1 (MaxCap 500)", "Hall 2 (MaxCap 300)", "Hall 3 (MaxCap 700)");
        hallNo.setItems(list1);
    }
    
    public void onCheck(ActionEvent actionEvent) {
        String fxmlPath = "/dashboard/screens/BookingSearch.fxml";
        String title = "Booking Availability";
        StageHandler.createStage(title, fxmlPath);
    }
    
    public void save(ActionEvent actionEvent) {
//        int nextID = DBService.getIntResult("Select MAX(ID)+1 From booking");
//        Id.setEditable(false);
//        Id.setText("" + nextID);
        try {
            String bookingDetail = String.format("Insert Into Booking( Id,Type,Booking_Date ,Start_Time,End_Time,Duration,person_count,location,Date_time) Values (%d,'%s', ParseDateTime('%s',  'yyyy-mm-dd'), ParseDateTime('%s','hh24:mi:ss'),ParseDateTime('%s','hh24:mi:ss'), %d, %d, '%s',ParseDateTime(sysdate,  'yyyy-mm-dd')",
                    
                    Integer.parseInt(Id.getText()),
                    eventType.getValue(),
                    eventDate.getValue(),
                    eventStartTime.getValue(),
                    eventEndTime.getValue(),
                    Integer.parseInt(durationField.getText()),
                    Integer.parseInt(noOfPersons.getText()),
                    hallNo.getValue());
            
            String customerDetail = String.format("Insert into Customer (Id , Name , Phone_Number, Email, Address , Date_Time) Values( %d , '%s','%s','%s','%s',to_char(sysdate , 'yyyy-mm-dd')",
                    Integer.parseInt(Id.getText()),
                    nameOfCustomer.getText(),
                    phoneOfCustomer.getText(),
                    emailOfCustomer.getText(),
                    customerAddress.getText());
            String q = String.format("insert into team(id , task_type)values(%d , '%s')", Integer.parseInt(Id.getText()), nameOfCustomer.getText());
            
            DBService.statement.executeUpdate(q);
            DBService.statement.executeUpdate(bookingDetail);
            DBService.statement.executeUpdate(customerDetail);
            
            MessageLabelOfEventSaved.setText("Saved!");
            clearFields();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void clearFields() {
        
        hallNo.setValue(null);
        eventType.setValue(null);
        eventStartTime.setValue(null);
        eventDate.setValue(null);
        eventEndTime.setValue(null);
        noOfPersons.clear();
        nameOfCustomer.clear();
        phoneOfCustomer.clear();
        emailOfCustomer.clear();
        customerAddress.setText(null);
    }
    
    public void onTeam(ActionEvent event) {
    
    }
}

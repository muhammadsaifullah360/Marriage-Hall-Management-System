package dashboard.screens;

import database.DBService;
import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BookingController implements Initializable {
    
    @FXML
    private JFXComboBox<String> hall_no;
    
    @FXML
    private JFXTimePicker event_time;
    
    @FXML
    private JFXTimePicker event_ending_time;
    
    @FXML
    private JFXTextArea ShowSearchResult;
    
    @FXML
    private JFXTimePicker SearchTime;
    
    @FXML
    private JFXDatePicker SearchDate;
    
    @FXML
    private JFXDatePicker event_date;
    
    @FXML
    private JFXTextField no_of_persons;
    
    @FXML
    private Label MessageLabelOfEventSaved;
    
    @FXML
    private JFXComboBox<String> event_type_box;
    
    @FXML
    private JFXTextField name_of_customer;
    
    @FXML
    private JFXTextField phone_no_of_customer;
    
    @FXML
    private JFXTextField email_of_customer;
    
    @FXML
    private JFXTextField invoiceNoTxT;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> list = FXCollections.observableArrayList("Wedding", "Seminar", "Party", "Mehfill");
        event_type_box.setItems(list);
        
        ObservableList<String> list1 = FXCollections.observableArrayList("Hall 1 (MaxCap 500)", "Hall 2 (MaxCap 300)");
        hall_no.setItems(list1);
    }
    
    public void SaveEventBtn(ActionEvent event) {
        /// todo  implementtation if empty query not run
        try {
            
            String query = String.format("Insert Into Event_Booking( EventType, EventStartTime,EventDate ,NoOfPerson, NameOfCustomer, PhoneNoOfCustomer, EmailOfCustomer,EventEndingTime, HallNo, INVOICENO) Values ('%s', to_date('2020-12-12 %s',  'yyyy-mm-dd hh24:mi:ss'), to_date('%s','yyyy-mm-dd'), '%s', '%s', '%s', '%s',to_date('2020-12-12 %s',  'yyyy-mm-dd hh24:mi:ss'), '%s', %d )",
                    event_type_box.getValue(),
                    event_time.getValue(),
                    event_date.getValue(),
                    no_of_persons.getText(),
                    name_of_customer.getText(),
                    phone_no_of_customer.getText(),
                    email_of_customer.getText(),
                    event_ending_time.getValue(),
                    hall_no.getValue(),
                    Integer.parseInt(invoiceNoTxT.getText()));
            
            DBService.statement.executeUpdate(query);
            MessageLabelOfEventSaved.setText("Saved!");
            clearFields();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    public void clearFields() {
        
        hall_no.setValue(null);
        event_type_box.setValue(null);
        event_time.setValue(null);
        event_date.setValue(null);
        event_ending_time.setValue(null);
        no_of_persons.clear();
        name_of_customer.clear();
        phone_no_of_customer.clear();
        email_of_customer.clear();
        invoiceNoTxT.setText(null);
    }
    
    public void EventSearchBtn(ActionEvent event) {
//todo starting and ending time query correction
        
        try {
            
            String query = String.format("select * from Event_Booking where EVENTDATE = to_date('%s','yyyy-mm-dd ') AND EVENTSTARTTIME = to_date('2020-12-12 %s','yyyy-mm-dd hh24-mi-ss')", SearchDate.getValue(), SearchTime.getValue());
            
            ResultSet rs = DBService.statement.executeQuery(query);
            while (rs.next()) {
//                StringBuilder result = new StringBuilder();
//                result.append("Event Type =   "+rs.getString(1));
//                result.append("\n");
                ShowSearchResult.setText("Event Type =   " + rs.getString(1) + "\nNo Of Persons =  " + rs.getString(4) + "\nName Of Customer = " + rs.getString(5) + "\nPhone.No Of Customer =  " + rs.getString(7) + "\n  Event Ending Time =  " + rs.getString(8) + "\nHall No  =  " + rs.getString(9));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    public void ClearSearch(ActionEvent event) {
        SearchDate.setValue(null);
        SearchTime.setValue(null);
        ShowSearchResult.clear();
    }
}

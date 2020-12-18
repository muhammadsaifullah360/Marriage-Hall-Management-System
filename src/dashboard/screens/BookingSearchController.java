package dashboard.screens;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTimePicker;
import database.DBService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class BookingSearchController {
    
    @FXML
    private JFXTextArea ShowSearchResult;
    @FXML
    private JFXTimePicker SearchTime;
    @FXML
    private JFXDatePicker SearchDate;

    
    public void EventSearchBtn(ActionEvent actionEvent) {
//        try {
//
//            String query = String.format("select * from Event_Booking where EVENTDATE = to_date('%s','yyyy-mm-dd ') AND EVENTSTARTTIME = to_date('2020-12-12 %s','yyyy-mm-dd hh24-mi-ss')", SearchDate.getValue(), SearchTime.getValue());
//
//            ResultSet rs = DBService.statement.executeQuery(query);
//            while (rs.next()) {
//                StringBuilder result = new StringBuilder();
//                result.append("Event Type =   "+rs.getString(1));
//                result.append("\n");
//                ShowSearchResult.setText("Event Type =   " + rs.getString(1) + "\nNo Of Persons =  " + rs.getString(4) + "\nName Of Customer = " + rs.getString(5) + "\nPhone.No Of Customer =  " + rs.getString(7) + "\n  Event Ending Time =  " + rs.getString(8) + "\nHall No  =  " + rs.getString(9));
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
    }
    
    public void ClearSearch(ActionEvent actionEvent) {
        SearchDate.setValue(null);
        SearchTime.setValue(null);
        ShowSearchResult.clear();
    }
}

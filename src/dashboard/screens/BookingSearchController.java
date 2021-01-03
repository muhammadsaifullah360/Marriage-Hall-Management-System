package dashboard.screens;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTimePicker;
import database.DBService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingSearchController {
    
    @FXML
    private JFXTextArea showSearchResult;
    @FXML
    private JFXTimePicker searchTime;
    @FXML
    private JFXDatePicker searchDate;

    
    public void EventSearchBtn(ActionEvent actionEvent) {
    try {

        String query = String.format("select * from Booking where Booking_date = PARSEDATETIME('%s','yyyy-mm-dd ') AND START_TIME = to_char('%s','hh-mi-ss')", searchDate.getValue(), searchTime.getValue());

        ResultSet rs = DBService.statement.executeQuery(query);
        while (rs.next()) {
            StringBuilder result = new StringBuilder();
            result.append("Event Type =   ").append(rs.getString(1));
            result.append("\n");
            showSearchResult.setText("Event Type =   " + rs.getString(4) + "\nNo Of Persons =  " + rs.getString(8) + "\n  End_Time =  " + rs.getString(11) + "\nHall No  =  " + rs.getString(9));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }
    
    public void clearSearch(ActionEvent actionEvent) {
        searchDate.setValue(null);
        searchTime.setValue(null);
        showSearchResult.clear();
    }
}

package dashboard.screens;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import database.DBService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BillingController {
    
    
    @FXML
    private JFXTextField DataLoadField;
    @FXML
    private JFXTextField dish1;
    
    @FXML
    private Text load;
    
    public void LoadData(ActionEvent actionEvent) throws SQLException {
        String query = String.format("select * from Event_Booking where invoiceNo = %d",Integer.parseInt(DataLoadField.getText()));
        ResultSet rs = DBService.statement.executeQuery(query);
    
        if (rs.next()) {
            load.setText(rs.getString(4));
        }
    }
}

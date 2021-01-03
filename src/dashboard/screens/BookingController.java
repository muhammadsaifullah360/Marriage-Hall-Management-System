package dashboard.screens;

import com.jfoenix.controls.*;
import database.DBService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import util.StageHandler;

import java.sql.SQLException;
import java.util.Random;

public class BookingController {
    ObservableList<String> MenuList = FXCollections.observableArrayList();
    ObservableList<String> menuType = FXCollections.observableArrayList();
    ObservableList<String> facility = FXCollections.observableArrayList();
    
    @FXML
    private JFXTextField eventId;
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
    @FXML
    private JFXTextField teamID;
    @FXML
    private ListView<Integer> members;
    ////////////Menu//////////
    @FXML
    private CheckBox qorma;
    @FXML
    private CheckBox rice;
    @FXML
    private CheckBox salads;
    @FXML
    private CheckBox coldDrinks;
    @FXML
    private CheckBox sweetDish;
    @FXML
    private CheckBox others;
    @FXML
    private JFXTextArea menuDescription;
    @FXML
    private CheckBox qorma1;
    @FXML
    private CheckBox rice1;
    @FXML
    private CheckBox sweetDish1;
    @FXML
    private CheckBox other1;
    @FXML
    private AnchorPane MenuSelect;
    @FXML
    private RadioButton SingleDish;
    @FXML
    private RadioButton MultiDish;
    @FXML
    private AnchorPane DishesPane;
    @FXML
    private AnchorPane DishesPane1;
    @FXML
    private CheckBox acCheck;
    @FXML
    private CheckBox heaterCheck;
    @FXML
    private CheckBox photography;
    @FXML
    private Label MenuSaveLabel;
    @FXML
    private JFXComboBox<String> menuService;
    @FXML
    private JFXComboBox<String> decorationBox;
    
    private String dishType;
    
    @FXML
    private JFXTextField invoiceNo;
    
    public void initialize() {
        ObservableList<String> list = FXCollections.observableArrayList("Wedding", "Seminar", "Party", "Other", "Mehfill");
        eventType.setItems(list);
        
        ObservableList<String> list1 = FXCollections.observableArrayList("Hall 1 (MaxCap 500)", "Hall 2 (MaxCap 300)", "Hall 3 (MaxCap 700)");
        hallNo.setItems(list1);
        
        ObservableList<String> list2 = FXCollections.observableArrayList("Self", "Hall");
        menuService.setItems(list2);
        
        ObservableList<String> list3 = FXCollections.observableArrayList("Simple", "Fancy", "Custom");
        decorationBox.setItems(list3);
        
        int nextTeamID = DBService.getIntResult("Select MAX(ID)+1 From team");
        teamID.setEditable(false);
        teamID.setText("" + nextTeamID);
        
        int nextCustomerID = DBService.getIntResult("Select MAX(ID)+1 From customer");
        idOfCustomer.setEditable(false);
        idOfCustomer.setText("" + nextCustomerID);
        
        int nextBookingID = DBService.getIntResult("Select MAX(ID)+1 From booking");
        eventId.setEditable(false);
        eventId.setText("" + nextBookingID);
    }
    
    public void onCheck(ActionEvent actionEvent) {
        String fxmlPath = "/dashboard/screens/BookingSearch.fxml";
        String title = "Booking Availability";
        StageHandler.createStage(title, fxmlPath);
    }
    
    public void save(ActionEvent actionEvent) {
        try {
            String bookingDetail = String.format("Insert Into Booking( Id,customer_Id,Team_Id,Type,Booking_Date ,Start_Time,End_Time,Duration,person_count,location,Date_time) Values (%d,%d,%d,'%s', ParseDateTime('%s',  'yyyy-mm-dd'), ParseDateTime('12:55:00','hh:mm:ss'),ParseDateTime('12:55:00','hh:mm:ss'), %d, %d, '%s',ParseDateTime(sysdate,  'yyyy-mm-dd'))",
                    
                    Integer.parseInt(eventId.getText()),
                    Integer.parseInt(idOfCustomer.getText()),
                    Integer.parseInt(teamID.getText()),
                    eventType.getValue(),
                    eventDate.getValue(),
//                    eventStartTime.getValue(),
//                    eventEndTime.getValue(),
                    Integer.parseInt(durationField.getText()),
                    Integer.parseInt(noOfPersons.getText()),
                    hallNo.getValue());
            
            String customerDetail = String.format("Insert into Customer (Id , Name , Phone_Number, Email, Address,Date_Time ) Values( %d , '%s','%s','%s','%s',to_char(sysdate , 'yyyy-mm-dd'))",
                    Integer.parseInt(idOfCustomer.getText()),
                    nameOfCustomer.getText(),
                    phoneOfCustomer.getText(),
                    emailOfCustomer.getText(),
                    customerAddress.getText());
            
            
            String team = String.format("insert into team( id ,task_type)values(%d, '%s')", Integer.parseInt(teamID.getText()), nameOfCustomer.getText());
            
            DBService.statement.executeUpdate(team);
            DBService.statement.executeUpdate(customerDetail);
            DBService.statement.executeUpdate(bookingDetail);
            
            MessageLabelOfEventSaved.setText("Saved!");
//            clearFields();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void clear1Fields() {
        
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
    
    public void onGen(ActionEvent actionEvent) {
        String code = String.valueOf(OTP());
        invoiceNo.setText(code);
        invoiceNo.setEditable(false);
    }
    
    static char[] OTP() {
        int length = 10;
        
        String numbers = "0123456789abcetuvwxyz";
        
        Random random = new Random();
        char[] otp = new char[length];
        for (int i = 0; i < length; i++) {
            otp[i] = numbers.charAt(random.nextInt(numbers.length()));
        }
        return otp;
    }
    
    public void checkMenuService(ActionEvent event) {
        if (menuService.getValue().equals("Self")) {
            MenuSelect.setDisable(true);
            DishesPane1.setVisible(false);
            DishesPane.setVisible(false);
        } else {
            MenuSelect.setDisable(false);
        }
    }
    
    public void multiDish(ActionEvent event) {
        DishesPane.setVisible(true);
        DishesPane1.setVisible(false);
        dishType = "Multi Dish";
    }
    
    public void singleDish(ActionEvent event) {
        DishesPane1.setVisible(true);
        DishesPane.setVisible(false);
        dishType = "Single Dish";
    }
    
    public void MenuDetailSave(ActionEvent event) {
        
        String query = String.format("INSERT  INTO Menu (Booking_Id,Menu_Service,Decoration,facility,Description,dish_type)VALUES(%d,'%s','%s','%s','%s','%s')",
                Integer.parseInt(eventId.getText()),
                menuService.getValue(),
                decorationBox.getValue(),
                heaterCheck.getText(),
                menuDescription.getText(),
                dishType
        );
        
        DBService.executeUpdate(query);
        MenuSaveLabel.setText("Saved");
        clearFields();
    }
    
    public void clearFields() {
        decorationBox.setValue(null);
    }
    
    public void qormaCheck(ActionEvent event) {
        if (qorma.isSelected()) {
            MenuList.addAll(qorma.getText());
        }
    }
    
    public void riceCheck(ActionEvent event) {
        if (rice.isSelected()) {
            MenuList.addAll(rice.getText());
        }
    }
    
    public void qorma1Check(ActionEvent event) {
        if (qorma1.isSelected()) {
            MenuList.addAll(qorma1.getText());
        }
    }
    
    public void rice1Check(ActionEvent event) {
        if (rice1.isSelected()) {
            MenuList.addAll(rice1.getText());
        }
    }
    
    public void sweetDish1Check(ActionEvent event) {
        if (sweetDish1.isSelected()) {
            MenuList.addAll(sweetDish1.getText());
        }
    }
    
    public void other1Check(ActionEvent event) {
        if (other1.isSelected()) {
            MenuList.addAll(other1.getText());
        }
    }
    
    public void OtherCheck(ActionEvent event) {
        if (others.isSelected()) {
            MenuList.addAll(others.getText());
        }
    }
    
    public void sweetDishCheck(ActionEvent event) {
        if (sweetDish.isSelected()) {
            MenuList.addAll(sweetDish.getText());
        }
    }
    
    public void coldDrinksCheck(ActionEvent event) {
        if (coldDrinks.isSelected()) {
            MenuList.addAll(coldDrinks.getText());
        }
    }
    
    public void saladsCheck(ActionEvent event) {
        if (salads.isSelected()) {
            MenuList.addAll(salads.getText());
        }
    }
    
    public void heaterCheck(ActionEvent event) {
        if (heaterCheck.isSelected()) {
            facility.addAll(heaterCheck.getText());
        }
    }
    
    public void AcCheck(ActionEvent event) {
        if (acCheck.isSelected()) {
            facility.addAll(acCheck.getText());
        }
    }
    
    public void photographyCheck(ActionEvent event) {
        if (photography.isSelected()) {
            facility.addAll(photography.getText());
        }
    }
}


package dashboard.screens;

import com.jfoenix.controls.*;
import database.DBService;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.StageHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

public class BookingController {
    
    private static final ObservableList<viewBooking> booking_list = FXCollections.observableArrayList();
    ObservableList<String> MenuList = FXCollections.observableArrayList();
    ObservableList<String> menuType = FXCollections.observableArrayList();
    ObservableList<String> facility = FXCollections.observableArrayList();
    int dishId;
    /////////////////////Table View/////////
    @FXML
    private TableView<viewBooking> bookingViewTable;
    @FXML
    private TableColumn<viewBooking, Integer> bookingIdCol;
    @FXML
    private TableColumn<viewBooking, String> eventTypeCol;
    @FXML
    private TableColumn<viewBooking, Integer> totalPersonCol;
    @FXML
    private TableColumn<viewBooking, LocalTime> startTimeCol;
    @FXML
    private TableColumn<viewBooking, Integer> DurationCol;
    @FXML
    private TableColumn<viewBooking, String> hallNoCol;
    @FXML
    private TableColumn<viewBooking, LocalTime> endTimeCol;
    @FXML
    private TableColumn<viewBooking, LocalDate> eventDateCol;
    ///////////booking//////////
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
    private JFXTextArea menuDescription;
    @FXML
    private AnchorPane menuPane;
    @FXML
    private AnchorPane DishesPane;
    @FXML
    private CheckBox acCheck;
    @FXML
    private CheckBox heaterCheck;
    @FXML
    private CheckBox photography;
    @FXML
    private Label saveLabel;
    @FXML
    private JFXComboBox<String> menuService;
    @FXML
    private JFXComboBox<String> decorationBox;
    @FXML
    private JFXButton load1;
    
    //////////billing/////////
    @FXML
    private JFXTextField invoiceNo;
    @FXML
    private JFXTextField totalPayment;
    @FXML
    private JFXTextField perHeadCharges;
    @FXML
    private Text pendingAmount;
    @FXML
    private JFXTextField advancePayment;
    
    public void initialize() throws SQLException {
        makeNumberOnly(noOfPersons, perHeadCharges,advancePayment, durationField);
        
        ChangeListener<String> totalPriceChanger = (obs, v1, v2) -> {
            int totalPrice = Integer.parseInt(noOfPersons.getText()) * Integer.parseInt(perHeadCharges.getText());
            totalPayment.setText("" + totalPrice);
        };
        
        ChangeListener<String> pending = (obs, v1, v2) -> {
            int pendingPay = Integer.parseInt(totalPayment.getText()) - Integer.parseInt(advancePayment.getText());
            pendingAmount.setText("Rs. " + pendingPay);
        };
        advancePayment.textProperty().addListener(pending);
        
        noOfPersons.textProperty().addListener(totalPriceChanger);
        perHeadCharges.textProperty().addListener(totalPriceChanger);
        
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
        
        dishId = DBService.getIntResult("Select MAX(ID)+1 From Dish");
        
        createTable();
        loadData();
    }
    
    private void makeNumberOnly(TextField... textFields) {
        for (TextField textField : textFields)
            textField.textProperty().addListener((obs, v1, v2) -> {
                if (v2 == null || v2.isEmpty()) textField.setText("0");
                else {
                    String plainText = v2.replaceAll("\\D", "");
                    int newValue = Integer.parseInt(plainText.isEmpty() ? "0" : plainText);
                    textField.setText("" + newValue);
                }
            });
    }
    
    private void createTable() {
        bookingIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        eventTypeCol.setCellValueFactory(new PropertyValueFactory<>("eventType"));
        totalPersonCol.setCellValueFactory(new PropertyValueFactory<>("noOfPersons"));
        startTimeCol.setCellValueFactory(new PropertyValueFactory<>("eventStartTime"));
        DurationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));
        hallNoCol.setCellValueFactory(new PropertyValueFactory<>("hallNo"));
        endTimeCol.setCellValueFactory(new PropertyValueFactory<>("eventEndTime"));
        eventDateCol.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
    
    
        bookingViewTable.setItems(booking_list);
    }
    
    public static void loadData() throws SQLException {
        booking_list.clear();
        String query = "select * from booking";
        ResultSet rs = DBService.executeQuery(query);
        while (rs.next()) {
            booking_list.add(new viewBooking(
                    rs.getInt("Id"),
                    rs.getString("type"),
                    rs.getInt("person_count"),
                    rs.getTime("Start_Time").toLocalTime(),
                    rs.getTime("end_Time").toLocalTime(),
                    rs.getInt("Duration"),
                    rs.getString("Location"),
                    rs.getDate("Booking_Date").toLocalDate()
            ));
        }
    }
    
    public void checkMenuService() {
        if (menuService.getValue().equals("Self")) {
            DishesPane.setDisable(true);
        } else {
            DishesPane.setDisable(false);
        }
    }
    
    public void heaterCheck() {
        if (heaterCheck.isSelected()) {
            facility.addAll(heaterCheck.getText());
        }
    }
    
    public void AcCheck() {
        if (acCheck.isSelected()) {
            facility.addAll(acCheck.getText());
        }
    }
    
    public void photographyCheck() {
        if (photography.isSelected()) {
            facility.addAll(photography.getText());
        }
    }
    
    public void checkBookingAvailability() {
        String fxmlPath = "/dashboard/screens/BookingSearch.fxml";
        String title = "Booking Availability";
        StageHandler.createStage(title, fxmlPath);
    }
    
    public void onGen() {
        String code = String.valueOf(OTP());
        invoiceNo.setText(code);
        invoiceNo.setEditable(false);
    }
    
    static char[] OTP() {
        int length = 13;
        
        String numbers = "0123456789";
        
        Random random = new Random();
        char[] otp = new char[length];
        for (int i = 0; i < length; i++) {
            otp[i] = numbers.charAt(random.nextInt(numbers.length()));
        }
        return otp;
    }
    
    public void saveBookingMenu() {
        if (customerAddress.getText() == null || eventId.getText() == null || eventType.getValue() == null || eventDate.getValue() == null || eventEndTime.getValue() == null ||
                eventStartTime.getValue() == null || durationField.getText() == null || hallNo.getValue() == null || teamID.getText() == null
                || nameOfCustomer.getText() == null || emailOfCustomer.getText() == null || phoneOfCustomer.getText() == null || idOfCustomer.getText() == null
        ) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning!");
            alert.setHeaderText("Warning!");
            alert.setContentText("Please Fill All The Fields..");
            alert.showAndWait();
        } else {
            saveLabel.setText(null);
            try {
                String bookingDetail = String.format("Insert Into Booking( Id,customer_Id,Team_Id,Type,Booking_Date ,Start_Time,End_Time,Duration,person_count,location,Date_time) Values (%d,%d,%d,'%s', ParseDateTime('%s',  'yyyy-mm-dd'), '%s','%s', %d, %d, '%s',ParseDateTime(sysdate,  'yyyy-mm-dd'))",
                        
                        Integer.parseInt(eventId.getText()),
                        Integer.parseInt(idOfCustomer.getText()),
                        Integer.parseInt(teamID.getText()),
                        eventType.getValue(),
                        eventDate.getValue(),
                        eventStartTime.getValue(),
                        eventEndTime.getValue(),
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
                
                String menuDetail = String.format("INSERT  INTO Menu (Booking_Id,Menu_Service,Decoration,facility,Description)VALUES(%d,'%s','%s','%s','%s')",
                        Integer.parseInt(eventId.getText()),
                        menuService.getValue(),
                        decorationBox.getValue(),
                        heaterCheck.getText(),
                        menuDescription.getText()
                );
                System.out.println(dishId);
                
                String menuList = String.format("Insert into Dish (id,name,Date_time )values(%d,'%s',to_char(sysdate , 'yyyy-mm-dd'))", dishId, MenuList);
                
                DBService.statement.executeUpdate(team);
                DBService.statement.executeUpdate(customerDetail);
                DBService.statement.executeUpdate(bookingDetail);
                DBService.statement.executeUpdate(menuDetail);
//                DBService.statement.executeUpdate(menuList);
                
                saveLabel.setText("Saved");
                
                clearFields();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void clearFields() {
        decorationBox.setValue(null);
        hallNo.setValue(null);
        eventType.setValue(null);
        eventStartTime.setValue(null);
        eventDate.setValue(null);
        eventEndTime.setValue(null);
        nameOfCustomer.clear();
        phoneOfCustomer.clear();
        emailOfCustomer.clear();
        customerAddress.setText(null);
    }
    
    public void savePayment(ActionEvent actionEvent) throws SQLException {
        String query = String.format("Insert into Billing(Booking_Id , Total_Amount, Advance_Payment, Per_Head_Charges, Invoice_No , Date_Time)values(%d , %d, %d,%d,'%s',ParseDateTime(sysdate,  'yyyy-mm-dd'))",
                Integer.parseInt(eventId.getText()),
                Integer.parseInt(totalPayment.getText()),
                Integer.parseInt(advancePayment.getText()),
                Integer.parseInt(perHeadCharges.getText()),
                invoiceNo.getText()
                );
        DBService.statement.executeUpdate(query);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Saved");
        alert.setHeaderText("Saved");
        alert.setContentText("Your Payment Details are Successfully Saved! ");
        alert.showAndWait();
    }
}


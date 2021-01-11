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
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
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
    private Label saveLabel;
    @FXML
    private JFXComboBox<String> menuService;
    @FXML
    private JFXComboBox<String> decorationBox;
    @FXML
    private JFXButton load1;
    @FXML
    private JFXTextField invoiceNo;
    @FXML
    private JFXTextField totalPayment;
    @FXML
    private JFXTextField advancePayment;
    @FXML
    private JFXTextField perHeadCharges;
    private String dishType;
    int dishId ;
    
    public void initialize() {
        total();
        
        load1.setDisable(true);
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
        
    }
    
    
    public void checkMenuService() {
        if (menuService.getValue().equals("Self")) {
            MenuSelect.setDisable(true);
            DishesPane1.setVisible(false);
            DishesPane.setVisible(false);
        } else {
            MenuSelect.setDisable(false);
        }
    }
    
    public void multiDish() {
        DishesPane.setVisible(true);
        DishesPane1.setVisible(false);
        dishType = "Multi Dish";
    }
    
    public void singleDish() {
        DishesPane1.setVisible(true);
        DishesPane.setVisible(false);
        dishType = "Single Dish";
    }
    
    public void qormaCheck() {
        if (qorma.isSelected()) {
            MenuList.addAll(qorma.getText());
        }
    }
    
    public void riceCheck() {
        if (rice.isSelected()) {
            MenuList.addAll(rice.getText());
        }
    }
    
    public void qorma1Check() {
        if (qorma1.isSelected()) {
            MenuList.addAll(qorma1.getText());
        }
    }
    
    public void rice1Check() {
        if (rice1.isSelected()) {
            MenuList.addAll(rice1.getText());
        }
    }
    
    public void sweetDish1Check() {
        if (sweetDish1.isSelected()) {
            MenuList.addAll(sweetDish1.getText());
        }
    }
    
    public void other1Check() {
        if (other1.isSelected()) {
            MenuList.addAll(other1.getText());
        }
    }
    
    public void OtherCheck() {
        if (others.isSelected()) {
            MenuList.addAll(others.getText());
        }
    }
    
    public void sweetDishCheck() {
        if (sweetDish.isSelected()) {
            MenuList.addAll(sweetDish.getText());
        }
    }
    
    public void coldDrinksCheck() {
        if (coldDrinks.isSelected()) {
            MenuList.addAll(coldDrinks.getText());
        }
    }
    
    public void saladsCheck() {
        if (salads.isSelected()) {
            MenuList.addAll(salads.getText());
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
        int length = 16;
        
        String numbers = "0123456789";
        
        Random random = new Random();
        char[] otp = new char[length];
        for (int i = 0; i < length; i++) {
            otp[i] = numbers.charAt(random.nextInt(numbers.length()));
        }
        return otp;
    }
    
    public void saveBookingMenu() {
        if(customerAddress.getText()== null && eventId.getText()== null && eventType.getValue()== null && eventDate.getValue()== null &&eventEndTime.getValue()== null && eventStartTime.getValue() == null && durationField.getText()== null && hallNo.getValue()== null && teamID.getText()== null
                && nameOfCustomer.getText()== null && emailOfCustomer.getText()== null && phoneOfCustomer.getText()== null && idOfCustomer.getText()== null
        ){
            saveLabel.setText("*Please fill the all fields");
        }
        else {
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
            
            String menuDetail = String.format("INSERT  INTO Menu (Booking_Id,Menu_Service,Decoration,facility,Description,dish_type)VALUES(%d,'%s','%s','%s','%s','%s')",
                    Integer.parseInt(eventId.getText()),
                    menuService.getValue(),
                    decorationBox.getValue(),
                    heaterCheck.getText(),
                    menuDescription.getText(),
                    dishType
            );
            System.out.println(dishId);
            String menuList = String.format("Insert into Dish (id,name,Date_time )values(%d,'%s',to_char(sysdate , 'yyyy-mm-dd'))",dishId,MenuList);
           
            DBService.statement.executeUpdate(team);
            DBService.statement.executeUpdate(customerDetail);
            DBService.statement.executeUpdate(bookingDetail);
            DBService.executeUpdate(menuDetail);
            DBService.executeUpdate(menuList);
    
            saveLabel.setText("Saved");
            
            clearFields();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }}
    public void clearFields() {
        decorationBox.setValue(null);
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
    
    public void save() {
    
    }
    
    private void total() {
        if(perHeadCharges.getText() == null){
            int i = 0 ;
            assert false;
            perHeadCharges.setText(String.valueOf(i));
        }else
        totalPayment.setEditable(false);
        perHeadCharges.textProperty().addListener((observable, oldValue, newValue) -> {
                int persons = Integer.parseInt(noOfPersons.getText());
                int charges = Integer.parseInt(perHeadCharges.getText());
        
                totalPayment.textProperty().setValue(String.valueOf(charges* persons));
        });
    }
}


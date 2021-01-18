package dashboard.screens;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.*;
import database.DBService;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import util.StageHandler;

import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.*;

public class BookingController {
    
    private static final ObservableList<viewBooking> booking_list = FXCollections.observableArrayList();
    ObservableList<String> MenuList = FXCollections.observableArrayList();
    ObservableList<String> facility = FXCollections.observableArrayList();
    
    ObservableList<String> available = FXCollections.observableArrayList();
    ObservableList<String> selected = FXCollections.observableArrayList();
    
    
    int dishId;
    @FXML
    private JFXTextField search;
    
    @FXML
    private ListView<String> teamMembers;
    @FXML
    private ListView<String> selectedMembers;
    
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
        makeNumberOnly(noOfPersons, perHeadCharges, advancePayment, durationField);
        
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
        
        
        createTable();
        loadData();
        listController();
        teamMembers.setItems(available);
        createSearchField();
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
    
    public List<String> listController() throws SQLException {
        String query = String.format("select * from employee");
        ResultSet rs = DBService.statement.executeQuery(query);
        while (rs.next()) {
            
            available.add(rs.getString("name"));
        }
        return available;
    }
    
    private void createSearchField() {
        FilteredList<viewBooking> filteredData = new FilteredList<>(booking_list, b -> true);
        search.textProperty().addListener((observable, oldV, newV) -> {
            filteredData.setPredicate(viewBooking -> {
                if (newV == null || newV.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newV.toLowerCase();
                boolean matchType = viewBooking.getEventType().toLowerCase().contains(lowerCaseFilter);
                
                
                boolean matchID = false;
                if (newV.matches("\\d*"))
                    matchID = viewBooking.getId() == Integer.parseInt(newV.toLowerCase());
                return matchType || matchID;
            });
        });
        
        SortedList<viewBooking> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(bookingViewTable.comparatorProperty());
        bookingViewTable.setItems(sortedData);
        bookingViewTable.getSelectionModel().select(0);
    }
    
    public void onAdd(ActionEvent actionEvent) {
        String potential = teamMembers.getSelectionModel().getSelectedItem();
        if (potential != null) {
            teamMembers.getSelectionModel().clearSelection();
            available.remove(potential);
            selected.add(potential);
            selectedMembers.setItems(selected);
        }
    }
    
    public void onBack(ActionEvent actionEvent) {
        String potential = selectedMembers.getSelectionModel().getSelectedItem();
        if (potential != null) {
            selectedMembers.getSelectionModel().clearSelection();
            selected.remove(potential);
            available.add(potential);
            teamMembers.setItems(available);
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
                
                
                String team = String.format("insert into team( id ,task_type)values(%d, '%s')", Integer.parseInt(teamID.getText()), selected);
                
                String menuDetail = String.format("INSERT  INTO Menu (Booking_Id,Menu_Service,Decoration,facility,Description)VALUES(%d,'%s','%s','%s','%s')",
                        Integer.parseInt(eventId.getText()),
                        menuService.getValue(),
                        decorationBox.getValue(),
                        heaterCheck.getText(),
                        menuDescription.getText()
                );

//                String menuList = String.format("Insert into Dish (Booking _id,name )values(%d,'%s'))", Integer.parseInt(eventId.getText()), );
                
                DBService.statement.executeUpdate(team);
                DBService.statement.executeUpdate(customerDetail);
                DBService.statement.executeUpdate(bookingDetail);
                DBService.statement.executeUpdate(menuDetail);
                for (var dish : getSelectedDishes()) {
                    
                    String query = String.format("Insert into Dish (Booking_id,Dish_name )values(%d,'%s')",
                            Integer.parseInt(eventId.getText()),
                            dish);
                    DBService.statement.executeUpdate(query);
                }
    
    
                saveLabel.setText("Saved");
                loadData();
//                clearFields();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    private List<String> getSelectedDishes() {
        List<String> selectedDishes = new ArrayList<>();
        Set<Node> nodes = DishesPane.lookupAll(".check-box");
        if (nodes != null) nodes.forEach(node -> {
            String dish = ((CheckBox) node).getText();
            selectedDishes.add(dish);
        });
        return selectedDishes;
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
        loadData();
    }
    
    public void getReceipt(ActionEvent actionEvent) {
        try {
            String pdf = "E:\\javaPdf\\Receipt.pdf";
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(pdf));
            
            document.open();
            Image img = Image.getInstance("E:\\javaPdf\\qq.jpg");
            document.add(img);
            
            Paragraph p1 = new Paragraph("Marriage Hall Management", FontFactory.getFont(FontFactory.TIMES_BOLD, 25, Font.BOLD, BaseColor.RED));
            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);
            Paragraph p2 = new Paragraph(new Date().toString() + "                                                 Contact Number: 03041302417");
            p2.setSpacingBefore(10);
            p2.setFont(FontFactory.getFont(FontFactory.TIMES_BOLD, 25, Font.BOLD));
            document.add(p2);
            document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------"));
            String num = invoiceNo.getText();
            Paragraph inV = new Paragraph("Invoice Number : " + num + "\n\n");
            document.add(inV);
    
            Paragraph p3 = new Paragraph("Booking Details", FontFactory.getFont(FontFactory.TIMES_BOLD, 25, Font.BOLD, BaseColor.RED));
            p3.setAlignment(Element.ALIGN_CENTER);
            document.add(p3);
    
            Paragraph inVoice = new Paragraph("\n\n" +
                    "Customer Name............................................... " + nameOfCustomer.getText() + "\n" +
                    "Event Type.................................................. " + eventType.getValue() + "\n" +
                    "Event Date.................................................. " + eventDate.getValue() + "\n" +
                    "Event Start Time............................................ " + eventStartTime.getValue() + "\n" +
                    "No of Persons............................................... " + noOfPersons.getText() + "\n" +
                    "Duration.................................................... " + durationField.getText() + " Hours" + "\n" +
                    "Hall No..................................................... " + hallNo.getValue() + "\n\n" +
                    "Selected Dishes:" + "\n" + "          " + dishes()+"\n\n"+
                    "Facility             "+heaterCheck.getText()+
                    "-------------------------------------------------"+"\n"+
                    "Per Head Charges          "+"Rs."+perHeadCharges.getText()+"\n"+
                    "Advance Payment          "+"Rs."+advancePayment.getText()+"\n\n"+
                    "-------------------------------------------------"+"\n"+
                    "Pending Amount           "+pendingAmount.getText()
            );
            document.add(inVoice);
            document.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText("Successfully");
            alert.setContentText("PDF Generated");
            alert.showAndWait();
            System.out.println("Done!!!!!!!!");
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
    
    private ObservableList<String> dishes() throws SQLException {
        String query = String.format("select dish_Name from dish where booking_Id = %d", Integer.parseInt(eventId.getText()));
        ResultSet rs = DBService.statement.executeQuery(query);
        while (rs.next()) {
            MenuList.addAll(rs.getString("dish_name"));
        }
        return MenuList;
    }
    
    public void onDeleteBooking(ActionEvent actionEvent) throws SQLException
    {
//       bookingViewTable.getSelectionModel().getSelectedItem();
//        int selectedIndex = bookingViewTable.getSelectionModel().getSelectedIndex();
//        bookingViewTable.getItems().remove(selectedIndex);
//        String selectedItem = bookingIdCol.getText();
//        if (selectedIndex >= 0) {
////
////            String query1 = String.format("DELETE FROM booking WHERE Id = %d", Integer.parseInt(selectedItem));
////            DBService.statement.executeUpdate(query1);
//            bookingViewTable.getItems().remove(selectedItem);
//        }
    }
}


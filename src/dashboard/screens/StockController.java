package dashboard.screens;

import com.jfoenix.controls.JFXTextField;
import database.DBService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StockController {
    
    private static final ObservableList<supplier> supplier_List = FXCollections.observableArrayList();
    
    @FXML
    private JFXTextField supplierId;
    @FXML
    private JFXTextField supplierName;
    @FXML
    private JFXTextField supplierType;
    @FXML
    private JFXTextField supplierPhoneNum;
    @FXML
    private JFXTextField supplierEmail;
    @FXML
    private JFXTextField supplierAddress;
    @FXML
    private TableView<supplier> supplierTable;
    @FXML
    private TableColumn<supplier, Integer> supplierID_col;
    @FXML
    private TableColumn<supplier, String> supplierName_col;
    @FXML
    private TableColumn<supplier, String> supplierType_col;
    @FXML
    private TableColumn<supplier, String> supplierPhone_col;
    @FXML
    private TableColumn<supplier, String> supplierEmail_col;
    @FXML
    private TableColumn<supplier, String> supplierAddress_col;
    @FXML
    private JFXTextField searchBar;
    
    public void initialize() throws SQLException {
        makeNumberOnly(supplierId, supplierPhoneNum);
        createTable();
        loadData();
        createSearchFilter();
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
        supplierID_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        supplierName_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        supplierPhone_col.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        supplierAddress_col.setCellValueFactory(new PropertyValueFactory<>("address"));
        supplierEmail_col.setCellValueFactory(new PropertyValueFactory<>("email"));
        supplierType_col.setCellValueFactory(new PropertyValueFactory<>("type"));
        supplierTable.setItems(supplier_List);
    }
    
    private void loadData() throws SQLException {
        supplier_List.clear();
        String query = "select * from Supplier";
        ResultSet rs = DBService.statement.executeQuery(query);
        while (rs.next()) {
            supplier_List.add(new supplier(
                    rs.getInt("ID"),
                    rs.getString("Name"),
                    rs.getString("Phone_Number"),
                    rs.getString("email"),
                    rs.getString("Address"),
                    rs.getString("type")
            ));
        }
    }
    
    private void createSearchFilter() {
        FilteredList<supplier> filteredData = new FilteredList<>(supplier_List, b -> true);
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(supplier -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                boolean matchName = supplier.getName().toLowerCase().contains(lowerCaseFilter);
                boolean matchPhoneNo = supplier.getPhoneNo().toLowerCase().contains(lowerCaseFilter);
                boolean matchEmail = supplier.getEmail().toLowerCase().contains(lowerCaseFilter);
                boolean matchAddress = supplier.getAddress().toLowerCase().contains(lowerCaseFilter);
                boolean matchType = supplier.getType().toLowerCase().contains(lowerCaseFilter);
                
                boolean matchID = false;
                if (newValue.matches("\\d*"))
                    matchID = supplier.getId() == Integer.parseInt(newValue.toLowerCase());
                return matchName || matchPhoneNo || matchEmail || matchAddress || matchType || matchID;
            });
        });
        
        SortedList<supplier> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(supplierTable.comparatorProperty());
        supplierTable.setItems(sortedData);
        supplierTable.getSelectionModel().select(0);
    }
    
    public void addSupplier() throws SQLException {
        if (supplierType.getText().isEmpty() || supplierId.getText().isEmpty() || supplierName.getText().isEmpty()
                || supplierEmail.getText().isEmpty() || supplierPhoneNum.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning!");
            alert.setHeaderText("Warning!");
            alert.setContentText("Please Fill All The Fields..");
            alert.showAndWait();
        } else {
            String query = String.format("Insert into Supplier(id , name ,Phone_Number, email, Address,Date_time,type ) values(%d,'%s','%s','%s','%s',ParseDateTime(sysdate,  'yyyy-mm-dd'),'%s')",
                    Integer.parseInt(supplierId.getText()),
                    supplierName.getText(),
                    supplierPhoneNum.getText(),
                    supplierEmail.getText(),
                    supplierAddress.getText(),
                    supplierType.getText()
            
            );
            DBService.statement.executeUpdate(query);
            loadData();
            clearFields();
        }
    }
    
    private void clearFields() {
        supplierId.clear();
        supplierName.clear();
        supplierEmail.clear();
        supplierType.clear();
        supplierAddress.clear();
        supplierPhoneNum.clear();
    }
    
    public void updateSupplier() throws SQLException {
        if (supplierType.getText().isEmpty() || supplierId.getText().isEmpty() || supplierName.getText().isEmpty()
                || supplierEmail.getText().isEmpty() || supplierPhoneNum.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning!");
            alert.setHeaderText("Warning!");
            alert.setContentText("Please Fill All The Fields..");
            alert.showAndWait();
        } else {
            String query = String.format("Update Supplier set  name= '%s' ,Phone_Number = '%s', email= '%s', Address= '%s',Date_time=ParseDateTime(sysdate,  'yyyy-mm-dd'),type= '%s' where ID = %d",
                    
                    supplierName.getText(),
                    supplierPhoneNum.getText(),
                    supplierEmail.getText(),
                    supplierAddress.getText(),
                    supplierType.getText(),
                    Integer.parseInt(supplierId.getText())
            
            );
            DBService.statement.executeUpdate(query);
            loadData();
            clearFields();
        }
    }
    
    public void deleteSupplier() throws SQLException {
        
        if (supplierType.getText().isEmpty() || supplierId.getText().isEmpty() || supplierName.getText().isEmpty()
                || supplierEmail.getText().isEmpty() || supplierPhoneNum.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning!");
            alert.setHeaderText("Warning!");
            alert.setContentText("Please Fill All The Fields..");
            alert.showAndWait();
        } else {
            
            String query = String.format("delete from supplier where id = %d", Integer.parseInt(supplierId.getText()));
            DBService.statement.executeUpdate(query);
            loadData();
            clearFields();
        }
    }
    
    public void getSelectedRow(MouseEvent mouseEvent) {
        
        initData(supplierTable.getSelectionModel().getSelectedItem());
    }
    
    private void initData(supplier supply) {
        supplierId.setText(String.valueOf(supply.getId()));
        supplierName.setText(supply.getName());
        supplierAddress.setText(supply.getAddress());
        supplierPhoneNum.setText(supply.getPhoneNo());
        supplierEmail.setText(supply.getEmail());
        supplierType.setText(supply.getType());
    }
    
    public void onReset() {
        supplierId.clear();
        supplierName.clear();
        supplierAddress.clear();
        supplierPhoneNum.clear();
        supplierEmail.clear();
        supplierType.clear();
    }
    
    public void clear() {
        searchBar.clear();
    }
}
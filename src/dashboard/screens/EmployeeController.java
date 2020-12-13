package dashboard.screens;

import com.jfoenix.controls.JFXButton;
import dashboard.screens.employeeOperations.OperationsController;
import database.DBService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeController {
    
    
    ObservableList<Employee> list_final;
    @FXML
    private JFXButton add_btn;
    @FXML
    private JFXButton delete_btn;
    @FXML
    private JFXButton update_btn;
    @FXML
    private JFXButton search_btn;
    @FXML
    private TextField searchField_btn;
    @FXML
    private TableView<Employee> E_Table;
    @FXML
    private TableColumn<Employee, Integer> id_col;
    @FXML
    private TableColumn<Employee, String> Fname_col;
    @FXML
    private TableColumn<Employee, String> Lname_col;
    @FXML
    private TableColumn<Employee, String> cnic_col;
    
    
    public void initialize() {
        
        id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        Fname_col.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        Lname_col.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        cnic_col.setCellValueFactory(new PropertyValueFactory<>("cnic"));
        list_final = getData();
        E_Table.setItems(list_final);
        
        FilteredList<Employee> filteredData = new FilteredList<>(list_final, b -> true);
        searchField_btn.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Employee -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (Employee.getId() != -1) {
                    return true;

                } else if (Employee.getFirstName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                    
                } else if (Employee.getCnic().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                    
                } else if (String.valueOf(Employee.getLastName()).indexOf(lowerCaseFilter) != -1)
                    return true;
                
                else
                    return false;
            });
        });
        
        SortedList<Employee> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(E_Table.comparatorProperty());
        E_Table.setItems(sortedData);
    }
    
    
    public static ObservableList<Employee> getData() {
        ObservableList<Employee> list = FXCollections.observableArrayList();
        try {
            
            String query = "Select ID, First_Name ,Last_Name,Father_Name, Emr_Name, Cnic, Age, to_char( DOB,'yyyy-mm-dd') as dob , Nationality from EMP_BASIC_DETAIL";
            ResultSet rs = DBService.statement.executeQuery(query);
            while (rs.next()) {
                
                list.add(new Employee(Integer.parseInt(String.valueOf(rs.getInt("ID"))),
                        rs.getString("FIRST_NAME"),
                        rs.getString("LAST_NAME"),
                        rs.getString("FATHER_NAME"),
                        rs.getString("Emr_Name"),
                        rs.getString("CNIC"),
                        rs.getString("Age"),
                        rs.getString("dob"),
                        rs.getString("Nationality")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public void onClickAdd(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dashboard/screens/employeeOperations/EmpOperations.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setTitle("Add Employee");
        stage.setScene(new Scene(root1));
        stage.show();
    }
    
    public void onClickUpdate(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dashboard/screens/employeeOperations/EmpOperations.fxml"));
        Parent root1 = fxmlLoader.load();
        OperationsController controller = fxmlLoader.getController();
        controller.initData(E_Table.getSelectionModel().getSelectedItem());
        Stage stage = new Stage();
        stage.setTitle("Update Employee");
        stage.setScene(new Scene(root1));
        stage.show();
    }
    
    public void onClickDelete(ActionEvent event) throws SQLException {
    
    }
}

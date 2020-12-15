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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import util.StageHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeController {
    
    static ObservableList<Employee> employee_list = FXCollections.observableArrayList();
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
    private TableView<Employee> employee_table;
    @FXML
    private TableColumn<Employee, Integer> id_col;
    @FXML
    private TableColumn<Employee, String> Fname_col;
    @FXML
    private TableColumn<Employee, String> Lname_col;
    @FXML
    private TableColumn<Employee, String> cnic_col;
    @FXML
    private TableColumn<Employee, String> type_col;
    @FXML
    private TableColumn<Employee, Integer> salary_col;
    @FXML
    private TableColumn<Employee, String> address_col;
    
    public void initialize() {
        createTable();
        createSearchFilter();
        getData();
    }
    
    private void createTable() {
        id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        Fname_col.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        Lname_col.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        cnic_col.setCellValueFactory(new PropertyValueFactory<>("cnic"));
//        type_col.setCellValueFactory(new PropertyValueFactory<>("type"));
//        salary_col.setCellValueFactory(new PropertyValueFactory<>("salary"));
//        address_col.setCellValueFactory(new PropertyValueFactory<>("address"));
        employee_table.setItems(employee_list);
    }
    
    public static void getData() {
        employee_list.clear();
        String query = "Select ID, First_Name ,Last_Name,Father_Name, Emr_Name, Cnic, Age, to_char( DOB,'yyyy-mm-dd') as dob , Nationality from EMP_BASIC_DETAIL";
        ResultSet rs = DBService.executeQuery(query);
        try {
            while (rs.next()) {
                employee_list.add(new Employee(Integer.parseInt(String.valueOf(rs.getInt("ID"))),
                        rs.getString("First_Name"),
                        rs.getString("Last_Name"),
                        rs.getString("Father_Name"),
                        rs.getString("Emr_Name"),
                        rs.getString("Cnic"),
                        rs.getString("Age"),
                        rs.getString("Dob"),
                        rs.getString("Nationality")
                ));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
    
    private void createSearchFilter() {
        FilteredList<Employee> filteredData = new FilteredList<>(employee_list, b -> true);
        searchField_btn.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Employee -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String str = newValue.toLowerCase();
                boolean matchFirstName = Employee.getFirstName().toLowerCase().contains(str);
                boolean matchCNIC = Employee.getCnic().toLowerCase().contains(str);
                boolean matchLastName = Employee.getLastName().toLowerCase().contains(str);
//                boolean matchID = false;
//                if (newValue.matches("\\d*"))
//                    matchID = Employee.getId() == Integer.parseInt(newValue.toLowerCase());
                boolean matchID = String.valueOf(Employee.getId()).equals(str);
                return matchFirstName || matchLastName || matchCNIC || matchID;
            });
        });
        
        SortedList<Employee> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(employee_table.comparatorProperty());
        employee_table.setItems(sortedData);
    }
    
    public void add(ActionEvent event) {
        String fxmlPath = "/dashboard/screens/employeeOperations/EmpOperations.fxml";
        String title = "Add Employee";
        StageHandler.createStage(fxmlPath, title);
    }
    
    public void update(ActionEvent event) throws SQLException {
        String fxmlPath = "/dashboard/screens/employeeOperations/EmpOperations.fxml";
        String title = "Update Employee";
        StageHandler.createStage(fxmlPath, title);
        OperationsController controller = StageHandler.loader.getController();
        controller.initData(employee_table.getSelectionModel().getSelectedItem());
    }
    
    public void delete(ActionEvent event) throws SQLException {
        String fxmlPath = "/dashboard/screens/employeeOperations/View_Employee.fxml";
        String title = "Delete Employee Details";
        StageHandler.createStage(fxmlPath, title);
        OperationsController controller = StageHandler.loader.getController();
        controller.initData(employee_table.getSelectionModel().getSelectedItem());
    }
    
    public void view(ActionEvent event) throws SQLException {
        String fxmlPath = "/dashboard/screens/employeeOperations/View_Employee.fxml";
        String title = "View Employee Details";
        StageHandler.createStage(fxmlPath, title);
        OperationsController controller = StageHandler.loader.getController();
        controller.initData(employee_table.getSelectionModel().getSelectedItem());
    }
}

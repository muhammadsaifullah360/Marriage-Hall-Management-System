package dashboard.screens;

import dashboard.screens.employeeOperations.OperationsController;
import database.DBService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
    private TextField searchField;
    @FXML
    private TableView<Employee> employeeTable;
    @FXML
    private TableColumn<Employee, Integer> idCol;
    @FXML
    private TableColumn<Employee, String> firstNameCol;
    @FXML
    private TableColumn<Employee, String> lastNameCol;
    @FXML
    private TableColumn<Employee, String> cnicCol;
    @FXML
    private TableColumn<Employee, String> typeCol;
    @FXML
    private TableColumn<Employee, Integer> salaryCol;
    @FXML
    private TableColumn<Employee, String> addressCol;
    
    public void initialize() {
        createTable();
        createSearchFilter();
        getData();
    }
    
    private void createTable() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        cnicCol.setCellValueFactory(new PropertyValueFactory<>("cnic"));
//        type_col.setCellValueFactory(new PropertyValueFactory<>("type"));
//        salary_col.setCellValueFactory(new PropertyValueFactory<>("salary"));
//        address_col.setCellValueFactory(new PropertyValueFactory<>("address"));
        employeeTable.setItems(employee_list);
    }
    
    private void createSearchFilter() {
        FilteredList<Employee> filteredData = new FilteredList<>(employee_list, b -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Employee -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                boolean matchFirstName = Employee.getFirstName().toLowerCase().contains(lowerCaseFilter);
                boolean matchCNIC = Employee.getCnic().toLowerCase().contains(lowerCaseFilter);
                boolean matchLastName = Employee.getLastName().toLowerCase().contains(lowerCaseFilter);
                boolean matchID = false;
                if (newValue.matches("\\d*"))
                    matchID = Employee.getId() == Integer.parseInt(newValue.toLowerCase());
                return matchFirstName || matchLastName || matchCNIC || matchID;
            });
        });
        
        SortedList<Employee> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(employeeTable.comparatorProperty());
        employeeTable.setItems(sortedData);
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
    
    public void add() {
    
        String fxmlPath = "/dashboard/screens/employeeOperations/EmpOperations.fxml";
        String title = "Add Employee";
        StageHandler.createStage(fxmlPath, title);
    }
    
    public void update() throws SQLException {
        
        String fxmlPath = "/dashboard/screens/employeeOperations/EmpOperations.fxml";
        String title = "Update Employee";
        StageHandler.createStage(fxmlPath, title);
        OperationsController controller = StageHandler.loader.getController();
        controller.initData(employeeTable.getSelectionModel().getSelectedItem());
    }
    
    public void delete() throws SQLException {
        String fxmlPath = "/dashboard/screens/employeeOperations/View_Employee.fxml";
        String title = "Delete Employee Details";
        StageHandler.createStage(fxmlPath, title);
        OperationsController controller = StageHandler.loader.getController();
        controller.initData(employeeTable.getSelectionModel().getSelectedItem());
    }
    
    public void view() throws SQLException {
        String fxmlPath = "/dashboard/screens/employeeOperations/View_Employee.fxml";
        String title = "View Employee Details";
        StageHandler.createStage(fxmlPath, title);
        OperationsController controller = StageHandler.loader.getController();
        controller.initData(employeeTable.getSelectionModel().getSelectedItem());
    }
    
    public void searchClearBtn() {
        searchField.clear();
    }
}

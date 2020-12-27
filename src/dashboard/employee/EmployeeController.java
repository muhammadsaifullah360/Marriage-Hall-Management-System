package dashboard.employee;

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
import org.w3c.dom.Document;
import util.StageHandler;

import java.io.FileOutputStream;
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
    private TableColumn<Employee, String> nameCol;
    @FXML
    private TableColumn<Employee, String> phoneCol;
    @FXML
    private TableColumn<Employee, String> emailCol;
    @FXML
    private TableColumn<Employee, String> addressCol;
    
    public void initialize() {
        createTable();
        createSearchFilter();
        getData();
    }
    
    private void createTable() {
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
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
                boolean matchName = Employee.getName().toLowerCase().contains(lowerCaseFilter);
                boolean matchPhoneNo = Employee.getPhone().toLowerCase().contains(lowerCaseFilter);
                boolean matchEmail = Employee.getEmail().toLowerCase().contains(lowerCaseFilter);
                boolean matchAddress = Employee.getAddress().toLowerCase().contains(lowerCaseFilter);
                
                boolean matchID = false;
                if (newValue.matches("\\d*"))
                    matchID = Employee.getId() == Integer.parseInt(newValue.toLowerCase());
                return matchName || matchPhoneNo || matchEmail || matchAddress || matchID;
            });
        });
        
        SortedList<Employee> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(employeeTable.comparatorProperty());
        employeeTable.setItems(sortedData);
    }
    
    public static void getData() {
        employee_list.clear();
        String query = "Select ID, Name ,Phone_Number, Email, Address,Date_Time from Employee";
        ResultSet rs = DBService.executeQuery(query);
        try {
            while (rs.next()) {
                employee_list.add(new Employee(Integer.parseInt(String.valueOf(rs.getInt("ID"))),
                        
                        rs.getString("Name"),
                        rs.getString("Phone_Number"),
                        rs.getString("Email"),
                        rs.getString("Address"),
                        rs.getString("Date_Time")
                ));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
    
    public void add() {
        String fxmlPath = "/dashboard/employee/EmployeePanel.fxml";
        String title = "Add Employee";
        StageHandler.createStage(fxmlPath, title);
    }
    
    public void update() throws SQLException {
        String fxmlPath = "/dashboard/employee/EmployeePanel.fxml";
        String title = "Update Employee";
        StageHandler.createStage(fxmlPath, title);
        EmployeePanelController controller = StageHandler.loader.getController();
        controller.initData(employeeTable.getSelectionModel().getSelectedItem());
    }
    
    public void delete() throws SQLException {
        String fxmlPath = "dashboard/employee/EmployeePanel.fxml";
        String title = "Delete Employee Details";
        StageHandler.createStage(fxmlPath, title);
        EmployeePanelController controller = StageHandler.loader.getController();
        controller.initData(employeeTable.getSelectionModel().getSelectedItem());
    }
    
    public void view() throws SQLException {
        String fxmlPath = "dashboard/employee/EmployeePanel.fxml";
        String title = "View Employee Details";
        StageHandler.createStage(fxmlPath, title);
        EmployeePanelController controller = StageHandler.loader.getController();
        controller.initData(employeeTable.getSelectionModel().getSelectedItem());
    }
    
    public void searchClearBtn() {
        searchField.clear();
    }
    
    public void exportPdf(ActionEvent actionEvent) {
//
//            try {
//
//                Document my_pdf_report = new Document();
//                PdfWriter.getInstance(my_pdf_report, new FileOutputStream("pdf_report_from_sql_using_java.pdf"));
//                my_pdf_report.open();
//                //we have four columns in our table
//                PdfPTable my_report_table = new PdfPTable(4);
//                //create a cell object
//                PdfPCell table_cell;
//
//                while (query_set.next()) {
//                    String dept_id = query_set.getString("code");
//                    table_cell=new PdfPCell(new Phrase(dept_id));
//                    my_report_table.addCell(table_cell);
//                    String dept_name=query_set.getString("category");
//                    table_cell=new PdfPCell(new Phrase(dept_name));
//                    my_report_table.addCell(table_cell);
//                    String manager_id=query_set.getString("total");
//                    table_cell=new PdfPCell(new Phrase(manager_id));
//                    my_report_table.addCell(table_cell);
//                    String location_id=query_set.getString("Sum");
//                    table_cell=new PdfPCell(new Phrase(location_id));
//                    my_report_table.addCell(table_cell);
//                }
//                /* Attach report table to PDF */
//                my_pdf_report.add(my_report_table);
//                my_pdf_report.close();
//
//                /* Close all DB related objects */
//                query_set.close();
//                stmt.close();
//                con.close();
//
//            } catch (FileNotFoundException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            } catch (DocumentException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//
//        }
    
    }
}

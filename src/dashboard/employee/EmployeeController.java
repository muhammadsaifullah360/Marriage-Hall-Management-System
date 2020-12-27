package dashboard.employee;

import com.jfoenix.controls.JFXButton;
import database.DBService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import util.StageHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeController {
    public TableView<Employee> employee_table;
    public TableColumn<Employee, Integer> id_col;
    public TableColumn<Employee, String> name_col;
    public TableColumn<Employee, String> phone_col;
    public TableColumn<Employee, String> email_Col;
    public TableColumn<Employee, String> address_col;
    public TextField searchField;
    public JFXButton add_btn;
    public JFXButton delete_btn;
    public JFXButton update_btn;
    public JFXButton view_btn;
    private static final ObservableList<Employee> employee_list = FXCollections.observableArrayList();
    
    public void initialize() {
        add_btn.setDisable(true);
        delete_btn.setDisable(true);
        update_btn.setDisable(true);
        view_btn.setDisable(true);
        createTable();
        createSearchFilter();
        loadData();
    }
    
    private void createTable() {
        id_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        email_Col.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        phone_col.setCellValueFactory(new PropertyValueFactory<>("email"));
        address_col.setCellValueFactory(new PropertyValueFactory<>("address"));
        employee_table.setItems(employee_list);
        employee_table.getSelectionModel().selectedItemProperty().addListener((obs, oldRow, newRow) -> {
            if (newRow == null) {
                add_btn.setDisable(true);
                delete_btn.setDisable(true);
                update_btn.setDisable(true);
                view_btn.setDisable(true);
            } else {
                add_btn.setDisable(false);
                delete_btn.setDisable(false);
                update_btn.setDisable(false);
                view_btn.setDisable(false);
            }
        });
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
                boolean matchPhoneNo = Employee.getPhoneNo().toLowerCase().contains(lowerCaseFilter);
                boolean matchEmail = Employee.getEmail().toLowerCase().contains(lowerCaseFilter);
                boolean matchAddress = Employee.getAddress().toLowerCase().contains(lowerCaseFilter);
                
                boolean matchID = false;
                if (newValue.matches("\\d*"))
                    matchID = Employee.getId() == Integer.parseInt(newValue.toLowerCase());
                return matchName || matchPhoneNo || matchEmail || matchAddress || matchID;
            });
        });
        
        SortedList<Employee> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(employee_table.comparatorProperty());
        employee_table.setItems(sortedData);
        employee_table.getSelectionModel().select(0);
    }
    
    public static void loadData() {
        employee_list.clear();
        String query = "Select * from Employee_View";
        ResultSet rs = DBService.executeQuery(query);
        try {
            while (rs.next()) {
                employee_list.add(new Employee(
                        rs.getInt("ID"),
                        rs.getString("NAME"),
                        rs.getString("PHONE_NO"),
                        rs.getString("EMAIL"),
                        rs.getString("ADDRESS"),
                        rs.getDate("DOB").toLocalDate(),
                        rs.getString("CERTIFICATE_TITLE"),
                        rs.getString("ORGANIZATION_NAME_EDU"),
                        rs.getString("VERIFICATION_NO"),
                        rs.getDate("ISSUE_DATE").toLocalDate(),
                        rs.getString("DESCRIPTION_EDU"),
                        rs.getString("SKILL_TITLE"),
                        rs.getString("FIELD_TITLE_SKILL"),
                        rs.getDate("SKILL_DATE").toLocalDate(),
                        rs.getString("DESCRIPTION_SKILL"),
                        rs.getString("JOB_TITLE_EXP"),
                        rs.getString("ORGANIZATION_NAME_EXP"),
                        rs.getString("FIELD_TITLE_EXP"),
                        rs.getInt("DURATION"),
                        rs.getString("JOB_TITLE_DUTY"),
                        rs.getInt("TEAM_ID"),
                        rs.getString("SHIFT"),
                        rs.getInt("WORKING_HOURS"),
                        rs.getInt("SALARY"),
                        rs.getDate("JOIN_DATE").toLocalDate()
                ));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
    
    public void add() {
        StageHandler.createStage("Add Employee", "/dashboard/employee/EmployeePanel.fxml");
        EmployeePanelController controller = StageHandler.loader.getController();
        controller.initData(null, OperationType.ADD);
    }
    
    public void delete() {
        StageHandler.createStage("Delete Employee", "/dashboard/employee/EmployeePanel.fxml");
        EmployeePanelController controller = StageHandler.loader.getController();
        controller.initData(employee_table.getSelectionModel().getSelectedItem(), OperationType.DELETE);
    }
    
    public void update() {
        StageHandler.createStage("Update Employee", "/dashboard/employee/EmployeePanel.fxml");
        EmployeePanelController controller = StageHandler.loader.getController();
        controller.initData(employee_table.getSelectionModel().getSelectedItem(), OperationType.UPDATE);
    }
    
    public void view() {
        StageHandler.createStage("View Employee", "/dashboard/employee/EmployeePanel.fxml");
        EmployeePanelController controller = StageHandler.loader.getController();
        controller.initData(employee_table.getSelectionModel().getSelectedItem(), OperationType.VIEW);
    }
    
    public void searchClearBtn() {
        searchField.clear();
    }
    
    public void exportPdf(ActionEvent actionEvent) {
/*
            try {

                Document my_pdf_report = new Document();
                PdfWriter.getInstance(my_pdf_report, new FileOutputStream("pdf_report_from_sql_using_java.pdf"));
                my_pdf_report.open();
                //we have four columns in our table
                PdfPTable my_report_table = new PdfPTable(4);
                //create a cell object
                PdfPCell table_cell;

                while (query_set.next()) {
                    String dept_id = query_set.getString("code");
                    table_cell=new PdfPCell(new Phrase(dept_id));
                    my_report_table.addCell(table_cell);
                    String dept_name=query_set.getString("category");
                    table_cell=new PdfPCell(new Phrase(dept_name));
                    my_report_table.addCell(table_cell);
                    String manager_id=query_set.getString("total");
                    table_cell=new PdfPCell(new Phrase(manager_id));
                    my_report_table.addCell(table_cell);
                    String location_id=query_set.getString("Sum");
                    table_cell=new PdfPCell(new Phrase(location_id));
                    my_report_table.addCell(table_cell);
                }
                *//* Attach report table to PDF *//*
                my_pdf_report.add(my_report_table);
                my_pdf_report.close();

                *//* Close all DB related objects *//*
                query_set.close();
                stmt.close();
                con.close();

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (DocumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }*/
        
    }
}

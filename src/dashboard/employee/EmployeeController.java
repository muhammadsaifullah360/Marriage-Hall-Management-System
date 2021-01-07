package dashboard.employee;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import database.DBService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import util.StageHandler;

import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class EmployeeController {
    private static final ObservableList<Employee> employee_list = FXCollections.observableArrayList();
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
    
    public static PdfPTable createFirstTable() {
        int i = 0;
        PdfPTable table = new PdfPTable(5);
        
        PdfPCell c1 = new PdfPCell(new Phrase("QTY"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("Item"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        
        c1 = new PdfPCell(new Phrase("Description"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("Unit Price"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("Total"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);
        while (i <= 10) {
            table.addCell("1");
            table.addCell("2");
            table.addCell("3");
            table.addCell("4");
            table.addCell("5");
            i++;
        }
        
        
        return table;
    }
    
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
        email_Col.setCellValueFactory(new PropertyValueFactory<>("email"));
        phone_col.setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
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
        
        try {
            String pdf = "E:\\javaPdf\\test2.pdf";
            Document document = new Document(PageSize.A4);
            PdfWriter.getInstance(document, new FileOutputStream(pdf));
            
            document.open();
            Image imgsup = Image.getInstance("E:\\javaPdf\\qq.jpg");
            document.add(imgsup);
            
            Paragraph p = new Paragraph("Customer Details", FontFactory.getFont(FontFactory.TIMES_BOLD, 25, Font.BOLD, BaseColor.RED));
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            Paragraph paragraph = new Paragraph(new Date().toString());
            paragraph.setSpacingBefore(10);
            paragraph.setFont(FontFactory.getFont(FontFactory.TIMES_BOLD, 25, Font.BOLD));
            document.add(paragraph);
            document.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------"));
            
            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
//            table.setSpacingBefore(11f);
//            table.setSpacingBefore(11f);
//            float[] colWidth = {2f,2f,2f};
//            table.setWidths(colWidth);
//
            
            PdfPCell c1, c2, c3, c4, c5, c6;
            PdfPCell c7 = new PdfPCell(new Phrase("ID"));
            table.addCell(c7);
            c7 = new PdfPCell(new Phrase("Name"));
            table.addCell(c7);
            c7 = new PdfPCell(new Phrase("Phone No"));
            table.addCell(c7);
            c7 = new PdfPCell(new Phrase("Email"));
            table.addCell(c7);
            c7 = new PdfPCell(new Phrase("DOB"));
            table.addCell(c7);
            c7 = new PdfPCell(new Phrase("Address"));
            table.addCell(c7);
            c7.setBackgroundColor(BaseColor.BLACK);
            table.setHeaderRows(1);
            
            Paragraph para = new Paragraph("Hi Its My First PDF");
            para.setAlignment(Element.ALIGN_CENTER);
            para.setSpacingAfter(20);
            document.add(para);
            
            String query = "Select * from employee";
            ResultSet rs = DBService.statement.executeQuery(query);
            while (rs.next()) {
               
                    String id = rs.getString("ID");
                    c1 = new PdfPCell(new Phrase(id));
                    table.addCell(c1);
                    String name = rs.getString("Name");
                    c2 = new PdfPCell(new Phrase(name));
                    table.addCell(c2);
                    String phone = rs.getString("Phone_No");
                    c3 = new PdfPCell(new Phrase(phone));
                    table.addCell(c3);
                    String Email = rs.getString("Email");
                    c4 = new PdfPCell(new Phrase(Email));
                    table.addCell(c4);
                    String dob = rs.getString("DOB");
                    c5 = new PdfPCell(new Phrase(dob));
                    table.addCell(c5);
                    String Address = rs.getString("Address");
                    c6 = new PdfPCell(new Phrase(Address));
                    table.addCell(c6);
                
            }
            document.add(table);

//            document.add(createFirstTable());
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
}

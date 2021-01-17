package dashboard.employee;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import database.DBService;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import org.w3c.dom.Document;
import util.StageHandler;

import java.io.FileOutputStream;
import java.sql.SQLException;

public class EmployeePanelController {
    public JFXTextField id;
    public JFXTextField name;
    public JFXTextField phoneNo;
    public JFXTextField email;
    public JFXTextField address;
    public JFXDatePicker dob;
    public JFXTextField certificateTitle;
    public JFXTextField organizationName_edu;
    public JFXTextField verificationNo;
    public JFXDatePicker issueDate;
    public JFXTextArea description_edu;
    public JFXTextField skillTitle;
    public JFXTextField fieldTitle_skill;
    public JFXDatePicker skillDate;
    public JFXTextArea description_skill;
    public JFXTextField jobTitle_exp;
    public JFXTextField organizationName_exp;
    public JFXTextField fieldTitle_exp;
    public JFXTextField duration;
    public JFXTextField jobTitle_duty;
    public JFXTextField teamID;
    public JFXTextField shift;
    public JFXTextField workingHours;
    public JFXTextField salary;
    public JFXDatePicker joinDate;
    public JFXButton add_btn;
    public JFXButton update_btn;
    public JFXButton delete_btn;
    
    
    public  void initialize(){
        makeNumberOnly( workingHours, salary, duration);
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
    
    public void initData(Employee employee, OperationType type) {
        switch (type) {
            case ADD:
                update_btn.setDisable(true);
                delete_btn.setDisable(true);
                int nextID = DBService.getIntResult("Select MAX(ID)+1 From Employee");
                id.setEditable(false);
                id.setText("" + nextID);
                break;
            case UPDATE:
                add_btn.setDisable(true);
                delete_btn.setDisable(true);
                fillForm(employee);
                break;
            case DELETE:
                add_btn.setDisable(true);
                update_btn.setDisable(true);
                fillForm(employee);
                break;
            case VIEW:
                add_btn.setDisable(true);
                delete_btn.setDisable(true);
                update_btn.setDisable(true);
                fillForm(employee);
                break;
            default:
                System.out.println("Please Select Operation Type!");
        }
    }
    
    private void fillForm(Employee employee) {
        id.setText("" + employee.getId());
        name.setText(employee.getName());
        phoneNo.setText(employee.getPhoneNo());
        email.setText(employee.getEmail());
        address.setText(employee.getAddress());
        dob.setValue(employee.getDob());
        certificateTitle.setText(employee.getCertificateTitle());
        organizationName_edu.setText(employee.getOrganizationName_edu());
        verificationNo.setText(employee.getVerificationNo());
        issueDate.setValue(employee.getIssueDate());
        description_edu.setText(employee.getDescription_edu());
        skillTitle.setText(employee.getSkillTitle());
        fieldTitle_skill.setText(employee.getFieldTitle_skill());
        skillDate.setValue(employee.getSkillDate());
        description_skill.setText(employee.getDescription_skill());
        jobTitle_exp.setText(employee.getJobTitle_exp());
        organizationName_exp.setText(employee.getOrganizationName_exp());
        fieldTitle_exp.setText(employee.getFieldTitle_exp());
        duration.setText("" + employee.getDuration());
        jobTitle_duty.setText(employee.getJobTitle_duty());
        teamID.setText("" + employee.getTeamID());
        shift.setText(employee.getShift());
        workingHours.setText("" + employee.getWorkingHours());
        salary.setText("" + employee.getSalary());
        joinDate.setValue(employee.getJoinDate());
    }
    
    public void add(ActionEvent event) throws SQLException {
        String basicDetail = String.format("Insert Into Employee (ID, Name, Phone_No, Email, Address, Dob) Values(%d, '%s', '%s', '%s', '%s', ParseDateTime('%s', 'yyyy-mm-dd'))",
                Integer.parseInt(id.getText()),
                name.getText(),
                phoneNo.getText(),
                email.getText(),
                address.getText(),
                dob.getValue()
        );
        
        String educationalDetail = String.format("Insert Into Employee_Education_Detail( Employee_ID  , Certificate_Title, Organization_Name , Verification_No,Issue_Date , Description , Date_time) Values( %d,'%s','%s','%s',ParseDateTime('%s', 'yyyy-mm-dd'),'%s',to_char(sysdate,'yyyy-mm-dd'))",
                
                Integer.parseInt(id.getText()),
                certificateTitle.getText(),
                organizationName_edu.getText(),
                verificationNo.getText(),
                issueDate.getValue(),
                description_edu.getText()
        
        );
        
        String skillDetail = String.format("Insert Into Employee_Skill_Detail( Employee_ID, Skill_Title, Field_Title , Date_Time, Description ) Values( %d,'%s','%s',to_date( sysdate,'yyyy-mm-dd'),'%s')",
                
                Integer.parseInt(id.getText()),
                skillTitle.getText(),
                fieldTitle_skill.getText(),
                description_skill.getText()
        );
        
        String experienceDetail = String.format("Insert Into Employee_Experience_Detail( Employee_ID, Job_Title ,Organization_Name , Field_Title , Duration ,  Date_Time   ) Values( %d,'%s','%s','%s','%s',to_date(sysdate,'yyyy-mm-dd'))",
                
                Integer.parseInt(id.getText()),
                jobTitle_exp.getText(),
                organizationName_exp.getText(),
                fieldTitle_exp.getText(),
                duration.getText()
        );
        
        String dutyDetail = String.format("Insert Into Employee_Duty_Detail( Employee_ID , Job_Title, Shift,Working_Hours,  Salary , Join_Date ) Values( %d,'%s','%s',%d,%d,ParseDateTime('%s', 'yyyy-mm-dd'))",
                
                Integer.parseInt(id.getText()),
                jobTitle_exp.getText(),
                shift.getText(),
                Integer.parseInt(workingHours.getText()),
                Integer.parseInt(salary.getText()),
                joinDate.getValue()
        );
        
        
        DBService.statement.executeUpdate(basicDetail);
        DBService.statement.executeUpdate(educationalDetail);
        DBService.statement.executeUpdate(skillDetail);
        DBService.statement.executeUpdate(experienceDetail);
        DBService.statement.executeUpdate(dutyDetail);
        Refresh();
        StageHandler.getStage(event).close();
    }
    
    public void Refresh() {
        EmployeeController.loadData();
    }
    
    public void clearFields() {
        
        name.setText(null);
        dob.setValue(null);
        email.setText(null);
        phoneNo.setText(null);
        address.setText(null);
        certificateTitle.setText(null);
        organizationName_edu.setText(null);
        verificationNo.setText(null);
        description_edu.setText(null);
        issueDate.setValue(null);
        skillTitle.setText(null);
        description_skill.setText(null);
        fieldTitle_exp.setText(null);
        duration.setText(null);
        organizationName_exp.setText(null);
        shift.setText(null);
        salary.setText(null);
        jobTitle_exp.setText(null);
        jobTitle_duty.setText(null);
        workingHours.setText(null);
        joinDate.setValue(null);
    }
    
    public void delete(ActionEvent event) throws SQLException {
        int id = Integer.parseInt(this.id.getText());
        String query = "Delete from Employee Where id = " + id;
        DBService.statement.executeUpdate(query);
        Refresh();
    }
    
    public void update(ActionEvent event) throws SQLException {
        String basicDetails_query = String.format("Update Employee set NAME='%s', Phone_No='%s', Email='%s',Address ='%s',Date_Time = to_date('%s','yyyy-mm-dd') Where id=%d",
                
                name.getText(),
                phoneNo.getText(),
                email.getText(),
                address.getText(),
                dob.getValue(),
                Integer.parseInt(id.getText())
        );
        // Employee Education Details
        String educationDetailsQuery = String.format("Update  Employee_Education_Detail set Certificate_Title = '%s', Organization_Name= '%s' ,Verification_No = '%s', Issue_Date = to_char('%s','yyyy-mm-dd'),Date_time = to_char(sysdate,'yyyy-mm-dd') Where  Employee_id= %d ",
                
                
                certificateTitle.getText(),
                organizationName_edu.getText(),
                verificationNo.getText(),
                issueDate.getValue(),
                Integer.parseInt(id.getText())
        );
        // Employee Experience Details
        String experienceDetails_query = String.format("Update  Employee_Experience_Detail set Job_Title = '%s',Duration = '%s',Field_Title ='%s' , Organization_Name = '%s'  Where  Employee_id= %d ",
                
                
                jobTitle_exp.getText(),
                duration.getText(),
                fieldTitle_exp.getText(),
                organizationName_exp.getText(),
                Integer.parseInt(id.getText())
        );
        
        // Employee Skill Details
        String skillDetails_query = String.format("Update Employee_Skill_Detail set Skill_Title='%s',Field_Title='%s', Description='%s' Where  Employee_id= %d ",
                
                skillTitle.getText(),
                fieldTitle_skill.getText(),
                description_skill.getText(),
                Integer.parseInt(id.getText())
        );
        
        // Employee Duty Details
        String dutyDetails_query = String.format("UPDATE Employee_Duty_Detail SET   Job_Title = '%s',Working_Hours = %d, Shift ='%s', Salary=%d ,Join_Date =  to_char('%s','yyyy-mm-dd') Where Employee_id=%d",
                
                jobTitle_exp.getText(),
                Integer.parseInt(workingHours.getText()),
                shift.getText(),
                Integer.parseInt(salary.getText()),
                joinDate.getValue(),
                Integer.parseInt(id.getText())
        );
        
        // Performing Database Update Operations
        DBService.statement.executeUpdate(basicDetails_query);
        DBService.statement.executeUpdate(educationDetailsQuery);
        DBService.statement.executeUpdate(experienceDetails_query);
        DBService.statement.executeUpdate(skillDetails_query);
        DBService.statement.executeUpdate(dutyDetails_query);
        Refresh();
    }
    
}
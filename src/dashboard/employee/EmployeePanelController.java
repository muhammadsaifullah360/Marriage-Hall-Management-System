package dashboard.employee;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import database.DBService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class EmployeePanelController {
    @FXML
    private Label label;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField phoneNo;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField address;
    @FXML
    private JFXDatePicker dob;
    @FXML
    private JFXTextField certificateTitle;
    @FXML
    private JFXTextField organizationName_edu;
    @FXML
    private JFXTextField verificationNo;
    @FXML
    private JFXDatePicker issueDate;
    @FXML
    private JFXTextArea description_edu;
    @FXML
    private JFXTextField skillTitle;
    @FXML
    private JFXTextField fieldTitle_skill;
    @FXML
    private JFXDatePicker skillDate;
    @FXML
    private JFXTextArea description_skill;
    @FXML
    private JFXTextField jobTitle;
    @FXML
    private JFXTextField organizationName_exp;
    @FXML
    private JFXTextField fieldTitle_exp;
    @FXML
    private JFXTextField duration;
    @FXML
    private JFXTextField jobTitle_duty;
    @FXML
    private JFXTextField shift;
    @FXML
    private JFXTextField workingHours;
    @FXML
    private JFXTextField salary;
    @FXML
    private JFXDatePicker joinDate;
    
    
    public void initData(Employee employee) throws SQLException {
        id.setText(String.valueOf(employee.getId()));
        name.setText(employee.getName());
        phoneNo.setText(employee.getPhone());
        email.setText(employee.getEmail());
        address.setText(employee.getAddress());
        dob.setValue(LocalDate.parse(employee.getDob()));
        loadEducationDetails(employee.getId());
        loadSkillDetails(employee.getId());
        loadExperienceDetails(employee.getId());
        loadDutyDetails(employee.getId());
    }
    
    private void loadEducationDetails(int id) throws SQLException {
        String query = String.format("select   Certificate_Title, Organization_Name , Verification_No, Date_Issued_On , Date_time,to_char( Date_Issued_On ,'yyyy-mm-dd') as Date_Issued_On,Description from Employee_Education_Detail where Employee_ID = %d", id);
        ResultSet rs = DBService.executeQuery(query);
        
        if (rs.next()) {
            certificateTitle.setText(rs.getString("Certificate_Title"));
            organizationName_edu.setText(rs.getString("Organization_Name"));
            verificationNo.setText(rs.getString("Verification_No"));
            issueDate.setValue(LocalDate.parse(rs.getString("Date_Issued_On")));
            description_edu.setText(rs.getString("Description"));
        }
    }
    
    private void loadSkillDetails(int id) throws SQLException {
        String query = String.format("Select Skill_Title, Field_Title , Date_Time, Description from Employee_Skill_Detail Where  Employee_ID = %d", id);
        ResultSet rs = DBService.executeQuery(query);
        if (rs.next()) {
            skillTitle.setText(rs.getString("Skill_Title"));
            fieldTitle_skill.setText(rs.getString("Field_Title"));
            skillDate.setValue(LocalDate.parse(rs.getString("Date_Time")));
            description_skill.setText(rs.getString("Description"));
        }
    }
    
    private void loadExperienceDetails(int id) throws SQLException {
        String query = String.format("Select  Job_Title  , Duration , Field_Name   ,Organization_Name from Employee_Experience_Detail Where Employee_id = %d", id);
        ResultSet rs = DBService.executeQuery(query);
        if (rs.next()) {
            jobTitle.setText(rs.getString("Job_Title"));
            duration.setText(rs.getString("Duration"));
//            .setValue(LocalDate.parse(rs.getString("Date_Time")));
            organizationName_exp.setText(rs.getString("Organization_Name"));
        }
    }
    
    private void loadDutyDetails(int id) throws SQLException {
//        String query = String.format("Select to_char( Duty_Time,'hh24-mi-ss') as Duty_Time,Shifts,Salary,Designation,Type from Emp_Duty_Details Where EMp_Id = %d",id);
        String query = String.format("Select   Job_Title, SHIFT,Working_Hours,  SALARY , Joining_Date from Employee_Duty_Detail Where Employee_Id = %d", id);
        ResultSet rs = DBService.executeQuery(query);
        if (rs.next()) {
            jobTitle_duty.setText(rs.getString("Job_Title"));
            shift.setText(rs.getString("Shift"));
            workingHours.setText(rs.getString("Working_Hours"));
            salary.setText(rs.getString("SALARY"));
            joinDate.setValue(LocalDate.parse(rs.getString("Joining_Date")));
        }
    }
    
    public void add(ActionEvent event) throws SQLException {
        String basicDetail = String.format("Insert Into Employee (ID, Name,Phone_Number,Email,Address,Date_Time) Values( %d,'%s','%s','%s','%s',to_date('%s','yyyy-mm-dd'))",
                Integer.parseInt(id.getText()),
                name.getText(),
                phoneNo.getText(),
                email.getText(),
                address.getText(),
                dob.getValue()
        );
        
        String educationalDetail = String.format("Insert Into Employee_Education_Detail( Employee_ID  , Certificate_Title, Organization_Name , Verification_No, Date_Issued_On , Description , Date_time) Values( %d,'%s','%s','%s',to_date('%s','yyyy-mm-dd'),'%s',to_char(sysdate,'yyyy-mm-dd'))",
                
                Integer.parseInt(id.getText()),
                certificateTitle.getText(),
                organizationName_edu.getText(),
                verificationNo.getText(),
                issueDate.getValue(),
                description_edu.getText()
        
        );
        
        String Skills_detail = String.format("Insert Into Employee_Skill_Detail( Employee_ID, Skill_Title, Field_Title , Date_Time, Description ) Values( %d,'%s','%s',to_date( sysdate,'yyyy-mm-dd'),'%s')",
                
                Integer.parseInt(id.getText()),
                skillTitle.getText(),
                fieldTitle_skill.getText(),
                description_skill.getText()
        );
        
        String Experience_detail = String.format("Insert Into Employee_Experience_Detail( Employee_ID, Job_Title ,Organization_Name , FIELD_NAME , Duration ,  Date_Time   ) Values( %d,'%s','%s','%s','%s',to_date(sysdate,'yyyy-mm-dd'))",
                
                Integer.parseInt(id.getText()),
                jobTitle.getText(),
                organizationName_exp.getText(),
                fieldTitle_exp.getText(),
                duration.getText()
        );
        
        String Duty_detail = String.format("Insert Into Employee_Duty_Detail( Employee_ID , Job_Title, SHIFT,Working_Hours,  SALARY , Joining_Date ) Values( %d,'%s','%s',%d,%d,to_date('%s','yyyy-mm-dd'))",
                
                Integer.parseInt(id.getText()),
                jobTitle.getText(),
                shift.getText(),
                Integer.parseInt(workingHours.getText()),
                Integer.parseInt(salary.getText()),
                joinDate.getValue()
        );
        
        DBService.statement.executeUpdate(basicDetail);
        DBService.statement.executeUpdate(educationalDetail);
        DBService.statement.executeUpdate(Skills_detail);
        DBService.statement.executeUpdate(Experience_detail);
        DBService.statement.executeUpdate(Duty_detail);
        label.setText("Saved!");
        Refresh();
        clearFields();
    }
    
    public void Refresh() {
        EmployeeController.getData();
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
        jobTitle.setText(null);
        jobTitle_duty.setText(null);
        workingHours.setText(null);
        joinDate.setValue(null);
    }
    
    public void delete(ActionEvent event) throws SQLException {
        int id = Integer.parseInt(this.id.getText());
        String query = String.format("Delete from Employee Where id = %d ", id);
        DBService.statement.executeUpdate(query);
        label.setText("Deleted!");
        Refresh();
    }
    
    public void update(ActionEvent event) throws SQLException {
        String basicDetails_query = String.format("Update Employee set NAME='%s', Phone_Number='%s', Email='%s',Address ='%s',Date_Time = to_date('%s','yyyy-mm-dd') Where id=%d",
            
                name.getText(),
                phoneNo.getText(),
                email.getText(),
                address.getText(),
                dob.getValue(),
                Integer.parseInt(id.getText())
        );
        // Employee Education Details
        String educationDetailsQuery = String.format("Update  Employee_Education_Detail set Certificate_Title = '%s', Organization_Name= '%s' ,Verification_No = '%s', Date_Issued_On = to_char('%s','yyyy-mm-dd'),Date_time = to_char(sysdate,'yyyy-mm-dd') Where  Employee_id= %d ",
            
            
                certificateTitle.getText(),
                organizationName_edu.getText(),
                verificationNo.getText(),
                issueDate.getValue(),
                Integer.parseInt(id.getText())
        );
        // Employee Experience Details
        String experienceDetails_query = String.format("Update  Employee_Experience_Detail set Job_Title = '%s',Duration = '%s',Field_Name ='%s' , Organization_Name = '%s'  Where  Employee_id= %d ",
        
                
                jobTitle.getText(),
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
        String dutyDetails_query = String.format("UPDATE Employee_Duty_Detail SET   Job_Title = '%s',Working_Hours = %d, Shift ='%s', Salary=%d ,Joining_Date =  to_char('%s','yyyy-mm-dd') Where Employee_id=%d",
                
                jobTitle.getText(),
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
        label.setText("Updated!");
        Refresh();
    }
}
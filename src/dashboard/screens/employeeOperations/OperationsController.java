package dashboard.screens.employeeOperations;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import dashboard.screens.Employee;
import database.DBService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OperationsController {
    
    @FXML
    private Label save_label;
    
    ////////////Basic Details///////////
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField Name;
    @FXML
    private JFXTextField phoneNo;
    @FXML
    private JFXTextField address;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXDatePicker dob;
    
    //////////////////Qualification details///////////
    @FXML
    private JFXTextField instituteName;
    @FXML
    private JFXTextField certificateTitle;
    @FXML
    private JFXTextField verificationCode;
    @FXML
    private JFXDatePicker issueDate;
    @FXML
    private JFXTextField fieldTitle;
    @FXML
    private JFXTextArea eduDescription;
    
    
    //////////////Skills///////
    @FXML
    private JFXTextArea skillDescription;
    @FXML
    private JFXTextField skillTitle;
    @FXML
    private JFXDatePicker skillDate;
    
    /////////////////////Experiences////////////
    
    @FXML
    private JFXDatePicker experienceDate;
    @FXML
    private JFXTextField expDuration;
    @FXML
    private JFXTextField expFieldName;
    @FXML
    private JFXTextField expJobTitle;
    @FXML
    private JFXTextField experienceOrganizationName;
    @FXML
    private JFXTextArea expDescription;
    
    
    ////////assign duty///////
    @FXML
    private JFXDatePicker dateOfJoin;
    @FXML
    private JFXTextField teamId;
    @FXML
    private JFXTextField dutyShift;
    @FXML
    private JFXTextField salary;
    @FXML
    private JFXTextField workHours;
    @FXML
    private JFXTextField jobTitle;
    
    
    public void initData(Employee employee) throws SQLException {
        id.setText(String.valueOf(employee.getId()));
        Name.setText(employee.getName());
        phoneNo.setText(employee.getPhone());
        email.setText(employee.getEmail());
        address.setText(employee.getAddress());
        dob.setValue(LocalDate.parse(employee.getDob()));
        loadQualificationDetails(employee.getId());
        loadSkillDetails(employee.getId());
        loadExperienceDetails(employee.getId());
        loadDutyDetails(employee.getId());
    }
    
    private void loadQualificationDetails(int id) throws SQLException {
        String query = String.format("select   Certificate_Title, Organization_Name , Verification_No, Date_Issued_On , Date_time,to_char( Date_Issued_On ,'yyyy-mm-dd') as Date_Issued_On,Description from Employee_Education_Detail where Employee_ID = %d", id);
        ResultSet rs = DBService.executeQuery(query);
        
        if (rs.next()) {
            certificateTitle.setText(rs.getString("Certificate_Title"));
            instituteName.setText(rs.getString("Organization_Name"));
            verificationCode.setText(rs.getString("Verification_No"));
            issueDate.setValue(LocalDate.parse(rs.getString("Date_Issued_On")));
            eduDescription.setText(rs.getString("Description"));
    
        }
    }
    
    private void loadSkillDetails(int id) throws SQLException {
        String query = String.format("Select Skill_Title, Field_Title , Date_Time, Description from Employee_Skill_Detail Where  Employee_ID = %d", id);
        ResultSet rs = DBService.executeQuery(query);
        if (rs.next()) {
            skillTitle.setText(rs.getString("Skill_Title"));
            fieldTitle.setText(rs.getString("Field_Title"));
            skillDate.setValue(LocalDate.parse(rs.getString("Date_Time")));
            skillDescription.setText(rs.getString("Description"));
        }
    }
    
    private void loadExperienceDetails(int id) throws SQLException {
        String query = String.format("Select  Job_Title  , Duration ,  Date_Time , Field_Name   ,Organization_Name from Employee_Experience_Detail Where Employee_id = %d", id);
        ResultSet rs = DBService.executeQuery(query);
        if (rs.next()) {
            expJobTitle.setText(rs.getString("Job_Title"));
            expDuration.setText(rs.getString("Duration"));
            experienceDate.setValue(LocalDate.parse(rs.getString("Date_Time")));
            expFieldName.setText(rs.getString("Field_Name"));
            experienceOrganizationName.setText(rs.getString("Organization_Name"));
    
        }
    }
    
    private void loadDutyDetails(int id) throws SQLException {
//        String query = String.format("Select to_char( Duty_Time,'hh24-mi-ss') as Duty_Time,Shifts,Salary,Designation,Type from Emp_Duty_Details Where EMp_Id = %d",id);
        String query = String.format("Select Team_ID  , Job_Title, SHIFT,Working_Hours,  SALARY , Joining_Date from Employee_Duty_Detail Where Employee_Id = %d", id);
        ResultSet rs = DBService.executeQuery(query);
        if (rs.next()) {
            teamId.setText(rs.getString("Team_ID"));
            jobTitle.setText(rs.getString("Job_Title"));
            dutyShift.setText(rs.getString("Shift"));
            workHours.setText(rs.getString("Working_Hours"));
            salary.setText(rs.getString("SALARY"));
            dateOfJoin.setValue(LocalDate.parse(rs.getString("Joining_Date")));
        }
    }
    
    public void add(ActionEvent event) throws SQLException {
        String basicDetail = String.format("Insert Into Employee (ID, Name,Phone_Number,Email,Address,Date_Time) Values( %d,'%s','%s','%s','%s',to_date('%s','yyyy-mm-dd'))",
                Integer.parseInt(id.getText()),
                Name.getText(),
                phoneNo.getText(),
                email.getText(),
                address.getText(),
                dob.getValue()
        );
        
        String educationalDetail = String.format("Insert Into Employee_Education_Detail( Employee_ID  , Certificate_Title, Organization_Name , Verification_No, Date_Issued_On , Description , Date_time) Values( %d,'%s','%s','%s',to_date('%s','yyyy-mm-dd'),'%s',to_char(sysdate,'yyyy-mm-dd'))",
                
                Integer.parseInt(id.getText()),
                certificateTitle.getText(),
                instituteName.getText(),
                verificationCode.getText(),
                issueDate.getValue(),
                eduDescription.getText()
        
        );
        
        String Skills_detail = String.format("Insert Into Employee_Skill_Detail( Employee_ID, Skill_Title, Field_Title , Date_Time, Description ) Values( %d,'%s','%s',to_date( sysdate,'yyyy-mm-dd'),'%s')",
                
                Integer.parseInt(id.getText()),
                skillTitle.getText(),
                fieldTitle.getText(),
                skillDescription.getText()
        );
        
        String Experience_detail = String.format("Insert Into Employee_Experience_Detail( Employee_ID, Job_Title  , Duration ,  Date_Time , Field_Name   ,Organization_Name) Values( %d,'%s','%s',to_date('%s','yyyy-mm-dd'),'%s','%s')",
                
                Integer.parseInt(id.getText()),
                expJobTitle.getText(),
                expDuration.getText(),
                experienceDate.getValue(),
                expFieldName.getText(),
                experienceOrganizationName.getText()
        );

        String Duty_detail = String.format("Insert Into Employee_Duty_Detail( Employee_ID , Job_Title, SHIFT,Working_Hours,  SALARY , Joining_Date ) Values( %d,'%s','%s',%d,%d,to_date('%s','yyyy-mm-dd'))",

                Integer.parseInt(id.getText()),
                jobTitle.getText(),
                dutyShift.getText(),
               Integer.parseInt(workHours.getText()) ,
               Integer.parseInt(salary.getText()) ,
                dateOfJoin.getValue()
        );
        
        DBService.statement.executeUpdate(basicDetail);
//        DBService.statement.executeUpdate(educationalDetail);
//        DBService.statement.executeUpdate(Skills_detail);
//        DBService.statement.executeUpdate(Experience_detail);
        DBService.statement.executeUpdate(Duty_detail);
        save_label.setText("Saved!");
        clearFields();
    }
    public void fresh(){
    
    }
    
    public void clearFields() {
        
        Name.setText(null);
        dob.setValue(null);
        email.setText(null);
        phoneNo.setText(null);
        address.setText(null);
        certificateTitle.setText(null);
        instituteName.setText(null);
        verificationCode.setText(null);
        expDescription.setText(null);
        issueDate.setValue(null);
        instituteName.setText(null);
        skillTitle.setText(null);
        fieldTitle.setText(null);
        skillDescription.setText(null);
        expJobTitle.setText(null);
        expDuration.setText(null);
        experienceDate.setValue(null);
        expFieldName.setText(null);
        experienceOrganizationName.setText(null);
        dutyShift.setText(null);
        salary.setText(null);
        jobTitle.setText(null);
        eduDescription.setText(null);
        teamId.setText(null);
        workHours.setText(null);
        dateOfJoin.setValue(null);
    }
    
    public void update(ActionEvent event) throws SQLException {
        String basicDetails_query = String.format("Update Employee set NAME='%s', Phone_Number='%s', Email='%s',Address ='%s',Date_Time = to_date('%s','yyyy-mm-dd') Where id=%d",
                
                Name.getText(),
                phoneNo.getText(),
                email.getText(),
                address.getText(),
                dob.getValue(),
                Integer.parseInt(id.getText())
        );
        // Employee Qualification Details
        String qualificationDetailsQuery = String.format("Update  Employee_Education_Detail set Certificate_Title = '%s', Organization_Name= '%s' ,Verification_No = '%s', Date_Issued_On = to_date('%s','yyyy-mm-dd'),Date_time = to_date('%s','yyyy-mm-dd') Where  Employee_id= %d ",
                
                
                certificateTitle.getText(),
                instituteName.getText(),
                verificationCode.getText(),
                issueDate.getValue(),
                Integer.parseInt(id.getText())
        );
        // Employee Experience Details
        String experienceDetails_query = String.format("Update  Employee_Experience_Detail set Job_Title = '%s',Duration = '%s',Date_Time=  to_date('%s','yyyy-mm-dd'),Field_Name ='%s' , Organization_Name = '%s'  Where  Employee_id= %d ",
                
                
                expJobTitle.getText(),
                expDuration.getText(),
                experienceDate.getValue(),
                expFieldName.getText(),
                experienceOrganizationName.getText(),
                Integer.parseInt(id.getText())
        );
        
        // Employee Skill Details
        String skillDetails_query = String.format("Update Employee_Skill_Detail set Skill_Title='%s',Field_Title='%s', Description='%s' Where  Employee_id= %d ",
                
                skillTitle.getText(),
                fieldTitle.getText(),
                skillDescription.getText(),
                Integer.parseInt(id.getText())
        );
        
        // Employee Duty Details
        String dutyDetails_query = String.format("UPDATE Employee_Duty_Detail SET  Team_ID = %d ,Job_Title = '%s',Working_Hours= '%s', Shifts='%s', Salary=%d ,Joining_Date =  to_date('%s','yyyy-mm-dd'), Where Emp_id=%d",
                
                Integer.parseInt(teamId.getText()),
                jobTitle.getText(),
                dutyShift.getText(),
                Integer.parseInt(workHours.getText()),
                Integer.parseInt(salary.getText()),
                dateOfJoin.getValue(),
                Integer.parseInt(id.getText())
        );
        
        // Performing Database Update Operations
        DBService.statement.executeUpdate(basicDetails_query);
        DBService.statement.executeUpdate(qualificationDetailsQuery);
        DBService.statement.executeUpdate(experienceDetails_query);
        DBService.statement.executeUpdate(skillDetails_query);
        DBService.statement.executeUpdate(dutyDetails_query);
        save_label.setText("Information Updated!");
        clearFields();
    }
    
    public void delete(ActionEvent event) throws SQLException {
        int id = Integer.parseInt(this.id.getText());
        String query = String.format("Delete from Employee Where id = %d ", id);
        DBService.statement.executeUpdate(query);
        save_label.setText("Deleted");
    }
}
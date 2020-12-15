package dashboard.screens.employeeOperations;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
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
    @FXML
    private Label delete_label;
    ////////////Basic Details///////////
    @FXML
    private JFXTextField firstName;
    @FXML
    private JFXTextField lastName;
    @FXML
    private JFXTextField fatherName;
    @FXML
    private JFXTextField emergencyContactName;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField cnic;
    @FXML
    private JFXTextField age;
    @FXML
    private JFXDatePicker dob;
    @FXML
    private JFXTextField nationality;
    
    /////////Contact Details///////////
    @FXML
    private JFXTextField currentAddress;
    @FXML
    private JFXTextField permanentAddress;
    @FXML
    private JFXTextField faxNo;
    @FXML
    private JFXTextField phoneNo;
    @FXML
    private JFXTextField otherPhoneNo;
    @FXML
    private JFXTextField email;
    
    //////////////////Qualification details///////////
    @FXML
    private JFXTextField instituteName;
    @FXML
    private JFXTextField totalMarks;
    @FXML
    private JFXTextField obtainedMarks;
    @FXML
    private JFXTextField grade;
    @FXML
    private JFXTextField examBoard;
    @FXML
    private JFXTextField degreeTitle;
    @FXML
    private JFXTextField country;
    @FXML
    private JFXDatePicker startingDate;
    @FXML
    private JFXDatePicker completionDate;
    
    //////////////Skills///////
    @FXML
    private JFXTextField skillOrganizationName;
    @FXML
    private JFXTextField skillCertificateName;
    @FXML
    private JFXDatePicker skillStartingDate;
    @FXML
    private JFXDatePicker skillEndingDate;
    @FXML
    private JFXTextArea remarks;
    
    /////////////////////Experiences////////////
    @FXML
    private JFXTextField experienceOrganizationName;
    @FXML
    private JFXTextField experienceCertificateName;
    @FXML
    private JFXDatePicker experienceStartingDate;
    @FXML
    private JFXDatePicker experienceEndingDate;
    @FXML
    private JFXTextField experienceDesignation;
    
    ////////assign duty///////
    @FXML
    private JFXTimePicker dutyTime;
    @FXML
    private JFXTextField dutyShift;
    @FXML
    private JFXTextField salary;
    @FXML
    private JFXTextField dutyDesignation;
    @FXML
    private JFXTextField dutyType;
    
    
    public void initData(Employee employee) throws SQLException {
        id.setText(String.valueOf(employee.getId()));
        firstName.setText(employee.getFirstName());
        lastName.setText(employee.getLastName());
        fatherName.setText(employee.getFatherName());
        emergencyContactName.setText(employee.getEmrName());
        cnic.setText(employee.getCnic());
        age.setText(employee.getAge());
        dob.setValue(LocalDate.parse(employee.getDOB()));
        nationality.setText(employee.getNationality());
        loadContactDetails(employee.getId());
        loadQualificationDetails(employee.getId());
        loadSkillDetails(employee.getId());
        loadExperienceDetails(employee.getId());
        loadDutyDetails(employee.getId());
    }
    
    private void loadContactDetails(int id) throws SQLException {
        String query = String.format("select * from EMP_CONTACT_DETAIL where emp_id = %d", id);
        ResultSet rs = DBService.executeQuery(query);
        if (rs.next()) {
            email.setText(rs.getString("Email"));
            phoneNo.setText(rs.getString("Phone_NO"));
            otherPhoneNo.setText(rs.getString("o_Phone_NO"));
            faxNo.setText(rs.getString("Fax_NO"));
            permanentAddress.setText(rs.getString("Per_Addres"));
            currentAddress.setText(rs.getString("Cur_Addres"));
        }
    }
    
    private void loadQualificationDetails(int id) throws SQLException {
        String query = String.format("select  DEGREE_TITLE,Institute,Exam_Board,Total_Marks,Obtain_Marks,Grade,Country,to_char( Do_Starting ,'yyyy-mm-dd') as Do_Starting,to_char( Do_Comp ,'yyyy-mm-dd') as Do_Comp  from EMP_Qualification_DETAIL where emp_id = %d", id);
        ResultSet rs = DBService.executeQuery(query);
        
        if (rs.next()) {
            degreeTitle.setText(rs.getString("DEGREE_TITLE"));
            instituteName.setText(rs.getString("Institute"));
            examBoard.setText(rs.getString("Exam_Board"));
            totalMarks.setText(rs.getString("Total_Marks"));
            obtainedMarks.setText(rs.getString("Obtain_Marks"));
            grade.setText(rs.getString("Grade"));
            country.setText(rs.getString("Country"));
            startingDate.setValue(LocalDate.parse(rs.getString("Do_Starting")));
            completionDate.setValue(LocalDate.parse(rs.getString("Do_Comp")));
        }
    }
    
    private void loadSkillDetails(int id) throws SQLException {
        String query = String.format("Select Organization, Certifcate,to_char(Starting_Date,'yyyy-mm-dd') as Starting_Date,to_char(Ending_Date,'yyyy-mm-dd') as Ending_Date,Remarks from Emp_Skills_Detail Where Emp_id = %d", id);
        ResultSet rs = DBService.executeQuery(query);
        if (rs.next()) {
            skillOrganizationName.setText(rs.getString("Organization"));
            skillCertificateName.setText(rs.getString("Certifcate"));
            skillStartingDate.setValue(LocalDate.parse(rs.getString("Starting_Date")));
            skillEndingDate.setValue(LocalDate.parse(rs.getString("Ending_Date")));
            remarks.setText(rs.getString("Remarks"));
        }
    }
    
    private void loadExperienceDetails(int id) throws SQLException {
        String query = String.format("Select  Certifcate,to_char(Starting_Date,'yyyy-mm-dd') as Starting_Date,to_char(Ending_Date,'yyyy-mm-dd') as Ending_Date,Designation,Organization from Emp_Experience_Detail Where Emp_id = %d", id);
        ResultSet rs = DBService.executeQuery(query);
        if (rs.next()) {
            experienceCertificateName.setText(rs.getString("Certifcate"));
            experienceOrganizationName.setText(rs.getString("Organization"));
            experienceStartingDate.setValue(LocalDate.parse(rs.getString("Starting_Date")));
            experienceEndingDate.setValue(LocalDate.parse(rs.getString("Ending_Date")));
            experienceDesignation.setText(rs.getString("Designation"));
        }
    }
    
    private void loadDutyDetails(int id) throws SQLException {
//        String query = String.format("Select to_char( Duty_Time,'hh24-mi-ss') as Duty_Time,Shifts,Salary,Designation,Type from Emp_Duty_Details Where EMp_Id = %d",id);
        String query = String.format("Select Shifts,Salary,Designation,Type from Emp_Duty_Details Where EMp_Id = %d", id);
        ResultSet rs = DBService.executeQuery(query);
        if (rs.next()) {
//            dutyTime.setValue(LocalTime.parse(rs.getString("Duty_Time")));
            dutyShift.setText(rs.getString("Shifts"));
            salary.setText(rs.getString("salary"));
            dutyDesignation.setText(rs.getString("Designation"));
            dutyType.setText(rs.getString("Type"));
        }
    }
    
    public void add(ActionEvent event) {
        String Basic_detail = String.format("Insert Into EMP_BASIC_DETAIL(ID, FIRST_NAME, LAST_NAME, FATHER_NAME, EMR_NAME, CNIC, AGE, DOB, NATIONALITY) Values( %d,'%s','%s','%s','%s','%s','%s',to_date('%s','yyyy-mm-dd'),'%s')",
                Integer.parseInt(id.getText()),
                firstName.getText(),
                lastName.getText(),
                fatherName.getText(),
                emergencyContactName.getText(),
                cnic.getText(),
                age.getText(),
                dob.getValue(),
                nationality.getText()
        );
        
        String Contact_detail = String.format("Insert Into EMP_CONTACT_DETAIL( EMP_ID, EMAIL, PHONE_NO , O_PHONE_NO, FAX_NO, PER_ADDRES, CUR_ADDRES) Values( %d,'%s','%s','%s','%s','%s','%s')",
        
                Integer.parseInt(id.getText()),
                email.getText(),
                phoneNo.getText(),
                otherPhoneNo.getText(),
                faxNo.getText(),
                permanentAddress.getText(),
                currentAddress.getText()
        );
        
        String Qualification_detail = String.format("Insert Into EMP_QUALIFICATION_DETAIL( EMP_ID, DEGREE_TITLE, INSTITUTE , EXAM_BOARD, TOTAL_MARKS, OBTAIN_MARKS, GRADE, COUNTRY , DO_STARTING , DO_COMP) Values( %d,'%s','%s','%s','%s','%s','%s','%s',to_date('%s','yyyy-mm-dd'),to_date('%s','yyyy-mm-dd'))",
        
                Integer.parseInt(id.getText()),
                degreeTitle.getText(),
                instituteName.getText(),
                examBoard.getText(),
                totalMarks.getText(),
                obtainedMarks.getText(),
                grade.getText(),
                country.getText(),
                startingDate.getValue(),
                completionDate.getValue()
        );
        
        String Skills_detail = String.format("Insert Into EMP_SKILLS_DETAIL( EMP_ID, ORGANIZATION, CERTIFCATE , STARTING_DATE,  ENDING_DATE , REMARKS ) Values( %d,'%s','%s',to_date('%s','yyyy-mm-dd'),to_date('%s','yyyy-mm-dd'),'%s')",
        
                Integer.parseInt(id.getText()),
                skillOrganizationName.getText(),
                skillCertificateName.getText(),
                skillStartingDate.getValue(),
                skillEndingDate.getValue(),
                remarks.getText()
        );
        
        String Experience_detail = String.format("Insert Into EMP_EXPERIENCE_DETAIL( EMP_ID, CERTIFCATE , STARTING_DATE,  ENDING_DATE , DESIGNATION ,ORGANIZATION) Values( %d,'%s',to_date('%s','yyyy-mm-dd'),to_date('%s','yyyy-mm-dd'),'%s','%s')",
        
                Integer.parseInt(id.getText()),
                experienceCertificateName.getText(),
                experienceStartingDate.getValue(),
                experienceEndingDate.getValue(),
                experienceDesignation.getText(),
                experienceOrganizationName.getText()
        );
        
        String Duty_detail = String.format("Insert Into EMP_DUTY_DETAILS( EMP_ID, DUTY_TIME , SHIFTS,  SALARY , DESIGNATION ,TYPE) Values( %d,to_date('2020-12-12 %s',  'yyyy-mm-dd hh24:mi:ss'),'%s', %d,'%s','%s')",
        
                Integer.parseInt(id.getText()),
                dutyTime.getValue(),
                dutyShift.getText(),
                Integer.parseInt(salary.getText()),
                dutyDesignation.getText(),
                dutyType.getText()
        );
        
        DBService.executeUpdate(Basic_detail);
        DBService.executeUpdate(Contact_detail);
        DBService.executeUpdate(Qualification_detail);
        DBService.executeUpdate(Skills_detail);
        DBService.executeUpdate(Experience_detail);
        DBService.executeUpdate(Duty_detail);
        save_label.setText("Saved!");
        clearFields();
    }
    
    public void clearFields() {
    
        firstName.setText(null);
        lastName.setText(null);
        fatherName.setText(null);
        emergencyContactName.setText(null);
        cnic.setText(null);
        age.setText(null);
        dob.setValue(null);
        nationality.setText(null);
        id.setText(null);
        email.setText(null);
        phoneNo.setText(null);
        otherPhoneNo.setText(null);
        faxNo.setText(null);
        permanentAddress.setText(null);
        currentAddress.setText(null);
        degreeTitle.setText(null);
        instituteName.setText(null);
        examBoard.setText(null);
        totalMarks.setText(null);
        obtainedMarks.setText(null);
        grade.setText(null);
        country.setText(null);
        startingDate.setValue(null);
        completionDate.setValue(null);
        skillOrganizationName.setText(null);
        skillCertificateName.setText(null);
        skillStartingDate.setValue(null);
        skillEndingDate.setValue(null);
        remarks.setText(null);
        experienceCertificateName.setText(null);
        experienceStartingDate.setValue(null);
        experienceEndingDate.setValue(null);
        experienceDesignation.setText(null);
        experienceOrganizationName.setText(null);
        dutyTime.setValue(null);
        dutyShift.setText(null);
        salary.setText(null);
        dutyDesignation.setText(null);
        dutyType.setText(null);
    }
    
    public void update(ActionEvent event) {
        String basicDetails_query = String.format("Update EMP_BASIC_DETAIL set First_NAME='%s', Last_Name='%s', Father_Name='%s',Emr_Name='%s',CNIC='%s', Age='%s', Dob=to_date('%s','yyyy-mm-dd'), Nationality='%s' Where id=%d",
                firstName.getText(),
                lastName.getText(),
                fatherName.getText(),
                emergencyContactName.getText(),
                cnic.getText(),
                age.getText(),
                dob.getValue(),
                nationality.getText(),
                Integer.parseInt(id.getText()));
    
        // Employee Contact Details
        String contactDetails_query = String.format("Update EMP_CONTACT_DETAIL set Email='%s', Phone_No='%s', O_Phone_No='%s', Fax_No='%s', Per_Addres='%s', Cur_Addres='%s' Where Emp_id=%d",
                email.getText(),
                phoneNo.getText(),
                otherPhoneNo.getText(),
                faxNo.getText(),
                permanentAddress.getText(),
                currentAddress.getText(),
                Integer.parseInt(id.getText()));
    
        // Employee Qualification Details
        String qualificationDetails_query = String.format("Update  EMP_Qualification_DETAIL set Degree_Title = '%s', Institute= '%s' ,Exam_Board = '%s',Total_Marks ='%s',Obtain_Marks = '%s',Grade = '%s',Country = '%s', Do_Starting = to_date('%s','yyyy-mm-dd'),Do_Comp = to_date('%s','yyyy-mm-dd') Where  Emp_id= %d ",
                degreeTitle.getText(),
                instituteName.getText(),
                examBoard.getText(),
                totalMarks.getText(),
                obtainedMarks.getText(),
                grade.getText(),
                country.getText(),
                startingDate.getValue(),
                completionDate.getValue(),
                Integer.parseInt(id.getText())
        );
    
        // Employee Education Details
        String experienceDetails_query = String.format("Update  EMP_Experience_DETAIL set Certifcate = '%s', Starting_Date = to_date('%s','yyyy-mm-dd') ,Ending_Date =  to_date('%s','yyyy-mm-dd'),Designation = '%s',Organization ='%s'  Where  Emp_id= %d ",
                experienceCertificateName.getText(),
                experienceStartingDate.getValue(),
                experienceEndingDate.getValue(),
                experienceDesignation.getText(),
                experienceOrganizationName.getText(),
                Integer.parseInt(id.getText())
        );
    
        // Employee Skill Details
        String skillDetails_query = String.format("Update Emp_Skills_DETAIL set Organization='%s', Certifcate='%s', Starting_Date=to_date('%s','yyyy-mm-dd'), Ending_Date=to_date('%s','yyyy-mm-dd'),Remarks = '%s' Where  Emp_id= %d ",
                skillOrganizationName.getText(),
                skillCertificateName.getText(),
                skillStartingDate.getValue(),
                skillEndingDate.getValue(),
                remarks.getText(),
                Integer.parseInt(id.getText())
        );
    
        // Employee Duty Details
        String dutyDetails_query = String.format("UPDATE Emp_Duty_Details SET Duty_Time=to_date('%s',  'yyyy-mm-dd hh24:mi:ss'), Shifts='%s', Salary=%d ,Designation='%s', type='%s' Where Emp_id=%d",
                dutyTime.getValue(),
                dutyShift.getText(),
                Integer.parseInt(salary.getText()),
                dutyDesignation.getText(),
                dutyType.getText(),
                Integer.parseInt(id.getText())
        );
    
        // Performing Database Update Operations
        DBService.executeUpdate(basicDetails_query);
        DBService.executeUpdate(contactDetails_query);
        DBService.executeUpdate(qualificationDetails_query);
        DBService.executeUpdate(experienceDetails_query);
        DBService.executeUpdate(skillDetails_query);
        DBService.executeUpdate(dutyDetails_query);
        save_label.setText("Information Updated!");
        clearFields();
    }
    
    public void delete(ActionEvent event) {
        int id = Integer.parseInt(this.id.getText());
        String query = String.format("Delete from Emp_basic_detail Where id=%d ", id);
        DBService.executeUpdate(query);
        delete_label.setText("Deleted Successfully");
    }
}
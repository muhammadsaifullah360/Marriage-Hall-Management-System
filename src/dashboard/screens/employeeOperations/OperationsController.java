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
    private Label savelabel;
    
    ////////////Basic Details///////////
    @FXML
    private JFXTextField fatherName;
    @FXML
    private JFXTextField firstName;
    @FXML
    private JFXTextField lastName;
    @FXML
    private JFXTextField emergContactName;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField cnicNo;
    @FXML
    private JFXTextField age;
    @FXML
    private JFXDatePicker dob;
    @FXML
    private JFXTextField nationality;
    
    /////////Contact Details///////////
    @FXML
    private JFXTextField perAddress;
    @FXML
    private JFXTextField currAddress;
    @FXML
    private JFXTextField faxNo;
    @FXML
    private JFXTextField PhoneNo;
    @FXML
    private JFXTextField otherPhoneNo;
    @FXML
    private JFXTextField email;
    
    ///////////////Qualification details///////////
    @FXML
    private JFXTextField Institute;
    @FXML
    private JFXTextField degTitle;
    @FXML
    private JFXTextField examBoard;
    @FXML
    private JFXTextField totalMarks;
    @FXML
    private JFXTextField grade;
    @FXML
    private JFXTextField obtainMarks;
    @FXML
    private JFXTextField country;
    @FXML
    private JFXDatePicker dateOfStarting;
    @FXML
    private JFXDatePicker dateOfCompletion;
    
    //////////////Skills///////////
    @FXML
    private JFXTextField skillOrg;
    @FXML
    private JFXTextField skillCertificateName;
    @FXML
    private JFXDatePicker skillDateOfStarting;
    @FXML
    private JFXDatePicker skillDateOfEnding;
    @FXML
    private JFXTextArea skillRemarks;
    
    /////////////////////Experiences///////////
    @FXML
    private JFXTextField exprCertificateName;
    @FXML
    private JFXDatePicker experienceStartDate;
    @FXML
    private JFXDatePicker experienceEndDate;
    @FXML
    private JFXTextField experienceDesignation;
    @FXML
    private JFXTextField experienceOrg;
    
    ///////////Assign duty//////////
    @FXML
    private JFXTimePicker dutyTime;
    @FXML
    private JFXTextField dutyShifts;
    @FXML
    private JFXTextField salary;
    @FXML
    private JFXTextField dutyDesign;
    @FXML
    private JFXTextField dutyType;
    
    
    @FXML
    private Label deleteLabel;
    
    public void initData(Employee employee) throws SQLException {
        id.setText(String.valueOf(employee.getId()));
        firstName.setText(employee.getFirstName());
        lastName.setText(employee.getLastName());
        fatherName.setText(employee.getFatherName());
        emergContactName.setText(employee.getEmrName());
        cnicNo.setText(employee.getCnic());
        age.setText(employee.getAge());
        dob.setValue(LocalDate.parse(employee.getDOB()));
        nationality.setText(employee.getNationality());
        loadContactDetails(employee.getId());
        loadQualDetails(employee.getId());
        loadSkillsDetails(employee.getId());
        loadExperienceDetails(employee.getId());
        loadDutyDetails(employee.getId());
    }
    
    private void loadContactDetails(int id) throws SQLException {
        String query = String.format("select * from EMP_CONTACT_DETAIL where emp_id = %d", id);
        ResultSet rs = DBService.statement.executeQuery(query);
        
        if (rs.next()) {
            email.setText(rs.getString("Email"));
            PhoneNo.setText(rs.getString("Phone_NO"));
            otherPhoneNo.setText(rs.getString("o_Phone_NO"));
            faxNo.setText(rs.getString("Fax_NO"));
            perAddress.setText(rs.getString("Per_Addres"));
            currAddress.setText(rs.getString("Cur_Addres"));
        }
    }
    
    private void loadQualDetails(int id) throws SQLException {
        String query = String.format("select  DEGREE_TITLE,Institute,Exam_Board,Total_Marks,Obtain_Marks,Grade,Country,to_char( Do_Starting ,'yyyy-mm-dd') as Do_Starting,to_char( Do_Comp ,'yyyy-mm-dd') as Do_Comp  from EMP_Qualification_DETAIL where emp_id = %d", id);
        ResultSet rs = DBService.statement.executeQuery(query);
        
        if (rs.next()) {
            degTitle.setText(rs.getString("DEGREE_TITLE"));
            Institute.setText(rs.getString("Institute"));
            examBoard.setText(rs.getString("Exam_Board"));
            totalMarks.setText(rs.getString("Total_Marks"));
            obtainMarks.setText(rs.getString("Obtain_Marks"));
            grade.setText(rs.getString("Grade"));
            country.setText(rs.getString("Country"));
            dateOfStarting.setValue(LocalDate.parse(rs.getString("Do_Starting")));
            dateOfCompletion.setValue(LocalDate.parse(rs.getString("Do_Comp")));
        }
    }
    
    private void loadSkillsDetails(int id) throws SQLException {
        String query = String.format("Select Organization, Certifcate,to_char(Starting_Date,'yyyy-mm-dd') as Starting_Date,to_char(Ending_Date,'yyyy-mm-dd') as Ending_Date,Remarks from Emp_Skills_Detail Where Emp_id = %d", id);
        ResultSet rs = DBService.statement.executeQuery(query);
        if (rs.next()) {
            skillOrg.setText(rs.getString("Organization"));
            skillCertificateName.setText(rs.getString("Certifcate"));
            skillDateOfStarting.setValue(LocalDate.parse(rs.getString("Starting_Date")));
            skillDateOfEnding.setValue(LocalDate.parse(rs.getString("Ending_Date")));
            skillRemarks.setText(rs.getString("Remarks"));
        }
    }
    
    private void loadExperienceDetails(int id) throws SQLException {
        String query = String.format("Select  Certifcate,to_char(Starting_Date,'yyyy-mm-dd') as Starting_Date,to_char(Ending_Date,'yyyy-mm-dd') as Ending_Date,Designation,Organization from Emp_Experience_Detail Where Emp_id = %d", id);
        ResultSet rs = DBService.statement.executeQuery(query);
        if (rs.next()) {
            exprCertificateName.setText(rs.getString("Certifcate"));
            experienceOrg.setText(rs.getString("Organization"));
            experienceStartDate.setValue(LocalDate.parse(rs.getString("Starting_Date")));
            experienceEndDate.setValue(LocalDate.parse(rs.getString("Ending_Date")));
            experienceDesignation.setText(rs.getString("Designation"));
        }
    }
    
    private void loadDutyDetails(int id) throws SQLException {
//        String query = String.format("Select to_char( Duty_Time,'hh24-mi-ss') as Duty_Time,Shifts,Salary,Designation,Type from Emp_Duty_Details Where EMp_Id = %d",id);
        String query = String.format("Select Shifts,Salary,Designation,Type from Emp_Duty_Details Where EMp_Id = %d", id);
        ResultSet rs = DBService.statement.executeQuery(query);
        if (rs.next()) {
//            dutyTime.setValue(LocalTime.parse(rs.getString("Duty_Time")));
            dutyShifts.setText(rs.getString("Shifts"));
            salary.setText(rs.getString("salary"));
            dutyDesign.setText(rs.getString("Designation"));
            dutyType.setText(rs.getString("Type"));
        }
    }
    
    public void SubmitEmpDetail(ActionEvent event) {
        try {
            
            String Basic_detail = String.format("Insert Into EMP_BASIC_DETAIL(ID, FIRST_NAME, LAST_NAME, FATHER_NAME, EMR_NAME, CNIC, AGE, DOB, NATIONALITY) Values( %d,'%s','%s','%s','%s','%s','%s',to_date('%s','yyyy-mm-dd'),'%s')",
                    Integer.parseInt(id.getText()),
                    firstName.getText(),
                    lastName.getText(),
                    fatherName.getText(),
                    emergContactName.getText(),
                    cnicNo.getText(),
                    age.getText(),
                    dob.getValue(),
                    nationality.getText()
            );
            
            String Contact_detail = String.format("Insert Into EMP_CONTACT_DETAIL( EMP_ID, EMAIL, PHONE_NO , O_PHONE_NO, FAX_NO, PER_ADDRES, CUR_ADDRES) Values( %d,'%s','%s','%s','%s','%s','%s')",
                    
                    Integer.parseInt(id.getText()),
                    email.getText(),
                    PhoneNo.getText(),
                    otherPhoneNo.getText(),
                    faxNo.getText(),
                    perAddress.getText(),
                    currAddress.getText()
            );
            
            String Qualification_detail = String.format("Insert Into EMP_QUALIFICATION_DETAIL( EMP_ID, DEGREE_TITLE, INSTITUTE , EXAM_BOARD, TOTAL_MARKS, OBTAIN_MARKS, GRADE, COUNTRY , DO_STARTING , DO_COMP) Values( %d,'%s','%s','%s','%s','%s','%s','%s',to_date('%s','yyyy-mm-dd'),to_date('%s','yyyy-mm-dd'))",
                    
                    Integer.parseInt(id.getText()),
                    degTitle.getText(),
                    Institute.getText(),
                    examBoard.getText(),
                    totalMarks.getText(),
                    obtainMarks.getText(),
                    grade.getText(),
                    country.getText(),
                    dateOfStarting.getValue(),
                    dateOfCompletion.getValue()
            );
            
            String Skills_detail = String.format("Insert Into EMP_SKILLS_DETAIL( EMP_ID, ORGANIZATION, CERTIFCATE , STARTING_DATE,  ENDING_DATE , REMARKS ) Values( %d,'%s','%s',to_date('%s','yyyy-mm-dd'),to_date('%s','yyyy-mm-dd'),'%s')",
                    
                    Integer.parseInt(id.getText()),
                    skillOrg.getText(),
                    skillCertificateName.getText(),
                    skillDateOfStarting.getValue(),
                    skillDateOfEnding.getValue(),
                    skillRemarks.getText()
            );
            
            String Experience_detail = String.format("Insert Into EMP_EXPERIENCE_DETAIL( EMP_ID, CERTIFCATE , STARTING_DATE,  ENDING_DATE , DESIGNATION ,ORGANIZATION) Values( %d,'%s',to_date('%s','yyyy-mm-dd'),to_date('%s','yyyy-mm-dd'),'%s','%s')",
                    
                    Integer.parseInt(id.getText()),
                    exprCertificateName.getText(),
                    experienceStartDate.getValue(),
                    experienceEndDate.getValue(),
                    experienceDesignation.getText(),
                    experienceOrg.getText()
            );
            
            String Duty_detail = String.format("Insert Into EMP_DUTY_DETAILS( EMP_ID, DUTY_TIME , SHIFTS,  SALARY , DESIGNATION ,TYPE) Values( %d,to_date('2020-12-12 %s',  'yyyy-mm-dd hh24:mi:ss'),'%s', %d,'%s','%s')",
                    
                    Integer.parseInt(id.getText()),
                    dutyTime.getValue(),
                    dutyShifts.getText(),
                    Integer.parseInt(salary.getText()),
                    dutyDesign.getText(),
                    dutyType.getText()
            );
            
            DBService.statement.executeUpdate(Basic_detail);
            DBService.statement.executeUpdate(Contact_detail);
            DBService.statement.executeUpdate(Qualification_detail);
            DBService.statement.executeUpdate(Skills_detail);
            DBService.statement.executeUpdate(Experience_detail);
            DBService.statement.executeUpdate(Duty_detail);
            
            
            savelabel.setText("Saved!");
            clearFields();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    public void clearFields() {
        
        firstName.setText(null);
        lastName.setText(null);
        fatherName.setText(null);
        emergContactName.setText(null);
        cnicNo.setText(null);
        age.setText(null);
        dob.setValue(null);
        nationality.setText(null);
        id.setText(null);
        email.setText(null);
        PhoneNo.setText(null);
        otherPhoneNo.setText(null);
        faxNo.setText(null);
        perAddress.setText(null);
        currAddress.setText(null);
        degTitle.setText(null);
        Institute.setText(null);
        examBoard.setText(null);
        totalMarks.setText(null);
        obtainMarks.setText(null);
        grade.setText(null);
        country.setText(null);
        dateOfStarting.setValue(null);
        dateOfCompletion.setValue(null);
        skillOrg.setText(null);
        skillCertificateName.setText(null);
        skillDateOfStarting.setValue(null);
        skillDateOfEnding.setValue(null);
        skillRemarks.setText(null);
        exprCertificateName.setText(null);
        experienceStartDate.setValue(null);
        experienceEndDate.setValue(null);
        experienceDesignation.setText(null);
        experienceOrg.setText(null);
        dutyTime.setValue(null);
        dutyShifts.setText(null);
        salary.setText(null);
        dutyDesign.setText(null);
        dutyType.setText(null);
    }
    
    public void updateEmpDetail(ActionEvent event) {
        try {
            String query = String.format("Update  EMP_BASIC_DETAIL set First_NAME = '%s', Last_Name= '%s' ,Father_Name = '%s',Emr_Name ='%s',CNIC = '%s',Age = '%s',Dob = to_date('%s','yyyy-mm-dd'),Nationality = '%s' Where  id= %d ",
                    firstName.getText(),
                    lastName.getText(),
                    fatherName.getText(),
                    emergContactName.getText(),
                    cnicNo.getText(),
                    age.getText(),
                    dob.getValue(),
                    nationality.getText(),
                    Integer.parseInt(id.getText()));
            DBService.statement.executeUpdate(query);
            
            // Employee Contact Details
            query = String.format("Update  EMP_CONTACT_DETAIL set Email = '%s', Phone_No= '%s' ,O_Phone_No = '%s',Fax_No ='%s',Per_Addres = '%s',Cur_Addres = '%s' Where  Emp_id= %d ",
                    email.getText(),
                    PhoneNo.getText(),
                    otherPhoneNo.getText(),
                    faxNo.getText(),
                    perAddress.getText(),
                    currAddress.getText(),
                    Integer.parseInt(id.getText()));
            DBService.statement.executeUpdate(query);
            
            // Employee Qualification Details
            query = String.format("Update  EMP_Qualification_DETAIL set Degree_Title = '%s', Institute= '%s' ,Exam_Board = '%s',Total_Marks ='%s',Obtain_Marks = '%s',Grade = '%s',Country = '%s', Do_Starting = to_date('%s','yyyy-mm-dd'),Do_Comp = to_date('%s','yyyy-mm-dd') Where  Emp_id= %d ",
                    degTitle.getText(),
                    Institute.getText(),
                    examBoard.getText(),
                    totalMarks.getText(),
                    obtainMarks.getText(),
                    grade.getText(),
                    country.getText(),
                    dateOfStarting.getValue(),
                    dateOfCompletion.getValue(),
                    Integer.parseInt(id.getText())
            );
            
            // Employee Education Details
            query = String.format("Update  EMP_Experience_DETAIL set Certifcate = '%s', Starting_Date = to_date('%s','yyyy-mm-dd') ,Ending_Date =  to_date('%s','yyyy-mm-dd'),Designation = '%s',Organization ='%s'  Where  Emp_id= %d ",
                    exprCertificateName.getText(),
                    experienceStartDate.getValue(),
                    experienceEndDate.getValue(),
                    experienceDesignation.getText(),
                    experienceOrg.getText(),
                    Integer.parseInt(id.getText())
            );
            DBService.statement.executeUpdate(query);
            
            // Employee Skill Details
            query = String.format("Update  Emp_Skills_DETAIL set Organization ='%s', Certifcate = '%s', Starting_Date = to_date('%s','yyyy-mm-dd') ,Ending_Date =  to_date('%s','yyyy-mm-dd'),Remarks = '%s' Where  Emp_id= %d ",
                    skillOrg.getText(),
                    skillCertificateName.getText(),
                    skillDateOfStarting.getValue(),
                    skillDateOfEnding.getValue(),
                    skillRemarks.getText(),
                    Integer.parseInt(id.getText())
            );
            DBService.statement.executeUpdate(query);
            
            // Employee Duty Details
            query = String.format("UPDATE  Emp_Duty_Details SET  Duty_Time= to_date('2020-12-12 %s',  'yyyy-mm-dd hh24:mi:ss'),Shifts = '%s', Salary = %d ,Designation = '%s', type = '%s' Where  Emp_id= %d ",
                    dutyTime.getValue(),
                    dutyShifts.getText(),
                    Integer.parseInt(salary.getText()),
                    dutyDesign.getText(),
                    dutyType.getText(),
                    Integer.parseInt(id.getText())
            );
            DBService.statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        savelabel.setText("Information Updated!");
        clearFields();
    }
    
    public void deleteEmpDetail(ActionEvent event) throws SQLException {
        String query = String.format("Delete from Emp_basic_detail Where id = %d ", Integer.parseInt(id.getText()));
        DBService.statement.executeUpdate(query);
        deleteLabel.setText("Successfully");
    }
}
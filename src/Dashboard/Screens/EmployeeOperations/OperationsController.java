package Dashboard.Screens.EmployeeOperations;

import Dashboard.Screens.Employee;
import Database.DBService;
import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OperationsController {
    
    @FXML
    private JFXButton AddBtn;
    @FXML
    private Label save_label;
    
    ////////////Basic Details///////////
    @FXML
    private JFXTextField Father_name;
    @FXML
    private JFXTextField F_name_txt;
    @FXML
    private JFXTextField L_name_txt;
    @FXML
    private JFXTextField Emr_name;
    @FXML
    private JFXTextField id_txt;
    @FXML
    private JFXTextField cnic_txt;
    @FXML
    private JFXTextField age_txt;
    @FXML
    private JFXDatePicker dob;
    @FXML
    private JFXTextField N_txt;
    
    /////////Contact Details///////////
    @FXML
    private JFXTextField p_address;
    @FXML
    private JFXTextField c_address;
    @FXML
    private JFXTextField fax_txt;
    @FXML
    private JFXTextField phone_txt;
    @FXML
    private JFXTextField o_phone_txt;
    @FXML
    private JFXTextField email_txt;
    
    //////////////////Qualification details///////////
    @FXML
    private JFXTextField inst_txt;
    @FXML
    private JFXTextField degTitle;
    @FXML
    private JFXTextField E_board;
    @FXML
    private JFXTextField t_marks;
    @FXML
    private JFXTextField grade;
    @FXML
    private JFXTextField o_marks;
    @FXML
    private JFXTextField country;
    @FXML
    private JFXDatePicker d_o_S;
    @FXML
    private JFXDatePicker d_o_comp;
    
    //////////////Skills///////
    @FXML
    private JFXTextField s_org;
    @FXML
    private JFXTextField s_cer_name;
    @FXML
    private JFXDatePicker s_start_date;
    @FXML
    private JFXDatePicker s_end_date;
    @FXML
    private JFXTextArea s_remarks;
    
    /////////////////////Experiences////////////
    @FXML
    private JFXTextField e_cer_name;
    @FXML
    private JFXDatePicker e_start_date;
    @FXML
    private JFXDatePicker e_end_date;
    @FXML
    private JFXTextField e_design;
    @FXML
    private JFXTextField e_org;
    
    ////////assign duty///////
    @FXML
    private JFXTimePicker dutyTime;
    @FXML
    private JFXTextField dutyShifts;
    @FXML
    private JFXTextField salary;
    @FXML
    private JFXTextField duty_design;
    @FXML
    private JFXTextField duty_type;
    
    
    public void initData(Employee employee) throws SQLException {
        id_txt.setText(String.valueOf(employee.getId()));
        F_name_txt.setText(employee.getFirstName());
        L_name_txt.setText(employee.getLastName());
        Father_name.setText(employee.getFatherName());
        Emr_name.setText(employee.getEmrName());
        cnic_txt.setText(employee.getCnic());
        age_txt.setText(employee.getAge());
        dob.setValue(LocalDate.parse(employee.getDOB()));
        N_txt.setText(employee.getNationality());
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
            email_txt.setText(rs.getString("Email"));
            phone_txt.setText(rs.getString("Phone_NO"));
            o_phone_txt.setText(rs.getString("o_Phone_NO"));
            fax_txt.setText(rs.getString("Fax_NO"));
            p_address.setText(rs.getString("Per_Addres"));
            c_address.setText(rs.getString("Cur_Addres"));
        }
    }
    
    private void loadQualDetails(int id) throws SQLException {
        String query = String.format("select  DEGREE_TITLE,Institute,Exam_Board,Total_Marks,Obtain_Marks,Grade,Country,to_char( Do_Starting ,'yyyy-mm-dd') as Do_Starting,to_char( Do_Comp ,'yyyy-mm-dd') as Do_Comp  from EMP_Qualification_DETAIL where emp_id = %d", id);
        ResultSet rs = DBService.statement.executeQuery(query);
        
        if (rs.next()) {
            degTitle.setText(rs.getString("DEGREE_TITLE"));
            inst_txt.setText(rs.getString("Institute"));
            E_board.setText(rs.getString("Exam_Board"));
            t_marks.setText(rs.getString("Total_Marks"));
            o_marks.setText(rs.getString("Obtain_Marks"));
            grade.setText(rs.getString("Grade"));
            country.setText(rs.getString("Country"));
            d_o_S.setValue(LocalDate.parse(rs.getString("Do_Starting")));
            d_o_comp.setValue(LocalDate.parse(rs.getString("Do_Comp")));
        }
    }
    
    private void loadSkillsDetails(int id) throws SQLException {
        String query = String.format("Select Organization, Certifcate,to_char(Starting_Date,'yyyy-mm-dd') as Starting_Date,to_char(Ending_Date,'yyyy-mm-dd') as Ending_Date,Remarks from Emp_Skills_Detail Where Emp_id = %d", id);
        ResultSet rs = DBService.statement.executeQuery(query);
        if (rs.next()) {
            s_org.setText(rs.getString("Organization"));
            s_cer_name.setText(rs.getString("Certifcate"));
            s_start_date.setValue(LocalDate.parse(rs.getString("Starting_Date")));
            s_end_date.setValue(LocalDate.parse(rs.getString("Ending_Date")));
            s_remarks.setText(rs.getString("Remarks"));
        }
    }
    
    private void loadExperienceDetails(int id) throws SQLException {
        String query = String.format("Select  Certifcate,to_char(Starting_Date,'yyyy-mm-dd') as Starting_Date,to_char(Ending_Date,'yyyy-mm-dd') as Ending_Date,Designation,Organization from Emp_Experience_Detail Where Emp_id = %d", id);
        ResultSet rs = DBService.statement.executeQuery(query);
        if (rs.next()) {
            e_cer_name.setText(rs.getString("Certifcate"));
            e_org.setText(rs.getString("Organization"));
            e_start_date.setValue(LocalDate.parse(rs.getString("Starting_Date")));
            e_end_date.setValue(LocalDate.parse(rs.getString("Ending_Date")));
            e_design.setText(rs.getString("Designation"));
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
            duty_design.setText(rs.getString("Designation"));
            duty_type.setText(rs.getString("Type"));
        }
    }
    
    public void OnClickSubmitEmp_Detail(ActionEvent event) {
        try {
            
            String Basic_detail = String.format("Insert Into EMP_BASIC_DETAIL(ID, FIRST_NAME, LAST_NAME, FATHER_NAME, EMR_NAME, CNIC, AGE, DOB, NATIONALITY) Values( %d,'%s','%s','%s','%s','%s','%s',to_date('%s','yyyy-mm-dd'),'%s')",
                    Integer.parseInt(id_txt.getText()),
                    F_name_txt.getText(),
                    L_name_txt.getText(),
                    Father_name.getText(),
                    Emr_name.getText(),
                    cnic_txt.getText(),
                    age_txt.getText(),
                    dob.getValue(),
                    N_txt.getText()
            );
            
            String Contact_detail = String.format("Insert Into EMP_CONTACT_DETAIL( EMP_ID, EMAIL, PHONE_NO , O_PHONE_NO, FAX_NO, PER_ADDRES, CUR_ADDRES) Values( %d,'%s','%s','%s','%s','%s','%s')",
                    
                    Integer.parseInt(id_txt.getText()),
                    email_txt.getText(),
                    phone_txt.getText(),
                    o_phone_txt.getText(),
                    fax_txt.getText(),
                    p_address.getText(),
                    c_address.getText()
            );
            
            String Qualification_detail = String.format("Insert Into EMP_QUALIFICATION_DETAIL( EMP_ID, DEGREE_TITLE, INSTITUTE , EXAM_BOARD, TOTAL_MARKS, OBTAIN_MARKS, GRADE, COUNTRY , DO_STARTING , DO_COMP) Values( %d,'%s','%s','%s','%s','%s','%s','%s',to_date('%s','yyyy-mm-dd'),to_date('%s','yyyy-mm-dd'))",
                    
                    Integer.parseInt(id_txt.getText()),
                    degTitle.getText(),
                    inst_txt.getText(),
                    E_board.getText(),
                    t_marks.getText(),
                    o_marks.getText(),
                    grade.getText(),
                    country.getText(),
                    d_o_S.getValue(),
                    d_o_comp.getValue()
            );
            
            String Skills_detail = String.format("Insert Into EMP_SKILLS_DETAIL( EMP_ID, ORGANIZATION, CERTIFCATE , STARTING_DATE,  ENDING_DATE , REMARKS ) Values( %d,'%s','%s',to_date('%s','yyyy-mm-dd'),to_date('%s','yyyy-mm-dd'),'%s')",
                    
                    Integer.parseInt(id_txt.getText()),
                    s_org.getText(),
                    s_cer_name.getText(),
                    s_start_date.getValue(),
                    s_end_date.getValue(),
                    s_remarks.getText()
            );
            
            String Experience_detail = String.format("Insert Into EMP_EXPERIENCE_DETAIL( EMP_ID, CERTIFCATE , STARTING_DATE,  ENDING_DATE , DESIGNATION ,ORGANIZATION) Values( %d,'%s',to_date('%s','yyyy-mm-dd'),to_date('%s','yyyy-mm-dd'),'%s','%s')",
                    
                    Integer.parseInt(id_txt.getText()),
                    e_cer_name.getText(),
                    e_start_date.getValue(),
                    e_end_date.getValue(),
                    e_design.getText(),
                    e_org.getText()
            );
            
            String Duty_detail = String.format("Insert Into EMP_DUTY_DETAILS( EMP_ID, DUTY_TIME , SHIFTS,  SALARY , DESIGNATION ,TYPE) Values( %d,to_date('2020-12-12 %s',  'yyyy-mm-dd hh24:mi:ss'),'%s',%d,'%s','%s')",
                    
                    Integer.parseInt(id_txt.getText()),
                    dutyTime.getValue(),
                    dutyShifts.getText(),
                    Integer.parseInt(salary.getText()),
                    duty_design.getText(),
                    duty_type.getText()
            );
            
            DBService.statement.executeUpdate(Basic_detail);
            DBService.statement.executeUpdate(Contact_detail);
            DBService.statement.executeUpdate(Qualification_detail);
            DBService.statement.executeUpdate(Skills_detail);
            DBService.statement.executeUpdate(Experience_detail);
            DBService.statement.executeUpdate(Duty_detail);
            
            
            save_label.setText("Saved!");
            clearFields();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    public void clearFields() {
        
        F_name_txt.setText(null);
        L_name_txt.setText(null);
        Father_name.setText(null);
        Emr_name.setText(null);
        cnic_txt.setText(null);
        age_txt.setText(null);
        dob.setValue(null);
        N_txt.setText(null);
        id_txt.setText(null);
        email_txt.setText(null);
        phone_txt.setText(null);
        o_phone_txt.setText(null);
        fax_txt.setText(null);
        p_address.setText(null);
        c_address.setText(null);
        degTitle.setText(null);
        inst_txt.setText(null);
        E_board.setText(null);
        t_marks.setText(null);
        o_marks.setText(null);
        grade.setText(null);
        country.setText(null);
        d_o_S.setValue(null);
        d_o_comp.setValue(null);
        s_org.setText(null);
        s_cer_name.setText(null);
        s_start_date.setValue(null);
        s_end_date.setValue(null);
        s_remarks.setText(null);
        e_cer_name.setText(null);
        e_start_date.setValue(null);
        e_end_date.setValue(null);
        e_design.setText(null);
        e_org.setText(null);
        dutyTime.setValue(null);
        dutyShifts.setText(null);
        salary.setText(null);
        duty_design.setText(null);
        duty_type.setText(null);
    }
    
    public void onUpdate(ActionEvent event) {
        try {
            String query = String.format("Update  EMP_BASIC_DETAIL set First_NAME = '%s', Last_Name= '%s' ,Father_Name = '%s',Emr_Name ='%s',CNIC = '%s',Age = '%s',Dob = to_date('%s','yyyy-mm-dd'),Nationality = '%s' Where  id= %d ",
                    F_name_txt.getText(),
                    L_name_txt.getText(),
                    Father_name.getText(),
                    Emr_name.getText(),
                    cnic_txt.getText(),
                    age_txt.getText(),
                    dob.getValue(),
                    N_txt.getText(),
                    Integer.parseInt(id_txt.getText())
            );
            
            DBService.statement.executeUpdate(query);
            save_label.setText("Information Updated!");
            clearFields();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
package dashboard.employee;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class Employee {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty phoneNo;
    private final SimpleStringProperty email;
    private final SimpleStringProperty address;
    private final ObjectProperty<LocalDate> dob;
    private final SimpleStringProperty certificateTitle;
    private final SimpleStringProperty organizationName_edu;
    private final SimpleStringProperty verificationNo;
    private final ObjectProperty<LocalDate> issueDate;
    private final SimpleStringProperty description_edu;
    private final SimpleStringProperty skillTitle;
    private final SimpleStringProperty fieldTitle_skill;
    private final ObjectProperty<LocalDate> skillDate;
    private final SimpleStringProperty description_skill;
    private final SimpleStringProperty jobTitle_exp;
    private final SimpleStringProperty organizationName_exp;
    private final SimpleStringProperty fieldTitle_exp;
    private final SimpleIntegerProperty duration;
    private final SimpleStringProperty jobTitle_duty;
    private final SimpleIntegerProperty teamID;
    private final SimpleStringProperty shift;
    private final SimpleIntegerProperty workingHours;
    private final SimpleIntegerProperty salary;
    private final ObjectProperty<LocalDate> joinDate;
    
    public Employee(Integer id, String name, String phoneNo, String email,
                    String address, LocalDate dob, String certificateTitle,
                    String organizationName_edu, String verificationNo,
                    LocalDate issueDate, String description_edu, String skillTitle,
                    String fieldTitle_skill, LocalDate skillDate, String description_skill,
                    String jobTitle_exp, String organizationName_exp, String fieldTitle_exp,
                    Integer duration, String jobTitle_duty, Integer teamID, String shift,
                    Integer workingHours, Integer salary, LocalDate joinDate) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.phoneNo = new SimpleStringProperty(phoneNo);
        this.email = new SimpleStringProperty(email);
        this.address = new SimpleStringProperty(address);
        this.dob = new SimpleObjectProperty<>(dob);
        this.certificateTitle = new SimpleStringProperty(certificateTitle);
        this.organizationName_edu = new SimpleStringProperty(organizationName_edu);
        this.verificationNo = new SimpleStringProperty(verificationNo);
        this.issueDate = new SimpleObjectProperty<>(issueDate);
        this.description_edu = new SimpleStringProperty(description_edu);
        this.skillTitle = new SimpleStringProperty(skillTitle);
        this.fieldTitle_skill = new SimpleStringProperty(fieldTitle_skill);
        this.skillDate = new SimpleObjectProperty<>(skillDate);
        this.description_skill = new SimpleStringProperty(description_skill);
        this.jobTitle_exp = new SimpleStringProperty(jobTitle_exp);
        this.organizationName_exp = new SimpleStringProperty(organizationName_exp);
        this.fieldTitle_exp = new SimpleStringProperty(fieldTitle_exp);
        this.duration = new SimpleIntegerProperty(duration);
        this.jobTitle_duty = new SimpleStringProperty(jobTitle_duty);
        this.teamID = new SimpleIntegerProperty(teamID);
        this.shift = new SimpleStringProperty(shift);
        this.workingHours = new SimpleIntegerProperty(workingHours);
        this.salary = new SimpleIntegerProperty(salary);
        this.joinDate = new SimpleObjectProperty<>(joinDate);
    }
    
    public int getId() {
        return id.get();
    }
    
    public void setId(int id) {
        this.id.set(id);
    }
    
    public SimpleIntegerProperty idProperty() {
        return id;
    }
    
    public String getName() {
        return name.get();
    }
    
    public void setName(String name) {
        this.name.set(name);
    }
    
    public SimpleStringProperty nameProperty() {
        return name;
    }
    
    public String getPhoneNo() {
        return phoneNo.get();
    }
    
    public void setPhoneNo(String phoneNo) {
        this.phoneNo.set(phoneNo);
    }
    
    public SimpleStringProperty phoneNoProperty() {
        return phoneNo;
    }
    
    public String getEmail() {
        return email.get();
    }
    
    public void setEmail(String email) {
        this.email.set(email);
    }
    
    public SimpleStringProperty emailProperty() {
        return email;
    }
    
    public String getAddress() {
        return address.get();
    }
    
    public void setAddress(String address) {
        this.address.set(address);
    }
    
    public SimpleStringProperty addressProperty() {
        return address;
    }
    
    public LocalDate getDob() {
        return dob.get();
    }
    
    public void setDob(LocalDate dob) {
        this.dob.set(dob);
    }
    
    public ObjectProperty<LocalDate> dobProperty() {
        return dob;
    }
    
    public String getCertificateTitle() {
        return certificateTitle.get();
    }
    
    public void setCertificateTitle(String certificateTitle) {
        this.certificateTitle.set(certificateTitle);
    }
    
    public SimpleStringProperty certificateTitleProperty() {
        return certificateTitle;
    }
    
    public String getOrganizationName_edu() {
        return organizationName_edu.get();
    }
    
    public void setOrganizationName_edu(String organizationName_edu) {
        this.organizationName_edu.set(organizationName_edu);
    }
    
    public SimpleStringProperty organizationName_eduProperty() {
        return organizationName_edu;
    }
    
    public String getVerificationNo() {
        return verificationNo.get();
    }
    
    public void setVerificationNo(String verificationNo) {
        this.verificationNo.set(verificationNo);
    }
    
    public SimpleStringProperty verificationNoProperty() {
        return verificationNo;
    }
    
    public LocalDate getIssueDate() {
        return issueDate.get();
    }
    
    public void setIssueDate(LocalDate issueDate) {
        this.issueDate.set(issueDate);
    }
    
    public ObjectProperty<LocalDate> issueDateProperty() {
        return issueDate;
    }
    
    public String getDescription_edu() {
        return description_edu.get();
    }
    
    public void setDescription_edu(String description_edu) {
        this.description_edu.set(description_edu);
    }
    
    public SimpleStringProperty description_eduProperty() {
        return description_edu;
    }
    
    public String getSkillTitle() {
        return skillTitle.get();
    }
    
    public void setSkillTitle(String skillTitle) {
        this.skillTitle.set(skillTitle);
    }
    
    public SimpleStringProperty skillTitleProperty() {
        return skillTitle;
    }
    
    public String getFieldTitle_skill() {
        return fieldTitle_skill.get();
    }
    
    public void setFieldTitle_skill(String fieldTitle_skill) {
        this.fieldTitle_skill.set(fieldTitle_skill);
    }
    
    public SimpleStringProperty fieldTitle_skillProperty() {
        return fieldTitle_skill;
    }
    
    public LocalDate getSkillDate() {
        return skillDate.get();
    }
    
    public void setSkillDate(LocalDate skillDate) {
        this.skillDate.set(skillDate);
    }
    
    public ObjectProperty<LocalDate> skillDateProperty() {
        return skillDate;
    }
    
    public String getDescription_skill() {
        return description_skill.get();
    }
    
    public void setDescription_skill(String description_skill) {
        this.description_skill.set(description_skill);
    }
    
    public SimpleStringProperty description_skillProperty() {
        return description_skill;
    }
    
    public String getJobTitle_exp() {
        return jobTitle_exp.get();
    }
    
    public void setJobTitle_exp(String jobTitle_exp) {
        this.jobTitle_exp.set(jobTitle_exp);
    }
    
    public SimpleStringProperty jobTitle_expProperty() {
        return jobTitle_exp;
    }
    
    public String getOrganizationName_exp() {
        return organizationName_exp.get();
    }
    
    public void setOrganizationName_exp(String organizationName_exp) {
        this.organizationName_exp.set(organizationName_exp);
    }
    
    public SimpleStringProperty organizationName_expProperty() {
        return organizationName_exp;
    }
    
    public String getFieldTitle_exp() {
        return fieldTitle_exp.get();
    }
    
    public void setFieldTitle_exp(String fieldTitle_exp) {
        this.fieldTitle_exp.set(fieldTitle_exp);
    }
    
    public SimpleStringProperty fieldTitle_expProperty() {
        return fieldTitle_exp;
    }
    
    public int getDuration() {
        return duration.get();
    }
    
    public void setDuration(int duration) {
        this.duration.set(duration);
    }
    
    public SimpleIntegerProperty durationProperty() {
        return duration;
    }
    
    public String getJobTitle_duty() {
        return jobTitle_duty.get();
    }
    
    public void setJobTitle_duty(String jobTitle_duty) {
        this.jobTitle_duty.set(jobTitle_duty);
    }
    
    public SimpleStringProperty jobTitle_dutyProperty() {
        return jobTitle_duty;
    }
    
    public int getTeamID() {
        return teamID.get();
    }
    
    public void setTeamID(int teamID) {
        this.teamID.set(teamID);
    }
    
    public SimpleIntegerProperty teamIDProperty() {
        return teamID;
    }
    
    public String getShift() {
        return shift.get();
    }
    
    public void setShift(String shift) {
        this.shift.set(shift);
    }
    
    public SimpleStringProperty shiftProperty() {
        return shift;
    }
    
    public int getWorkingHours() {
        return workingHours.get();
    }
    
    public void setWorkingHours(int workingHours) {
        this.workingHours.set(workingHours);
    }
    
    public SimpleIntegerProperty workingHoursProperty() {
        return workingHours;
    }
    
    public int getSalary() {
        return salary.get();
    }
    
    public void setSalary(int salary) {
        this.salary.set(salary);
    }
    
    public SimpleIntegerProperty salaryProperty() {
        return salary;
    }
    
    public LocalDate getJoinDate() {
        return joinDate.get();
    }
    
    public void setJoinDate(LocalDate joinDate) {
        this.joinDate.set(joinDate);
    }
    
    public ObjectProperty<LocalDate> joinDateProperty() {
        return joinDate;
    }
}
    
    

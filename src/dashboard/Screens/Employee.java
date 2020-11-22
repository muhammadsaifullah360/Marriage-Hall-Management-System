package dashboard.Screens;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Employee {
    
    SimpleIntegerProperty id;
    SimpleStringProperty firstName;
    SimpleStringProperty lastName;
    SimpleStringProperty fatherName;
    SimpleStringProperty emrName;
    SimpleStringProperty cnic;
    SimpleStringProperty age;
    SimpleStringProperty DOB;
    SimpleStringProperty nationality;
    
    
    public Employee(int id, String firstName, String lastName, String fatherName, String emrName, String cnic,String age ,String DOB,  String nationality) {
        this.id = new SimpleIntegerProperty(id);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.fatherName = new SimpleStringProperty(fatherName);
        this.emrName = new SimpleStringProperty(emrName);
        this.cnic = new SimpleStringProperty(cnic);
        this.age = new SimpleStringProperty(age);
        this.DOB = new SimpleStringProperty(DOB);
        this.nationality = new SimpleStringProperty(nationality);
    }
    
    public String getDOB() {
        return DOB.get();
    }
    
    public void setDOB(String DOB) {
        this.DOB.set(DOB);
    }
    
    public SimpleStringProperty DOBProperty() {
        return DOB;
    }
 
    
    public String getAge() {
        return age.get();
    }
    
    public void setAge(String age) {
        this.age.set(age);
    }
    
    public SimpleStringProperty ageProperty() {
        return age;
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
    
    public String getFirstName() {
        return firstName.get();
    }
    
    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }
    
    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName.get();
    }
    
    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }
    
    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }
    
    public String getFatherName() {
        return fatherName.get();
    }
    
    public void setFatherName(String fatherName) {
        this.fatherName.set(fatherName);
    }
    
    public SimpleStringProperty fatherNameProperty() {
        return fatherName;
    }
    
    public String getEmrName() {
        return emrName.get();
    }
    
    public void setEmrName(String emrName) {
        this.emrName.set(emrName);
    }
    
    public SimpleStringProperty emrNameProperty() {
        return emrName;
    }
    
    public String getNationality() {
        return nationality.get();
    }
    
    public void setNationality(String nationality) {
        this.nationality.set(nationality);
    }
    
    public SimpleStringProperty nationalityProperty() {
        return nationality;
    }
    
    public String getCnic() {
        return cnic.get();
    }
    
    public void setCnic(String cnic) {
        this.cnic.set(cnic);
    }
    
    public SimpleStringProperty cnicProperty() {
        return cnic;
    }
}

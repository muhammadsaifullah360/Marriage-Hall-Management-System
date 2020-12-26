package dashboard.screens;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Employee {
    
    
    SimpleIntegerProperty id;
    SimpleStringProperty Name;
    SimpleStringProperty phone;
    SimpleStringProperty email;
    SimpleStringProperty address;
    
    public String getDob() {
        return dob.get();
    }
    
    public SimpleStringProperty dobProperty() {
        return dob;
    }
    
    public void setDob(String dob) {
        this.dob.set(dob);
    }
    
    SimpleStringProperty dob;
    
    public Employee(int id, String name, String phone, String email, String address,String dob) {
        this.id = new SimpleIntegerProperty(id);
        this.Name = new SimpleStringProperty(name);
        this.phone = new SimpleStringProperty(phone);
        this.email = new SimpleStringProperty(email);
        this.address =  new SimpleStringProperty(address);
        this.dob = new SimpleStringProperty(dob);
    }
    
    public int getId() {
        return id.get();
    }
    
    public SimpleIntegerProperty idProperty() {
        return id;
    }
    
    public void setId(int id) {
        this.id.set(id);
    }
    
    public String getName() {
        return Name.get();
    }
    
    public SimpleStringProperty nameProperty() {
        return Name;
    }
    
    public void setName(String name) {
        this.Name.set(name);
    }
    
    public String getPhone() {
        return phone.get();
    }
    
    public SimpleStringProperty phoneProperty() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone.set(phone);
    }
    
    public String getEmail() {
        return email.get();
    }
    
    public SimpleStringProperty emailProperty() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email.set(email);
    }
    
    public String getAddress() {
        return address.get();
    }
    
    public SimpleStringProperty addressProperty() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address.set(address);
    }
 
}
    
    

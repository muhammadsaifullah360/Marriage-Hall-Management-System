package dashboard.screens;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class supplier {
    private final SimpleIntegerProperty id;
    
    private final SimpleStringProperty name;
    private final SimpleStringProperty phoneNo;
    private final SimpleStringProperty email;
    private final SimpleStringProperty address;
    private final SimpleStringProperty type;
    
    public supplier(Integer id, String name, String phoneNo, String email, String address, String type) {
        this.id =  new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.phoneNo = new SimpleStringProperty(phoneNo);
        this.email =new SimpleStringProperty(email) ;
        this.address =new SimpleStringProperty(address) ;
        this.type = new SimpleStringProperty(type);
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
        return name.get();
    }
    
    public SimpleStringProperty nameProperty() {
        return name;
    }
    
    public void setName(String name) {
        this.name.set(name);
    }
    
    public String getPhoneNo() {
        return phoneNo.get();
    }
    
    public SimpleStringProperty phoneNoProperty() {
        return phoneNo;
    }
    
    public void setPhoneNo(String phoneNo) {
        this.phoneNo.set(phoneNo);
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
    
    public String getType() {
        return type.get();
    }
    
    public SimpleStringProperty typeProperty() {
        return type;
    }
    
    public void setType(String type) {
        this.type.set(type);
    }
}

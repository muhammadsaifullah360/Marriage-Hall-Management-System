package dashboard.screens;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class viewBooking {
    public int getId() {
        return id.get();
    }
    
    public SimpleIntegerProperty idProperty() {
        return id;
    }
    
    public void setId(int id) {
        this.id.set(id);
    }
    
    public String getEventType() {
        return eventType.get();
    }
    
    public SimpleStringProperty eventTypeProperty() {
        return eventType;
    }
    
    public void setEventType(String eventType) {
        this.eventType.set(eventType);
    }
    
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty eventType;
    
    public viewBooking(Integer id, String eventType) {
        this.id =  new SimpleIntegerProperty(id);
        this.eventType =  new SimpleStringProperty(eventType);
    }
}

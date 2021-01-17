package dashboard.screens;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.time.LocalTime;

public class viewBooking {
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty eventType;
    private final SimpleIntegerProperty noOfPersons;
    private final ObjectProperty<LocalTime> eventStartTime;
    private final ObjectProperty<LocalTime> eventEndTime;
    private final SimpleIntegerProperty duration;
    private final SimpleStringProperty hallNo;
    private final ObjectProperty<LocalDate> eventDate;
    
    
    public viewBooking(Integer id, String eventType, Integer noOfPersons, LocalTime eventStartTime, LocalTime eventEndTime, Integer duration, String hallNo,LocalDate eventDate) {
        this.id = new SimpleIntegerProperty(id);
        this.eventType = new SimpleStringProperty(eventType);
        this.noOfPersons = new SimpleIntegerProperty(noOfPersons);
        this.eventStartTime =new SimpleObjectProperty<>(eventStartTime) ;
        this.eventEndTime = new SimpleObjectProperty<>(eventEndTime);
        this.duration = new SimpleIntegerProperty(duration);
        this.hallNo = new SimpleStringProperty(hallNo);
        this.eventDate = new SimpleObjectProperty<>(eventDate);
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
    
    public String getEventType() {
        return eventType.get();
    }
    
    public SimpleStringProperty eventTypeProperty() {
        return eventType;
    }
    
    public void setEventType(String eventType) {
        this.eventType.set(eventType);
    }
    
    public int getNoOfPersons() {
        return noOfPersons.get();
    }
    
    public SimpleIntegerProperty noOfPersonsProperty() {
        return noOfPersons;
    }
    
    public void setNoOfPersons(int noOfPersons) {
        this.noOfPersons.set(noOfPersons);
    }
    
    public LocalTime getEventStartTime() {
        return eventStartTime.get();
    }
    
    public ObjectProperty<LocalTime> eventStartTimeProperty() {
        return eventStartTime;
    }
    
    public void setEventStartTime(LocalTime eventStartTime) {
        this.eventStartTime.set(eventStartTime);
    }
    
    public LocalTime getEventEndTime() {
        return eventEndTime.get();
    }
    
    public ObjectProperty<LocalTime> eventEndTimeProperty() {
        return eventEndTime;
    }
    
    public void setEventEndTime(LocalTime eventEndTime) {
        this.eventEndTime.set(eventEndTime);
    }
    
    public int getDuration() {
        return duration.get();
    }
    
    public SimpleIntegerProperty durationProperty() {
        return duration;
    }
    
    public void setDuration(int duration) {
        this.duration.set(duration);
    }
    
    public String getHallNo() {
        return hallNo.get();
    }
    
    public SimpleStringProperty hallNoProperty() {
        return hallNo;
    }
    
    public void setHallNo(String hallNo) {
        this.hallNo.set(hallNo);
    }
    
    public LocalDate getEventDate() {
        return eventDate.get();
    }
    
    public ObjectProperty<LocalDate> eventDateProperty() {
        return eventDate;
    }
    
    public void setEventDate(LocalDate eventDate) {
        this.eventDate.set(eventDate);
    }
    
}

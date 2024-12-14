package cse213.tibproject.models;

import javafx.beans.property.*;
import java.time.LocalDate;

public class Event {
    private final IntegerProperty id;
    private final StringProperty name;
    private final ObjectProperty<LocalDate> date;
    private final StringProperty location;

    public Event(int id, String name, LocalDate date, String location) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.date = new SimpleObjectProperty<>(date);
        this.location = new SimpleStringProperty(location);
    }

    // Getters
    public Integer getId() { return id.get(); }
    public String getName() { return name.get(); }
    public LocalDate getDate() { return date.get(); }
    public String getLocation() { return location.get(); }

    // Property accessors
    public IntegerProperty idProperty() { return id; }
    public StringProperty nameProperty() { return name; }
    public ObjectProperty<LocalDate> dateProperty() { return date; }
    public StringProperty locationProperty() { return location; }

    // Setters
    public void setName(String name) { this.name.set(name); }
    public void setDate(LocalDate date) { this.date.set(date); }
    public void setLocation(String location) { this.location.set(location); }
} 
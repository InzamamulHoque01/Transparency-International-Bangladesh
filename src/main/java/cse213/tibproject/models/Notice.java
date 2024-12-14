package cse213.tibproject.models;

import javafx.beans.property.*;

public class Notice {
    private final IntegerProperty id;
    private final StringProperty date;
    private final StringProperty template;
    private final StringProperty status;

    public Notice(int id, String date, String template, String status) {
        this.id = new SimpleIntegerProperty(id);
        this.date = new SimpleStringProperty(date);
        this.template = new SimpleStringProperty(template);
        this.status = new SimpleStringProperty(status);
    }

    // Getters
    public Integer getId() { return id.get(); }
    public String getDate() { return date.get(); }
    public String getTemplate() { return template.get(); }
    public String getStatus() { return status.get(); }

    // Property accessors
    public IntegerProperty idProperty() { return id; }
    public StringProperty dateProperty() { return date; }
    public StringProperty templateProperty() { return template; }
    public StringProperty statusProperty() { return status; }
} 
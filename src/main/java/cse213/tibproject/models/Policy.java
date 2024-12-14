package cse213.tibproject.models;

import javafx.beans.property.*;

public class Policy {
    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty details;
    private final StringProperty status;
    private final StringProperty lastReview;

    public Policy(int id, String name, String details, String status, String lastReview) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.details = new SimpleStringProperty(details);
        this.status = new SimpleStringProperty(status);
        this.lastReview = new SimpleStringProperty(lastReview);
    }

    // Getters
    public Integer getId() { return id.get(); }
    public String getName() { return name.get(); }
    public String getDetails() { return details.get(); }
    public String getStatus() { return status.get(); }
    public String getLastReview() { return lastReview.get(); }

    // Property accessors
    public IntegerProperty idProperty() { return id; }
    public StringProperty nameProperty() { return name; }
    public StringProperty detailsProperty() { return details; }
    public StringProperty statusProperty() { return status; }
    public StringProperty lastReviewProperty() { return lastReview; }
} 
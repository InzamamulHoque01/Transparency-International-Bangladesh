package cse213.tibproject.models;

import javafx.beans.property.*;

public class Member {
    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty email;
    private final StringProperty phone;
    private final StringProperty status;

    public Member(int id, String name, String email, String phone, String status) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);
        this.phone = new SimpleStringProperty(phone);
        this.status = new SimpleStringProperty(status);
    }

    // Getters
    public Integer getId() { return id.get(); }
    public String getName() { return name.get(); }
    public String getEmail() { return email.get(); }
    public String getPhone() { return phone.get(); }
    public String getStatus() { return status.get(); }

    // Property accessors
    public IntegerProperty idProperty() { return id; }
    public StringProperty nameProperty() { return name; }
    public StringProperty emailProperty() { return email; }
    public StringProperty phoneProperty() { return phone; }
    public StringProperty statusProperty() { return status; }

    // Setters
    public void setName(String name) { this.name.set(name); }
    public void setEmail(String email) { this.email.set(email); }
    public void setPhone(String phone) { this.phone.set(phone); }
    public void setStatus(String status) { this.status.set(status); }
} 
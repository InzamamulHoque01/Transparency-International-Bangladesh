package cse213.tibproject.models;

import javafx.beans.property.*;
import java.time.LocalDate;

public class Campaign {
    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty description;
    private final DoubleProperty targetAmount;
    private final DoubleProperty currentAmount;
    private final ObjectProperty<LocalDate> startDate;
    private final ObjectProperty<LocalDate> endDate;

    public Campaign(int id, String name, String description, double targetAmount, 
                   double currentAmount, LocalDate startDate, LocalDate endDate) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.targetAmount = new SimpleDoubleProperty(targetAmount);
        this.currentAmount = new SimpleDoubleProperty(currentAmount);
        this.startDate = new SimpleObjectProperty<>(startDate);
        this.endDate = new SimpleObjectProperty<>(endDate);
    }

    // Getters
    public Integer getId() { return id.get(); }
    public String getName() { return name.get(); }
    public String getDescription() { return description.get(); }
    public Double getTargetAmount() { return targetAmount.get(); }
    public Double getCurrentAmount() { return currentAmount.get(); }
    public LocalDate getStartDate() { return startDate.get(); }
    public LocalDate getEndDate() { return endDate.get(); }

    // Setters
    public void setName(String name) { this.name.set(name); }
    public void setDescription(String description) { this.description.set(description); }
    public void setTargetAmount(double amount) { this.targetAmount.set(amount); }
    public void setCurrentAmount(double amount) { this.currentAmount.set(amount); }
    public void setStartDate(LocalDate date) { this.startDate.set(date); }
    public void setEndDate(LocalDate date) { this.endDate.set(date); }

    // Property accessors
    public StringProperty nameProperty() { return name; }
    public StringProperty descriptionProperty() { return description; }
    public DoubleProperty targetAmountProperty() { return targetAmount; }
    public DoubleProperty currentAmountProperty() { return currentAmount; }
    public ObjectProperty<LocalDate> startDateProperty() { return startDate; }
    public ObjectProperty<LocalDate> endDateProperty() { return endDate; }
} 
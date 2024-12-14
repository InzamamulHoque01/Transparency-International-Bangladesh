package cse213.tibproject.models;

import javafx.beans.property.*;
import java.time.LocalDate;

public class Expense {
    private final IntegerProperty id;
    private final StringProperty name;
    private final DoubleProperty amount;
    private final ObjectProperty<LocalDate> date;
    private final StringProperty category;

    public Expense(int id, String name, double amount, LocalDate date, String category) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.amount = new SimpleDoubleProperty(amount);
        this.date = new SimpleObjectProperty<>(date);
        this.category = new SimpleStringProperty(category);
    }

    // Getters
    public Integer getId() { return id.get(); }
    public String getName() { return name.get(); }
    public Double getAmount() { return amount.get(); }
    public LocalDate getDate() { return date.get(); }
    public String getCategory() { return category.get(); }

    // Property accessors
    public IntegerProperty idProperty() { return id; }
    public StringProperty nameProperty() { return name; }
    public DoubleProperty amountProperty() { return amount; }
    public ObjectProperty<LocalDate> dateProperty() { return date; }
    public StringProperty categoryProperty() { return category; }
} 
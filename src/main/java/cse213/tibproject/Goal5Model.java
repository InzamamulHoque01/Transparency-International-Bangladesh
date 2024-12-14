package cse213.tibproject;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Goal5Model implements Serializable {
    private static final long serialVersionUID = 1L;

    private transient Button performanceRecordButton;
    private transient ComboBox<String> recordComboBox;
    private transient Button submitButton;
    private transient Text recordText;

    private List<String> records; // To hold ComboBox data persistently

    // Constructor
    public Goal5Model() {
        records = new ArrayList<>();
    }

    // Getter and Setter for Performance Record Button
    public Button getPerformanceRecordButton() {
        return performanceRecordButton;
    }

    public void setPerformanceRecordButton(Button performanceRecordButton) {
        this.performanceRecordButton = performanceRecordButton;
    }

    // Getter and Setter for Record ComboBox
    public ComboBox<String> getRecordComboBox() {
        return recordComboBox;
    }

    public void setRecordComboBox(ComboBox<String> recordComboBox) {
        this.recordComboBox = recordComboBox;
    }

    // Getter and Setter for Submit Button
    public Button getSubmitButton() {
        return submitButton;
    }

    public void setSubmitButton(Button submitButton) {
        this.submitButton = submitButton;
    }

    // Getter and Setter for Record Text
    public Text getRecordText() {
        return recordText;
    }

    public void setRecordText(Text recordText) {
        this.recordText = recordText;
    }

    // Getter and Setter for Records
    public List<String> getRecords() {
        return records;
    }

    public void setRecords(List<String> records) {
        this.records = records;
    }

    // Save the object to a binary file
    public void saveToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(this);
            System.out.println("Data saved successfully to " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    // Load the object from a binary file
    public static Goal5Model loadFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Goal5Model) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }
        return new Goal5Model(); // Return a new instance if loading fails
    }
}

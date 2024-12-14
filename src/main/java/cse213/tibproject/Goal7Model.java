package cse213.tibproject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Goal7Model implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<String> specificResourceCategories; // To store the ComboBox data
    private String summary;                          // To store the TextField data

    // Constructor
    public Goal7Model() {
        specificResourceCategories = new ArrayList<>();
        summary = "";
    }

    // Getters and Setters
    public List<String> getSpecificResourceCategories() {
        return specificResourceCategories;
    }

    public void setSpecificResourceCategories(List<String> specificResourceCategories) {
        this.specificResourceCategories = specificResourceCategories;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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
    public static Goal7Model loadFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Goal7Model) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }
        return new Goal7Model(); // Return a new instance if loading fails
    }
}

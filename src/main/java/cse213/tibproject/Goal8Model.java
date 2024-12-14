package cse213.tibproject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Goal8Model implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<String> resourceCategories; // To store ComboBox data
    private String summary;                  // To store TextField data

    // Constructor
    public Goal8Model() {
        resourceCategories = new ArrayList<>();
        summary = "";
    }

    // Getters and Setters
    public List<String> getResourceCategories() {
        return resourceCategories;
    }

    public void setResourceCategories(List<String> resourceCategories) {
        this.resourceCategories = resourceCategories;
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
    public static Goal8Model loadFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Goal8Model) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }
        return new Goal8Model(); // Return a new instance if loading fails
    }
}

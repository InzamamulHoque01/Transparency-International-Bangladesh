package cse213.tibproject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Goal6Model implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<String> recordList;          // To hold ComboBox data for records
    private List<String> complaintStatusList; // To hold ComboBox data for complaint status
    private String investigationFindings;    // To hold the content of the TextField

    // Constructor
    public Goal6Model() {
        recordList = new ArrayList<>();
        complaintStatusList = new ArrayList<>();
        investigationFindings = "";
    }

    // Getters and Setters
    public List<String> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<String> recordList) {
        this.recordList = recordList;
    }

    public List<String> getComplaintStatusList() {
        return complaintStatusList;
    }

    public void setComplaintStatusList(List<String> complaintStatusList) {
        this.complaintStatusList = complaintStatusList;
    }

    public String getInvestigationFindings() {
        return investigationFindings;
    }

    public void setInvestigationFindings(String investigationFindings) {
        this.investigationFindings = investigationFindings;
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
    public static Goal6Model loadFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Goal6Model) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }
        return new Goal6Model(); // Return a new instance if loading fails
    }
}

package cse213.tibproject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Goal1Model implements Serializable {
    private static final long serialVersionUID = 1L; // Ensures compatibility during deserialization

    private String id;
    private String name;
    private String address;
    private String contactNumber;
    private String contract;

    // Constructor
    public Goal1Model(String id, String name, String address, String contactNumber, String contract) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
        this.contract = contract;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    // Method to write a list of Performance objects to a binary file
    public static void writeToFile(List<Goal1Model> performances, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(performances); // Write the list of performances to the file
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            throw e; // Rethrow the exception to handle it elsewhere
        }
    }

    // Method to read a list of Performance objects from a binary file
    @SuppressWarnings("unchecked")
    public static List<Goal1Model> readFromFile(String filePath) throws IOException, ClassNotFoundException {
        File file = new File(filePath);
        if (!file.exists()) {
            return new ArrayList<>();  // Return an empty list if the file doesn't exist
        }

        // Open the file for reading
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<Goal1Model>) ois.readObject(); // Read the list of performances
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
            throw e; // Rethrow the exception to handle it elsewhere
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found: " + e.getMessage());
            throw e; // Handle class deserialization issues
        }
    }

    @Override
    public String toString() {
        return "Performance{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", contract='" + contract + '\'' +
                '}';
    }
}

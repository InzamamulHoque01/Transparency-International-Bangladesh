package cse213.tibproject;
import java.io.*;
import java.util.List;

public class Goal3Model implements Serializable {
    private String comment;
    private String report;

    public Goal3Model(String report, String comment) {
        this.report = report;
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    // Method to save the object to a binary file
    public static void saveToFile(Goal3Model model, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(model);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to read the object from a binary file
    public static Goal3Model readFromFile(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (Goal3Model) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

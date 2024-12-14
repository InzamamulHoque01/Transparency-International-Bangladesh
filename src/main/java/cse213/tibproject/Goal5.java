package cse213.tibproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class Goal5 {

    @FXML
    private ComboBox<String> recordComboBox;



;

    private Goal5Model model;
    @FXML
    private Label messageLabel;

    @FXML
    private void initialize() {
        // Initialize the model
        model = Goal5Model.loadFromFile("goal5data.bin"); // Load existing data from the binary file

        // Populate ComboBox with data from the model
        recordComboBox.getItems().addAll(model.getRecords());

        // Set up event handlers


    }

    @FXML
    private void performanceRecordButtonOnAction() {
        // Add a new record to the ComboBox and the model
        String newRecord = "Record " + (recordComboBox.getItems().size() + 1);
        recordComboBox.getItems().add(newRecord);
        model.getRecords().add(newRecord);

        // Save the updated data to the binary file
        model.saveToFile("goal5data.bin");
messageLabel.setText("Success");
    }

    @FXML
    private void submitButtonOnAction() {
        // Display the selected record in the Text element
        String selectedRecord = recordComboBox.getValue();
        if (selectedRecord != null) {
            messageLabel.setText("Selected Record: " + selectedRecord);
        } else {
            messageLabel.setText("No record selected.");
        }
    }
}

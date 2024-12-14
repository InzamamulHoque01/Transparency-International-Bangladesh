package cse213.tibproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

public class Goal5 {

    @FXML
    private Button performanceRecordButton;

    @FXML
    private ComboBox<String> recordComboBox;

    @FXML
    private Button submitButton;

    @FXML
    private Text recordText;

    private Goal5Model model;

    @FXML
    private void initialize() {
        // Initialize the model
        model = Goal5Model.loadFromFile("goal5data.bin"); // Load existing data from the binary file

        // Populate ComboBox with data from the model
        recordComboBox.getItems().addAll(model.getRecords());

        // Set up event handlers
        performanceRecordButton.setOnAction(event -> performanceRecordButtonOnAction());
        submitButton.setOnAction(event -> submitButtonOnAction());
    }

    @FXML
    private void performanceRecordButtonOnAction() {
        // Add a new record to the ComboBox and the model
        String newRecord = "Record " + (recordComboBox.getItems().size() + 1);
        recordComboBox.getItems().add(newRecord);
        model.getRecords().add(newRecord);

        // Save the updated data to the binary file
        model.saveToFile("goal5data.bin");
        recordText.setText("New record added: " + newRecord);
    }

    @FXML
    private void submitButtonOnAction() {
        // Display the selected record in the Text element
        String selectedRecord = recordComboBox.getValue();
        if (selectedRecord != null) {
            recordText.setText("Selected Record: " + selectedRecord);
        } else {
            recordText.setText("No record selected.");
        }
    }
}

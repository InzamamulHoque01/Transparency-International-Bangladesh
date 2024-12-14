package cse213.tibproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Goal6 {

    @FXML
    private Button specificComplaintButton;

    @FXML
    private ComboBox<String> recordComboBox;

    @FXML
    private ComboBox<String> complaintStatusComboBox;

    @FXML
    private TextField investigatingFinfingsTextField;

    @FXML
    private Button submitButton;

    private Goal6Model model;

    @FXML
    private void initialize() {
        // Load model data
        model = Goal6Model.loadFromFile("goal6data.bin");

        // Populate ComboBoxes with model data
        recordComboBox.getItems().addAll(model.getRecordList());
        complaintStatusComboBox.getItems().addAll(model.getComplaintStatusList());

        // Set initial values
        investigatingFinfingsTextField.setText(model.getInvestigationFindings());

        // Set up event handlers
        specificComplaintButton.setOnAction(event -> specificComplaintButtonOnAction());
        submitButton.setOnAction(event -> submitButtonOnAction());
    }

    @FXML
    private void specificComplaintButtonOnAction() {
        // Add a new record to the ComboBox and model
        String newRecord = "Record " + (recordComboBox.getItems().size() + 1);
        recordComboBox.getItems().add(newRecord);
        model.getRecordList().add(newRecord);

        // Save updated data to file
        model.saveToFile("goal6data.bin");
    }

    @FXML
    private void submitButtonOnAction() {
        // Get the selected values and text input
        String selectedRecord = recordComboBox.getValue();
        String selectedComplaintStatus = complaintStatusComboBox.getValue();
        String findings = investigatingFinfingsTextField.getText();

        // Update model with the latest data
        model.setInvestigationFindings(findings);

        // Save updated data to file
        model.saveToFile("goal6data.bin");

        // Debugging or confirmation message
        System.out.println("Data submitted successfully:");
        System.out.println("Record: " + selectedRecord);
        System.out.println("Complaint Status: " + selectedComplaintStatus);
        System.out.println("Investigation Findings: " + findings);
    }
}

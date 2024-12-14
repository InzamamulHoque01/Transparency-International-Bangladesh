package cse213.tibproject;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Goal4 {

    @FXML
    private Button detailedDataButton;

    @FXML
    private ComboBox<String> recordComboBox;

    @FXML
    private Button submitButton;

    @FXML
    private Text recordText;

    private Goal4Model model;

    @FXML
    private void initialize() {
        // Initialize the model and link FXML components to the model's getters/setters
        model = new Goal4Model();
        model.setDetailedDataButton(detailedDataButton);
        model.setRecordComboBox(recordComboBox);
        model.setSubmitButton(submitButton);
        model.setRecordText(recordText);

        // Optionally, populate the ComboBox with some items
        recordComboBox.getItems().addAll("Training module", "Completion Status", "Pending requirements");
    }

    @FXML
    private void detailedDataButtonOnAction() {
        // Logic for when the Detailed Data button is clicked
        String selectedRecord = model.getRecordComboBox().getValue();
        System.out.println("Selected Record: " + selectedRecord);

        // Additional logic for detailed data can go here
    }

    @FXML
    private void submitButtonOnAction() {
        // Logic for when the Submit button is clicked
        String selectedRecord = model.getRecordComboBox().getValue();
        if (selectedRecord != null) {
            model.getRecordText().setText("Selected Record: " + selectedRecord);
        } else {
            model.getRecordText().setText("No record selected.");
        }
    }
}

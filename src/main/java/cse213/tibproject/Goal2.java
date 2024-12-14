package cse213.tibproject;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;

public class Goal2 {

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatepicker;

    @FXML
    private ComboBox<String> timelineStatusComboBox;

    @FXML
    private TextArea resultTextArea;

    @FXML
    private Button generateButton;

    @FXML
    private Button submitButton;

    @FXML
    private Label startDateLabel;

    @FXML
    private Label endDateLabel;

    @FXML
    private Label timelineStatusLabel;

    private Goal2Model model;

    @FXML
    private void initialize() {
        // Initialize the model and link the FXML elements with the model's getters/setters
        model = new Goal2Model();
        model.setStartDatePicker(startDatePicker);
        model.setEndDatepicker(endDatepicker);
        model.setTimelineStatusComboBox(timelineStatusComboBox);
        model.setResultTextArea(resultTextArea);
        model.setGenerateButton(generateButton);
        model.setSubmitButton(submitButton);
        model.setStartDateLabel(startDateLabel);
        model.setEndDateLabel(endDateLabel);
        model.setTimelineStatusLabel(timelineStatusLabel);

        // Optionally, populate combo box with items (if needed)
        timelineStatusComboBox.getItems().addAll("Resolved", "Pending", "Delayed");
    }

    @FXML
    private void generateButtonOnAction() {
        // Logic for handling the Generate button action
        String startDate = model.getStartDatePicker().getValue() != null ? model.getStartDatePicker().getValue().toString() : "N/A";
        String endDate = model.getEndDatepicker().getValue() != null ? model.getEndDatepicker().getValue().toString() : "N/A";

        // Set result in TextArea
        model.getResultTextArea().setText("Start Date: " + startDate + "\nEnd Date: " + endDate);
    }

    @FXML
    private void submitButtonOnAction() {
        // Logic for handling the Submit button action
        String timelineStatus = model.getTimelineStatusComboBox().getValue();
        if (timelineStatus == null) {
            model.getResultTextArea().appendText("\nPlease select a timeline status.");
        } else {
            model.getResultTextArea().appendText("\nTimeline Status: " + timelineStatus);
        }
    }
}

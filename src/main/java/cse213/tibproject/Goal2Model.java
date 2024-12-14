package cse213.tibproject;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;

public class Goal2Model {

    private DatePicker startDatePicker;
    private DatePicker endDatepicker;
    private ComboBox<String> timelineStatusComboBox;
    private TextArea resultTextArea;
    private Button generateButton;
    private Button submitButton;
    private Label startDateLabel;
    private Label endDateLabel;
    private Label timelineStatusLabel;

    // Getter and Setter for Start DatePicker
    public DatePicker getStartDatePicker() {
        return startDatePicker;
    }

    public void setStartDatePicker(DatePicker startDatePicker) {
        this.startDatePicker = startDatePicker;
    }

    // Getter and Setter for End DatePicker
    public DatePicker getEndDatepicker() {
        return endDatepicker;
    }

    public void setEndDatepicker(DatePicker endDatepicker) {
        this.endDatepicker = endDatepicker;
    }

    // Getter and Setter for Timeline Status ComboBox
    public ComboBox<String> getTimelineStatusComboBox() {
        return timelineStatusComboBox;
    }

    public void setTimelineStatusComboBox(ComboBox<String> timelineStatusComboBox) {
        this.timelineStatusComboBox = timelineStatusComboBox;
    }

    // Getter and Setter for Result TextArea
    public TextArea getResultTextArea() {
        return resultTextArea;
    }

    public void setResultTextArea(TextArea resultTextArea) {
        this.resultTextArea = resultTextArea;
    }

    // Getter and Setter for Generate Button
    public Button getGenerateButton() {
        return generateButton;
    }

    public void setGenerateButton(Button generateButton) {
        this.generateButton = generateButton;
    }

    // Getter and Setter for Submit Button
    public Button getSubmitButton() {
        return submitButton;
    }

    public void setSubmitButton(Button submitButton) {
        this.submitButton = submitButton;
    }

    // Getter and Setter for Start Date Label
    public Label getStartDateLabel() {
        return startDateLabel;
    }

    public void setStartDateLabel(Label startDateLabel) {
        this.startDateLabel = startDateLabel;
    }

    // Getter and Setter for End Date Label
    public Label getEndDateLabel() {
        return endDateLabel;
    }

    public void setEndDateLabel(Label endDateLabel) {
        this.endDateLabel = endDateLabel;
    }

    // Getter and Setter for Timeline Status Label
    public Label getTimelineStatusLabel() {
        return timelineStatusLabel;
    }

    public void setTimelineStatusLabel(Label timelineStatusLabel) {
        this.timelineStatusLabel = timelineStatusLabel;
    }
}


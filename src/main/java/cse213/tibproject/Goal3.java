package cse213.tibproject;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.File;

public class Goal3 {
    @FXML
    private ComboBox<String> reportComboBox;
    @FXML
    private TextField commentsTextField;
    @FXML
    private TextArea displayTextArea;

    // Action triggered when the "Submit" button is clicked
    @FXML
    private void submitButtonOnAction() {
        String report = reportComboBox.getValue();
        String comment = commentsTextField.getText();

        // Create a Goal3Model object and save it to a file
        Goal3Model model = new Goal3Model(report, comment);
        Goal3Model.saveToFile(model, "goal3Data.bin");

        // Display the data in the TextArea
        displayTextArea.setText("Report: " + report + "\nComment: " + comment);

        // Optional: Load the model from the file and display it
        Goal3Model loadedModel = Goal3Model.readFromFile("goal3Data.bin");
        if (loadedModel != null) {
            displayTextArea.appendText("\n\nLoaded from file:\nReport: " + loadedModel.getReport() + "\nComment: " + loadedModel.getComment());
        }
    }

    // Method to initialize the ComboBox with report options
    public void initialize() {
        reportComboBox.getItems().addAll("Mid", "Moderate", "Excessive");
    }
}

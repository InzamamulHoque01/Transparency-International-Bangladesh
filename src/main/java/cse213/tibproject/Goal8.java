package cse213.tibproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class Goal8 {

    @FXML
    private ComboBox<String> resourceComboBox;

    @FXML
    private TextField summarytextField;

    @FXML
    private Button submitButton;

    private Goal8Model model;

    @FXML
    private void initialize() {
        // Load the model from the binary file
        model = Goal8Model.loadFromFile("goal8data.bin");

        // Populate the ComboBox with existing resource categories
        resourceComboBox.getItems().addAll(model.getResourceCategories());

        // Set the summary text field with the saved summary
        summarytextField.setText(model.getSummary());

        // Add event handler for the submit button
        submitButton.setOnAction(event -> submitButtonOnAction());
    }

    @FXML
    private void submitButtonOnAction() {
        // Get the selected resource category and summary text
        String selectedCategory = resourceComboBox.getValue();
        String summary = summarytextField.getText();

        // Update the model with the new data
        if (selectedCategory != null && !model.getResourceCategories().contains(selectedCategory)) {
            model.getResourceCategories().add(selectedCategory);
        }
        model.setSummary(summary);

        // Save the updated model to the binary file
        model.saveToFile("goal8data.bin");

        // Debugging output to confirm success
        System.out.println("Data submitted successfully:");
        System.out.println("Selected Category: " + selectedCategory);
        System.out.println("Summary: " + summary);
    }
}

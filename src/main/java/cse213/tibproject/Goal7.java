package cse213.tibproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class Goal7 {

    @FXML
    private ComboBox<String> specificResourceCategoryComboBox;

    @FXML
    private TextField summaryTextField;

    @FXML
    private Button generateButton;

    @FXML
    private Button submitButton;

    private Goal7Model model;

    @FXML
    private void initialize() {
        // Load the model from file
        model = Goal7Model.loadFromFile("goal7data.bin");

        // Populate ComboBox with data from the model
        specificResourceCategoryComboBox.getItems().addAll(model.getSpecificResourceCategories());

        // Set summary text field with saved data
        summaryTextField.setText(model.getSummary());

        // Set up event handlers
        generateButton.setOnAction(event -> generateButtonOnAction());
        submitButton.setOnAction(event -> submitButtonOnAction());
    }

    @FXML
    private void generateButtonOnAction() {
        // Generate a placeholder summary based on selected category
        String selectedCategory = specificResourceCategoryComboBox.getValue();
        if (selectedCategory != null) {
            String generatedSummary = "Summary for " + selectedCategory;
            summaryTextField.setText(generatedSummary);
        } else {
            summaryTextField.setText("Please select a category.");
        }
    }

    @FXML
    private void submitButtonOnAction() {
        // Get data from UI components
        String selectedCategory = specificResourceCategoryComboBox.getValue();
        String summary = summaryTextField.getText();

        // Update model
        if (selectedCategory != null && !model.getSpecificResourceCategories().contains(selectedCategory)) {
            model.getSpecificResourceCategories().add(selectedCategory);
        }
        model.setSummary(summary);

        // Save model to file
        model.saveToFile("goal7data.bin");

        // Debugging/confirmation output
        System.out.println("Data submitted successfully:");
        System.out.println("Selected Category: " + selectedCategory);
        System.out.println("Summary: " + summary);
    }
}

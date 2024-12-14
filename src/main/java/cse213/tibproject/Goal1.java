package cse213.tibproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.List;

public class Goal1 {

    @FXML
    private TextField idTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField addressTextField;

    @FXML
    private TextField contactNumberTextField;

    @FXML
    private TextField contractTextField;

    @FXML
    private TableView<Goal1Model> goal1TableView;

    @FXML
    private TableColumn<Goal1Model, String> idTableView;

    @FXML
    private TableColumn<Goal1Model, String> nameTableView;

    @FXML
    private TableColumn<Goal1Model, String> addressTableView;

    @FXML
    private TableColumn<Goal1Model, String> contactNumberTableView;

    @FXML
    private TableColumn<Goal1Model, String> contractTableView;

    private final ObservableList<Goal1Model> performanceList = FXCollections.observableArrayList();

    private final String filePath = "performances.bin"; // Path to store the binary file

    @FXML
    public void initialize() {
        // Bind table columns to the Performance properties
        idTableView.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getId()));
        nameTableView.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        addressTableView.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getAddress()));
        contactNumberTableView.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getContactNumber()));
        contractTableView.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getContract()));

        // Load data from the file if it exists
        try {
            List<Goal1Model> savedPerformances = Goal1Model.readFromFile(filePath);
            performanceList.addAll(savedPerformances);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }

        // Set data to the table view
        goal1TableView.setItems(performanceList);
    }

    @FXML
    public void submitButtonOnAction(ActionEvent event) {
        // Read inputs from text fields
        String id = idTextField.getText();
        String name = nameTextField.getText();
        String address = addressTextField.getText();
        String contactNumber = contactNumberTextField.getText();
        String contract = contractTextField.getText();

        // Validate inputs
        if (id.isEmpty() || name.isEmpty() || address.isEmpty() || contactNumber.isEmpty() || contract.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Please fill in all fields!");
            return;
        }

        // Create a new Performance object
        Goal1Model performance = new Goal1Model(id, name, address, contactNumber, contract);

        // Add to the list and refresh the table
        performanceList.add(performance);

        // Save the updated list to the file
        try {
            Goal1Model.writeToFile(performanceList, filePath);
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "File Error", "Could not save data: " + e.getMessage());
        }

        // Clear the input fields
        clearFields();
    }

    private void clearFields() {
        idTextField.clear();
        nameTextField.clear();
        addressTextField.clear();
        contactNumberTextField.clear();
        contractTextField.clear();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

package cse213.tibproject.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.chart.*;
import cse213.tibproject.models.Campaign;
import cse213.tibproject.helpers.SceneHelper;
import java.time.LocalDate;

public class FundraisingManagementController {
    @FXML private TableView<Campaign> campaignTable;
    @FXML private TableColumn<Campaign, Integer> idColumn;
    @FXML private TableColumn<Campaign, String> nameColumn;
    @FXML private TableColumn<Campaign, Double> targetColumn;
    @FXML private TableColumn<Campaign, Double> currentColumn;
    @FXML private TableColumn<Campaign, LocalDate> startDateColumn;
    @FXML private TableColumn<Campaign, LocalDate> endDateColumn;
    
    @FXML private TextField nameField;
    @FXML private TextArea descriptionField;
    @FXML private TextField targetField;
    @FXML private TextField donationField;
    @FXML private DatePicker startDatePicker;
    @FXML private DatePicker endDatePicker;
    
    @FXML private BarChart<String, Number> campaignChart;
    
    private ObservableList<Campaign> campaignList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        setupTable();
        loadSampleData();
        updateChart();
    }

    private void setupTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        targetColumn.setCellValueFactory(new PropertyValueFactory<>("targetAmount"));
        currentColumn.setCellValueFactory(new PropertyValueFactory<>("currentAmount"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        
        campaignTable.setItems(campaignList);
    }

    @FXML
    public void handleCampaignSelection(ActionEvent event) {
        Campaign selected = campaignTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            nameField.setText(selected.getName());
            descriptionField.setText(selected.getDescription());
            targetField.setText(String.valueOf(selected.getTargetAmount()));
            startDatePicker.setValue(selected.getStartDate());
            endDatePicker.setValue(selected.getEndDate());
        }
    }

    @FXML
    public void addCampaign() {
        if (validateInput()) {
            int newId = campaignList.size() + 1;
            Campaign newCampaign = new Campaign(
                newId,
                nameField.getText(),
                descriptionField.getText(),
                Double.parseDouble(targetField.getText()),
                0.0,
                startDatePicker.getValue(),
                endDatePicker.getValue()
            );
            campaignList.add(newCampaign);
            clearForm();
            updateChart();
        }
    }

    @FXML
    public void addDonation() {
        Campaign selectedCampaign = campaignTable.getSelectionModel().getSelectedItem();
        if (selectedCampaign != null && !donationField.getText().isEmpty()) {
            try {
                double donation = Double.parseDouble(donationField.getText());
                double newAmount = selectedCampaign.getCurrentAmount() + donation;
                selectedCampaign.setCurrentAmount(newAmount);
                
                campaignTable.refresh();
                updateChart();
                donationField.clear();
            } catch (NumberFormatException e) {
                showAlert("Error", "Invalid Amount", "Please enter a valid number");
            }
        }
    }

    private void updateChart() {
        XYChart.Series<String, Number> targetSeries = new XYChart.Series<>();
        XYChart.Series<String, Number> currentSeries = new XYChart.Series<>();
        
        targetSeries.setName("Target Amount");
        currentSeries.setName("Current Amount");
        
        for (Campaign campaign : campaignList) {
            targetSeries.getData().add(new XYChart.Data<>(campaign.getName(), campaign.getTargetAmount()));
            currentSeries.getData().add(new XYChart.Data<>(campaign.getName(), campaign.getCurrentAmount()));
        }
        
        campaignChart.getData().clear();
        campaignChart.getData().addAll(targetSeries, currentSeries);
    }

    private void loadSampleData() {
        campaignList.addAll(
            new Campaign(1, "Education Fund", "Support for students", 50000.0, 30000.0, 
                        LocalDate.now(), LocalDate.now().plusMonths(3)),
            new Campaign(2, "Community Center", "Building renovation", 100000.0, 45000.0, 
                        LocalDate.now(), LocalDate.now().plusMonths(6))
        );
        updateChart();
    }

    

    @FXML
    public void backToDashboard(ActionEvent event) {
        try {
            SceneHelper.switchScene("CommunityDashboard.fxml", event);
        } catch (Exception e) {
            showAlert("Error", "Navigation Error", "Could not return to dashboard");
        }
    }

    @FXML
    public void showFundraisingManagement(ActionEvent event) {
        try {
            SceneHelper.switchScene("FundraisingManagement.fxml", event);
        } catch (Exception e) {
            System.out.println("Error showing fundraising management: " + e.getMessage());
        }
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    public void clearForm() {
        nameField.clear();
        descriptionField.clear();
        targetField.clear();
        donationField.clear();
        startDatePicker.setValue(null);
        endDatePicker.setValue(null);
        campaignTable.getSelectionModel().clearSelection();
    }

    private boolean validateInput() {
        if (nameField.getText().isEmpty() || 
            targetField.getText().isEmpty() || 
            startDatePicker.getValue() == null || 
            endDatePicker.getValue() == null) {
            showAlert("Error", "Missing Information", "Please fill all required fields");
            return false;
        }
        try {
            Double.parseDouble(targetField.getText());
            return true;
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid Amount", "Target amount must be a number");
            return false;
        }
    }

    @FXML
    public void updateCampaign() {
        Campaign selected = campaignTable.getSelectionModel().getSelectedItem();
        if (selected != null && validateInput()) {
            selected.setName(nameField.getText());
            selected.setDescription(descriptionField.getText());
            selected.setTargetAmount(Double.parseDouble(targetField.getText()));
            selected.setStartDate(startDatePicker.getValue());
            selected.setEndDate(endDatePicker.getValue());
            
            campaignTable.refresh();
            updateChart();
            clearForm();
        } else if (selected == null) {
            showAlert("Error", "No Selection", "Please select a campaign to update");
        }
    }

    @FXML
    public void deleteCampaign() {
        Campaign selected = campaignTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            campaignList.remove(selected);
            updateChart();
            clearForm();
        } else {
            showAlert("Error", "No Selection", "Please select a campaign to delete");
        }
    }
} 
package cse213.tibproject.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.IOException;
import java.time.LocalDate;
import cse213.tibproject.models.Notice;
import cse213.tibproject.models.Policy;
import cse213.tibproject.helpers.SceneHelper;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert;

public class LawyerDashboardController {
    @FXML private Label welcomeLabel;
    @FXML private VBox dashboardContent;
    @FXML private VBox legalNoticeSection;
    @FXML private Button enterButton;
    @FXML private Button logoutButton;
    @FXML private ComboBox<String> templateComboBox;
    @FXML private TextArea noticePreview;
    @FXML private TableView<Notice> noticeTable;
    @FXML private TableColumn<Notice, Integer> idColumn;
    @FXML private TableColumn<Notice, String> dateColumn;
    @FXML private TableColumn<Notice, String> templateColumn;
    @FXML private TableColumn<Notice, String> noticeStatusColumn;
    
    @FXML private Label memberCountLabel;
    @FXML private Label ongoingCasesLabel;
    @FXML private Label resolvedCasesLabel;
    @FXML private Label successRateLabel;

    @FXML private VBox complianceSection;
    @FXML private TableView<Policy> policyTable;
    @FXML private TableColumn<Policy, Integer> policyIdColumn;
    @FXML private TableColumn<Policy, String> policyNameColumn;
    @FXML private TableColumn<Policy, String> policyStatusColumn;
    @FXML private TableColumn<Policy, String> lastReviewColumn;
    @FXML private TextArea policyDetails;
    @FXML private ComboBox<String> statusComboBox;

    private ObservableList<Notice> notices = FXCollections.observableArrayList();
    private ObservableList<Policy> policies = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        welcomeLabel.setText("Welcome, Lawyer!");
        setupTemplateComboBox();
        setupNoticeTable();
        updateMetrics();
        setupPolicyTable();
        setupStatusComboBox();
        loadPolicies();
    }

    private void setupTemplateComboBox() {
        templateComboBox.getItems().addAll(
            "Corruption Complaint Notice",
            "Legal Warning Notice",
            "Investigation Request",
            "Transparency Violation Notice"
        );
    }

    @FXML
    public void handleTemplateSelection(ActionEvent event) {
        updatePreview();
    }

    private void setupNoticeTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        templateColumn.setCellValueFactory(new PropertyValueFactory<>("template"));
        noticeStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        noticeTable.setItems(notices);
    }

    @FXML
    private void showDashboardContent(ActionEvent event) {
        dashboardContent.setVisible(true);
        enterButton.setVisible(false);
        logoutButton.setVisible(true);
    }

    @FXML
    private void showLegalNoticeSection() {
        dashboardContent.setVisible(false);
        legalNoticeSection.setVisible(true);
    }

    @FXML
    private void backToDashboard() {
        complianceSection.setVisible(false);
        legalNoticeSection.setVisible(false);
        dashboardContent.setVisible(true);
    }

    @FXML
    private void logout(ActionEvent event) {
        try {
            SceneHelper.switchScene("User-Login.fxml", event);
        } catch (IOException e) {
            System.err.println("Error returning to login: " + e.getMessage());
        }
    }

    private void updatePreview() {
        String selectedTemplate = templateComboBox.getValue();
        if (selectedTemplate != null) {
            noticePreview.setText(getTemplateText(selectedTemplate));
        }
    }

    private String getTemplateText(String template) {
        switch (template) {
            case "Corruption Complaint Notice":
                return "NOTICE OF CORRUPTION COMPLAINT\n\nDate: [Current Date]\n\nDear [Recipient],\n\nThis notice serves to formally address corrupt practices observed...";
            case "Legal Warning Notice":
                return "LEGAL WARNING\n\nDate: [Current Date]\n\nTo whom it may concern,\n\nThis is a formal warning regarding legal violations...";
            default:
                return "Select a template to view preview";
        }
    }

    @FXML
    private void saveDraft() {
        if (templateComboBox.getValue() != null) {
            Notice notice = new Notice(
                notices.size() + 1,
                LocalDate.now().toString(),
                templateComboBox.getValue(),
                "Draft"
            );
            notices.add(notice);
        }
    }

    @FXML
    private void sendNotice() {
        if (templateComboBox.getValue() != null) {
            Notice notice = new Notice(
                notices.size() + 1,
                LocalDate.now().toString(),
                templateComboBox.getValue(),
                "Sent"
            );
            notices.add(notice);
        }
    }

    private void updateMetrics() {
        // Example metrics
        memberCountLabel.setText("Total Members: 150");
        ongoingCasesLabel.setText("Ongoing Cases: 45");
        resolvedCasesLabel.setText("Resolved Cases: 30");
        successRateLabel.setText("Success Rate: 75.5%");
    }

    private void setupPolicyTable() {
        policyIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        policyNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        policyStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        lastReviewColumn.setCellValueFactory(new PropertyValueFactory<>("lastReview"));
        policyTable.setItems(policies);
    }

    private void setupStatusComboBox() {
        String[] statuses = {"Compliant", "Non-Compliant", "Under Review"};
        statusComboBox.getItems().addAll(statuses);
    }

    @FXML
    public void handlePolicySelection(ActionEvent event) {
        Policy selected = policyTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            policyDetails.setText(selected.detailsProperty().get());
        }
    }

    @FXML
    public void updateComplianceStatus(ActionEvent event) {
        Policy selected = policyTable.getSelectionModel().getSelectedItem();
        String newStatus = statusComboBox.getValue();
        
        if (selected != null && newStatus != null) {
            selected.statusProperty().set(newStatus);
            policyTable.refresh();
            
            if (newStatus.equals("Non-Compliant")) {
                showAlert("Non-Compliance Alert", 
                         "Policy Non-Compliance Detected",
                         "Policy '" + selected.nameProperty().get() + 
                         "' has been marked as non-compliant. Immediate review required.");
            }
        }
    }

    private void loadPolicies() {
        policies.addAll(
            new Policy(1, "Data Protection Policy", "Guidelines for handling sensitive data...", "Compliant", "2024-03-15"),
            new Policy(2, "Anti-Corruption Policy", "Procedures to prevent corruption...", "Under Review", "2024-03-10"),
            new Policy(3, "Ethics Policy", "Standards for ethical conduct...", "Non-Compliant", "2024-03-01")
        );
        policyTable.setItems(policies);
    }

    @FXML
    private void showComplianceSection() {
        dashboardContent.setVisible(false);
        legalNoticeSection.setVisible(false);
        complianceSection.setVisible(true);
    }

    @javafx.fxml.FXML
    public void showExpenseAnalysis(ActionEvent event) {
        try {
            SceneHelper.switchScene("ExpenseAnalysis.fxml", event);
        } catch (Exception e) {
            showAlert("Error", "Navigation Error", "Could not open Expense Analysis");
        }
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
} 
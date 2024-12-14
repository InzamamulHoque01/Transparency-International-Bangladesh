package cse213.tibproject.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import cse213.tibproject.models.Member;
import cse213.tibproject.helpers.SceneHelper;

public class MemberManagementController {
    @FXML private TableView<Member> memberTable;
    @FXML private TableColumn<Member, Integer> idColumn;
    @FXML private TableColumn<Member, String> nameColumn;
    @FXML private TableColumn<Member, String> emailColumn;
    @FXML private TableColumn<Member, String> phoneColumn;
    @FXML private TableColumn<Member, String> statusColumn;
    
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private ComboBox<String> statusComboBox;
    
    @FXML private Label totalMembersLabel;
    @FXML private Label activeMembersLabel;
    
    private ObservableList<Member> memberList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        String[] statuses = {"Active", "Inactive", "Pending"};
        statusComboBox.getItems().addAll(statuses);
        
        setupTable();
        loadSampleData();
        updateSummary();
    }

    private void setupTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        memberTable.setItems(memberList);
    }

    @FXML
    public void addMember(ActionEvent event) {
        if (validateInput()) {
            int newId = memberList.size() + 1;
            String name = nameField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();
            String status = statusComboBox.getValue();
            
            Member newMember = new Member(newId, name, email, phone, status);
            memberList.add(newMember);
            memberTable.setItems(memberList);
            clearForm();
            updateSummary();
        }
    }

    @FXML
    public void updateMember() {
        Member selectedMember = memberTable.getSelectionModel().getSelectedItem();
        if (selectedMember != null && validateInput()) {
            selectedMember.setName(nameField.getText());
            selectedMember.setEmail(emailField.getText());
            selectedMember.setPhone(phoneField.getText());
            selectedMember.setStatus(statusComboBox.getValue());
            
            memberTable.refresh();
            clearForm();
            updateSummary();
        }
    }

    @FXML
    public void deleteMember() {
        Member selectedMember = memberTable.getSelectionModel().getSelectedItem();
        if (selectedMember != null) {
            memberList.remove(selectedMember);
            clearForm();
            updateSummary();
        }
    }

    @FXML
    public void clearForm() {
        nameField.clear();
        emailField.clear();
        phoneField.clear();
        statusComboBox.setValue(null);
        memberTable.getSelectionModel().clearSelection();
    }

    private boolean validateInput() {
        if (nameField.getText().isEmpty() || 
            emailField.getText().isEmpty() || 
            phoneField.getText().isEmpty() || 
            statusComboBox.getValue() == null) {
            
            showAlert("Error", "Missing Information", "Please fill all fields");
            return false;
        }
        return true;
    }

    private void updateSummary() {
        int totalMembers = memberList.size();
        int activeMembers = 0;
        
        for (Member member : memberList) {
            if ("Active".equals(member.getStatus())) {
                activeMembers++;
            }
        }
        
        totalMembersLabel.setText("Total Members: " + totalMembers);
        activeMembersLabel.setText("Active Members: " + activeMembers);
    }

    private void loadSampleData() {
        memberList.addAll(
            new Member(1, "Aiman Saad Hamid", "aimansaadhamid1234@gmail.com", "01727197643", "Active"),
            new Member(2, "Rafi Inzamul Hoque", "rafihoque@gmail.com", "01234567888", "Pending"),
            new Member(3, "Haider Bhuiyan", "haider@gmail.com", "01122334455", "Inactive")
        );
        updateSummary();
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
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
    public void showMemberManagement(ActionEvent event) {
        try {
            SceneHelper.switchScene("MemberManagement.fxml", event);
        } catch (Exception e) {
            System.out.println("Error showing member management: " + e.getMessage());
        }
    }
} 
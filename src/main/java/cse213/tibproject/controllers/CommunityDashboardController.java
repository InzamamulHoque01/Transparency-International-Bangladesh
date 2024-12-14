package cse213.tibproject.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import cse213.tibproject.helpers.SceneHelper;

public class CommunityDashboardController {
    @FXML private Label welcomeLabel;
    @FXML private Button enterButton;
    @FXML private Button logoutButton;
    @FXML private VBox dashboardContent;

    @FXML
    public void initialize() {
        welcomeLabel.setText("Welcome, COmmunity Manager!");
    }
    
    @FXML
    public void showDashboardContent() {
        enterButton.setVisible(false);
        logoutButton.setVisible(true);
        dashboardContent.setVisible(true);
    }
    
    @FXML
    public void showMemberManagement(ActionEvent event) {
        try {
            SceneHelper.switchScene("MemberManagement.fxml", event);
        } catch (Exception e) {
            System.out.println("Error showing member management: " + e.getMessage());
        }
    }
    
    @FXML
    public void showEventManagement(ActionEvent event) {
        try {
            SceneHelper.switchScene("EventManagement.fxml", event);
        } catch (Exception e) {
            System.out.println("Error showing event management: " + e.getMessage());
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
    
    @FXML
    public void logout(ActionEvent event) {
        try {
            SceneHelper.switchScene("User-Login.fxml", event);
        } catch (Exception e) {
            System.out.println("Error during logout: " + e.getMessage());
        }
    }
} 
package cse213.tibproject.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import cse213.tibproject.helpers.SceneHelper;
import javafx.scene.control.PasswordField;

public class LoginController {
    @FXML
    private TextField usernameField;
    
    @FXML
    private Label errorLabel;
    @FXML
    private PasswordField passwordField;

    private void openNewWindow(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/cse213/tibproject/" + fxmlFile));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void login(ActionEvent event) {
        String username = usernameField.getText();
        
        try {
            if (username.contains("Lawyer")) {
                SceneHelper.switchScene("LawyerDashboard.fxml", event);
            } 
            else if (username.contains("Community")) {
                SceneHelper.switchScene("CommunityDashboard.fxml", event);
            }
            else {
                errorLabel.setText("Username must contain 'Lawyer' or 'Community'");
                errorLabel.setTextFill(Color.RED);
            }
        } catch (IOException e) {
            errorLabel.setText("Login failed: " + e.getMessage());
            errorLabel.setTextFill(Color.RED);
        }
    }

    @FXML
    private void switchToRegister(ActionEvent event) {
        try {
            SceneHelper.switchScene("Register.fxml", event);
        } catch (IOException e) {
            errorLabel.setText("Could not switch to register page");
            errorLabel.setTextFill(Color.RED);
        }
    }

    @FXML
    public void Goal1(ActionEvent actionEvent) {
        openNewWindow("User7/goal1.fxml");
    }

    @FXML
    public void Goal2(ActionEvent actionEvent) {
        openNewWindow("User7/goal2.fxml");
    }

    @FXML
    public void Goal3(ActionEvent actionEvent) {
        openNewWindow("User7/goal3.fxml");
    }

    @FXML
    public void Goal4(ActionEvent actionEvent) {
        openNewWindow("User7/goal4.fxml");
    }

    @FXML
    public void Goal5(ActionEvent actionEvent) {
        openNewWindow("User7/goal5.fxml");
    }

    @FXML
    public void Goal6(ActionEvent actionEvent) {
        openNewWindow("User7/goal6.fxml");
    }

    @FXML
    public void Goal7(ActionEvent actionEvent) {
        openNewWindow("User7/goal7.fxml");
    }

    @FXML
    public void Goal8(ActionEvent actionEvent) {
        openNewWindow("User7/goal8.fxml");
    }

    @FXML
    public void Goal1U8(ActionEvent actionEvent) {
        openNewWindow("User8/goal1.fxml");
    }
}
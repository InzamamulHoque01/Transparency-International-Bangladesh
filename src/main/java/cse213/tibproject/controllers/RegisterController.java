package cse213.tibproject.controllers;

import cse213.tibproject.helpers.SceneHelper;
import cse213.tibproject.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;

public class RegisterController {
    @FXML private TextField usernameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Label errorLabel;

    @FXML
    protected void handleRegister() {
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            SceneHelper.showAlert(Alert.AlertType.ERROR, "Error", "Please fill all fields");
            return;
        }

        if (!password.equals(confirmPassword)) {
            SceneHelper.showAlert(Alert.AlertType.ERROR, "Error", "Passwords do not match");
            return;
        }

        if (!username.contains("Lawyer") && !username.contains("Community")) {
            errorLabel.setText("Username must contain either 'Lawyer' or 'Community'");
            errorLabel.setTextFill(Color.RED);
            return;
        }

        boolean userExists = SceneHelper.getAllUsers().stream()
                .anyMatch(user -> user.getUsername().equals(username));

        if (userExists) {
            SceneHelper.showAlert(Alert.AlertType.ERROR, "Error", "Username already exists");
            return;
        }

        User newUser = new User(username, password, email);
        SceneHelper.saveUser(newUser);
        SceneHelper.showAlert(Alert.AlertType.INFORMATION, "Success", "Registration successful!");
    }

    @FXML
    protected void switchToLogin(ActionEvent event) throws IOException {
        SceneHelper.switchScene("User-Login.fxml", event);
    }
} 
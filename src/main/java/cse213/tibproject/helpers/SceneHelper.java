package cse213.tibproject.helpers;

import cse213.tibproject.UserLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import cse213.tibproject.models.User;

public class SceneHelper {
    private static final String USER_DATA_FILE = "users.bin";
    
    public static void switchScene(String fxmlFile, ActionEvent event) throws IOException {
        String fullPath = "/" + UserLogin.class.getPackageName().replace(".", "/") + "/" + fxmlFile;
        FXMLLoader fxmlLoader = new FXMLLoader(UserLogin.class.getResource(fullPath));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void saveUser(User user) {
        List<User> users = getAllUsers();
        users.add(user);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_DATA_FILE))) {
            oos.writeObject(users);
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Could not save user data");
        }
    }

    @SuppressWarnings("unchecked")
    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        File file = new File(USER_DATA_FILE);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                users = (List<User>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                showAlert(Alert.AlertType.ERROR, "Error", "Could not read user data");
            }
        }
        return users;
    }
} 
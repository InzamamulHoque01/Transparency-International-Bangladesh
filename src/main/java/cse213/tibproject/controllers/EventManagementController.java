package cse213.tibproject.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import cse213.tibproject.models.Event;
import cse213.tibproject.helpers.SceneHelper;
import java.time.LocalDate;

public class EventManagementController {
    @FXML private TableView<Event> eventTable;
    @FXML private TableColumn<Event, Integer> idColumn;
    @FXML private TableColumn<Event, String> nameColumn;
    @FXML private TableColumn<Event, LocalDate> dateColumn;
    @FXML private TableColumn<Event, String> locationColumn;
    
    @FXML private TextField nameField;
    @FXML private DatePicker datePicker;
    @FXML private TextField locationField;
    
    @FXML private ListView<String> upcomingEventsList;
    
    private ObservableList<Event> eventList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        setupTable();
        loadSampleData();
        updateUpcomingEvents();
    }

    private void setupTable() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        eventTable.setItems(eventList);
    }

    private void updateUpcomingEvents() {
        ObservableList<String> upcomingEvents = FXCollections.observableArrayList();
        
        for (Event event : eventList) {
            if (event.getDate().isAfter(LocalDate.now().minusDays(1))) {
                String eventString = String.format("%s - %s at %s",
                    event.getDate().format(java.time.format.DateTimeFormatter.ofPattern("MMM dd, yyyy")),
                    event.getName(),
                    event.getLocation());
                upcomingEvents.add(eventString);
            }
        }
        
        upcomingEventsList.setItems(upcomingEvents);
    }

    @FXML
    public void addEvent(ActionEvent event) {
        if (validateInput()) {
            int newId = eventList.size() + 1;
            String name = nameField.getText();
            LocalDate date = datePicker.getValue();
            String location = locationField.getText();
            
            Event newEvent = new Event(newId, name, date, location);
            eventList.add(newEvent);
            clearForm();
            updateUpcomingEvents();
        }
    }

    @FXML
    public void updateEvent(ActionEvent event) {
        Event selectedEvent = eventTable.getSelectionModel().getSelectedItem();
        if (selectedEvent != null && validateInput()) {
            selectedEvent.setName(nameField.getText());
            selectedEvent.setDate(datePicker.getValue());
            selectedEvent.setLocation(locationField.getText());
            eventTable.refresh();
            clearForm();
            updateUpcomingEvents();
        }
    }

    @FXML
    public void deleteEvent(ActionEvent event) {
        Event selectedEvent = eventTable.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {
            eventList.remove(selectedEvent);
            clearForm();
            updateUpcomingEvents();
        }
    }

    @FXML
    public void clearForm() {
        nameField.clear();
        datePicker.setValue(null);
        locationField.clear();
        eventTable.getSelectionModel().clearSelection();
    }

    private boolean validateInput() {
        if (nameField.getText().isEmpty() || 
            datePicker.getValue() == null || 
            locationField.getText().isEmpty()) {
            showAlert("Error", "Missing Information", "Please fill all fields");
            return false;
        }
        return true;
    }

    private void loadSampleData() {
        eventList.addAll(
            new Event(1, "Anti-Corruption Workshop", LocalDate.now().plusDays(7), "TIB Main Office"),
            new Event(2, "Community Meeting", LocalDate.now().plusDays(14), "Community Center"),
            new Event(3, "Legal Awareness Seminar", LocalDate.now().plusDays(21), "City Convention Center")
        );
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
} 
package cse213.tibproject.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.chart.PieChart;
import java.time.LocalDate;
import cse213.tibproject.models.Expense;
import cse213.tibproject.helpers.SceneHelper;
import java.util.ArrayList;

public class ExpenseAnalysisController {
    @javafx.fxml.FXML private TableView<Expense> expenseTable;
    @javafx.fxml.FXML private TableColumn<Expense, Integer> expenseIdCol;
    @javafx.fxml.FXML private TableColumn<Expense, String> expenseNameCol;
    @javafx.fxml.FXML private TableColumn<Expense, Double> expenseAmountCol;
    @javafx.fxml.FXML private TableColumn<Expense, LocalDate> expenseDateCol;
    @javafx.fxml.FXML private TableColumn<Expense, String> expenseCategoryCol;
    
    @javafx.fxml.FXML private TextField expenseNameField;
    @javafx.fxml.FXML private TextField expenseAmountField;
    @javafx.fxml.FXML private DatePicker expenseDatePicker;
    @javafx.fxml.FXML private ComboBox<String> categoryComboBox;
    
    @javafx.fxml.FXML private PieChart expenseChart;
    @javafx.fxml.FXML private Label totalExpensesLabel;
    @javafx.fxml.FXML private Label remainingBudgetLabel;
    
    private ObservableList<Expense> expenseList = FXCollections.observableArrayList();
    private final double TOTAL_BUDGET = 10000.0; // Example budget

    @javafx.fxml.FXML
    public void initialize() {
        setupTable();
        setupCategoryComboBox();
        loadSampleData();
        updateSummary();
    }

    private void setupTable() {
        expenseIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        expenseNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        expenseAmountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        expenseDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        expenseCategoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        
        expenseTable.setItems(expenseList);
    }

    private void setupCategoryComboBox() {
        String[] categories = {"Legal Fees", "Office Supplies", "Travel", "Consultation", "Other"};
        categoryComboBox.getItems().addAll(categories);
    }

    @javafx.fxml.FXML
    public void addExpense(ActionEvent event) {
        try {
            String name = expenseNameField.getText();
            double amount = Double.parseDouble(expenseAmountField.getText());
            LocalDate date = expenseDatePicker.getValue();
            String category = categoryComboBox.getValue();
            
            if(name.isEmpty() || date == null || category == null) {
                showAlert("Error", "Missing Information", "Please fill all fields");
                return;
            }

            int newId = expenseList.size() + 1;
            Expense newExpense = new Expense(newId, name, amount, date, category);
            expenseList.add(newExpense);
            
            clearFields();
            updateSummary();
            updateChart();
            
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid Amount", "Please enter a valid number for amount");
        }
    }

    private void updateSummary() {
        double total = 0.0;
        for (Expense expense : expenseList) {
            total += expense.getAmount();
        }
        
        totalExpensesLabel.setText(String.format("Total Expenses: $%.2f", total));
        remainingBudgetLabel.setText(String.format("Remaining Budget: $%.2f", TOTAL_BUDGET - total));
    }

    private void updateChart() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        ArrayList<String> categories = new ArrayList<>();
        ArrayList<Double> totals = new ArrayList<>();
        
        // First find all unique categories
        for (Expense expense : expenseList) {
            String category = expense.getCategory();
            if (!categories.contains(category)) {
                categories.add(category);
                totals.add(0.0);
            }
        }
        
        // Calculate totals for each category
        for (Expense expense : expenseList) {
            String category = expense.getCategory();
            int index = categories.indexOf(category);
            double currentTotal = totals.get(index);
            totals.set(index, currentTotal + expense.getAmount());
        }
        
        // Create pie chart data
        for (int i = 0; i < categories.size(); i++) {
            String label = categories.get(i) + ": $" + String.format("%.2f", totals.get(i));
            pieChartData.add(new PieChart.Data(label, totals.get(i)));
        }
        
        expenseChart.setData(pieChartData);
    }

    private void loadSampleData() {
        expenseList.addAll(
            new Expense(1, "Court Filing Fees", 500.0, LocalDate.now().minusDays(5), "Legal Fees"),
            new Expense(2, "Office Supplies", 200.0, LocalDate.now().minusDays(3), "Office Supplies"),
            new Expense(3, "Client Meeting Travel", 300.0, LocalDate.now().minusDays(1), "Travel")
        );
        updateChart();
    }

    private void clearFields() {
        expenseNameField.clear();
        expenseAmountField.clear();
        expenseDatePicker.setValue(null);
        categoryComboBox.setValue(null);
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @javafx.fxml.FXML
    public void backToDashboard(ActionEvent event) {
        try {
            SceneHelper.switchScene("LawyerDashboard.fxml", event);
        } catch (Exception e) {
            showAlert("Error", "Navigation Error", "Could not return to dashboard");
        }
    }
} 
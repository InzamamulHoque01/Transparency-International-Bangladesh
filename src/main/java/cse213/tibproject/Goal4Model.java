package cse213.tibproject;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

public class Goal4Model {

    private Button detailedDataButton;
    private ComboBox<String> recordComboBox;
    private Button submitButton;
    private Text recordText;

    // Getter and Setter for Detailed Data Button
    public Button getDetailedDataButton() {
        return detailedDataButton;
    }

    public void setDetailedDataButton(Button detailedDataButton) {
        this.detailedDataButton = detailedDataButton;
    }

    // Getter and Setter for Record ComboBox
    public ComboBox<String> getRecordComboBox() {
        return recordComboBox;
    }

    public void setRecordComboBox(ComboBox<String> recordComboBox) {
        this.recordComboBox = recordComboBox;
    }

    // Getter and Setter for Submit Button
    public Button getSubmitButton() {
        return submitButton;
    }

    public void setSubmitButton(Button submitButton) {
        this.submitButton = submitButton;
    }

    // Getter and Setter for Record Text
    public Text getRecordText() {
        return recordText;
    }

    public void setRecordText(Text recordText) {
        this.recordText = recordText;
    }
}

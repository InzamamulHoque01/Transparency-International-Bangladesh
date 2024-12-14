module cse213.tibproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens cse213.tibproject to javafx.fxml;
    exports cse213.tibproject;
}
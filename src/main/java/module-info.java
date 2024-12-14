module cse213.tibproject {
    requires javafx.base;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

	
    opens cse213.tibproject to javafx.fxml;
    opens cse213.tibproject.controllers to javafx.fxml;
    opens cse213.tibproject.models to javafx.base;
    
    exports cse213.tibproject;
    exports cse213.tibproject.controllers;
    exports cse213.tibproject.models;

}
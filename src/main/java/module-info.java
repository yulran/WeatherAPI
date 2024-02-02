module com.example.demo13dz {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.example.demo13dz to javafx.fxml;
    exports com.example.demo13dz;
}
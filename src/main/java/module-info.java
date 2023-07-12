module com.example.fxgl {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens com.example.fxgl to javafx.fxml;
    exports com.example.fxgl;
}
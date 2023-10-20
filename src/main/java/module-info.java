module com.example.test_uijfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires okhttp3;
    requires com.google.gson;
    requires com.github.benmanes.caffeine;
    requires java.desktop;


    opens com.example.test_uijfx to javafx.fxml, com.google.gson;
    exports com.example.test_uijfx;
}
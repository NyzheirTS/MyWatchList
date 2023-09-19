module com.example.test_uijfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.test_uijfx to javafx.fxml;
    exports com.example.test_uijfx;
}
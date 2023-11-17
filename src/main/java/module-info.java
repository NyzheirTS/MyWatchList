module com.example.test_uijfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires okhttp3;
    requires com.google.gson;
    requires com.github.benmanes.caffeine;
    requires java.desktop;


    opens com.example.MyWatchList to javafx.fxml, com.google.gson;
    exports com.example.MyWatchList;
    exports DataClasses;
    opens DataClasses to com.google.gson, javafx.fxml;
    exports com.example.MyWatchList.CachingClasses;
    opens com.example.MyWatchList.CachingClasses to com.google.gson, javafx.fxml;
    exports com.example.MyWatchList.APIClasses;
    opens com.example.MyWatchList.APIClasses to com.google.gson, javafx.fxml;
    exports Interfaces;
    opens Interfaces to com.google.gson, javafx.fxml;
}
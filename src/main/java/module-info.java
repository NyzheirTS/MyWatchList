module com.example.test_uijfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires okhttp3;
    requires com.google.gson;
    requires com.github.benmanes.caffeine;
    requires java.desktop;


    opens com.example.MyWatchList to javafx.fxml, com.google.gson;
    exports com.example.MyWatchList;
    exports com.example.MyWatchList.DataClasses;
    opens com.example.MyWatchList.DataClasses to com.google.gson, javafx.fxml;
    exports com.example.MyWatchList.Caching;
    opens com.example.MyWatchList.Caching to com.google.gson, javafx.fxml;
    exports com.example.MyWatchList.ApiClass;
    opens com.example.MyWatchList.ApiClass to com.google.gson, javafx.fxml;
    exports com.example.MyWatchList.Interfaces;
    opens com.example.MyWatchList.HomePage to com.google.gson, javafx.fxml;
    exports com.example.MyWatchList.AppConfig;
    opens com.example.MyWatchList.AppConfig to com.google.gson, javafx.fxml;
    exports com.example.MyWatchList.WatchedList;
    opens com.example.MyWatchList.WatchedList to com.google.gson, javafx.fxml;
    opens com.example.MyWatchList.SettingsPage to com.google.gson, javafx.fxml;
}
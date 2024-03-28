module com.example.test_uijfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires okhttp3;
    requires com.google.gson;
    requires com.github.benmanes.caffeine;
    requires java.desktop;
    requires annotations;


    opens com.example.MyWatchList to javafx.fxml, com.google.gson;
    exports com.example.MyWatchList;
    exports com.example.MyWatchList.DataModels;
    opens com.example.MyWatchList.DataModels to com.google.gson, javafx.fxml;
    exports com.example.MyWatchList.Caching;
    opens com.example.MyWatchList.Caching to com.google.gson, javafx.fxml;
    exports com.example.MyWatchList.ApiClass;
    opens com.example.MyWatchList.HomePage to com.google.gson, javafx.fxml;
    exports com.example.MyWatchList.AppConfig;
    opens com.example.MyWatchList.AppConfig to com.google.gson, javafx.fxml;
    exports com.example.MyWatchList.WatchedList;
    opens com.example.MyWatchList.WatchedList to com.google.gson, javafx.fxml;
    opens com.example.MyWatchList.SettingsPage to com.google.gson, javafx.fxml;
    exports com.example.MyWatchList.InfoPage;
    opens com.example.MyWatchList.InfoPage to com.google.gson, javafx.fxml;
    exports com.example.MyWatchList.DataModels.MovieModels;
    opens com.example.MyWatchList.DataModels.MovieModels to com.google.gson, javafx.fxml;
    exports com.example.MyWatchList.DataModels.TvModels;
    opens com.example.MyWatchList.DataModels.TvModels to com.google.gson, javafx.fxml;
}
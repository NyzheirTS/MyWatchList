module com.example.test_uijfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires okhttp3;
    requires com.google.gson;
    requires com.github.benmanes.caffeine;
    requires java.desktop;
    requires annotations;
    requires io.nayuki.qrcodegen;
    requires javafx.swing;
    requires org.controlsfx.controls;
    requires junit;
    requires javafx.web;
    requires java.prefs;
    requires org.fxmisc.flowless;


    exports com.example.MyWatchList.DataModels;
    opens com.example.MyWatchList.DataModels to com.google.gson, javafx.fxml;
    exports com.example.MyWatchList.Caching;
    opens com.example.MyWatchList.Caching to com.google.gson, javafx.fxml;
    exports com.example.MyWatchList.ApiClass;
    exports com.example.MyWatchList.AppConfig;
    opens com.example.MyWatchList.AppConfig to com.google.gson, javafx.fxml;
    exports com.example.MyWatchList.Controllers.WatchedList;
    opens com.example.MyWatchList.Controllers.WatchedList to com.google.gson, javafx.fxml;
    opens com.example.MyWatchList.Controllers.SettingsPage to com.google.gson, javafx.fxml;
    exports com.example.MyWatchList.DataModels.MovieModels;
    opens com.example.MyWatchList.DataModels.MovieModels to com.google.gson, javafx.fxml;
    exports com.example.MyWatchList.DataModels.TvModels;
    opens com.example.MyWatchList.DataModels.TvModels to com.google.gson, javafx.fxml;
    opens com.example.MyWatchList.Controllers.HomePage to com.google.gson, javafx.fxml;
    exports com.example.MyWatchList.Controllers.InfoPage;
    opens com.example.MyWatchList.Controllers.InfoPage to com.google.gson, javafx.fxml;
    exports com.example.MyWatchList.AppEntry;
    opens com.example.MyWatchList.AppEntry to com.google.gson, javafx.fxml;
    exports com.example.MyWatchList.TestFolder;
    opens com.example.MyWatchList.TestFolder to com.google.gson, javafx.fxml;
    exports com.example.MyWatchList.Controllers.HomePage;
    exports com.example.MyWatchList.Controllers.CommonComponent;
    opens com.example.MyWatchList.Controllers.CommonComponent to com.google.gson, javafx.fxml;
    exports com.example.MyWatchList.TestFolder.JUnitTest;
    opens com.example.MyWatchList.TestFolder.JUnitTest to javafx.fxml;
    exports com.example.MyWatchList.DataModels.CommonModels;
    opens com.example.MyWatchList.DataModels.CommonModels to com.google.gson, javafx.fxml;
    exports com.example.MyWatchList.Controllers.CommonComponent.CastCrewPages;
    opens com.example.MyWatchList.Controllers.CommonComponent.CastCrewPages to com.google.gson, javafx.fxml;

}
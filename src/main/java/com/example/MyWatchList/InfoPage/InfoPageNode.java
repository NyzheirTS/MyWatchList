package com.example.MyWatchList.InfoPage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;
import java.util.Objects;

public class InfoPageNode {
    private final Node node;
    private static final String basePosterImgUrl = "https://image.tmdb.org/t/p/w780";

    public InfoPageNode() throws IOException {
        node = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("info-page.fxml")));

    }
}

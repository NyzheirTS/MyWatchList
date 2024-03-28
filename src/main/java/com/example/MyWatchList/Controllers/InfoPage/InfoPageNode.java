package com.example.MyWatchList.Controllers.InfoPage;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;
import java.util.Objects;

public class InfoPageNode {
    private final Node node;
    private static final String basePosterImgUrl = "https://image.tmdb.org/t/p/w300";
    private static final String baseBackDropUrl = "https://image.tmdb.org/t/p/w1280";

    public InfoPageNode() throws IOException {
        node = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("info-page.fxml")));

    }
}

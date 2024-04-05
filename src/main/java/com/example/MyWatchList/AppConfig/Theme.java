/**
 * This file is part of the MyWatchList project, which is inspired by and adapts ideas from the Atlantafx project by mkpaz.
 * For full licensing information, see the LICENSE.txt file in the root directory of this project.
 * Original project: https://github.com/mkpaz/atlantafx
 */
package com.example.MyWatchList.AppConfig;
import javafx.application.Application;


public enum Theme {
    // Theme.java
    CupertinoLight("Cupertino Light", "file:/C:/Users/eshas/IdeaProjects/MyWatchList/target/classes/com/example/MyWatchList/StyleSheets/cupertino-light.css"),
    CupertinoDark("Cupertino Dark", "file:/C:/Users/eshas/IdeaProjects/MyWatchList/target/classes/com/example/MyWatchList/StyleSheets/cupertino-dark.css"),
    Dracula("Dracula", "file:/C:/Users/eshas/IdeaProjects/MyWatchList/target/classes/com/example/MyWatchList/StyleSheets/dracula.css" ),
    PrimerDark("Primer Dark", "file:/C:/Users/eshas/IdeaProjects/MyWatchList/target/classes/com/example/MyWatchList/StyleSheets/primer-dark.css"),
    PrimerLight("Primer Light", "file:/C:/Users/eshas/IdeaProjects/MyWatchList/target/classes/com/example/MyWatchList/StyleSheets/primer-light.css"),
    NordDark("Nord Dark", "file:/C:/Users/eshas/IdeaProjects/MyWatchList/target/classes/com/example/MyWatchList/StyleSheets/nord-dark.css"),
    NordLight("Nord Light", "file:/C:/Users/eshas/IdeaProjects/MyWatchList/target/classes/com/example/MyWatchList/StyleSheets/nord-light.css");



    private final String name;
    private final String stylesheet;

    Theme(String name, String stylesheet) {
        this.name = name;
        this.stylesheet = stylesheet;
    }

    public String getName() {
        return name;
    }

    public String getStylesheet() {
        return stylesheet;
    }

    public void apply() {
         Application.setUserAgentStylesheet(stylesheet);
    }
}


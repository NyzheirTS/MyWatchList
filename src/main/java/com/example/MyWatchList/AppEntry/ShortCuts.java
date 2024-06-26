package com.example.MyWatchList.AppEntry;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

public class ShortCuts {
    private ShortCuts(){}

    public static final KeyCombination shiftEsc = new KeyCodeCombination(KeyCode.ESCAPE, KeyCombination.SHIFT_DOWN);
    public static final KeyCombination shift1 = new KeyCodeCombination(KeyCode.DIGIT1, KeyCombination.SHIFT_DOWN);
    public static final KeyCombination shift2 = new KeyCodeCombination(KeyCode.DIGIT2, KeyCombination.SHIFT_DOWN);
    public static final KeyCombination shift5 = new KeyCodeCombination(KeyCode.DIGIT5, KeyCombination.SHIFT_DOWN);
}

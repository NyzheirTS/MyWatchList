package com.example.MyWatchList.DataModels.Utils;

import com.example.MyWatchList.Controllers.DynamicPages.PersonPage.PersonInfoCardController;
import javafx.scene.layout.HBox;

import java.time.LocalDate;
import java.util.List;

public class QuickSort {
    private QuickSort(){}
    private static void swap(List<HBox> list, int i, int j){
        HBox temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    private static int partitionScoreUp(List<HBox> arr, int low, int high){
        Double pivot = getFromController(arr.get(high)).getRate();

        int i = (low - 1);
        for(int j = low; j <= high - 1; j++) {
            if (getFromController(arr.get(j)).getRate() > pivot){
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i +1, high);
        return (i + 1);
    }

    private static int partitionScoreDown(List<HBox> arr, int low, int high){
        Double pivot = getFromController(arr.get(high)).getRate();

        int i = (low - 1);
        for(int j = low; j <= high - 1; j++) {
            if (getFromController(arr.get(j)).getRate() < pivot){
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i +1, high);
        return (i + 1);
    }

    public static List<HBox> sortScoreDown(List<HBox> arr, int low, int high){
        if (low < high) {
            int pi = partitionScoreDown(arr, low, high);

            sortScoreDown(arr, low, pi - 1);
            sortScoreDown(arr, pi + 1, high);
        }
        return arr;
    }
    public static List<HBox> sortScoreUp(List<HBox> arr, int low, int high){
        if (low < high) {
            int pi = partitionScoreUp(arr, low, high);

            sortScoreUp(arr, low, pi - 1);
            sortScoreUp(arr, pi + 1, high);
        }
        return arr;
    }


    private static PersonInfoCardController getFromController(HBox node){
        return (PersonInfoCardController) node.getProperties().get("controller");
    }
}

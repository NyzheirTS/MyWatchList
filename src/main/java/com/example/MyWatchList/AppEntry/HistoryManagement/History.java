package com.example.MyWatchList.AppEntry.HistoryManagement;

import javafx.scene.control.Button;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

public class History {
    Deque<Command> back = new ArrayDeque<>();
    Deque<Command> forward = new ArrayDeque<>();
    private final Button forwardButton;
    private final Button backButton;

    public History(Button forward, Button back){
        this.backButton = back;
        this.forwardButton = forward;
        updateButtonStates();
    }

    public void executeCommand(Command command){
        try {
            command.execute();
            back.push(command);
            forward.clear();
            System.gc();
        } catch (IOException e){
            e.printStackTrace();
        }
        updateButtonStates();
        printHistory();
    }

    public void goBack(){
        if (!back.isEmpty()){
            Command command = back.pop();
            try{
                command.execute();
                forward.push(command);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        updateButtonStates();
        printHistory();
    }

    public void goForward(){
        if (!forward.isEmpty()){
            Command command = forward.pop();
            try{
                command.execute();
                back.push(command);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        updateButtonStates();
        printHistory();
    }

    private void printHistory(){
        System.out.println("Back History : " + back);
        System.out.println("Forward History : " + forward);
    }

    private void updateButtonStates(){
        backButton.setDisable(back.isEmpty());
        forwardButton.setDisable(forward.isEmpty());
    }

}

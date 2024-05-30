package com.example.MyWatchList.Controllers.HistoryManager;

import javafx.scene.control.Button;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.StreamSupport;

public class History {
    Deque<Command> back = new ArrayDeque<>();
    private Command lastCommand;
    private boolean initHistory = false;
    private final Button backButton;

    public History(Button back){
        this.backButton = back;
        updateButtonStates();
    }

    public void executeCommand(Command command) throws IOException {
        command.execute();
        if (Boolean.TRUE.equals(initHistory)){
            back.push(lastCommand);
            lastCommand = command;
        } else {
            lastCommand = command;
            initHistory = true;
        }
        updateButtonStates();
        printHistory();
    }

    public void goBack(){
        if (!back.isEmpty()){
            try{
                back.pop().execute();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        sysGC();
        updateButtonStates();
        printHistory();
    }


    private void printHistory(){
        System.out.println("Back History : " + back);
    }

    private void updateButtonStates(){
        backButton.setVisible(!back.isEmpty());
    }

    public void clearHistory(){
        initHistory = false;
    }

    private void sysGC(){
        if (back.isEmpty()){
            System.gc();
        }
    }

}

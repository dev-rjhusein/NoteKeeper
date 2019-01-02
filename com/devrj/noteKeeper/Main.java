package com.devrj.noteKeeper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import note_model.NoteData;

public class Main extends Application {

    private Stage useStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("keeperMain.fxml"));
        primaryStage.setTitle("Note Tracker");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();

        useStage = primaryStage;
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        try {
            NoteData.getInstance().storeNotes();

        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void init() throws Exception {
        try {
            NoteData.getInstance().loadNotes();

        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

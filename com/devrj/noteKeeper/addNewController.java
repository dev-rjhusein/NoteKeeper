package com.devrj.noteKeeper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import note_model.NoteData;
import note_model.NoteItem;
import java.io.IOException;
import java.time.LocalDate;

public class addNewController {
    @FXML
    TextArea noteText;
    @FXML
    TextField titleText;
    @FXML
    DatePicker dateSelector;
    @FXML
    Button cancelButton, addNoteButton;
    @FXML
    AnchorPane addNotePane;
    @FXML
    Label errorLabel;


    public void onButtonClicked(ActionEvent e) throws IOException {
        if(e.getSource().equals(cancelButton)){
            AnchorPane mainPane = FXMLLoader.load(getClass().getResource("keeperMain.fxml"));
            addNotePane.getChildren().setAll(mainPane);

        }else if(e.getSource().equals(addNoteButton)){
            LocalDate date = dateSelector.getValue();
            String note = (noteText.getParagraphs().toString());
            note = note.substring(1, note.length()-1);
            String title = titleText.getText();

            //Require all 3 fields before making the note
            if(date == null){
                System.out.println("Enter a date");
                errorLabel.setText("Please enter a date");
                errorLabel.visibleProperty().setValue(Boolean.TRUE);
                return;

            }else if(note == null || note.isEmpty()){
                System.out.println("Enter a note");
                errorLabel.setText("Please enter a note");
                return;

            }else  if(title == null || title.isEmpty()){
                System.out.println("Enter a Title");
                errorLabel.setText("Please enter a title");
                return;
            }

            //Save new note object
            NoteData.getInstance().addNote(new NoteItem(title, note, date));

            //Load the main pane
            AnchorPane mainPane = FXMLLoader.load(getClass().getResource("keeperMain.fxml"));
            addNotePane.getChildren().setAll(mainPane);
        }
    }


}

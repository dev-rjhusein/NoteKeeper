package com.devrj.noteKeeper;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import note_model.NoteData;
import note_model.NoteItem;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class mainController {

    @FXML
    private Button newNoteButton, deleteButton;
    @FXML
    private Label labelView, dueLabel, noNotesWarning;
    @FXML
    private ListView<NoteItem> listView;
    @FXML
    private AnchorPane mainParentPane;



    public void initialize(){

        if(NoteData.getInstance().getListOfNotes().isEmpty()){
            labelView.setText("There are no notes saved. Click the + button to add one!");
        }

        //Push list of notes to the Listview
        listView.getItems().setAll(NoteData.getInstance().getListOfNotes());

        //Add change listener to ListView to handle selections
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<NoteItem>() {
            @Override
            public void changed(ObservableValue<? extends NoteItem> observableValue, NoteItem prevSelection, NoteItem currentSelection) {
                System.out.println(currentSelection);

                labelView.setText(currentSelection.getNote());
                dueLabel.setText("DUE ON: " + currentSelection.getDueDate().format(DateTimeFormatter.ofPattern("MM-dd-yyyy")));
                deleteButton.visibleProperty().setValue(Boolean.TRUE);

            }
        });
    }

    public void onButtonClicked(ActionEvent e) throws IOException {
        //Add a note - Switch to pane with form to add a note
        if(e.getSource().equals(newNoteButton)){
            AnchorPane addNotePane = FXMLLoader.load(getClass().getResource("addNote.fxml"));
            mainParentPane.getChildren().setAll(addNotePane);

            //Delete current selected note
        }else if(e.getSource().equals(deleteButton)){
            labelView.setText("");
            dueLabel.setText("");
            NoteData.getInstance().getListOfNotes().remove(listView.getSelectionModel().getSelectedItem());
            try{
                listView.getItems().setAll(NoteData.getInstance().getListOfNotes());      //Why does this throw NullPointerException?
            }catch(NullPointerException except){
                //Error Log?
            }

            deleteButton.visibleProperty().setValue(Boolean.FALSE);
            if(NoteData.getInstance().getListOfNotes().isEmpty()){
                labelView.setText("There are no notes saved. Click the + button to add one!");
            }

        }
    }
}

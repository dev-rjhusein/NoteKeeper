package note_model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

public class NoteData implements Serializable{
    private ObservableList<NoteItem> listOfNotes;
    private static NoteData instance = new NoteData();
//    private static String filename = System.getProperty("user.dir") + "/IdeaProjects/NoteKeeper/resources/listOfNotes.txt";     // ---- For jar file
    private static String filename = "resources/listOfNotes.txt";   // ---- For debug

    private DateTimeFormatter formatter;

    public static NoteData getInstance(){
        return instance;
    }
    private NoteData(){
        formatter = DateTimeFormatter.ofPattern("dd-MM-yyy");
    }
    public List<NoteItem> getListOfNotes(){
        return listOfNotes;
    }
    public void addNote(NoteItem item){
        listOfNotes.add(item); }


    /* LOAD */
    public void loadNotes() throws IOException, ClassNotFoundException{

        listOfNotes = FXCollections.observableArrayList();
        Path path = Paths.get(filename);
        BufferedReader reader = Files.newBufferedReader(path);

        String input;

        try{
            while((input = reader.readLine()) != null){
                String[] noteTokens = input.split("~~~");

                String titleText = noteTokens[0];
                String noteText = noteTokens[1];
                String dateText = noteTokens[2];

                LocalDate date = LocalDate.parse(dateText, formatter);

                NoteItem noteItem = new NoteItem(titleText, noteText, date);

                listOfNotes.add(noteItem);
            }
        }finally{
            if(reader != null){
                reader.close();
            }
        }
    }


    /* STORE */
    public void storeNotes() throws IOException{

        Path path = Paths.get(filename);
        BufferedWriter writer = Files.newBufferedWriter(path);
        try {
            Iterator<NoteItem> iterator = listOfNotes.iterator();
            while (iterator.hasNext()) {
                NoteItem noteItem = iterator.next();
                writer.write(String.format("%s~~~%s~~~%s",
                        noteItem.getTitle(),
                        noteItem.getNote(),
                        noteItem.getDueDate().format(formatter)));
                writer.newLine();
            }
        } finally {
            if (writer != null) {
                writer.close();
            }
        }


    }
}

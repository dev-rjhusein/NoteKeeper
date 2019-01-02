package note_model;

import java.time.LocalDate;

public class NoteItem {
    private String note;
    private LocalDate dueDate;
    private String title;

    public NoteItem(String note, String title) {
        this.note = note;
        this.title = title;
    }

    public NoteItem(String title, String note, LocalDate dueDate) {
        this.title = title;
        this.note = note;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return title;
    }
}

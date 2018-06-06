package com.apps.morfiwifi.morfi_project_samane.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;

import java.text.DateFormat;
import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;


// Class for testing ORM Thing
@Entity(indexes = {
        @Index(value = "text, date DESC", unique = true)
})
public class Note {

    @Id(autoincrement = true)
    private Long id;

    @NotNull
    private String text;
    private Date date;

@Generated(hash = 1501873839)
public Note(Long id, @NotNull String text, Date date) {
    this.id = id;
    this.text = text;
    this.date = date;
}

@Generated(hash = 1272611929)
public Note() {
}

    private void addNote() {
        String noteText = "max";
        //editText.setText("");

        final DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
        String comment = "Added on " + df.format(new Date());

        Note note = new Note();
        note.setText(noteText);
        //note.setComment(comment);
        note.setDate(new Date());
        //note.setType(NoteType.TEXT);
        //noteDao.insert(note);
       //Log.d("DaoExample", "Inserted new note, ID: " + note.getId());

        //updateNotes();
    }

public Long getId() {
    return this.id;
}

public void setId(Long id) {
    this.id = id;
}

public String getText() {
    return this.text;
}

public void setText(String text) {
    this.text = text;
}

public Date getDate() {
    return this.date;
}

public void setDate(Date date) {
    this.date = date;
}


}


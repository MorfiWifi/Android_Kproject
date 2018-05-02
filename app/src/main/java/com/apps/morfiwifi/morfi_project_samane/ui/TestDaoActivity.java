package com.apps.morfiwifi.morfi_project_samane.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.apps.morfiwifi.morfi_project_samane.R;
import com.apps.morfiwifi.morfi_project_samane.models.DaoMaster;
import com.apps.morfiwifi.morfi_project_samane.models.DaoSession;
import com.apps.morfiwifi.morfi_project_samane.models.Note;
import com.apps.morfiwifi.morfi_project_samane.models.NoteDao;
import com.apps.morfiwifi.morfi_project_samane.utility.Init;

import org.greenrobot.greendao.query.Query;

public class TestDaoActivity extends AppCompatActivity {

    private NoteDao noteDao;
    private Query<Note> notesQuery;
    //private NotesAdapter notesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_dao);



        // get the note DAO




        DaoSession daoSession = DaoMaster.newDevSession(getApplicationContext(),"DB_DB");
        noteDao = daoSession.getNoteDao();
        Note note = new Note();
        note.setId(1l);
        note.setText("Max");
        //noteDao.insert(note);


    }

    public void load_dao(View view) {
        DaoSession daoSession = DaoMaster.newDevSession(getApplicationContext(),"DB_DB");
        noteDao = daoSession.getNoteDao();
//        Note note = new Note();
//        note.setId(1l);
//        note.setText("Max");
//        noteDao.insert(note);

        Note nn = noteDao.load(1L);
        if (nn != null)
            Init.Toas(getApplicationContext() , nn.getText());



    }
}

package com.example.notesappmvvm.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.notesappmvvm.Model.Note;

@Database(entities = {Note.class}, version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {
    private static volatile NoteDatabase INSTANCE;

    public abstract NoteDao noteDao();

    public synchronized static NoteDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext()
                            , NoteDatabase.class, "note_table")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    //  .addCallback(roomCallback)
                    .build();
        }

        return INSTANCE;
    }
}

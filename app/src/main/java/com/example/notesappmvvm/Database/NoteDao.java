package com.example.notesappmvvm.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notesappmvvm.Model.Note;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert
    void insertNote(Note... note);

    @Update
    void updateNote(Note note);

    @Query("DELETE FROM note_table WHERE id =:id")
    void deleteNote(int id);

    @Query("SELECT * FROM note_table")
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT * FROM note_table ORDER BY note_Priority DESC")
    LiveData<List<Note>> highToLow();

    @Query("SELECT * FROM note_table ORDER BY note_Priority ASC")
    LiveData<List<Note>> lowToHigh();
}

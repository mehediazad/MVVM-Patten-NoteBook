package com.example.notesappmvvm.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.notesappmvvm.Database.NoteDao;
import com.example.notesappmvvm.Database.NoteDatabase;
import com.example.notesappmvvm.Model.Note;

import java.util.List;

public class NoteRepository {
    public NoteDao noteDao;
    public LiveData<List<Note>> getAllNotes;
    public LiveData<List<Note>> highToLow;
    public LiveData<List<Note>> lowToHigh;

    public NoteRepository(Application application){
        NoteDatabase database =  NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        getAllNotes = noteDao.getAllNotes();
        highToLow = noteDao.highToLow();
        lowToHigh = noteDao.lowToHigh();
    }
    public  void insertNote(Note note){
        noteDao.insertNote(note);
    }
    public  void updateNote(Note note){
        noteDao.updateNote(note);
    }
    public  void deleteNote(int id){
        noteDao.deleteNote(id);
    }

}

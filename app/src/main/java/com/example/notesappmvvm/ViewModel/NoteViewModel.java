package com.example.notesappmvvm.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.notesappmvvm.Model.Note;
import com.example.notesappmvvm.Repository.NoteRepository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    public NoteRepository repository;
    public LiveData<List<Note>> getAllNotes;
    public LiveData<List<Note>> highToLow;
    public LiveData<List<Note>> lowToHigh;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        getAllNotes = repository.getAllNotes;
        highToLow = repository.highToLow;
        lowToHigh = repository.lowToHigh;
    }

    public void insertNote(Note note) {
        repository.insertNote(note);
    }

    public void updateNote(Note note) {
        repository.updateNote(note);
    }

    public void deleteNote(int id) {
        repository.deleteNote(id);
    }

}

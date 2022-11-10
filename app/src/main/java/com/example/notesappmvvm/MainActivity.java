package com.example.notesappmvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.notesappmvvm.Activity.InsertNoteActivity;
import com.example.notesappmvvm.Adapter.NotesAdapter;
import com.example.notesappmvvm.Model.Note;
import com.example.notesappmvvm.ViewModel.NoteViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton newNoteBtn;
    NoteViewModel noteViewModel;
    private RecyclerView recyclerView;
    private List<Note> noteList;
    private NotesAdapter notesAdapter;
    TextView noFilter, highToLow, lowToHigh;
    private List<Note> filterNotesAllList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        newNoteBtn = findViewById(R.id.newNoteBtn);
        recyclerView = findViewById(R.id.recyclerView);

        noFilter = findViewById(R.id.noFilter);
        highToLow = findViewById(R.id.highToLow);
        lowToHigh = findViewById(R.id.lowToHigh);

        noFilter.setBackgroundResource(R.drawable.filter_selected_shape);

        noFilter.setOnClickListener(v -> {
            loadData(0);
            highToLow.setBackgroundResource(R.drawable.filter_unselected_shape);
            lowToHigh.setBackgroundResource(R.drawable.filter_unselected_shape);
            noFilter.setBackgroundResource(R.drawable.filter_selected_shape);
        });
        highToLow.setOnClickListener(v -> {
            loadData(1);
            highToLow.setBackgroundResource(R.drawable.filter_selected_shape);
            lowToHigh.setBackgroundResource(R.drawable.filter_unselected_shape);
            noFilter.setBackgroundResource(R.drawable.filter_unselected_shape);
        });
        lowToHigh.setOnClickListener(v -> {
            loadData(2);
            highToLow.setBackgroundResource(R.drawable.filter_unselected_shape);
            lowToHigh.setBackgroundResource(R.drawable.filter_selected_shape);
            noFilter.setBackgroundResource(R.drawable.filter_unselected_shape);
        });

        noteList = new ArrayList<>();
        //setAdapter();
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);


        newNoteBtn.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, InsertNoteActivity.class));
        });

//        newNoteBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, InsertNoteActivity.class);
//                startActivity(intent);
//            }
//        });
        noteViewModel.getAllNotes.observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> noteList) {
                //notesAdapter.setNotes(noteList);
                setAdapter(noteList);
                filterNotesAllList = noteList;

            }
        });
    }

    private void loadData(int i) {
        if (i == 0) {
            noteViewModel.getAllNotes.observe(this, new Observer<List<Note>>() {
                @Override
                public void onChanged(List<Note> noteList) {
                    setAdapter(noteList);
                    filterNotesAllList = noteList;
                }
            });
        }else if (i==1){
            noteViewModel.highToLow.observe(this, new Observer<List<Note>>() {
                @Override
                public void onChanged(List<Note> noteList) {
                    setAdapter(noteList);
                    filterNotesAllList = noteList;
                }
            });
        }else if (i==2){
            noteViewModel.lowToHigh.observe(this, new Observer<List<Note>>() {
                @Override
                public void onChanged(List<Note> noteList) {
                    setAdapter(noteList);
                    filterNotesAllList = noteList;
                }
            });
        }
    }

    private void setAdapter(List<Note> noteList) {
        notesAdapter = new NotesAdapter(this, noteList);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(notesAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_notes,menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Notes Here...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                NotesFilter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
    private void NotesFilter(String newText) {
        ArrayList<Note> FilterNames = new ArrayList<>();
        for (Note note : this.filterNotesAllList){
            if (note.noteTitle.contains(newText) || note.noteSubtitle.contains(newText)){
                FilterNames.add(note);
            }
        }
        this.notesAdapter.searchNotes(FilterNames);

    }
}
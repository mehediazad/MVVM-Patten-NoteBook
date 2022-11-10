package com.example.notesappmvvm.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.notesappmvvm.Model.Note;
import com.example.notesappmvvm.R;
import com.example.notesappmvvm.ViewModel.NoteViewModel;
import com.example.notesappmvvm.databinding.ActivityInsertNoteBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class InsertNoteActivity extends AppCompatActivity {
    ActivityInsertNoteBinding binding;
    String title, subtitle, notes;
    NoteViewModel noteViewModel;
    String priority = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        binding.greenPriority.setOnClickListener(v -> {
            binding.greenPriority.setImageResource(R.drawable.done_24);
            binding.yellowPriority.setImageResource(0);
            binding.redPriority.setImageResource(0);
            priority = "1";

        });
        binding.yellowPriority.setOnClickListener(v -> {
            binding.greenPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(R.drawable.done_24);
            binding.redPriority.setImageResource(0);
            priority = "2";

        });
        binding.redPriority.setOnClickListener(v -> {
            binding.greenPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(0);
            binding.redPriority.setImageResource(R.drawable.done_24);
            priority = "3";
        });

//        binding.doneNoteBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                title = binding.titleEditText.getText().toString();
//                subtitle = binding.SubtitleEditText.getText().toString();
//                notes = binding.notesDataEditText.getText().toString();
//
//                CreateNotes(title,subtitle,notes);
//            }
//        });


        binding.doneNoteBtn.setOnClickListener(v -> {
            title = binding.titleEditText.getText().toString();
            subtitle = binding.SubtitleEditText.getText().toString();
            notes = binding.notesDataEditText.getText().toString();

            CreateNotes(title, subtitle, notes);
        });
    }

    private void CreateNotes(String title, String subtitle, String notes) {

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

//        Date date = new Date();
//        CharSequence sequence = DateFormat.format("MMMM d,YYYY", date.getTime());

        Note note1 = new Note();

        note1.noteTitle = title;
        note1.noteSubtitle = subtitle;
        note1.notePriority = priority;
        note1.notes = notes;
      //  note1.noteDate = sequence.toString();
        note1.noteDate = formattedDate.toString();

        noteViewModel.insertNote(note1);

        Toast.makeText(this, "Notes created Successfully", Toast.LENGTH_SHORT).show();
        finish();

    }
}
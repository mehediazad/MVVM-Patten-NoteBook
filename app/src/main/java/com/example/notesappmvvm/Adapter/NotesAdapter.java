package com.example.notesappmvvm.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesappmvvm.Activity.UpdateNoteActivity;
import com.example.notesappmvvm.MainActivity;
import com.example.notesappmvvm.Model.Note;
import com.example.notesappmvvm.R;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.notesViewHolder> {
    private Context context;
    private List<Note> noteList;
    private List<Note> allNotesItem;


    public NotesAdapter(Context context, List<Note> noteList) {
        this.context = context;
        this.noteList = noteList;
        allNotesItem = new ArrayList<>(noteList);
    }

    public void searchNotes(List<Note> filterredName){
        this.noteList = filterredName;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public notesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notes, parent, false);
        return new notesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull notesViewHolder holder, int position) {
        Note note = noteList.get(position);

        holder.notesTitle.setText(note.noteTitle);
        holder.notesSubtitle.setText(note.noteSubtitle);
        holder.notesTextView.setText(note.notes);
        holder.notesDate.setText(note.noteDate);

        if (note.notePriority.equals("1")) {
            holder.notesPriority.setBackgroundResource(R.drawable.green_shape);
        } else if (note.notePriority.equals("2")) {
            holder.notesPriority.setBackgroundResource(R.drawable.yellow_shape);
        } else if (note.notePriority.equals("3")) {
            holder.notesPriority.setBackgroundResource(R.drawable.red_shape);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, UpdateNoteActivity.class);
            intent.putExtra("id", note.id);
            intent.putExtra("title", note.noteTitle);
            intent.putExtra("subtitle", note.noteSubtitle);
            intent.putExtra("priority", note.notePriority);
            intent.putExtra("notes", note.notes);

            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return noteList.size();
    }

//    public void setNotes(List<Note> noteList) {
//        this.noteList = noteList;
//        notifyDataSetChanged();
//    }

    public class notesViewHolder extends RecyclerView.ViewHolder {
        private TextView notesTitle;
        private TextView notesSubtitle;
        private TextView notesTextView;
        private TextView notesDate;
        private View notesPriority;

        public notesViewHolder(@NonNull View itemView) {
            super(itemView);
            notesTitle = itemView.findViewById(R.id.notesTitle);
            notesSubtitle = itemView.findViewById(R.id.notesSubtitle);
            notesTextView = itemView.findViewById(R.id.notesTextView);
            notesDate = itemView.findViewById(R.id.notesDate);
            notesPriority = itemView.findViewById(R.id.notesPriority);
        }
    }
}

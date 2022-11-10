package com.example.notesappmvvm.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table", indices = @Index(value = {"id"}, unique = true))
public class Note {
   @PrimaryKey(autoGenerate = true)
    public int id;

   @ColumnInfo(name = "note_Title")
   public String noteTitle;

   @ColumnInfo(name = "note_Subtitle")
   public String noteSubtitle;

   @ColumnInfo(name = "note_Date")
   public String noteDate;

   @ColumnInfo(name = "notes")
   public String notes;

   @ColumnInfo(name = "note_Priority")
   public String notePriority;
}

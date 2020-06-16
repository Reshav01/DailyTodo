package com.example.daily_todo;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    //just defined method name, return type and argument. and inotaded with corresponding db.
    //predefined annotations
    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void  delete(Note note);

    //custom annotations
    @Query("DELETE FROM note_table")
    void deleteALLNotes();

    //at compile time room will check if the columns of this table fit to the List<Note> java object.
    //room can return live data.
    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    LiveData<List<Note>> getALLNotes();
}

package com.example.daily_todo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//thi is an SQ-lite naming convention
@Entity(tableName = "note_table") //by default the table name will be note (but it's changed assigning table name)
// this is a room annotation
//at compile time it will create all necessary notes
//to create an SQ-lite table for this object.
public class Note {
    //if member variables were public we wont have to use getters.
    //creating member variables for notes

    @PrimaryKey(autoGenerate = true)
    private int id; //row 1

    private String title;
    private String description;
    private int priority;
    private String IsComplete;
    private String inputDate;


    //constructors


    public Note(String title, String description, int priority,  String IsComplete, String inputDate) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.IsComplete = IsComplete;
        this.inputDate = inputDate;


    }

    //room will later use this method
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public String getIsComplete() { return IsComplete; }

    public String getInputDate() { return inputDate; }

}

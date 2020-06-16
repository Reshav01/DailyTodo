package entities;

import java.io.Serializable;

public class Todo implements Serializable {
    private int id;
    private String title;
    private String description;


    //getter and setter...
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //constructor
    public Todo(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    //constructor_todo
    public Todo() {
    }
}

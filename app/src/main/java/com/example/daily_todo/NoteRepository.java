package com.example.daily_todo;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

    //member variables
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    //constructor
    public NoteRepository(Application application) {

        NoteDatabase database = NoteDatabase.getInstance(application);

        //using abstract method from database class
        //we can't call abstract method since we don't have a method body
        //but using instance with builder
        //Room subclasses our abstract classes.
        noteDao = database.noteDao();
        //get all notes from NOteDao interface.
        allNotes = noteDao.getALLNotes();
    }

    //methods

    public  void  insert(Note note){

        new InsertNoteAsyncTask(noteDao).execute(note);

    }

    public  void  update(Note note){

        new UpdateNoteAsyncTask(noteDao).execute(note);
    }

    public  void  delete(Note note){

        new DeleteNoteAsyncTask(noteDao).execute(note);

    }

    public  void  deleteAllNotes(){

        new DeleteAllNotesAsyncTask(noteDao).execute();

    }

    public LiveData<List<Note>> getAllNotes(){
        return allNotes;

    }


    //class
    //Insert async task
    private  static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {

        //member variable for notedao
        private NoteDao noteDao;

        private InsertNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    //Update async task
    private  static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {

        //member variable for notedao
        private NoteDao noteDao;

        private UpdateNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    //delete async task
    private  static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {

        //member variable for notedao
        private NoteDao noteDao;

        private DeleteNoteAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    //deleteALL async task
    private  static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {

        //member variable for notedao
        private NoteDao noteDao;

        private DeleteAllNotesAsyncTask(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteALLNotes();
            return null;
        }
    }
}

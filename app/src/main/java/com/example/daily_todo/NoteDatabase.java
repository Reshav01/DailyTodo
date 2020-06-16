package com.example.daily_todo;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

//whenever we make changes in our database we have to change the version number.
@Database(entities ={Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    //variables
    private static NoteDatabase instance; //we cant create multiple instances of this database
                                         //we always use same instance everywhere in our app (static)

    //similar to Dao interface
    public abstract NoteDao noteDao();

    //singleton sync means only one thread at a time.
    public static synchronized NoteDatabase getInstance(Context context) {

        if (instance == null) {

            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration() //telling room how to migrate the schema.(this will delete the db if version is increased)
                    .addCallback(roomCallback)
                    .build();
        }

        return instance;
    }

    private  static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        private NoteDao noteDao;
        private PopulateDbAsyncTask(NoteDatabase db) {
            noteDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            noteDao.insert(new Note("Todo Title 1", "Description 1",  1, "Complete", "May 24, 2020"));
            noteDao.insert(new Note("Todo Title 2", "Description 2", 2, "Not Complete", "May 25, 2020"));
            noteDao.insert(new Note("Todo Title 3", "Description 3", 3, "On Going", "May 26, 2020"));
            return null;
        }
    }
}

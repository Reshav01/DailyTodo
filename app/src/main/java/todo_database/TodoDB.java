package todo_database;

import android.content.*;
import android.database.Cursor;
import android.database.sqlite.*;

import entities.*;
import java.util.*;


public class TodoDB extends SQLiteOpenHelper {


    public static String dbName = "TodoDB";
    private static String tableName = "todo_list";
    private static String idColumn1 = "id";
    private static String titleColumn2 = "title";
    private static String descriptionColumn3 = "description";
    private Context context;

    public TodoDB(Context context) {

        super(context, dbName, null, 1 );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE todo_list (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + tableName);
        onCreate(sqLiteDatabase);
    }

    public List<Todo> findAll() {
        try {

            List<Todo> todos = new ArrayList<Todo>();
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();

            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + tableName, null);
            if (cursor.moveToFirst()) {

                do {

                    Todo todo = new Todo();
                    todo.setId(cursor.getInt(0));
                    todo.setTitle(cursor.getString(1));
                    todo.setDescription(cursor.getString(2));
                    todos.add(todo);

                } while (cursor.moveToNext());
            }

            sqLiteDatabase.close();
            return todos;

        } catch (Exception e) {
            return null;
        }
    }

    public boolean create(Todo todo) {

        try {


            SQLiteDatabase sqLiteDatabase = getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put(titleColumn2, todo.getTitle());
            contentValues.put(descriptionColumn3, todo.getDescription());
            long rows = sqLiteDatabase.insert(tableName, null, contentValues);
            sqLiteDatabase.close();
            return rows > 0;
        } catch (Exception e) {
            return false;
        }
    }


    //delete button function..
    public boolean delete(int id) {

        try{

            SQLiteDatabase sqLiteDatabase = getWritableDatabase();

           int rows = sqLiteDatabase.delete(tableName, idColumn1 + " = ?", new String[] { String.valueOf(id)});
            sqLiteDatabase.close();

            return rows > 0;

        } catch (Exception e) {
            return false;
        }
    }

    //edit function.... getting vales from each id
    public Todo find(int id) {

        try {

        SQLiteDatabase  sqLiteDatabase = getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + tableName + " where " + idColumn1 + "= ?", new String[] { String.valueOf(id) } );

        Todo todo = null;
        if (cursor.moveToFirst()) {

            todo = new Todo();
            todo.setId(cursor.getInt(0));
            todo.setTitle(cursor.getString(1));
            todo.setDescription(cursor.getString(2));


        }

        sqLiteDatabase.close();
        return null;
        } catch (Exception e) {
            return null;
        }
    }

    //edit save button function... update function...

    public boolean update(Todo todo) {

        try {

            SQLiteDatabase sqLiteDatabase = getWritableDatabase();

            ContentValues contentValues =  new ContentValues();
            contentValues.put(titleColumn2, todo.getTitle());
            contentValues.put(descriptionColumn3, todo.getDescription());

           int rows = sqLiteDatabase.update(tableName, contentValues, idColumn1 + " = ?", new String[] { String .valueOf(todo.getId()) });
            sqLiteDatabase.close();
            return rows > 0;

        } catch (Exception e) {

            return false;
        }
    }



}

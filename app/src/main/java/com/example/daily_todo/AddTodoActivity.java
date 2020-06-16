package com.example.daily_todo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import entities.Todo;
import todo_database.*;

import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import android.content.*;

public class AddTodoActivity extends AppCompatActivity {

    private Button buttonBack;
    private Button buttonSave;
    private EditText editText_title;
    private EditText editText_description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        //edit text title and description...
        this.editText_title = (EditText) findViewById(R.id.editText_title);
        this.editText_description = (EditText) findViewById(R.id.editText_description);

        //back button Onclick_listener function..
        this.buttonBack = (Button) findViewById(R.id.buttonBack);
        this.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddTodoActivity.this, "Todo Home Page", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(AddTodoActivity.this, HomeActivity.class);
                startActivity(intent1);
                finish();
            }
        });


        //save button function...
        this.buttonSave = (Button) findViewById(R.id.buttonSave);
        this.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TodoDB todoDB = new TodoDB(getBaseContext());
                Todo todo = new Todo();
                todo.setTitle(editText_title.getText().toString().trim());
                todo.setDescription(editText_description.getText().toString().trim());

                //Empty fields validation ....
                if(editText_title.getText().toString().isEmpty()) {
                    Toast.makeText(AddTodoActivity.this, "Title Field is blank ", Toast.LENGTH_SHORT).show();
                    return;
                } else if(editText_description.getText().toString().isEmpty()) {
                    Toast.makeText(AddTodoActivity.this, "Description Field is blank ", Toast.LENGTH_SHORT).show();
                    return;
                }


                if(todoDB.create(todo)){
                    Toast.makeText(AddTodoActivity.this, "New Todo Added", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(AddTodoActivity.this, HomeActivity.class);
                    startActivity(intent1);
                } else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setMessage("Failed to Add Todo!!");
                    builder.setCancelable(false);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {

                            dialogInterface.cancel();
                        }
                    });
                    builder.create().show();
                }
            }
        });

    }
}

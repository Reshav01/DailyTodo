package com.example.daily_todo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

//import adapters.TodoListAdapter;
import entities.Todo;
import todo_database.TodoDB;

import android.view.View;
import android.widget.*;
import android.content.*;

public class TodoDetailActivity extends AppCompatActivity {

    private TextView textView_title;
    private TextView textView_description;
    private Button buttonBack;
    private Button buttonEdit;
    private Button buttonDelete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_detail);

        final Intent intent = getIntent();
        final Todo todo = (Todo) intent.getSerializableExtra("todo");
        this.textView_title = findViewById(R.id.textView_title);
        this.textView_title.setText(todo.getTitle());
        this.textView_description = findViewById(R.id.textView_description);
        this.textView_description.setText(todo.getDescription());

        //back button function...
        this.buttonBack = findViewById(R.id.buttonBack);
        this.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(TodoDetailActivity.this, HomeActivity.class);
                startActivity(intent1);
                finish();
            }
        });

        //delete button function...
        this.buttonDelete = findViewById(R.id.buttonDelete);
        this.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                builder.setCancelable(false);
                builder.setTitle("Confirm");
                builder.setMessage("Are your sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialogInterface, int which) {

                        TodoDB  todoDB = new TodoDB(getBaseContext());

                        if(todoDB.delete(todo.getId())) {

                            Toast.makeText(TodoDetailActivity.this, "Todo Deleted.", Toast.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(new Intent(TodoDetailActivity.this, HomeActivity.class));
                            startActivity(intent1);

                        } else  {

                            AlertDialog.Builder builder1 =new AlertDialog.Builder(getBaseContext());
                            builder1.setMessage("Failed to delete!!");
                            builder1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialogInterface.cancel();
                                }
                            });
                            builder1.create().show();
                        }


                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.cancel();
                    }
                });
                builder.create().show();
            }
        });

        this.buttonEdit = findViewById(R.id.buttonEdit);
        this.buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(TodoDetailActivity.this, EditActivity.class);

                intent1.putExtra("todo", todo);
                startActivity(intent1);
            }
        });
    }
}

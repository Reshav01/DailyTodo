package com.example.daily_todo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import entities.Todo;
import todo_database.*;
import android.view.View;
import android.widget.*;
import android.content.*;


public class EditActivity extends AppCompatActivity {

    private EditText editText_title;
    private EditText editText_description;
    private Button buttonBack;
    private Button buttonSave;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_todo);

        //edit functions contains back and save.....
        final Intent intent = getIntent();
        final Todo todo = (Todo) intent.getSerializableExtra("todo");

        this.editText_title = findViewById(R.id.editText_title);
        this.editText_title.setText(todo.getTitle());


        this.editText_description = findViewById(R.id.editText_description);
        this.editText_description.setText(todo.getDescription());

        //back button function...
        this.buttonBack = findViewById(R.id.buttonBack);
        this.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(EditActivity.this, HomeActivity.class);
                startActivity(intent1);
            }
        });

        //save button function ... to save the new edited values...
        this.buttonSave = findViewById(R.id.buttonSave);
        this.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TodoDB todoDB = new TodoDB(getBaseContext());
                todo.setTitle(editText_title.getText().toString());
                todo.setDescription(editText_description.getText().toString());

                if(todoDB.update(todo)) {

                    Toast.makeText(EditActivity.this, "Todo Updated!!", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(EditActivity.this, HomeActivity.class);
                    startActivity(intent1);

                } else  {

                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

                    builder.setMessage("Failed to update!!");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.create().show();

                }

            }
        });

    }
}

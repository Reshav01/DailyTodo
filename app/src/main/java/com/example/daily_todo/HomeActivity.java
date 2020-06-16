package com.example.daily_todo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import adapters.TodoListAdapter;
import entities.Todo;
import todo_database.*;
import android.view.View;
import android.widget.*;
import android.content.*;

import com.example.daily_todo.fragment.FragmentActivity;


public class HomeActivity extends AppCompatActivity {

    private Button button_add;
    private ListView listview_todo;
    private Button button_logout;
    private Button button_about;
    private Button button_dashboard, mvvmormtodobutton;

    TodoDB todoDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        this.button_add = (Button) findViewById(R.id.button_add);
        this.button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Add your Todos", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(HomeActivity.this, AddTodoActivity.class);
                startActivity(intent1);
            }
        });

        this.button_dashboard = (Button) findViewById(R.id.button_dashboard);
        this.button_dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Fragments Activity", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(HomeActivity.this, FragmentActivity.class);
                startActivity(intent1);
            }
        });

        this.button_logout = (Button) findViewById(R.id.button_logout);
        this.button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Successfully Logged out!", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });

        this.button_about = (Button) findViewById(R.id.button_about);
        this.button_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "mvvm page", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(HomeActivity.this, MvvmActivity.class);
                startActivity(intent1);
            }
        });

        //use orm to-do
        this.mvvmormtodobutton = (Button) findViewById(R.id.button_mvvmormtodo);
        this.mvvmormtodobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Launching orm Todo", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(HomeActivity.this, MvvmMainActivity.class);
                startActivity(intent1);
            }
        });


        todoDB = new TodoDB(this);
        this.listview_todo = (ListView) findViewById(R.id.listview_todo);
        this.listview_todo.setAdapter(new TodoListAdapter(this, todoDB.findAll()));

        this.listview_todo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                Todo todo = todoDB.findAll().get(i);
                Intent intent1 = new Intent(HomeActivity.this, TodoDetailActivity.class);
                intent1.putExtra("todo", todo);
                startActivity(intent1);
            }
        });
    }
}

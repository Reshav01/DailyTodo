package com.example.daily_todo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.daily_todo.R;
import com.example.daily_todo.mvvm.AdminLoginActivity;

public class FragmentActivity extends AppCompatActivity {

    private Button button_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);


        //add to-do button function..
        this.button_db = (Button) findViewById(R.id.dashboard_button);
        this.button_db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FragmentActivity.this, "Mvvm Dashboard Login Activity", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(FragmentActivity.this, AdminLoginActivity.class);
                startActivity(intent1);
            }
        });
    }
}

package com.example.daily_todo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    //adding database helper..
    DatabaseHelper db;

    //using and calling edit-text, button and text-view
    EditText mTextUsername;
    EditText mTextPassword;
    EditText mTextCnfPassword;
    Button mButtonRegister;
    TextView mTextViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //using database...
        db = new DatabaseHelper(this);

        mTextUsername = (EditText) findViewById(R.id.edittext_username);
        mTextPassword = (EditText) findViewById(R.id.edittext_password);
        mTextCnfPassword = (EditText) findViewById(R.id.edittext_cnf_password);
        mButtonRegister = (Button) findViewById(R.id.button_register);
        mTextViewLogin = (TextView) findViewById(R.id.textview_login);
        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(LoginIntent);
            }
        });

        //function to register user after clicking register button...
        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            String user = mTextUsername.getText().toString().trim();
            String pwd = mTextPassword.getText().toString().trim();
            String cnf_pwd = mTextCnfPassword.getText().toString().trim();


            //empty fields validation...
                if(TextUtils.isEmpty(user)) {
                    Toast.makeText( RegisterActivity.this, "User name field is blank. ", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(pwd)) {
                    Toast.makeText( RegisterActivity.this, "Password field is blank. ", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (TextUtils.isEmpty(cnf_pwd)) {
                    Toast.makeText( RegisterActivity.this, "Cnf_Password field is blank. ", Toast.LENGTH_SHORT).show();
                    return;
                }
            //checking password and confirm password fields...
            if(pwd.equals(cnf_pwd)) {

                long val = db.addUser(user,pwd); //adding user and passing the parameter...

                //checking user before adding...
                if(val > 0) {

                    Toast.makeText(RegisterActivity.this, "Successfully Registered!!", Toast.LENGTH_SHORT).show();
                    Intent moveToLogin = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(moveToLogin);
                }
                else{

                    Toast.makeText(RegisterActivity.this, "Error in Registration!!", Toast.LENGTH_SHORT).show();
                    Intent moveToLogin = new Intent(RegisterActivity.this, RegisterActivity.class);
                    startActivity(moveToLogin);
                }


            }
            else {

                Toast.makeText(RegisterActivity.this, "Both Password fields Should match!!", Toast.LENGTH_SHORT).show();
            }



            } //ends public void onClick(View view)
        }); // ends  mButtonRegister.setOnClickListener(new View.OnClickListener()
    }
}

package com.example.daily_todo.mvvm.model;

import android.text.TextUtils;
import android.util.Patterns;

public class User {


    //model class

    String admin_email, admin_password;

    //setting getter and setter
    public String getAdmin_email() {
        return admin_email;
    }

    public void setAdmin_email(String admin_email) {
        this.admin_email = admin_email;
    }

    public String getAdmin_password() {
        return admin_password;
    }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }

    public boolean isValid() {
        return this.admin_email != null && !TextUtils.isEmpty(admin_email) && Patterns.EMAIL_ADDRESS.matcher(admin_email).matches();
    }
}

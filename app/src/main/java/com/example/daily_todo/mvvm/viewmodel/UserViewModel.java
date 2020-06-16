package com.example.daily_todo.mvvm.viewmodel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.daily_todo.mvvm.model.User;


//here data comes from xml
//passing data from here to  model

public class UserViewModel extends ViewModel {

    // variable, array string mutablelivedata
    public MutableLiveData<String> admin_email = new MutableLiveData<>();
    public MutableLiveData<String> admin_password = new MutableLiveData<>();


    public User user; //model class object
    private Context context;

    //creating constructor
    //view bata data aucha teslai as a constructor pass garna banako
//pass context and model class
    public UserViewModel(Context context, User user) {

        this.user = user;
        this.context = context;

    }

    //login button
    public void onSubmitClick() {
        user.setAdmin_email(admin_email.getValue());
        user.setAdmin_password(admin_password.getValue());

        if (user.isValid()) {
            Toast.makeText(context, "Welcome Admin, Dashboard is Under Construction", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Please Enter a Valid Admin Email", Toast.LENGTH_SHORT).show();
        }

    }
}

package com.example.daily_todo;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.daily_todo.User;

import java.util.ArrayList;

public class MvvmViewModel extends ViewModel {

    MutableLiveData<ArrayList<User>> userLiveData;
    ArrayList<User> userArrayList;

    public MvvmViewModel() {
        userLiveData = new MutableLiveData<>();

        // call your Rest API in init method
        init();
    }

    public MutableLiveData<ArrayList<User>> getUserMutableLiveData() {
        return userLiveData;
    }

    public void init(){
        populateList();
        userLiveData.setValue(userArrayList);
    }

    public void populateList(){

        User user = new User();
        user.setTitle(" About App ");
        user.setDescription(" To do app your daily todo \n Created By: Rishav Kharel \n You can add, edit or delete your todo \n user must be login to add todo. \n Contact: developerrishav@tbc.edu.np");
        user.setDescription2("© 2020 Rishav K. DailyTodo. All Rights Reserved. ");
        userArrayList = new ArrayList<>();
        userArrayList.add(user);

    }
}
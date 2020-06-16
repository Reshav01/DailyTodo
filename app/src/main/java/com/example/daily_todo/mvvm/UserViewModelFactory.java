package com.example.daily_todo.mvvm;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.daily_todo.mvvm.model.User;
import com.example.daily_todo.mvvm.viewmodel.UserViewModel;
//using lifecycle


class UserViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    //mvvm method ma observable use garne
    private User user;
    private Context context;
    public UserViewModelFactory(AdminLoginActivity adminLoginActivity, User user) {

        this.context = adminLoginActivity;
        this.user = user;

    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        return (T)new UserViewModel(context,user);
    }
}

package com.example.daily_todo.mvvm;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.example.daily_todo.R;
import com.example.daily_todo.databinding.ActivityAdminLoginBinding;
import com.example.daily_todo.mvvm.model.User;
import com.example.daily_todo.mvvm.viewmodel.UserViewModel;

public class AdminLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAdminLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_admin_login);

        //userview model initializing

        UserViewModel userViewModel = ViewModelProviders.of(this,new  UserViewModelFactory(this, new User())).get(UserViewModel.class);

        binding.setUserModel(userViewModel);
    }
}

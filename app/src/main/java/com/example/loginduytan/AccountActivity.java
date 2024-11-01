package com.example.loginduytan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.loginduytan.api.UserData;
import com.example.loginduytan.databinding.ActivityAccountBinding;
import com.example.loginduytan.databinding.ActivityMainBinding;
import com.google.gson.Gson;

public class AccountActivity extends AppCompatActivity {
    private ActivityAccountBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadDataUser();
    }

    private void loadDataUser(){
        // Lấy chuỗi JSON từ Intent
        String userDataJson = getIntent().getStringExtra("userData");

        // Chuyển đổi JSON trở lại thành đối tượng UserData
        Gson gson = new Gson();
        UserData userData = gson.fromJson(userDataJson, UserData.class);

        // Sử dụng đối tượng userData (ví dụ: hiển thị thông tin)
        System.out.println("Account ID: " + userData.getACCOUNT_ID());
        System.out.println("Username: " + userData.getUSERNAME());
        System.out.println("Role Name: " + userData.getROLE_NAME());
    }
}
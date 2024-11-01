package com.example.loginduytan;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginduytan.api.ApiService;
import com.example.loginduytan.api.Role;
import com.example.loginduytan.api.RoleManager;
import com.example.loginduytan.api.RoleResponse;
import com.example.loginduytan.api.UserData;
import com.example.loginduytan.databinding.ActivityAccountBinding;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountActivity extends AppCompatActivity {
    private ActivityAccountBinding binding;
    private final String key = "mydtu.duytan.edu.vn";
    private RoleManager roleManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadRole();

        setupEvent();

        delayExecution(500);
    }

    private void loadDataUser() {
        // Lấy chuỗi JSON từ Intent
        String userDataJson = getIntent().getStringExtra("userData");

        // Chuyển đổi JSON trở lại thành đối tượng UserData
        Gson gson = new Gson();
        UserData userData = gson.fromJson(userDataJson, UserData.class);

        // Sử dụng đối tượng userData (ví dụ: hiển thị thông tin)
        System.out.println("Account ID: " + userData.getACCOUNT_ID());
        System.out.println("Username: " + userData.getUSERNAME());
        System.out.println("Role Name: " + userData.getROLE_NAME());

        String username = userData.getUSERNAME();
        String createDate = userData.getCREATE_DATE();
        int role = userData.getROLE_ID();
        String roleName = roleManager.getRoleNameById(role);
        Log.e("roleName", roleName);
        binding.usernameTextView.setText(username);
        binding.createDateTextView.setText(createDate);
        binding.roleTextView.setText(roleName);
    }

    private void loadRole() {
        ApiService.apiService.getRoles(key).enqueue(new Callback<RoleResponse>() {
            @Override
            public void onResponse(Call<RoleResponse> call, Response<RoleResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    RoleResponse roleResponse = response.body();
                    if (roleResponse.getErrorCode() == 0) {
                        roleManager = new RoleManager(roleResponse.getData());
                        // Bạn có thể lưu danh sách vai trò vào SharedPreferences hoặc Database để sử dụng sau này
                    }

//                    List<Role> roles = roleResponse.getData();
//                    for (Role role : roles) {
//                        Log.d("Role", "ID: " + role.getROLE_ID() + ", Name: " + role.getROLE_NAME());
//                    }
                } else {
                    Log.e("API Error", "Response không thành công.");
                }
            }

            @Override
            public void onFailure(Call<RoleResponse> call, Throwable t) {
                Log.e("API Error", "Gọi API thất bại: " + t.getMessage());
            }
        });
    }

    private void setupEvent() {
        binding.btnLogout.setOnClickListener(v -> handleLogout());
    }

    private void handleLogout() {
        finish();
    }

    private void delayExecution(long delayMillis) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Đây là nơi bạn gọi hàm tiếp theo sau delay
                loadDataUser();
            }
        }, delayMillis);
    }
}
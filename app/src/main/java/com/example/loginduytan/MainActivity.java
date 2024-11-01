package com.example.loginduytan;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginduytan.api.ApiService;
import com.example.loginduytan.api.LoginRequest;
import com.example.loginduytan.api.LoginResponse;
import com.example.loginduytan.api.UserData;
import com.example.loginduytan.databinding.ActivityMainBinding;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupEvent();
    }

    private void setupEvent() {
        binding.btnLogin.setOnClickListener(v -> onClickLogin());

        binding.btnRegister.setOnClickListener(v -> onClickRegister());
    }

    private void onClickLogin() {
        handleLogin();
    }

    private void onClickRegister() {
        gotoRegister();
    }

    private void gotoRegister() {
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    private void handleLogin() {
        String username = binding.txtUsername.getText().toString();
        String password = binding.txtPassword.getText().toString();
        LoginRequest loginRequest = new LoginRequest(username, password);

        ApiService.apiService.login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();

                    if (loginResponse != null && loginResponse.getErrorCode() == 0) {
                        String message = loginResponse.getMessage();
                        UserData userData = loginResponse.getData();

                        System.out.println("Đăng nhập thành công. Tên người dùng: " + userData.getUSERNAME());
                        gotoAccount(userData);

                    } else {
                        // Xử lý trường hợp đăng nhập không thành công
                        System.out.println("Đăng nhập thất bại: " + (loginResponse != null ? loginResponse.getMessage() : "Lỗi không xác định"));

                    }
                } else {
                    // Xử lý lỗi phản hồi
                    System.out.println("Phản hồi lỗi từ server: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // Xử lý khi gọi API thất bại, ví dụ: mất kết nối mạng
                System.out.println("Gọi API thất bại: " + t.getMessage());
            }
        });
    }

    private void gotoAccount(UserData userData) {
        Gson gson = new Gson();
        String userDataJson = gson.toJson(userData);
        Intent intent = new Intent(MainActivity.this, AccountActivity.class);
        intent.putExtra("userData", userDataJson);
        startActivity(intent);
    }
}
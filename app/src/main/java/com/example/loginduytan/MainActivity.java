package com.example.loginduytan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginduytan.api.ApiService;
import com.example.loginduytan.api.LoginRequest;
import com.example.loginduytan.api.LoginResponse;
import com.example.loginduytan.api.UserData;
import com.example.loginduytan.databinding.ActivityMainBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupEvent();
    }

    private void setupEvent() {
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLogin();
            }
        });

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRegister();
            }
        });
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

        LoginRequest loginRequest = new LoginRequest("test", "123456"); // Ví dụ về dữ liệu


        ApiService.apiService.login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();

                    // Kiểm tra phản hồi từ API
                    if (loginResponse != null && loginResponse.getErrorCode() == 0) {
                        // Đăng nhập thành công
                        String message = loginResponse.getMessage();
                        UserData userData = loginResponse.getData();

                        // Ví dụ: Hiển thị tên người dùng
                        System.out.println("Đăng nhập thành công. Tên người dùng: " + userData.getUSERNAME());
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
}
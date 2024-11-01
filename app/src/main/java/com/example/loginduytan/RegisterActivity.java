package com.example.loginduytan;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginduytan.api.ApiService;
import com.example.loginduytan.api.RegisterRequest;
import com.example.loginduytan.api.RegisterResponse;
import com.example.loginduytan.databinding.ActivityRegisterBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    String key = "mydtu.duytan.edu.vn";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
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
        gotoLogin();
    }

    private void onClickRegister() {
        handleRegister();
    }

    private void gotoLogin() {
        finish();
    }

    private void handleRegister(){

        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC")); // Đặt múi giờ UTC để có đuôi "Z"
        String formattedDate = sdf.format(now);
        System.out.println("Thời gian hiện tại: " + formattedDate);

        String username = binding.txtUsername.getText().toString();
        String password = binding.txtPassword.getText().toString();
        String role = binding.txtRole.getText().toString();
        System.out.println("role: " + role);
        int role_id = Integer.parseInt(role);
        RegisterRequest registerRequest = new RegisterRequest(key, username, password, role_id, formattedDate);

        ApiService.apiService.register(registerRequest).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    RegisterResponse registerResponse = response.body();
                    if (registerResponse != null && registerResponse.getErrorCode() == 0) {
                        // Đăng ký thành công
                        System.out.println("Đăng ký thành công: " + registerResponse.getMessage());
                    } else {
                        // Xử lý lỗi từ server
                        System.out.println("Lỗi đăng ký: " + (registerResponse != null ? registerResponse.getMessage() : "Không xác định"));
                    }
                } else {
                    System.out.println("Phản hồi lỗi từ server: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                // Xử lý khi gọi API thất bại
                System.out.println("Gọi API thất bại: " + t.getMessage());
            }
        });
    }
}
package com.example.loginduytan;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginduytan.api.ApiService;
import com.example.loginduytan.api.RoleManager;
import com.example.loginduytan.api.RoleResponse;
import com.example.loginduytan.api.UserData;
import com.example.loginduytan.databinding.ActivityAccountBinding;
import com.google.gson.Gson;

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
        String userDataJson = getIntent().getStringExtra("userData");

        Gson gson = new Gson();
        UserData userData = gson.fromJson(userDataJson, UserData.class);

        String username = userData.getUSERNAME();
        String createDate = userData.getCREATE_DATE();
        int role = userData.getROLE_ID();
        String roleName = roleManager.getRoleNameById(role);
        Log.e("roleName", roleName);
        binding.usernameTextView.setText(username);
        binding.createDateTextView.setText(createDate);
        binding.roleTextView.setText(roleName);
        String avatarBase64 = userData.getAVATAR();
        Bitmap avatarBitmap = decodeBase64ToBitmap(avatarBase64);
        binding.avatarImageView.setImageBitmap(avatarBitmap);
    }

    private Bitmap decodeBase64ToBitmap(String base64String) {
        if (base64String.contains(",")) {
            base64String = base64String.split(",")[1];
        }

        byte[] decodedBytes = Base64.decode(base64String, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    private void loadRole() {
        ApiService.apiService.getRoles(key).enqueue(new Callback<RoleResponse>() {
            @Override
            public void onResponse(Call<RoleResponse> call, Response<RoleResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    RoleResponse roleResponse = response.body();
                    if (roleResponse.getErrorCode() == 0) {
                        roleManager = new RoleManager(roleResponse.getData());
                    }

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
        new Handler().postDelayed(() -> loadDataUser(), delayMillis);
    }
}
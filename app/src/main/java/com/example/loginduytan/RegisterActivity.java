package com.example.loginduytan;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginduytan.api.ApiService;
import com.example.loginduytan.api.RegisterRequest;
import com.example.loginduytan.api.RegisterResponse;
import com.example.loginduytan.api.Role;
import com.example.loginduytan.api.RoleManager;
import com.example.loginduytan.api.RoleResponse;
import com.example.loginduytan.api.UserData;
import com.example.loginduytan.databinding.ActivityRegisterBinding;
import com.example.loginduytan.dialog.CustomDialog;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private final String key = "mydtu.duytan.edu.vn";
    private Context context;
    private List<Role> roleList;
    private List<String> listRoleName = new ArrayList<>();
    private Spinner dropdownMenu;
    private RoleManager roleManager;
    private int roleID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = this;
        setupEvent();
        delayExecution(500);
    }

    private void setupEvent() {
        binding.btnLogin.setOnClickListener(v -> onClickLogin());

        binding.btnRegister.setOnClickListener(v -> onClickRegister());
        dropdownMenu = binding.dropdownMenu;
        listenSelectRole();
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

    private void handleRegister() {
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String formattedDate = sdf.format(now);
        String username = binding.txtUsername.getText().toString();
        String password = binding.txtPassword.getText().toString();

        RegisterRequest registerRequest = new RegisterRequest(key, username, password, roleID, formattedDate);

        ApiService.apiService.register(registerRequest).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    RegisterResponse registerResponse = response.body();
                    if (registerResponse != null && registerResponse.getErrorCode() == 0) {
                        showDialog("Đăng ký thành công: " + registerResponse.getMessage());
                    } else {
                        showDialog("Lỗi đăng ký" + (registerResponse != null ? registerResponse.getMessage() : "Không xác định"));
                    }
                } else {
                    showDialog("Phản hồi lỗi từ server: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                showDialog("Gọi API thất bại: " + t.getMessage());
            }
        });
    }

    private void showDialog(String content){
        CustomDialog customDialog = new CustomDialog(this, content);
        customDialog.show();
    }

    private void loadRole() {
        ApiService.apiService.getRoles(key).enqueue(new Callback<RoleResponse>() {
            @Override
            public void onResponse(Call<RoleResponse> call, Response<RoleResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    RoleResponse roleResponse = response.body();
                    roleManager = new RoleManager(roleResponse.getData());
                    roleList = roleResponse.getData();
                    for (Role role : roleList) {
                        listRoleName.add(role.getROLE_NAME());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(context,
                            android.R.layout.simple_dropdown_item_1line, listRoleName);
                    dropdownMenu.setAdapter(adapter);
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

    private void delayExecution(long delayMillis) {
        new Handler().postDelayed(() -> loadRole(), delayMillis);
    }

    private void listenSelectRole(){
        dropdownMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Lấy dữ liệu đã chọn
                String selectedRole = (String) parent.getItemAtPosition(position);
                Log.e("API Error", selectedRole);

                roleID= roleManager.getRoleIdByName(selectedRole);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Không làm gì khi không có lựa chọn
            }
        });
    }
}
package com.app.servicekendaraan.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.app.servicekendaraan.MainActivity;
import com.app.servicekendaraan.databinding.ActivityLoginBinding;
import com.app.servicekendaraan.register.RegisterActivity;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;
    public SharedPreferences sharedpreferences;
    public static final String mypreference = "service_preference";
    public static final String userNamePref = "userKey";
    private ArrayList<String> permissions = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        initPreferences();
        checkPermission();

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               login();
            }
        });

        binding.textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private void login() {
        String username = binding.etUsername.getText().toString();
        String password = binding.etPassword.getText().toString();

        if(username.length() == 0) {
            binding.etUsername.setError("Username is required");
            return;
        }

        if(password.length() == 0) {
            binding.etPassword.setError("Password is required");
            return;
        }

        viewModel.login(username,password).observe(LoginActivity.this, userData -> {
            if (userData == null) {
                Toast.makeText(LoginActivity.this, "User tidak ditemukan!", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString(userNamePref, username);
                editor.commit();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void initPreferences() {
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains(userNamePref)) {
            String userName = sharedpreferences.getString(userNamePref, "");
            Log.d("shared_pref", userName);
            if(!userName.isEmpty()) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        }
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED) {
            permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }

        if (permissions.size() > 0) {
            String[] str = new String[permissions.size()];
            for (int i = 0; i < permissions.size(); i++)
                str[i] = permissions.get(i);
            ActivityCompat.requestPermissions(this, str, 130);
        }
    }
}
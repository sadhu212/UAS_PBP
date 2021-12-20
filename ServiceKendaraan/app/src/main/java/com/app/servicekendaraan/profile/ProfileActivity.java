package com.app.servicekendaraan.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.app.servicekendaraan.MainActivity;
import com.app.servicekendaraan.databinding.ActivityProfileBinding;
import com.app.servicekendaraan.history.HistoryActivity;
import com.app.servicekendaraan.login.LoginActivity;
import com.app.servicekendaraan.roomdatabase.user.UserData;
import com.app.servicekendaraan.setting.SettingActivity;
import com.app.servicekendaraan.teknisi.ProfilTeknisiActivity;

public class ProfileActivity extends AppCompatActivity {

    ActivityProfileBinding binding;
    private ProfileViewModel viewModel;
    public SharedPreferences sharedpreferences;
    public static final String mypreference = "service_preference";
    public static final String userNamePref = "userKey";
    private String userName;
    private UserData userdata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initPreferences();

        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        if(!userName.isEmpty()) {
            viewModel.getDataByUsername(userName).observe(this, userData -> {
                if (userData == null) {
                    return;
                }

                this.userdata = userData;
                binding.usernameEdt.setText(userData.getUsername());
                binding.umurEdt.setText(String.valueOf(userData.getUmur()));
                binding.alamatEdt.setText(userData.getAlamat());
                binding.noEdt.setText(userData.getTelpon());
                binding.emaiEdt.setText(userData.getEmail());
            });
        }
        
        binding.editCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nama = binding.usernameEdt.getText().toString();
                String umur = binding.umurEdt.getText().toString();
                String alamat = binding.alamatEdt.getText().toString();
                String noTelp = binding.noEdt.getText().toString();
                String email = binding.emaiEdt.getText().toString();
                
                
                if(nama.length() == 0) {
                    binding.usernameEdt.setError("Required");
                    return;
                }
                
                if(umur.length() == 0) {
                    binding.umurEdt.setError("Required");
                    return;
                }
                
                if(alamat.length() == 0) {
                    binding.alamatEdt.setError("Required");
                    return;
                }
                
                if(noTelp.length() == 0) {
                    binding.noEdt.setError("Required");
                    return;
                }
                
                if(email.length() == 0) {
                    binding.emaiEdt.setError("Required");
                    return;
                }
                
                userdata.setNama(nama);
                userdata.setUmur(Integer.parseInt(umur));
                userdata.setAlamat(alamat);
                userdata.setTelpon(noTelp);
                userdata.setEmail(email);
                
                viewModel.edit(userdata);
                Toast.makeText(ProfileActivity.this, "Sukses update profile!", Toast.LENGTH_SHORT).show();
            }
        });

        binding.backCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.historyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, HistoryActivity.class));
            }
        });

        binding.settingImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, SettingActivity.class));
            }
        });

        binding.logoutCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.remove(userNamePref);
                editor.apply();

                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    private void initPreferences() {
        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains(userNamePref)) {
            userName = sharedpreferences.getString(userNamePref, "");
        }
    }
}
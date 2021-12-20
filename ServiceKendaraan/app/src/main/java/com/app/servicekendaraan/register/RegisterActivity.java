package com.app.servicekendaraan.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.app.servicekendaraan.databinding.ActivityRegisterBinding;
import com.app.servicekendaraan.roomdatabase.user.UserData;
import com.app.servicekendaraan.utils.LoadingDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private LoadingDialog loadingDialog;
    private RegisterViewModel viewModel;
    boolean dataIsEmpty = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        loadingDialog = new LoadingDialog(this);

        binding.RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingDialog.startLoadingDialog();
                dataIsEmpty = false;

                String nama = binding.NamaLengkap.getText().toString();
                if(nama.length() == 0) {
                    binding.NamaLengkap.setError("Required");
                    return;
                }

                String umur = binding.Umur.getText().toString();
                if (umur.length() == 0) {
                    binding.Umur.setError("Required");
                    return;
                }

                String alamat = binding.Alamat.getText().toString();
                if(alamat.length() == 0) {
                    binding.Alamat.setError("Required");
                    return;
                }

                String telpn = binding.NoTelp.getText().toString();
                if(telpn.length() == 0) {
                    binding.NoTelp.setError("Required");
                    return;
                }

                String email = binding.Email.getText().toString();
                if(email.length() == 0) {
                    binding.Email.setError("Required");
                    return;
                }

                String username = binding.Username.getText().toString();
                if(username.length() == 0) {
                    binding.Username.setError("Required");
                    return;
                }

                String password = binding.Password.getText().toString();
                if(password.length() == 0) {
                    binding.Password.setError("Required");
                    return;
                }


                viewModel.getDataByUsername(username).observe(RegisterActivity.this, data -> {
                    Log.d("ABC_LOG ", "data " + dataIsEmpty);
                    if (data == null) {
                        dataIsEmpty = true;


                        Date c = Calendar.getInstance().getTime();
                        SimpleDateFormat df = new SimpleDateFormat("dd/MMM/yyyy", Locale.getDefault());
                        String formattedDate = df.format(c);

                        UserData userData = new UserData();
                        userData.setNama(nama);
                        userData.setAlamat(alamat);
                        userData.setEmail(email);
                        userData.setUsername(username);
                        userData.setPassword(password);
                        userData.setTelpon(telpn);
                        userData.setUmur(Integer.parseInt(umur));
                        userData.setTanggal(formattedDate);
                        viewModel.addUser(userData);
                        loadingDialog.dismisDialog();
                        clearText();
                        Toast.makeText(RegisterActivity.this, "Register Success", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        if(!dataIsEmpty) {
                            loadingDialog.dismisDialog();
                            Toast.makeText(RegisterActivity.this, "Username sudah digunakan!", Toast.LENGTH_SHORT).show();
                            binding.Username.setError("Username sudah digunakan!");
                        }
                        return;
                    }
                });


            }
        });

    }

    private void clearText() {
        binding.NamaLengkap.setText("");
        binding.Alamat.setText("");
        binding.Email.setText("");
        binding.Username.setText("");
        binding.Password.setText("");
        binding.NoTelp.setText("");
        binding.Umur.setText("");
    }
}
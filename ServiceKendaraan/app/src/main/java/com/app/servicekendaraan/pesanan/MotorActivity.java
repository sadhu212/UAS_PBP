package com.app.servicekendaraan.pesanan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.app.servicekendaraan.Camera.QRScannerActivity;
import com.app.servicekendaraan.databinding.ActivityMotorBinding;

public class MotorActivity extends AppCompatActivity {

    private ActivityMotorBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMotorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = binding.etInput.getText().toString();
                if(text.length() == 0 ) {
                    binding.etInput.setError("Cannot empty");
                    return;
                }
                startActivity(new Intent(MotorActivity.this, PesananActivity.class).putExtra("dekripsi", text));
            }
        });

    }
    public void Pindah(View view) {
        Intent intent = new Intent(MotorActivity.this, QRScannerActivity.class);
        startActivity(intent);
    }
}
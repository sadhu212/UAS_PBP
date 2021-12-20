package com.app.servicekendaraan.pesanan;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.app.servicekendaraan.MapsActivity;
import com.app.servicekendaraan.databinding.ActivityPesananBinding;
import com.app.servicekendaraan.roomdatabase.pesanan.PesananData;
import com.app.servicekendaraan.teknisi.ProfilTeknisiActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PesananActivity extends AppCompatActivity {
    private ActivityPesananBinding binding;
    private PesananViewModel viewModel;
    private String desc;
    private String harga;
    private String metode;
    private String alamat;
    private String mapsLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPesananBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(PesananViewModel.class);

        desc = getIntent().getStringExtra("dekripsi");


        ActivityResultLauncher<Intent> launchSomeActivity = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            mapsLocation = data.getStringExtra("dataAddress");
                            binding.tvLocationMaps.setText(mapsLocation);
                        }
                    }
                });


        binding.btnBuatPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alamat = binding.etInputAlamat.getText().toString();
                if(alamat.length() == 0) {
                    binding.etInputAlamat.setError("Required");
                    return;
                }

                harga = binding.etInputHarga.getText().toString();
                if(harga.length() == 0) {
                    binding.etInputHarga.setError("Required");
                    return;
                }

                metode = binding.etInputMetode.getText().toString();
                if(metode.length() == 0) {
                    binding.etInputMetode.setError("Required");
                    return;
                }

                saveData();
            }
        });

        binding.ivLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(PesananActivity.this, MapsActivity.class);
                launchSomeActivity.launch(intent);
            }
        });

    }

    private void saveData () {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MMM/yyyy", Locale.getDefault());
        String formattedDate = df.format(c);

        PesananData pesananData = new PesananData();
        pesananData.setAlamat(alamat);
        pesananData.setAlamatMaps(mapsLocation);
        pesananData.setHarga(harga);
        pesananData.setMetodePembayaran(metode);
        pesananData.setTanggal(formattedDate);

        viewModel.addPesanan(pesananData);

        startActivity(new Intent(PesananActivity.this, ProfilTeknisiActivity.class));
    }
}
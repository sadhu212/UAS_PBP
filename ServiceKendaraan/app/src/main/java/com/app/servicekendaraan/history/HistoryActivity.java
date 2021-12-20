package com.app.servicekendaraan.history;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.app.servicekendaraan.R;
import com.app.servicekendaraan.databinding.ActivityHistoryBinding;
import com.app.servicekendaraan.history.adapter.HistoryAdapter;
import com.app.servicekendaraan.pesanan.MobilActivity;
import com.app.servicekendaraan.pesanan.PesananViewModel;
import com.app.servicekendaraan.roomdatabase.pesanan.PesananData;
import com.app.servicekendaraan.roomdatabase.user.UserData;

public class HistoryActivity extends AppCompatActivity {

    private ActivityHistoryBinding binding;
    private HistoryViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel= new ViewModelProvider(this).get(HistoryViewModel.class);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));


        viewModel.getAllData().observe(this, pesananData -> {
            binding.recyclerView.setAdapter(new HistoryAdapter(pesananData, new HistoryAdapter.HistoryClick() {
                @Override
                public void historyClick(UserData userData) {
                    viewModel.deleteData(userData);
                }
            }));
        });

        binding.backCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.buttonHistoryPelanggan.setOnClickListener(view -> {
            startActivity(new Intent(HistoryActivity.this, HistoryKeluhanPelangganActivity.class));
        });

        binding.buttonBikinPesanan.setOnClickListener(view -> {
            startActivity(new Intent(HistoryActivity.this, MobilActivity.class));
        });

    }
}
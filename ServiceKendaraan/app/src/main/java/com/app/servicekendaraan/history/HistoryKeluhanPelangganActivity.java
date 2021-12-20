package com.app.servicekendaraan.history;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.app.servicekendaraan.R;
import com.app.servicekendaraan.databinding.ActivityHistoryBinding;
import com.app.servicekendaraan.databinding.ActivityHistoryPesananBinding;
import com.app.servicekendaraan.history.adapter.HistoryAdapter;
import com.app.servicekendaraan.roomdatabase.pesanan.PesananData;
import com.app.servicekendaraan.roomdatabase.user.UserData;

public class HistoryKeluhanPelangganActivity extends AppCompatActivity {

    private ActivityHistoryPesananBinding binding;
    private HistoryViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistoryPesananBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel= new ViewModelProvider(this).get(HistoryViewModel.class);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));


        viewModel.getKeluhanPelangganList().observe(this, pesananData -> binding.recyclerView.setAdapter(new PelangganAdapter(pesananData, userData -> {
            viewModel.deletePesanan(userData);
        })));

        binding.backCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }
}
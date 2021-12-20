package com.app.servicekendaraan.history;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.servicekendaraan.databinding.HistoryLayoutBinding;
import com.app.servicekendaraan.roomdatabase.pesanan.PesananData;
import com.app.servicekendaraan.roomdatabase.user.UserData;

import java.util.List;

public class PelangganAdapter extends RecyclerView.Adapter<PelangganAdapter.ViewHolder> {

    List<PesananData> historyList;
    HistoryClick listener;

    public PelangganAdapter(List<PesananData> historyList, HistoryClick listener){
        this.historyList = historyList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PelangganAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HistoryLayoutBinding binding = HistoryLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new PelangganAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PelangganAdapter.ViewHolder holder, int position) {
        PesananData data = historyList.get(holder.getAdapterPosition());
        holder.binding.dateTxt.setText(data.getTanggal());
        holder.binding.nameTxt.setText(data.getAlamat());

        holder.binding.profilCard.setOnClickListener(v ->{


//            Navigation.findNavController(v).navigate(HistoryFragmentDirections.toDetail(data.id));
        });

        holder.binding.deleteImg.setOnClickListener(v ->{
            listener.historyClick(data);
        });
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        HistoryLayoutBinding binding;
        public ViewHolder(@NonNull HistoryLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface HistoryClick {
        void historyClick(PesananData userData);
    }
}
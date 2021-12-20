package com.app.servicekendaraan.history.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.app.servicekendaraan.databinding.HistoryLayoutBinding;
import com.app.servicekendaraan.roomdatabase.pesanan.PesananDao;
import com.app.servicekendaraan.roomdatabase.pesanan.PesananData;
import com.app.servicekendaraan.roomdatabase.user.UserData;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    List<UserData> historyList;
    HistoryClick listener;

    public HistoryAdapter(List<UserData> historyList, HistoryClick listener){
        this.historyList = historyList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HistoryLayoutBinding binding = HistoryLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserData data = historyList.get(holder.getAdapterPosition());
        holder.binding.dateTxt.setText(data.getTanggal());
        holder.binding.nameTxt.setText(data.getNama());

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
        void historyClick(UserData userData);
    }
}

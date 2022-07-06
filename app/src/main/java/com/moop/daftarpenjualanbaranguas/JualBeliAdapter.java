package com.moop.daftarpenjualanbaranguas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class JualBeliAdapter extends RecyclerView.Adapter<JualBeliAdapter.ItemViewHolder> {
    ArrayList<DataModel> data;
    Context cont ;

    JualBeliAdapter(Context cont, ArrayList<DataModel> data){
        this.cont = cont;
        this.data = data;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(cont).inflate(R.layout.oneitemrecord, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        DataModel exclusiveItem = data.get(position) ;
        holder.txtNama.setText(exclusiveItem.getNama());
        holder.txtHarga.setText("Rp." + exclusiveItem.getHarga());
        holder.txtTanggal.setText(exclusiveItem.getTanggal());
        holder.txtKuantitas.setText(exclusiveItem.getKuantiti());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView txtNama, txtHarga, txtTanggal, txtKuantitas ;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNama = (TextView) itemView.findViewById(R.id.produknya) ;
            txtHarga = (TextView) itemView.findViewById(R.id.hargaproduknya) ;
            txtTanggal= (TextView) itemView.findViewById(R.id.tanggalProduknya) ;
            txtKuantitas= (TextView) itemView.findViewById(R.id.kuantitasProduknya);
        }
    }
}

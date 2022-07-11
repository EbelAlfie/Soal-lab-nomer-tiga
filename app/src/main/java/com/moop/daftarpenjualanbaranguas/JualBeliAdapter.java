package com.moop.daftarpenjualanbaranguas;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class JualBeliAdapter extends RecyclerView.Adapter<JualBeliAdapter.ItemViewHolder> {
    ArrayList<DataModel> data;
    Context cont ;
    OnitemClickListener onitemClickListener ;

    public void setList(ArrayList<DataModel> filteredList){
        data = filteredList ;
        notifyDataSetChanged();
    }

    JualBeliAdapter(Context cont, ArrayList<DataModel> data, OnitemClickListener onitemClickListener){
        this.cont = cont;
        this.data = data;
        this.onitemClickListener = onitemClickListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(cont).inflate(R.layout.oneitemrecord, parent, false);
        return new ItemViewHolder(v, onitemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        DataModel exclusiveItem = data.get(position) ;
        holder.productImg.setImageBitmap(exclusiveItem.getImage());
        holder.txtNama.setText(exclusiveItem.getNama());
        holder.txtHarga.setText("Rp. " + exclusiveItem.getHarga());
        holder.txtToko.setText(exclusiveItem.getToko());
        holder.txtKuantitas.setText(exclusiveItem.getKuantiti() + " Left");
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txtNama, txtHarga, txtToko, txtKuantitas ;
        ImageView productImg;
        OnitemClickListener onitemClickListener ;
        public ItemViewHolder(@NonNull View itemView, OnitemClickListener onitemClickListener) {
            super(itemView);
            txtNama = (TextView) itemView.findViewById(R.id.produknya) ;
            txtToko = (TextView) itemView. findViewById(R.id.tokoproduknya) ;
            txtHarga = (TextView) itemView.findViewById(R.id.hargaproduknya) ;
            productImg = (ImageView) itemView.findViewById(R.id.gambarnya) ;
            txtKuantitas= (TextView) itemView.findViewById(R.id.kuantitasProduknya);
            this.onitemClickListener = onitemClickListener ;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onitemClickListener.onItemClick(getAdapterPosition());
        }
    }
    public interface OnitemClickListener{
        void onItemClick(int position) ;
    }
    public void setOnItemCLickedListener(OnitemClickListener clickedListener){
        onitemClickListener = clickedListener ;
    }
}

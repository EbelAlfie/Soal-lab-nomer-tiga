package com.moop.daftarpenjualanbaranguas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductInformation extends Fragment {
    private TextView namaP, tanggalP, deskripsiP, hargaP, kuantitasP;
    private ImageView productImage ;
    private DataModel data;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments() ;
        data = new DataModel() ;
        if (bundle != null) {
            data = bundle.getParcelable("ItemToDisplay") ;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_product_information, container, false);

        productImage = v.findViewById(R.id.imageView2);
        namaP = v.findViewById(R.id.namaProduktampil);
        tanggalP = v.findViewById(R.id.tanggalproduktampil);
        deskripsiP = v.findViewById(R.id.descproduktampil) ;
        hargaP = v.findViewById(R.id.hargaproduktampil) ;
        kuantitasP = v.findViewById(R.id.kuantitasproduktampil);

        productImage.setImageURI(data.getImageLoc());
        namaP.setText(data.getNama());
        tanggalP.setText(data.getTanggal());
        deskripsiP.setText(data.getDeskripsi());
        hargaP.setText(data.getHarga());
        kuantitasP.setText(data.getKuantiti());
        return v;
    }
}
package com.moop.daftarpenjualanbaranguas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class InformasiLengkap extends AppCompatActivity {
    private TextView namaP, tanggalP, deskripsiP, hargaP, kuantitasP;
    private ImageView productImage ;
    private DataModel data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi_lengkap);
        Intent intent = this.getIntent();
        data = new DataModel();
        if (intent != null) {
            data = intent.getParcelableExtra("ItemToDisplay");
        }

        productImage = (ImageView) findViewById(R.id.imageView2);
        namaP = findViewById(R.id.namaProduktampil);
        tanggalP = findViewById(R.id.tanggalproduktampil);
        deskripsiP = findViewById(R.id.descproduktampil) ;
        hargaP = findViewById(R.id.hargaproduktampil) ;
        kuantitasP = findViewById(R.id.kuantitasproduktampil);

        try {
            productImage.setImageBitmap(data.getImage());
            namaP.setText(data.getNama());
            tanggalP.setText(data.getTanggal());
            deskripsiP.setText(data.getDeskripsi());
            hargaP.setText(data.getHarga());
            kuantitasP.setText(data.getKuantiti());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
package com.moop.daftarpenjualanbaranguas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView ;
    private JualBeliAdapter adapter ;
    private ArrayList<DataModel> datas;
    private EditText namaP, hargaP, tanggalP, kuantitiP ;
    private Button addP ;
    private DataModel added;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        namaP = (EditText) findViewById(R.id.editTextNama) ;
        hargaP =(EditText) findViewById(R.id.editTextHarga) ;
        tanggalP= (EditText) findViewById(R.id.editTextTanggal) ;
        kuantitiP= (EditText) findViewById(R.id.editTextKuantiti) ;
        addP = (Button) findViewById(R.id.addProduk) ;

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView) ;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        datas = new ArrayList<>() ;

        adapter = new JualBeliAdapter(getApplicationContext(), datas) ;

        recyclerView.setAdapter(adapter);

        addP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = String.valueOf(namaP.getText());
                String price = String.valueOf(hargaP.getText());
                String date = String.valueOf(tanggalP.getText());
                String kuantiti = String.valueOf(kuantitiP.getText()) ;
                if(!name.equals("") && !price.equals("") && !date.equals("") && !kuantiti.equals("") && price.matches("[0-9]*$") && kuantiti.matches("[0-9]*$")){
                    added = new DataModel(name, price, date, kuantiti) ;
                    datas.add(added) ;
                    adapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(MainActivity.this, "Kosong/salah input", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
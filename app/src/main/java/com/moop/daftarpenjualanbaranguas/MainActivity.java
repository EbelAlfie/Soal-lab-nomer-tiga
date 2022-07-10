package com.moop.daftarpenjualanbaranguas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements JualBeliAdapter.OnitemClickListener {
    private static final int PICK_FROM_GALLERY = 23;
    private RecyclerView recyclerView ;
    private JualBeliAdapter adapter ;
    private ArrayList<DataModel> datas;
    private Button navigate ;
    private DataModel passed ;
    private SearchView searchView ;

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_FROM_GALLERY);
        }
        searchView = (SearchView) findViewById(R.id.searchFilter);
        //searchView.clearFocus();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView) ;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        navigate = (Button) findViewById(R.id.testAdd) ;

        datas = new ArrayList<>() ;

        adapter = new JualBeliAdapter(getApplicationContext(), datas, this) ;

        recyclerView.setAdapter(adapter);

        adapter.setOnItemCLickedListener(this);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText) ;
                return true;
            }
        });

        navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(getApplicationContext(), TestAdd.class);
                startActivityForResult(inten, 12);
            }
        });
    }

    private void filterList(String newText) {
        ArrayList<DataModel> queryData = new ArrayList<>() ;
        for(DataModel oneData : datas){
            if(oneData.getNama().toLowerCase().contains(newText.toLowerCase())){
                queryData.add(oneData) ;
            }
        }
        adapter.setList(queryData);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 12)
        {
            passed = (DataModel) data.getParcelableExtra("AddedData");
            datas.add(passed) ;
            adapter.setList(datas);
        }
    }

    @Override
    public void onItemClick(int position) {
        try {
            Intent intent = new Intent(getApplicationContext(), InformasiLengkap.class);
            intent.putExtra("ItemToDisplay", datas.get(position));
            startActivity(intent);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
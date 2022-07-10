package com.moop.daftarpenjualanbaranguas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
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
        searchView = (SearchView) findViewById(R.id.searchFilter);
        //searchView.clearFocus();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView) ;
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        navigate = (Button) findViewById(R.id.testAdd) ;

        datas = new ArrayList<>() ;

        adapter = new JualBeliAdapter(getApplicationContext(), datas) ;

        recyclerView.setAdapter(adapter);

        navigate.setOnClickListener(this);
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
    public void onClick(View view) {
        Intent inten = new Intent(getApplicationContext(), TestAdd.class);
        startActivityForResult(inten, 12);
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
}
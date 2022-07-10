package com.moop.daftarpenjualanbaranguas;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.Serializable;

public class TestAdd extends AppCompatActivity implements View.OnClickListener{
    private static final int PICK_FROM_GALLERY = 1;
    private EditText namaP, hargaP, deskripsiP, tanggalP, kuantitiP ;
    private Button addP, returnToMain ;
    private DataModel added;
    private Uri imageUri ;
    private ImageView productImage;
    private Bitmap image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_add);

        productImage = (ImageView) findViewById(R.id.imageView);

        namaP = (EditText) findViewById(R.id.editTextNama) ;
        hargaP =(EditText) findViewById(R.id.editTextHarga) ;
        deskripsiP = (EditText) findViewById(R.id.editTextDesc) ;
        tanggalP= (EditText) findViewById(R.id.editTextTanggal) ;
        kuantitiP= (EditText) findViewById(R.id.editTextKuantiti) ;
        addP = (Button) findViewById(R.id.addProduk) ;
        returnToMain = (Button) findViewById(R.id.returnTo) ;
        try {
            imageUri = Uri.parse("android.resource://com.moop.daftarpenjualanbaranguas/" + R.drawable.gambarbebasplaceholder) ;
            image = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
        } catch (IOException e) {
            e.printStackTrace();
        }

        returnToMain.setOnClickListener(this) ;
        productImage.setOnClickListener(this);
        addP.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.imageView:
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_FROM_GALLERY);
                } else {
                    Intent galeryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galeryIntent, PICK_FROM_GALLERY);
                }
                break;
            case R.id.addProduk:
                String name = String.valueOf(namaP.getText());
                String price = String.valueOf(hargaP.getText());
                String deskripsi = String.valueOf(deskripsiP.getText()) ;
                String date = String.valueOf(tanggalP.getText());
                String kuantiti = String.valueOf(kuantitiP.getText()) ;
                image = Bitmap.createScaledBitmap(image, 320, 320, false);

                if(!name.equals("") && !price.equals("") && !date.equals("") && !kuantiti.equals("") && price.matches("[0-9]*$") && kuantiti.matches("[0-9]*$")){
                    try {
                    added = new DataModel(name, price, deskripsi, date, kuantiti, image) ;
                    Toast.makeText(TestAdd.this, "Data added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class) ;
                    intent.putExtra("AddedData", added);
                    setResult(12, intent);
                    finish();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(TestAdd.this, "Kosong/salah input", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.returnTo:
                finish() ;
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                try {
                    imageUri = data.getData() ;
                    image = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                    productImage.setImageBitmap(image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
package com.pbp.android_firebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardView addMhs = findViewById(R.id.add_mhs);
        addMhs.setOnClickListener(this);

        CardView listMhs = findViewById(R.id.list_mhs);
        listMhs.setOnClickListener(this);

    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.add_mhs:
                Intent addMhs = new Intent(MainActivity.this, TambahMhsActivity.class);
                startActivity(addMhs);
                break;
            case R.id.list_mhs:
                Intent listMhs = new Intent(MainActivity.this, ListMhsActivity.class);
                startActivity(listMhs);
                break;
        }
    }
}
package com.pbp.android_firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TambahMhsActivity extends AppCompatActivity {
    EditText etNIM,etNama,etJurusan,etAngkatan;
    Button btnTambah;
    ImageButton btnBack;

    FirebaseDatabase database;
    DatabaseReference myref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_mhs);

        //inisialisasi variable dengan id yang ada di layout
        etNIM = findViewById(R.id.et_nim);
        etNama = findViewById(R.id.et_nama);
        etJurusan = findViewById(R.id.et_jurusan);
        etAngkatan = findViewById(R.id.et_angkatan);

        btnTambah = findViewById(R.id.btn_tambah);
        btnBack = findViewById(R.id.btn_back);


        //inisialisasi object ke firebase
        database = FirebaseDatabase.getInstance("https://praktikum-firebase-a0e14-default-rtdb.asia-southeast1.firebasedatabase.app");
        myref = database.getReference("mhs");

        //memanggil fungsi untuk input data ke firebase
        //melakukan sesuatu jika btn ditekan
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAG","onClick: Tambah Data");

                //mengambil data dari setiap edittext dan di ubah kedalam string
                final String nim = etNIM.getText().toString();
                final String nama = etNama.getText().toString();
                final String jurusan = etJurusan.getText().toString();
                final String angkatan = etAngkatan.getText().toString();

                //Validasi data yang diinput tidak boleh kosong
                if (nim.equals("") || nama.equals("") || jurusan.equals("") || angkatan.equals("")){
                    Toast.makeText(TambahMhsActivity.this, "Data Harus diisi semua!!", Toast.LENGTH_SHORT).show();
                } else{
                    //memasukkan data - data tersebut kedalam objek 'Mahasiswa'
                    Mahasiswa mhs = new Mahasiswa(nim,nama,jurusan,angkatan);

                    //input data kedalam firebase
                    myref.child(nim).setValue(mhs).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {

                            //apabila input data berhasil maka akan melakukan hal berikut
                            Toast.makeText(TambahMhsActivity.this, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                            etNIM.setText("");
                            etNama.setText("");
                            etJurusan.setText("");
                            etAngkatan.setText("");
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("TAG","onFailure: ",e.fillInStackTrace());
                            //apabila input data gagal maka akan melakukan hal berikut
                            Toast.makeText(TambahMhsActivity.this, "Data Gagal ditambahkan", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void insertData() {

    }

}
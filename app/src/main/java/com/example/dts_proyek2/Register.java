package com.example.dts_proyek2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class Register extends AppCompatActivity {
    EditText txtUsername, txtPassword, txtNamaLengkap, txtEmail, txtAsalSekolah, txtAlamat;
    Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtNamaLengkap = (EditText) findViewById(R.id.txtNamaLengkap);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtAsalSekolah = (EditText) findViewById(R.id.txtAsalSekolah);
        txtAlamat = (EditText) findViewById(R.id.txtAlamatTinggal);
        btnSimpan =  findViewById(R.id.btnSimpan);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validasi()){
                    simpanFileData();
                }else {
                    Toast.makeText(Register.this, "Lengkapi data anda", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    boolean validasi(){
        if(txtUsername.getText().toString().equals("") ||
                txtPassword.getText().toString().equals("") ||
                txtEmail.getText().toString().equals("") ||
                txtNamaLengkap.getText().toString().equals("") ||
                txtAsalSekolah.getText().toString().equals("") ||
                txtAlamat.getText().toString().equals("")){
            return false;
        }else {
            return true;
        }
    }

    void simpanFileData(){
        String isi = txtUsername.getText().toString()
                +";"+txtPassword.getText().toString()
                +";"+txtEmail.getText().toString()
                +";"+txtNamaLengkap.getText().toString()
                +";"+txtAsalSekolah.getText().toString()
                +";"+txtAlamat.getText().toString();
        File file = new File(getFilesDir(), txtUsername.getText().toString());

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, false);
            outputStream.write(isi.getBytes());
            outputStream.flush();
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        Toast.makeText(Register.this,"Register Berhasil", Toast.LENGTH_LONG).show();
        onBackPressed();
    }
}

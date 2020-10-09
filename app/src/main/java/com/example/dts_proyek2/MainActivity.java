package com.example.dts_proyek2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static final String FILENAME = "login";
    Button btnLogin, btnRegister;
    EditText txtUsername, txtPassword;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setSubtitle("Login");
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnReg);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    void login(){
        File sdcard = getFilesDir();
        File file = new File(sdcard,txtUsername.getText().toString());

        if(file.exists()){
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line != null){
                    text.append(line);
                    line = br.readLine();
                }
            }catch (IOException e){
                System.out.println("Error"+e.getMessage());
            }
            String data = text.toString();
            String[] dataUser = data.split(";");

            if(dataUser[1].equals(txtPassword.getText().toString())){
                simpanfilelogin();
                Intent intent = new Intent(MainActivity.this, Home.class);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(MainActivity.this, "Password Tidak Sesuai", Toast.LENGTH_LONG).show();
            }
            Toast.makeText(MainActivity.this, "Username Tidak Ditemukan", Toast.LENGTH_LONG).show();
        }
    }

    void simpanfilelogin(){
        String isi = txtUsername.getText().toString()+";"+txtPassword.getText().toString();
        File file = new File(getFilesDir(),FILENAME);

        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file,false);
            outputStream.write(isi.getBytes());
            outputStream.flush();
            outputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }
}

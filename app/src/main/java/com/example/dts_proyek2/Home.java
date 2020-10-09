package com.example.dts_proyek2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Home extends AppCompatActivity {
    public static final String FILENAME = "login";
    EditText txtUsername, txtPassword, txtNamaLengkap, txtEmail, txtAsalSekolah, txtAlamat;
    TextView txtpss;
    Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setSubtitle("Home");
        toolbar.inflateMenu(R.menu.menuitem);
        txtpss = (TextView) findViewById(R.id.txtpss);
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtNamaLengkap = (EditText) findViewById(R.id.txtNamaLengkap);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtAsalSekolah = (EditText) findViewById(R.id.txtAsalSekolah);
        txtAlamat = (EditText) findViewById(R.id.txtAlamatTinggal);
        btnSimpan =  findViewById(R.id.btnSimpan);
        txtUsername.setEnabled(false);
        txtAlamat.setEnabled(false);
        txtPassword.setVisibility(View.GONE);
        txtAsalSekolah.setEnabled(false);
        txtNamaLengkap.setEnabled(false);
        txtEmail.setEnabled(false);
        btnSimpan.setVisibility(View.GONE);
        bacaFileLogin();

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.exit){
                    tampilkanDialogKonfirmasiLogout();
                }
                return false;
            }
        });
    }

    void bacaFileLogin() {
        File sdcard = getFilesDir();
        File file = new File(sdcard, FILENAME);
        if (file.exists()) {
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br =
                        new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line != null) {
                    text.append(line);
                    line = br.readLine();
                }
                br.close();
            } catch (IOException e) {
                System.out.println("Error " + e.getMessage());
            }
            String data = text.toString();
            String[] dataUser = data.split(";");
            bacaDataUser(dataUser[0]);
        }
    }

    void bacaDataUser(String fileName) {
        File sdcard = getFilesDir();
        File file = new File(sdcard, fileName);
        if (file.exists()) {
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br =  new BufferedReader(new FileReader(file));
                String line = br.readLine();
                while (line != null) {
                    text.append(line);

                    line = br.readLine();
                }
            br.close();
        } catch (IOException e) {
                System.out.println("Error " + e.getMessage());
            }

        String data = text.toString();
        String[] dataUser = data.split(";");
        txtUsername.setText(dataUser[0]);
        txtEmail.setText(dataUser[2]);
        txtNamaLengkap.setText(dataUser[3]);
        txtAsalSekolah.setText(dataUser[4]);
        txtAlamat.setText(dataUser[5]);
        } else {
            Toast.makeText(this, "User Tidak Ditemukan",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuitem, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.exit:
                tampilkanDialogKonfirmasiLogout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    void hapusFile() {
        File file = new File(getFilesDir(), FILENAME);
        if (file.exists()) {
            file.delete();
        }
    }

    void tampilkanDialogKonfirmasiLogout() {
        new AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin Logout?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                    hapusFile();
                    Intent intent = new Intent(Home.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    }
                })
                .setNegativeButton(android.R.string.no, null).show();
    }
}



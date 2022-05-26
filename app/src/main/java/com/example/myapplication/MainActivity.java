package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {
    private EditText username, password;
    private Button tmblLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.editUsername);
        password = (EditText) findViewById(R.id.editPassword);
        tmblLogin = (Button) findViewById(R.id.btnLogin);
        tmblLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = username.getText().toString();
                String pass = password.getText().toString();
                if (nama.isEmpty() || pass.isEmpty()){
                    showErrorLogin();
                } else {
                    if (nama.equals("admin") && pass.equals("admin")){
                        successLogin();


                    } else {
                        errorLogin();
                    }
                }
            }
        });
    }
    public void showErrorLogin(){
        Toast.makeText(this, "Error Username dan Password tidak Valid !",
                Toast.LENGTH_SHORT).show();
    }
    public void successLogin(){
        Toast.makeText(this, "Anda Berhasil Login !", Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(myIntent);
    }
    public void errorLogin(){
        Toast.makeText(this, "Username atau Password Salah !",
                Toast.LENGTH_SHORT).show();
    }
}

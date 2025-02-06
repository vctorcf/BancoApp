package com.example.bancoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DashboardPixActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_pix);
    }

    public void EnviarPix(View view){
        Intent intent = new Intent(this,EnviarPixActivity.class);
        startActivity(intent);
    }

    public void CadastrarPix(View view){
        Intent intent = new Intent(this,CadastrarPixActivity.class);
        startActivity(intent);
    }

    public void RemoverPix(View view){
        Intent intent = new Intent(this,RemoverPixActivity.class);
        startActivity(intent);
    }

    public void ListarPix(View view){
        Intent intent = new Intent(this,ListarPixActivity.class);
        startActivity(intent);
    }
}
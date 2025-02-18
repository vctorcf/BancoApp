package com.example.bancoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void FazerDeposito(View view){
        Intent intent = new Intent(this,DepositarActivity.class);
        startActivity(intent);
    }

    public void FazerRetirada(View view){
        Intent intent = new Intent(this, RetirarActivity.class);
        startActivity(intent);
    }

    public void MostrarExtrato(View view){
        Intent intent = new Intent(this,ExtratoActivity.class);
        startActivity(intent);
    }

    public void FazerPix(View view){
        Intent intent = new Intent(this,DashboardPixActivity.class);
        startActivity(intent);
    }
}
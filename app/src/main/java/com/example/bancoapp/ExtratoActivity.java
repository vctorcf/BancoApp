package com.example.bancoapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class ExtratoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extrato);

        RepositorioExtrato repositorioExtrato = new RepositorioExtrato(this);

        List<Extrato> listaExtrato = repositorioExtrato.listarExtrato();
        for (Extrato extrato : listaExtrato) {
            Log.i("extrato", "ID: " + extrato.getId());
            Log.i("extrato", "Operação: " + extrato.getOperacao());
            Log.i("extrato", "Valor: " + extrato.getValor());
            Log.i("extrato", "Saldo: " + extrato.getSaldo());
            Log.i("extrato", "------------------------");
        }

        ListView listView = findViewById(R.id.listViewExtrato);

        String[] dados = new String[listaExtrato.size()];
        for (int i = 0; i < listaExtrato.size(); i++) {
            Extrato extrato = listaExtrato.get(i);
            dados[i] = "ID: " + extrato.getId() + "\n" +
                    "Operação: " + extrato.getOperacao() + "\n" +
                    "Valor: " + extrato.getValor() + "\n" +
                    "Saldo: " + extrato.getSaldo();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dados);
        listView.setAdapter(adapter);
    }
}
package com.example.bancoapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ListarPixActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pix);

        // Criação do repositório para acessar as chaves Pix
        RepositorioPix repositorioPix = new RepositorioPix(this);

        // Recuperando a lista de chaves Pix do banco de dados
        List<Pix> listaPix = repositorioPix.listaPix();

        // Log para verificar os dados recuperados
        for (Pix pix : listaPix) {
            Log.i("pix", "ID: " + pix.getId());
            Log.i("pix", "Nome: " + pix.getNome());
            Log.i("pix", "Chave Pix: " + pix.getChavePix());
            Log.i("pix", "------------------------");
        }

        // Encontrando o ListView no layout
        ListView listView = findViewById(R.id.listViewPix);

        // Preparando os dados para exibição
        String[] dados = new String[listaPix.size()];
        for (int i = 0; i < listaPix.size(); i++) {
            Pix pix = listaPix.get(i);
            dados[i] = "ID: " + pix.getId() + "\n" +
                    "Nome: " + pix.getNome() + "\n" +
                    "Chave Pix: " + pix.getChavePix();
        }

        // Criando o adapter para preencher a ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dados);
        listView.setAdapter(adapter);
    }
}
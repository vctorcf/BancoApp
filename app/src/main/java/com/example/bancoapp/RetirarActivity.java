package com.example.bancoapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RetirarActivity extends AppCompatActivity {

    private EditText valor;
    private RepositorioExtrato repositorioExtrato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retirar);

        valor = findViewById(R.id.editTextRetirarValor);

        repositorioExtrato = new RepositorioExtrato(this); //Instanciando um objeto de RepositorioExtrato
    }

    public void RealizarRetirada(View view){
        String valorTexto = valor.getText().toString().trim();

        if (valorTexto.isEmpty()) {
            Toast.makeText(this, "Insira um valor para retirar.", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double valorRetirada = Double.parseDouble(valorTexto);

            if (valorRetirada <= 0) {
                Toast.makeText(this, "O valor deve ser maior que zero.", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean deuCerto = repositorioExtrato.retirar(valorRetirada);
            if (deuCerto==true){
                Toast.makeText(this, "Retirada realizada com sucesso!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Saldo insuficiente.", Toast.LENGTH_SHORT).show();
            }

        } catch (NumberFormatException e) {
            // Trata entrada inválida (ex.: letras)
            Toast.makeText(this, "Valor inválido. Insira um número válido.", Toast.LENGTH_SHORT).show();
        }
    }
}
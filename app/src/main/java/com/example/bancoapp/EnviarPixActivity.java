package com.example.bancoapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EnviarPixActivity extends AppCompatActivity {

    private EditText valor;
    private EditText chave;
    RepositorioExtrato repositorioExtrato = new RepositorioExtrato(this);
    RepositorioPix repositorioPix = new RepositorioPix(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar_pix);

        valor = findViewById(R.id.editTextValor);
        chave = findViewById(R.id.editTextChave);
    }

    public void RealizarEnvioPix(View view) {

        double saldo = repositorioExtrato.saldoAtual();
        String valorTexto = valor.getText().toString().trim();
        String chaveTexto = chave.getText().toString().trim();

        if (valorTexto.isEmpty()){
            Toast.makeText(this, "Insira um valor para depositar.",Toast.LENGTH_SHORT).show();
            return;
        }

        if (chaveTexto.isEmpty()){
            Toast.makeText(this, "Insira a chave pix.",Toast.LENGTH_SHORT).show();
            return;
        }

        if(repositorioPix.checarSeChaveCadastrada(chaveTexto)){
            try{//tentando transformar a String em double
                double valorEnvio = Double.parseDouble(valorTexto);

                if (repositorioExtrato.retirar(valorEnvio)){
                    Toast.makeText(this, "Valor " + valorEnvio + " enviado com sucesso para a chave " + chaveTexto + ".",Toast.LENGTH_SHORT).show();
                }

            }catch (NumberFormatException e) {
                Toast.makeText(this, "Valor inválido. Insira um número válido.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Chave não cadastrada, por favor cadastre a chave antes de realizar esta operação.",Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
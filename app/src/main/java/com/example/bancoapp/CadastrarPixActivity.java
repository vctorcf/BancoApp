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

public class CadastrarPixActivity extends AppCompatActivity {

    RepositorioPix repositorioPix = new RepositorioPix(this);
    private EditText novaChave;
    private EditText novoNome;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_pix);

        novaChave = findViewById(R.id.editTextCadastrarPix);
        novoNome = findViewById(R.id.editTextNomeCadPix);
    }

    public void CadastrarChavePix(View view) {
        String chaveTexto = novaChave.getText().toString().trim();
        String nomeTexto = novoNome.getText().toString().trim();

        if (chaveTexto.isEmpty()){
            Toast.makeText(this, "Insira a nova chave.",Toast.LENGTH_SHORT).show();
            return;
        }



        if (nomeTexto.isEmpty()){
            Toast.makeText(this, "Insira o nome do dono da chave.",Toast.LENGTH_SHORT).show();
            return;
        }

        if (!chaveTexto.equals("\\d{11}")){
            repositorioPix.cadastrarChave(nomeTexto, chaveTexto);
        } else {
            Toast.makeText(this, "A chave pode ter somente 11 números. Não use letras ou caracteres especiais.",Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
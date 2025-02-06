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

public class RemoverPixActivity extends AppCompatActivity {

    RepositorioPix repositorioPix = new RepositorioPix(this);
    private EditText chave;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remover_pix);

        chave = findViewById(R.id.editTextChaveRemovida);
    }

    public void RemoverChave(View view)
    {
        String chaveTexto = chave.getText().toString().trim();

        if (chaveTexto.isEmpty()){
            Toast.makeText(this, "Insira um valor no campo 'Chave'.",Toast.LENGTH_SHORT).show();
            return;
        }

        repositorioPix.removerChave(chaveTexto);
        Toast.makeText(this, "Se a chave " + chaveTexto + " existia no registro, ela não existe mais.",Toast.LENGTH_SHORT).show();
    }
}
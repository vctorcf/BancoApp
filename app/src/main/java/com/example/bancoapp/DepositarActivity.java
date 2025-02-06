package com.example.bancoapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class DepositarActivity extends AppCompatActivity {

    private EditText valor;
    private RepositorioExtrato repositorioExtrato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depositar);

        valor = findViewById(R.id.editTextDepositoValor);
    }

    public void RealizarDeposito(View view) {
        String valorTexto = valor.getText().toString().trim();

       //checando se o espaço está em branco
        if (valorTexto.isEmpty()){
            Toast.makeText(this, "Insira um valor para depositar.",Toast.LENGTH_SHORT).show();
            return;
        }

        try {//tentando transformar o dado informado em double
            double valorDeposito = Double.parseDouble(valorTexto);


            if (valorDeposito <= 0) {
                Toast.makeText(this, "O valor deve ser maior que zero.", Toast.LENGTH_SHORT).show();
                return;
            }

            repositorioExtrato.depositar(valorDeposito);


            Toast.makeText(this, "Depósito realizado com sucesso!", Toast.LENGTH_SHORT).show();


        } catch (NumberFormatException e) {//mensagem caso o dado informado não seja um número
            Toast.makeText(this, "Valor inválido. Insira um número válido.", Toast.LENGTH_SHORT).show();
        }
    }
}
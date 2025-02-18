package com.example.bancoapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;


public class RepositorioExtrato extends SQLiteOpenHelper{
    public RepositorioExtrato(@Nullable Context context) {
        super(context, "extrato", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        //criando a tabela extrato
        String sql ="create table extrato (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "operacao text not null," +
                "valor real not null," +
                "saldo real not null);";
        sqLiteDatabase.execSQL(sql);
        Log.i("extrato","A tabela extrato foi criada com sucesso.");

        //adicionando os primeiros valores para iniciar a tabela e criar saldo zerado
        String sqlIniciarTabela = "insert into extrato (operacao, valor, saldo) " +
                "values ('novaConta', 0, 0)";
        sqLiteDatabase.execSQL(sqlIniciarTabela);
        Log.i("extrato", "Valores iniciais inseridos: novaConta, 0, 0");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.i("extrato", "Atualizando banco de dados da versão " + oldVersion + " para " + newVersion);
        // Se der problema, atualize a tabela aqui
    }

    public double saldoAtual(){
        double saldoAtual = 0.0; // Valor padrão caso não haja registros
        String sql = "SELECT saldo FROM extrato ORDER BY id DESC LIMIT 1";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            saldoAtual = cursor.getDouble(0); // Pega o valor da coluna 'saldo'
        }
        cursor.close();
        return saldoAtual;
    }

    public void depositar(double valor){
        double saldoAtualizado = saldoAtual() + valor;
        String sql = "INSERT INTO extrato (operacao, valor, saldo) VALUES ('deposito', " +
                valor + ", " + saldoAtualizado + ")";
        getWritableDatabase().execSQL(sql);
        Log.i("extrato", "Depósito realizado: valor = " + valor + ", saldo atualizado = " + saldoAtualizado);
    }

    public boolean retirar(double valor){
        double saldo = saldoAtual();
        if (saldo>=valor){
            double saldoAtualizado = saldo - valor;
            String sql = "INSERT INTO extrato (operacao, valor, saldo) VALUES ('retirada', " +
                    valor + ", " + saldoAtualizado + ")";
            getWritableDatabase().execSQL(sql);
            Log.i("extrato", "retirada realizada: valor = " + valor + ", saldo atualizado = " + saldoAtualizado);
            return true;
        } else {
            Log.w("extrato", "Saque não realizado. Saldo insuficiente: saldo = " + saldo + ", valor solicitado = " + valor);
            return false;
        }
    }

    @SuppressLint("Range")
    public List<Extrato> listarExtrato() {
        List<Extrato> listaExtrato = new ArrayList<>();
        String sql = "SELECT * FROM extrato ORDER BY id DESC";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                Extrato extrato = new Extrato();
                extrato.setId(cursor.getInt(cursor.getColumnIndex("id")));
                extrato.setOperacao(cursor.getString(cursor.getColumnIndex("operacao")));
                extrato.setValor(cursor.getDouble(cursor.getColumnIndex("valor")));
                extrato.setSaldo(cursor.getDouble(cursor.getColumnIndex("saldo")));
                listaExtrato.add(extrato);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return listaExtrato;
    }

    public void enviarPix(String chave, double valor){
        double saldo = saldoAtual();
        String sqlVerificarChave = "SELECT * FROM pix WHERE chavePix = " + chave;
        Cursor cursorPix = getReadableDatabase().rawQuery(sqlVerificarChave, null);

        if (!cursorPix.moveToFirst()){
            Log.w("extrato", "Chave Pix não encontrada. Por favor, cadastre a chave antes de realizar a operação.");
            cursorPix.close();
            return;
        }
        cursorPix.close();

        if (saldo < valor) {
            Log.w("extrato", "Saldo insuficiente. Saldo atual: " + saldo + ", valor solicitado: " + valor);
            return;
        }

        double saldoAtualizado = saldo - valor;
        String sql = "INSERT INTO extrato (operacao, valor, saldo) VALUES ('envioPix', " +
                valor + ", " + saldoAtualizado + ")";
        getWritableDatabase().execSQL(sql);
        Log.i("extrato", "Depósito realizado: valor = " + valor + ", saldo atualizado = " + saldoAtualizado);

    }
}

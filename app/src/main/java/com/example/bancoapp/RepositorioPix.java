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

public class RepositorioPix extends SQLiteOpenHelper {

    public RepositorioPix(@Nullable Context context) {
        super(context, "pix", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql ="create table pix " +
                "(id integer not  null primary key autoincrement," +
                "nome text," +
                "chavePix text)";
        sqLiteDatabase.execSQL(sql);
        Log.i("pix","Criado com sucesso a tabela pix");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.i("pix", "Atualizando banco de dados da versão " + oldVersion + " para " + newVersion);
        // Se der problema, atualize a tabela aqui
    }

    public void cadastrarChave(String nome, String chave) {
        if (chave.matches("\\d{11}")){
            String sql = "INSERT INTO pix (nome, chavePix) VALUES ('" + nome + "' ,'" + chave + "')";
            getWritableDatabase().execSQL(sql);
            Log.i("pix", "Chave Pix " + chave + " foi cadastrada no nome de " + nome);
        } else {
            Log.e("pix", "Chave Pix inválida. A chave deve ter exatamente 11 dígitos numéricos.");
        }
    }

    public void removerChave(String chave) {
        String sql = "DELETE FROM pix WHERE chavePix = ?";
        getWritableDatabase().execSQL(sql, new Object[]{chave});
        Log.i("pix", "Chave Pix " + chave + " foi deletada.");
    }

    public boolean checarSeChaveCadastrada(String chave) {
        String sql = "SELECT * FROM pix WHERE chavePix = ?";
        Cursor cursor = getReadableDatabase().rawQuery(sql, new String[]{chave});

        boolean chaveExiste = cursor.moveToFirst();
        cursor.close();

        return chaveExiste;
    }

    @SuppressLint("Range")
    public List<Pix> listaPix() {
        List<Pix> listaPix = new ArrayList<>();
        String sql = "SELECT * FROM pix ORDER BY id ASC";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        Log.i("pix", "Total de chaves Pix encontradas: " + cursor.getCount());

        if (cursor.moveToFirst()) {
            do {
                Pix pix = new Pix();
                pix.setId(cursor.getInt(cursor.getColumnIndex("id")));
                pix.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                pix.setChavePix(cursor.getString(cursor.getColumnIndex("chavePix")));
                listaPix.add(pix);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return listaPix;
    }
}

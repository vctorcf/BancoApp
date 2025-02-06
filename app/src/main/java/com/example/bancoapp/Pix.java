package com.example.bancoapp;

public class Pix {
    private int id;
    private String nome;
    private String chavePix;


    public Pix() {}

    public Pix(int id, String nome, String chavePix) {
        this.id = id;
        this.nome = nome;
        this.chavePix = chavePix;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getChavePix() {
        return chavePix;
    }

    public void setChavePix(String chavePix) {
        // Validação para garantir que só números sejam permitidos
        if (chavePix.matches("\\d+")) { // \\d+=numeros de 1 ou mais digitos
            this.chavePix = chavePix;
        } else {
            throw new IllegalArgumentException("A chave Pix deve conter apenas números.");
        }
    }

}

package org.example.model;

public class ExcecaoPreco extends Exception {
    public ExcecaoPreco() {
        super("Preço inválido. O preço deve ser positivo.");
    }

    public ExcecaoPreco(String mensagem) {
        super(mensagem);
    }
}


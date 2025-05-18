package org.example.model;

public class ExcecaoPreco extends Exception {
    public ExcecaoPreco() {
        super("Preço inválido. O preço deve ser positivo e não pode conter letras ou caracteres inválidos.");
    }

    public ExcecaoPreco(String mensagem) {
        super(mensagem);
    }
}
package org.example.model;

public class ExcecaoNome extends Exception {
    public ExcecaoNome() {
        super("Nome inválido. O nome só deve conter letras e espaços.");
    }

    public ExcecaoNome(String mensagem) {
        super(mensagem);
    }
}

package org.example.model;

public class ExcecaoNumeroAluno extends Exception {
    public ExcecaoNumeroAluno() {
        super("Número de aluno inválido. O número só deve conter dígitos.");
    }

    public ExcecaoNumeroAluno(String mensagem) {
        super(mensagem);
    }
}

package org.example.model;

/**
 * Exceção personalizada lançada quando um número de aluno inválido é detectado.
 * Um número de aluno inválido é aquele que contém caracteres que não sejam dígitos.
 * Esta exceção pode ser usada para validar números de aluno em diversas partes do programa.
 */
public class ExcecaoNumeroAluno extends Exception {

    /**
     * Construtor padrão que cria a exceção com uma mensagem padrão.
     * A mensagem padrão é: "Número de aluno inválido. O número só deve conter dígitos."
     */
    public ExcecaoNumeroAluno() {
        super("Número de aluno inválido. O número só deve conter dígitos.");
    }

    /**
     * Construtor que cria a exceção com uma mensagem personalizada.
     *
     * @param mensagem A mensagem detalhada da exceção.
     */
    public ExcecaoNumeroAluno(String mensagem) {
        super(mensagem);
    }
}

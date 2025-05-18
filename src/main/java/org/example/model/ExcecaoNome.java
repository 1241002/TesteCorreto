package org.example.model;

/**
 * Exceção personalizada lançada quando um nome inválido é detectado.
 * Um nome inválido é aquele que contém caracteres diferentes de letras e espaços.
 * Pode ser usada para validar nomes em várias partes do programa.
 */
public class ExcecaoNome extends Exception {

    /**
     * Construtor padrão que cria a exceção com uma mensagem padrão.
     * A mensagem padrão é: "Nome inválido. O nome só deve conter letras e espaços."
     */
    public ExcecaoNome() {
        super("Nome inválido. O nome só deve conter letras e espaços.");
    }

    /**
     * Construtor que cria a exceção com uma mensagem personalizada.
     *
     * @param mensagem A mensagem detalhada da exceção.
     */
    public ExcecaoNome(String mensagem) {
        super(mensagem);
    }
}

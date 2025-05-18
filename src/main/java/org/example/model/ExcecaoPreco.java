package org.example.model;

/**
 * Exceção personalizada lançada quando o preço informado é inválido.
 * Essa exceção é utilizada para garantir que o preço seja um valor positivo
 * e que não contenha letras ou caracteres inválidos.
 */
public class ExcecaoPreco extends Exception {

    /**
     * Construtor padrão que cria a exceção com uma mensagem padrão.
     * A mensagem padrão é:
     * "Preço inválido. O preço deve ser positivo e não pode conter letras ou caracteres inválidos."
     */
    public ExcecaoPreco() {
        super("Preço inválido. O preço deve ser positivo e não pode conter letras ou caracteres inválidos.");
    }

    /**
     * Construtor que cria a exceção com uma mensagem personalizada.
     *
     * @param mensagem A mensagem detalhada da exceção.
     */
    public ExcecaoPreco(String mensagem) {
        super(mensagem);
    }
}

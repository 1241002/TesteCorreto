package org.example.model;

/**
 * Exceção personalizada lançada quando o número de vendas de um produto é inválido.
 * Um número de vendas inválido é aquele que não é positivo.
 * Esta exceção é usada para garantir que o número de vendas registrado seja sempre positivo.
 */
public class ExcecaoNumeroVendasProdutos extends Exception {

    /**
     * Construtor padrão que cria a exceção com uma mensagem padrão.
     * A mensagem padrão é: "Número de vendas inválido. O número de vendas deve ser positivo."
     */
    public ExcecaoNumeroVendasProdutos() {
        super("Número de vendas inválido. O número de vendas deve ser positivo.");
    }

    /**
     * Construtor que cria a exceção com uma mensagem personalizada.
     *
     * @param mensagem A mensagem detalhada da exceção.
     */
    public ExcecaoNumeroVendasProdutos(String mensagem) {
        super(mensagem);
    }
}

package org.example.model;

/**
 * Exceção personalizada lançada quando se tenta operar sobre um produto
 * que não existe no stock.
 */
public class ExcecaoProdutoNaoExistente extends Exception {

    /**
     * Construtor padrão que cria a exceção com a mensagem padrão:
     * "Produto não existente em stock."
     */
    public ExcecaoProdutoNaoExistente() {
        super("Produto não existente em stock.");
    }

    /**
     * Construtor que cria a exceção com uma mensagem personalizada.
     *
     * @param mensagem Mensagem detalhada da exceção.
     */
    public ExcecaoProdutoNaoExistente(String mensagem) {
        super(mensagem);
    }
}

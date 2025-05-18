package org.example.model;

/**
 * Exceção personalizada lançada quando um voluntário de stock não é encontrado
 * ou não existe no sistema.
 */
public class ExcecaoVoluntarioStockNaoExistente extends Exception {

    /**
     * Construtor padrão que cria a exceção com a mensagem padrão:
     * "Voluntário de stock não existe."
     */
    public ExcecaoVoluntarioStockNaoExistente() {
        super("Voluntário de stock não existe.");
    }

    /**
     * Construtor que cria a exceção com uma mensagem personalizada.
     *
     * @param mensagem Mensagem detalhada da exceção.
     */
    public ExcecaoVoluntarioStockNaoExistente(String mensagem) {
        super(mensagem);
    }
}

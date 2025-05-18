package org.example.model;

/**
 * Exceção personalizada lançada quando um voluntário de vendas não é encontrado
 * ou não existe no sistema.
 */
public class ExcecaoVoluntarioVendasNaoExistente extends Exception {

    /**
     * Construtor padrão que cria a exceção com a mensagem padrão:
     * "Voluntário de vendas não existe."
     */
    public ExcecaoVoluntarioVendasNaoExistente() {
        super("Voluntário de vendas não existe.");
    }

    /**
     * Construtor que cria a exceção com uma mensagem personalizada.
     *
     * @param mensagem Mensagem detalhada da exceção.
     */
    public ExcecaoVoluntarioVendasNaoExistente(String mensagem) {
        super(mensagem);
    }
}

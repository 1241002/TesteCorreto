package org.example.model;

/**
 * Interface para voluntários que realizam vendas.
 * Define os métodos essenciais que qualquer voluntário de vendas deve implementar.
 */
public interface IVendasVoluntarios {

    /**
     * Obtém o nome do voluntário.
     * @return Nome do voluntário.
     */
    String getNome();

    /**
     * Calcula o total das vendas realizadas pelo voluntário.
     * @return Valor total das vendas.
     */
    double getTotalVendas();

    /**
     * Retorna a categoria do voluntário com base nas suas vendas.
     * Pode ser "Bronze", "Prata" ou "Ouro".
     * @return Categoria do voluntário.
     */
    String getCategoria();

    /**
     * Mostra no ecrã a categoria atual do voluntário.
     * A mensagem inclui nome, total de vendas e categoria atribuída.
     */
    void verificarEExibirCategoria();
}

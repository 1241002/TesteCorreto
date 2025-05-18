package org.example.model;

/**
 * Exceção personalizada para representar erros relacionados à quantidade inválida.
 * Estende RuntimeException, podendo ser lançada em situações onde a quantidade
 * fornecida não atende aos requisitos esperados.
 */
public class ExecaoQuantidade extends RuntimeException {

    /**
     * Construtor que recebe a mensagem detalhada da exceção.
     *
     * @param message Mensagem descritiva do motivo da exceção.
     */
    public ExecaoQuantidade(String message) {
        super(message);
    }
}

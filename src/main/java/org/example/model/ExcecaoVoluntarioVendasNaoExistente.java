package org.example.model;

public class ExcecaoVoluntarioVendasNaoExistente extends Exception {
    public ExcecaoVoluntarioVendasNaoExistente() {
        super("Voluntário de vendas não existe.");
    }

    public ExcecaoVoluntarioVendasNaoExistente(String mensagem) {
        super(mensagem);
    }
}

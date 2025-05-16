package org.example.model;

public class ExcecaoVoluntarioStockNaoExistente extends Exception {
    public ExcecaoVoluntarioStockNaoExistente() {
        super("Voluntário de stock não existe.");
    }

    public ExcecaoVoluntarioStockNaoExistente(String mensagem) {
        super(mensagem);
    }
}

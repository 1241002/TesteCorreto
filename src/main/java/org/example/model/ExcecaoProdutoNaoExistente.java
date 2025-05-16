package org.example.model;

public class ExcecaoProdutoNaoExistente extends Exception {
    public ExcecaoProdutoNaoExistente() {
        super("Produto não existente em stock.");
    }

    public ExcecaoProdutoNaoExistente(String mensagem) {
        super(mensagem);
    }
}

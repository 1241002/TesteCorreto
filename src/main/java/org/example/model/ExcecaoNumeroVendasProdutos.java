package org.example.model;

public class ExcecaoNumeroVendasProdutos extends Exception {
    public ExcecaoNumeroVendasProdutos() {
        super("Número de vendas inválido. O número de vendas deve ser positivo.");
    }

    public ExcecaoNumeroVendasProdutos(String mensagem) {
        super(mensagem);
    }
}

package org.example.model;

public interface IVendasVoluntarios {
    String getNome();

    int getNumeroAluno();

    Instituicao getInstituicao();

    double getTotalVendas();

     void setInstituicao(Instituicao instituicao);

     void setNumeroAluno(int numeroAluno);

    default String getCategoria() {
        double totalVendas = getTotalVendas();
        if (totalVendas < 500) return "Bronze";
        else if (totalVendas <= 1000) return "Prata";
        else return "Ouro";
    }

    default void verificarEExibirCategoria() {
        System.out.println("Voluntário: " + getNome() + ", Total de Vendas: " + getTotalVendas() + "€, Classificação: " + getCategoria());
    }

}

package org.example.model;

public interface IVendasVoluntarios {
    String getNome();

    int getNumeroAluno();

    Instituicao getInstituicao();

    double getTotalVendas();

    //Ver com os bacanos

     void setInstituicao(Instituicao instituicao);
     void setNumeroAluno(int numeroAluno);

    default void verificarEExibirCategoria() {
        String categoria;
        double totalVendas = getTotalVendas();
        if (totalVendas < 500) {
            categoria = "Bronze";
        } else if (totalVendas >= 500 && totalVendas <= 1000) {
            categoria = "Prata";
        } else {
            categoria = "Ouro";
        }
        System.out.println("Voluntário: " + getNome() + ", Total de Vendas: " + totalVendas + "€, Classificação: " + categoria);
    }
}

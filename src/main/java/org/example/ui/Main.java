package org.example.ui;

import org.example.model.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Construção da federação
            Federacao fap = new Federacao("Federação Académica do Porto");

            // Criar escala do dia automaticamente
            EscalaDiaria escalaAtual = EscalaDiaria.criarEscalaHoje();
            fap.setEscalaAtual(escalaAtual);
            fap.adicionarEscala(escalaAtual);

            // Criando instituições
            Instituicao instituicao1 = new Instituicao("Instituição A");
            Instituicao instituicao2 = new Instituicao("Instituição B");
            fap.adicionarInstituicao(instituicao1);
            fap.adicionarInstituicao(instituicao2);

            // Criando barracas
            Barraca barraca1 = new Barraca("Barraca 1", instituicao1);
            Barraca barraca2 = new Barraca("Barraca 2", instituicao2);
            fap.getTodasBarracas().add(barraca1);
            fap.getTodasBarracas().add(barraca2);
            escalaAtual.adicionarBarraca(barraca1);
            escalaAtual.adicionarBarraca(barraca2);

            // Criando voluntários
            VoluntarioStock voluntarioStock1 = new VoluntarioStock("João Stock", 12345, instituicao1);
            VoluntarioVendas voluntarioVendas1 = new VoluntarioVendas("Maria Vendas", 12346, instituicao1);
            VoluntarioStock voluntarioStock2 = new VoluntarioStock("Ana Stock", 12347, instituicao2);
            VoluntarioVendas voluntarioVendas2 = new VoluntarioVendas("Pedro Vendas", 12348, instituicao2);

            // Adicionando voluntários às instituições
            instituicao1.adicionarVoluntario(voluntarioStock1);
            instituicao1.adicionarVoluntario(voluntarioVendas1);
            instituicao2.adicionarVoluntario(voluntarioStock2);
            instituicao2.adicionarVoluntario(voluntarioVendas2);

            // Associando voluntários às barracas
            barraca1.adicionarVoluntario(voluntarioStock1);
            barraca1.adicionarVoluntario(voluntarioVendas1);
            barraca2.adicionarVoluntario(voluntarioStock2);
            barraca2.adicionarVoluntario(voluntarioVendas2);

            // Adicionando produtos ao estoque das barracas
            StockProdutos produto1 = new StockProdutos("Bolo", 3.5, 50);
            StockProdutos produto2 = new StockProdutos("Suco", 2.0, 30);
            StockProdutos produto3 = new StockProdutos("Caneta", 1.5, 100);
            barraca1.adicionarStock("Bolo", 3.5, 50);
            barraca1.adicionarStock("Suco", 2.0, 30);
            barraca2.adicionarStock("Caneta", 1.5, 100);
            voluntarioStock1.adicionarProdutoAoStock(produto1);
            voluntarioStock1.adicionarProdutoAoStock(produto2);
            voluntarioStock2.adicionarProdutoAoStock(produto3);

            // Realizando algumas vendas iniciais
            voluntarioVendas1.registarVenda("Bolo", 5); // Vende 5 Bolos da Barraca 1
            voluntarioVendas1.registarVenda("Suco", 3); // Vende 3 Sucos da Barraca 1
            voluntarioVendas2.registarVenda("Caneta", 10);

            // Iniciar menu principal
            MenuInicial_UI uiMenu = new MenuInicial_UI(fap);
            uiMenu.run();

            System.out.println(fap); // Mostrar estado final da federação
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

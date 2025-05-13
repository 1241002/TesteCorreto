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

            // Criando produtos
            Produto produto1 = new Produto("Bolo", 3.5);
            Produto produto2 = new Produto("Suco", 2.0);

            // Adicionando produtos à federação
            fap.adicionarProduto(produto1);
            fap.adicionarProduto(produto2);

            // Criando as Instituições
            Instituicao instituicao1 = new Instituicao("Instituição A");
            Instituicao instituicao2 = new Instituicao("Instituição B");

            // Adicionando Instituições à Federação
            fap.adicionarInstituicao(instituicao1);
            fap.adicionarInstituicao(instituicao2);

            // Criando Barracas
            Barraca barraca1 = new Barraca("Barraca 1", instituicao1);
            Barraca barraca2 = new Barraca("Barraca 2", instituicao2);

            // Criando Voluntários de forma correta, usando a classe VoluntarioVendas
            VoluntarioVendas voluntario1 = new VoluntarioVendas("João", 12345, instituicao1);
            VoluntarioVendas voluntario2 = new VoluntarioVendas("Maria", 12346, instituicao1);
            VoluntarioVendas voluntario3 = new VoluntarioVendas("Pedro", 12347, instituicao2);  // Exemplo com vendas

            // Adicionando Voluntários às Instituições
            instituicao1.adicionarVoluntario(voluntario1);
            instituicao1.adicionarVoluntario(voluntario2);
            instituicao2.adicionarVoluntario(voluntario3);

            // Adicionando Voluntários às Barracas
            barraca1.adicionarVoluntario(voluntario1); // Associando voluntários à barraca 1
            barraca1.adicionarVoluntario(voluntario2); // Associando voluntários à barraca 1
            barraca2.adicionarVoluntario(voluntario3); // Associando voluntário à barraca 2

            // Adicionando Barracas à Escala
            escalaAtual.adicionarBarraca(barraca1);
            escalaAtual.adicionarBarraca(barraca2);

            // Associando a Escala Diária à Federação
            fap.adicionarEscala(escalaAtual);
            fap.setEscalaAtual(escalaAtual);

            // Adicionando Barracas à Federação (para o teste)
            fap.getTodasBarracas().add(barraca1);
            fap.getTodasBarracas().add(barraca2);

            // Iniciar menu principal
            MenuInicial_UI uiMenu = new MenuInicial_UI(fap);
            uiMenu.run();

            System.out.println(fap); // Mostrar estado final da federação
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

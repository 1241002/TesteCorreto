package org.example.ui;

import org.example.model.*;
import org.example.utils.PersistenciaDados;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            // Tentar carregar dados persistidos
            Federacao fap = PersistenciaDados.carregarDados();

            // Se não houver dados salvos (primeira execução), inicializar a federação
            if (fap == null) {
                // Construção da federação
                fap = new Federacao("Federação Académica do Porto");

                // Adicionar administradores
                Administrador admin = new Administrador("Admin", 99999, "admin123", "Gestão");
                fap.adicionarAdministrador(admin);

                Administrador exdima = new Administrador("EXDIMA", 99998, "EXDIMA123", "Engenharia");
                fap.adicionarAdministrador(exdima);

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
                instituicao1.adicionarBarraca(barraca1);
                instituicao2.adicionarBarraca(barraca2);
                escalaAtual.adicionarBarraca(barraca1);
                escalaAtual.adicionarBarraca(barraca2);

                // Criando voluntários com curso e senha
                VoluntarioStock voluntarioStock1 = new VoluntarioStock("João Stock", 12345, instituicao1, "Engenharia", "joao123");
                VoluntarioVendas voluntarioVendas1 = new VoluntarioVendas("Maria Vendas", 12346, instituicao1, "Administração", "maria123");
                VoluntarioStock voluntarioStock2 = new VoluntarioStock("Ana Stock", 12347, instituicao2, "Ciências", "ana123");
                VoluntarioVendas voluntarioVendas2 = new VoluntarioVendas("Pedro Vendas", 12348, instituicao2, "Artes", "pedro123");

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

                // Adicionando produtos à federação
                fap.adicionarProduto(new Produto("Bolo", 3.5));
                fap.adicionarProduto(new Produto("Suco", 2.0));
                fap.adicionarProduto(new Produto("Caneta", 1.5));

                // Adicionando produtos ao stock das barracas via voluntários
                voluntarioStock1.adicionarProdutoAoStock("Bolo", 3.5, 50);
                voluntarioStock1.adicionarProdutoAoStock("Suco", 2.0, 30);
                voluntarioStock2.adicionarProdutoAoStock("Caneta", 1.5, 100);

                // Realizando algumas vendas iniciais
                voluntarioVendas1.registarVenda("Bolo", 5);
                voluntarioVendas1.registarVenda("Suco", 3);
                voluntarioVendas2.registarVenda("Caneta", 10);
            }

            // Iniciar menu principal
            MenuInicial_UI uiMenu = new MenuInicial_UI(fap);
            uiMenu.run();
        } catch (Exception e) {
            System.out.println("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
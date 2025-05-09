package org.example.ui;

import org.example.model.Federacao;
import org.example.model.Instituicao;
import org.example.model.VoluntarioStock;
import org.example.model.VoluntarioVendas;
import org.example.utils.Utils;
import java.io.IOException;

public class MenuVoluntarios {
    private Federacao federacao;
    private String opcao;

    public MenuVoluntarios(Federacao federacao) {
        this.federacao = federacao;
    }

    public void run() throws IOException {
        do {
            System.out.println("###### MENU VOLUNTÁRIOS #####");
            System.out.println("1. Adicionar Voluntário de Stock");
            System.out.println("2. Adicionar Voluntário de Vendas");
            System.out.println("0. Voltar");

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            if (opcao.equals("1")) {
                adicionarVoluntarioStock();
            } else if (opcao.equals("2")) {
                adicionarVoluntarioVendas();
            }
        } while (!opcao.equals("0"));
    }

    private void adicionarVoluntarioStock() {
        int numeroAluno = Utils.readIntFromConsole("Número do aluno: ");
        String nome = Utils.readLineFromConsole("Nome do voluntário: ");

        // Escolher a instituição através do metodo
        Instituicao instituicaoEscolhida = escolherInstituicao();

        // Criação de voluntário de Stock e associação à instituição
        VoluntarioStock voluntario = new VoluntarioStock(nome, numeroAluno, instituicaoEscolhida);

        // Adiciona à lista de voluntários da instituição
        instituicaoEscolhida.adicionarVoluntario(voluntario);
        System.out.println("Voluntário de Stock adicionado com sucesso à instituição " + instituicaoEscolhida.getNome());
    }

    private void adicionarVoluntarioVendas() {
        int numeroAluno = Utils.readIntFromConsole("Número do aluno: ");
        String nome = Utils.readLineFromConsole("Nome do voluntário: ");

        // Escolher a instituição através do metodo
        Instituicao instituicaoEscolhida = escolherInstituicao();

        // Criação de voluntário de Vendas e associação à instituição
        VoluntarioVendas voluntario = new VoluntarioVendas(nome, numeroAluno, instituicaoEscolhida);

        // Adiciona à lista de voluntários da instituição
        instituicaoEscolhida.adicionarVoluntario(voluntario);
        System.out.println("Voluntário de Vendas adicionado com sucesso à instituição " + instituicaoEscolhida.getNome());
    }

    // Metodo para escolher a instituição
    private Instituicao escolherInstituicao() {
        System.out.println("Escolha a instituição:");
        for (int i = 0; i < federacao.getInstituicoes().size(); i++) {
            System.out.println(i + 1 + ". " + federacao.getInstituicoes().get(i).getNome());
        }
        int opcaoInstituicao = Utils.readIntFromConsole("Escolha a instituição: ");
        return federacao.getInstituicoes().get(opcaoInstituicao - 1);  // Retorna a instituição escolhida
    }
}

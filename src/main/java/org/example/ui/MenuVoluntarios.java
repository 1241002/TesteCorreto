package org.example.ui;

import org.example.model.Federacao;
import org.example.model.Instituicao;
import org.example.model.VoluntarioStock;
import org.example.model.VoluntarioVendas;
import org.example.utils.Utils;

import java.io.IOException;
import java.util.List;

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
        String curso = Utils.readLineFromConsole("Curso do voluntário: ");
        String senha = Utils.readLineFromConsole("Senha do voluntário: "); // Adiciona a coleta da senha

        Instituicao instituicaoEscolhida = escolherInstituicao();
        if (instituicaoEscolhida == null) {
            System.out.println("Operação cancelada.");
            return;
        }

        if (instituicaoEscolhida.listaContemVoluntario(numeroAluno)) {
            System.out.println("Voluntário com esse número já existe na instituição.");
            return;
        }

        VoluntarioStock voluntario = new VoluntarioStock(nome, numeroAluno, instituicaoEscolhida, curso, senha);
        instituicaoEscolhida.adicionarVoluntario(voluntario);

        System.out.println("Voluntário de Stock adicionado com sucesso à instituição " + instituicaoEscolhida.getNome());
    }

    private void adicionarVoluntarioVendas() {
        int numeroAluno = Utils.readIntFromConsole("Número do aluno: ");
        String nome = Utils.readLineFromConsole("Nome do voluntário: ");
        String curso = Utils.readLineFromConsole("Curso do voluntário: ");
        String senha = Utils.readLineFromConsole("Senha do voluntário: "); // Adiciona a coleta da senha

        Instituicao instituicaoEscolhida = escolherInstituicao();
        if (instituicaoEscolhida == null) {
            System.out.println("Operação cancelada.");
            return;
        }

        if (instituicaoEscolhida.listaContemVoluntario(numeroAluno)) {
            System.out.println("Voluntário com esse número já existe na instituição.");
            return;
        }

        VoluntarioVendas voluntario = new VoluntarioVendas(nome, numeroAluno, instituicaoEscolhida, curso, senha);
        instituicaoEscolhida.adicionarVoluntario(voluntario);

        System.out.println("Voluntário de Vendas adicionado com sucesso à instituição " + instituicaoEscolhida.getNome());
    }

    private Instituicao escolherInstituicao() {
        List<Instituicao> instituicoes = federacao.getInstituicoes();

        if (instituicoes.isEmpty()) {
            System.out.println("Não existem instituições registadas.");
            return null;
        }

        System.out.println("Escolha a instituição:");
        for (int i = 0; i < instituicoes.size(); i++) {
            System.out.println((i + 1) + ". " + instituicoes.get(i).getNome());
        }

        int opcaoInstituicao = Utils.readIntFromConsole("Opção (0 para cancelar): ");
        if (opcaoInstituicao < 1 || opcaoInstituicao > instituicoes.size()) {
            return null;
        }

        return instituicoes.get(opcaoInstituicao - 1);
    }
}
package org.example.ui;

import org.example.model.Barraca;
import org.example.model.Instituicao;
import org.example.model.Voluntario;
import org.example.model.Federacao;
import org.example.utils.Utils;

import java.io.Serializable;

public class MenuBarraca{
    private Federacao federacao;

    public MenuBarraca(Federacao federacao) {
        this.federacao = federacao;
    }

    public void run() {
        String opcao;
        do {
            System.out.println("\n###### MENU BARRACA #####");
            System.out.println("1. Criar uma nova barraca");
            System.out.println("2. Adicionar voluntários a uma barraca existente");
            System.out.println("0. Voltar ao menu principal");
            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            switch (opcao) {
                case "1":
                    criarBarraca();
                    break;
                case "2":
                    adicionarVoluntariosBarracaExistente();
                    break;
                case "0":
                    System.out.println("A voltar ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

            if (!opcao.equals("0")) {
                Utils.readLineFromConsole("Pressione Enter para continuar...");
            }
        } while (!opcao.equals("0"));
    }

    private void criarBarraca() {
        String nomeBarraca = Utils.readLineFromConsole("Digite o nome da barraca: ");
        if (nomeBarraca == null || nomeBarraca.trim().isEmpty()) {
            System.out.println("Nome da barraca inválido.");
            return;
        }

        Instituicao instituicaoSelecionada = escolherInstituicao();
        if (instituicaoSelecionada == null) {
            System.out.println("Instituição não selecionada.");
            return;
        }

        Barraca novaBarraca = new Barraca(nomeBarraca, instituicaoSelecionada);

        // Pedir pelo menos 2 voluntários
        while (novaBarraca.getVoluntarios().size() < 2) {
            System.out.println("Adicione voluntários à barraca (" + novaBarraca.getVoluntarios().size() + "/2):");
            adicionarVoluntario(novaBarraca);
        }

        // Confirmação final antes de adicionar
        String confirma = Utils.readLineFromConsole("Confirmar criação da barraca? (s/n): ");
        if (confirma == null || !confirma.equalsIgnoreCase("s")) {
            System.out.println("Criação da barraca cancelada.");
            return;
        }

        instituicaoSelecionada.adicionarBarraca(novaBarraca);
        System.out.println("Barraca criada com sucesso!");

        if (federacao.getEscalaAtual() != null) {
            federacao.getEscalaAtual().adicionarBarraca(novaBarraca);
            System.out.println("Barraca adicionada à escala atual do dia: " + federacao.getEscalaAtual().getData());
        }
    }

    private Instituicao escolherInstituicao() {
        if (federacao.getInstituicoes().isEmpty()) {
            System.out.println("Nenhuma instituição disponível.");
            return null;
        }

        System.out.println("Escolha a instituição:");
        int i = 1;
        for (Instituicao instituicao : federacao.getInstituicoes()) {
            System.out.println(i + ". " + instituicao.getNome());
            i++;
        }

        int opcaoInstituicao = Utils.readIntFromConsole("Escolha a instituição: ");
        if (opcaoInstituicao < 1 || opcaoInstituicao > federacao.getInstituicoes().size()) {
            System.out.println("Opção inválida.");
            return null;
        }
        return federacao.getInstituicoes().get(opcaoInstituicao - 1);
    }

    private void adicionarVoluntariosBarracaExistente() {
        Instituicao instituicao = escolherInstituicao();
        if (instituicao == null) return;

        if (instituicao.getBarracas().isEmpty()) {
            System.out.println("Essa instituição ainda não tem barracas.");
            return;
        }

        Barraca barraca = escolherBarraca(instituicao);
        if (barraca == null) return;

        boolean continuar;
        do {
            adicionarVoluntario(barraca);
            String resposta = Utils.readLineFromConsole("Deseja adicionar outro voluntário? (s/n): ");
            continuar = resposta != null && resposta.equalsIgnoreCase("s");
        } while (continuar);
    }

    private Barraca escolherBarraca(Instituicao instituicao) {
        System.out.println("Escolha a barraca:");
        int j = 1;
        for (Barraca b : instituicao.getBarracas()) {
            System.out.println(j + ". " + b.getNome());
            j++;
        }

        int escolhaBarraca = Utils.readIntFromConsole("Escolha: ");
        if (escolhaBarraca < 1 || escolhaBarraca > instituicao.getBarracas().size()) {
            System.out.println("Opção inválida.");
            return null;
        }
        return instituicao.getBarracas().get(escolhaBarraca - 1);
    }

    private void adicionarVoluntario(Barraca barraca) {
        int numeroAluno = Utils.readIntFromConsole("Digite o número de aluno do voluntário: ");
        Voluntario voluntario = federacao.buscarVoluntarioPorNumeroAluno(numeroAluno);

        if (voluntario != null) {
            if (barraca.adicionarVoluntario(voluntario)) {
                System.out.println("Voluntário " + voluntario.getNome() + " adicionado com sucesso!");
            } else {
                System.out.println("Erro: voluntário já está associado ou não é elegível.");
            }
        } else {
            System.out.println("Voluntário com número de aluno " + numeroAluno + " não encontrado.");
        }
    }
}

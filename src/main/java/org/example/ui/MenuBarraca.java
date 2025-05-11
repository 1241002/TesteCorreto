package org.example.ui;

import org.example.model.Barraca;
import org.example.model.Instituicao;
import org.example.model.Voluntario;
import org.example.model.Federacao;
import org.example.utils.Utils;

public class MenuBarraca {

    private Federacao federacao;

    public MenuBarraca(Federacao federacao) {
        this.federacao = federacao;
    }

    public void run() {
        String opcao;
        do {
            System.out.println("###### MENU BARRACA #####");
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

        } while (!opcao.equals("0"));
    }

    private void criarBarraca() {
        String nomeBarraca = Utils.readLineFromConsole("Digite o nome da barraca: ");

        // Escolher instituição
        System.out.println("Escolha a instituição:");
        int i = 1;
        for (Instituicao instituicao : federacao.getInstituicoes()) {
            System.out.println(i + ". " + instituicao.getNome());
            i++;
        }

        int opcaoInstituicao = Utils.readIntFromConsole("Escolha a instituição: ");
        Instituicao instituicaoSelecionada = federacao.getInstituicoes().get(opcaoInstituicao - 1);

        // Criar a barraca
        Barraca novaBarraca = new Barraca(nomeBarraca, instituicaoSelecionada);

        // Adicionar pelo menos 2 voluntários
        while (novaBarraca.getVoluntarios().size() < 2) {
            System.out.println("Adicione voluntários à barraca:");
            adicionarVoluntario(novaBarraca);
        }

        instituicaoSelecionada.adicionarBarraca(novaBarraca);
        System.out.println("Barraca criada com sucesso!");
    }

    private void adicionarVoluntariosBarracaExistente() {
        System.out.println("Escolha a instituição da barraca:");
        int i = 1;
        for (Instituicao instituicao : federacao.getInstituicoes()) {
            System.out.println(i + ". " + instituicao.getNome());
            i++;
        }

        int escolhaInst = Utils.readIntFromConsole("Escolha: ");
        Instituicao instituicao = federacao.getInstituicoes().get(escolhaInst - 1);

        if (instituicao.getBarracas().isEmpty()) {
            System.out.println("Essa instituição ainda não tem barracas.");
            return;
        }

        System.out.println("Escolha a barraca:");
        int j = 1;
        for (Barraca b : instituicao.getBarracas()) {
            System.out.println(j + ". " + b.getNome());
            j++;
        }

        int escolhaBarraca = Utils.readIntFromConsole("Escolha: ");
        Barraca barraca = instituicao.getBarracas().get(escolhaBarraca - 1);

        boolean continuar;
        do {
            adicionarVoluntario(barraca);
            continuar = Utils.readLineFromConsole("Deseja adicionar outro voluntário? (s/n): ").equalsIgnoreCase("s");
        } while (continuar);
    }

    private void adicionarVoluntario(Barraca barraca) {
        int numeroAluno = Utils.readIntFromConsole("Digite o número de aluno do voluntário: ");
        Voluntario voluntario = federacao.buscarVoluntarioPorNumeroAluno(numeroAluno);

        if (voluntario != null) {
            if (barraca.adicionarVoluntario(voluntario)) {
                System.out.println("Voluntário " + voluntario.getNome() + " adicionado com sucesso!");
            } else {
                System.out.println("Erro: voluntário já pode estar associado ou não é elegível.");
            }
        } else {
            System.out.println("Voluntário com número de aluno " + numeroAluno + " não encontrado.");
        }
    }
}

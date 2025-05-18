package org.example.ui;

import org.example.model.Barraca;
import org.example.model.Instituicao;
import org.example.model.Voluntario;
import org.example.model.Federacao;
import org.example.utils.Utils;

import java.io.Serializable;

/**
 * Classe que implementa o menu de interação para gestão de barracas
 * dentro da federação. Permite criar barracas, adicionar voluntários
 * às barracas existentes e navegar no menu.
 */
public class MenuBarraca {
    private Federacao federacao;

    /**
     * Construtor da classe MenuBarraca.
     *
     * @param federacao Instância da federação que contém as instituições,
     *                  barracas e voluntários.
     */
    public MenuBarraca(Federacao federacao) {
        this.federacao = federacao;
    }

    /**
     * Executa o menu principal de barracas, permitindo ao usuário escolher
     * as opções disponíveis até optar por voltar ao menu principal.
     */
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

    /**
     * Metodo auxiliar que cria uma nova barraca, associando-a a uma instituição
     * e adicionando pelo menos dois voluntários.
     * Solicita confirmação antes de concluir a criação.
     */
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

        // Pedir pelo menos 2 voluntários, repetindo até ter 2 ou cancelar
        while (novaBarraca.getVoluntarios().size() < 2) {
            System.out.println("Adicione voluntários à barraca (" +
                    novaBarraca.getVoluntarios().size() + "/2):");

            String cancelar = Utils.readLineFromConsole(
                    "Deseja cancelar a criação da barraca? (s para cancelar / Enter para continuar): "
            );
            if (cancelar != null && cancelar.equalsIgnoreCase("s")) {
                System.out.println("Criação da barraca cancelada.");
                return;
            }

            String linha = Utils.readLineFromConsole(
                    "Digite o número de aluno do voluntário: "
            );
            if (linha == null || linha.trim().isEmpty()) {
                System.out.println("Número inválido.");
                continue;
            }

            int numeroAluno;
            try {
                numeroAluno = Integer.parseInt(linha.trim());
            } catch (NumberFormatException e) {
                System.out.println("Número inválido. Tente novamente.");
                continue;
            }

            Voluntario voluntario = federacao.buscarVoluntarioPorNumeroAluno(numeroAluno);
            if (voluntario == null) {
                System.out.println("Voluntário com número " + numeroAluno + " não encontrado.");
                continue;
            }

            if (!novaBarraca.adicionarVoluntario(voluntario)) {
                System.out.println("Erro: voluntário já está associado ou não é elegível.");
                continue;
            }

            System.out.println("Voluntário " + voluntario.getNome() + " adicionado com sucesso!");
        }

        // confirmação final
        String confirma = Utils.readLineFromConsole("Confirmar criação da barraca? (s/n): ");
        if (confirma == null || !confirma.equalsIgnoreCase("s")) {
            System.out.println("Criação da barraca cancelada.");
            return;
        }

        instituicaoSelecionada.adicionarBarraca(novaBarraca);
        System.out.println("Barraca criada com sucesso!");
        if (federacao.getEscalaAtual() != null) {
            federacao.getEscalaAtual().adicionarBarraca(novaBarraca);
            System.out.println("Barraca adicionada à escala atual: " +
                    federacao.getEscalaAtual().getData());
        }
    }



    /**
     * Exibe a lista de instituições e permite ao usuário escolher uma delas.
     *
     * @return Instituição selecionada pelo usuário, ou null se não houver escolha válida.
     */
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

    /**
     * Permite adicionar voluntários a uma barraca já existente de uma instituição.
     * Continua pedindo voluntários enquanto o usuário desejar.
     */
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

    /**
     * Exibe as barracas de uma instituição para o usuário escolher uma.
     *
     * @param instituicao Instituição cujas barracas serão listadas.
     * @return Barraca selecionada ou null caso a escolha seja inválida.
     */
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

    /**
     * Solicita o número de aluno do voluntário e tenta adicioná-lo à barraca.
     * Verifica se o voluntário existe e se pode ser associado à barraca.
     *
     * @param barraca Barraca à qual o voluntário será adicionado.
     */
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

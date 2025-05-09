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
            System.out.println("0. Voltar ao menu principal");
            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            if (opcao.equals("1")) {
                criarBarraca();
            }

        } while (!opcao.equals("0"));
    }

    private void criarBarraca() {
        String nomeBarraca = Utils.readLineFromConsole("Digite o nome da barraca: ");

        // Exibir lista de instituições e permitir que o usuário escolha
        System.out.println("Escolha a instituição:");
        int i = 1;
        for (Instituicao instituicao : federacao.getInstituicoes()) {
            System.out.println(i + ". " + instituicao.getNome());
            i++;
        }

        int opcaoInstituicao = Utils.readIntFromConsole("Escolha a instituição: ");
        Instituicao instituicaoSelecionada = federacao.getInstituicoes().get(opcaoInstituicao - 1);

        // Criar a barraca com a instituição escolhida
        Barraca novaBarraca = new Barraca(nomeBarraca, instituicaoSelecionada);

        // Adicionar voluntários até que a barraca tenha pelo menos 2
        while (novaBarraca.getVoluntarios().size() < 2) {
            System.out.println("Adicione voluntários à barraca:");

            int numeroAluno = Utils.readIntFromConsole("Digite o número de aluno do voluntário: ");

            // Buscar o voluntário pelo número de aluno
            Voluntario voluntario = federacao.buscarVoluntarioPorNumeroAluno(numeroAluno);

            if (voluntario != null) {
                if (novaBarraca.adicionarVoluntario(voluntario)) {
                    System.out.println("Voluntário " + voluntario.getNome() + " adicionado com sucesso!");
                } else {
                    System.out.println("Erro ao adicionar voluntário com número de aluno " + numeroAluno);
                }
            } else {
                System.out.println("Voluntário com número de aluno " + numeroAluno + " não encontrado.");
            }
        }

        System.out.println("Barraca criada com sucesso!");
    }



}

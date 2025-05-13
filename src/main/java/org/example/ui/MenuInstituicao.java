package org.example.ui;

import org.example.model.Federacao;
import org.example.model.Instituicao;
import org.example.utils.Utils;

public class MenuInstituicao {

    private final Federacao federacao;

    public MenuInstituicao(Federacao federacao) {
        this.federacao = federacao;
    }

    public void run() {
        System.out.println();
        System.out.println("### Menu Instituição ###");

        String nome = Utils.readLineFromConsole("Nome da nova instituição: ");

        if (nome == null || nome.equals("")) {
            System.out.println("O nome da instituição não pode estar vazio.");
            return;
        }

        if (federacao.listaContemInstituicao(nome)) {
            System.out.println("Já existe uma instituição com esse nome.");
            return;
        }

        Instituicao novaInstituicao = new Instituicao(nome);
        federacao.adicionarInstituicao(novaInstituicao);
        System.out.println("Instituição '" + nome + "' adicionada com sucesso.");
    }
}

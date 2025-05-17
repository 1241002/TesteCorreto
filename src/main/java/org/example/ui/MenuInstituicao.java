package org.example.ui;

import org.example.model.ExcecaoNome;
import org.example.model.Federacao;
import org.example.model.Instituicao;
import org.example.utils.Utils;

import java.io.Serializable;

/**
 * Classe que representa o menu de gestão de instituições dentro da federação.
 * Permite ao utilizador adicionar novas instituições, garantindo que não haja
 * duplicados e que o nome seja válido.
 */
public class MenuInstituicao {

    /**
     * Instância da federação onde as instituições são geridas.
     */
    private final Federacao federacao;

    /**
     * Construtor que inicializa o menu com a instância da federação.
     *
     * @param federacao A federação usada para adicionar e verificar instituições.
     */
    public MenuInstituicao(Federacao federacao) {
        if (federacao == null) {
            throw new IllegalArgumentException("Federação não pode ser nula");
        }
        this.federacao = federacao;
    }

    /**
     * Executa o menu de instituição, solicitando ao utilizador o nome da nova instituição,
     * valida se o nome não está vazio e se ainda não existe uma instituição com o mesmo nome,
     * e adiciona a nova instituição à federação caso tudo esteja correto.
     */
    public void run() {
        try {
            System.out.println("\n### Menu Instituição ###");
            String nome = Utils.readLineFromConsole("Nome da nova instituição: ");
            if (nome == null || nome.trim().isEmpty()) {
                throw new ExcecaoNome("O nome da instituição não pode estar vazio");
            }
            if (!nome.matches("^[A-Za-zÀ-ÿ\\s]+$")) {
                throw new ExcecaoNome("O nome da instituição deve conter apenas letras e espaços");
            }

            if (federacao.listaContemInstituicao(nome)) {
                System.out.println("Já existe uma instituição com esse nome.");
                return;
            }

            Instituicao novaInstituicao = new Instituicao(nome);
            federacao.adicionarInstituicao(novaInstituicao);
            System.out.println("Instituição '" + nome + "' adicionada com sucesso.");
        } catch (ExcecaoNome e) {
            System.out.println("Erro no nome: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de entrada: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
        }
        Utils.readLineFromConsole("Pressione Enter para continuar...");
    }
}

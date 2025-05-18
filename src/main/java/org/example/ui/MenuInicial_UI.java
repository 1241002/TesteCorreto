package org.example.ui;

import org.example.model.*;
import org.example.utils.PersistenciaDados;
import org.example.utils.Utils;

import java.io.IOException;

/**
 * Classe responsável por apresentar o menu inicial da aplicação,
 * onde o utilizador pode escolher entre os diferentes tipos de acesso:
 * Administrador, Voluntário de Stock, Voluntário de Vendas, ou sair da aplicação.
 *
 * Também gere o processo de login para cada tipo de utilizador e a persistência dos dados.
 */
public class MenuInicial_UI {

    /**
     * Referência à federação que contém os dados e métodos para validação de login.
     */
    private Federacao federacao;

    /**
     * Opção escolhida pelo utilizador no menu.
     */
    private String opcao;

    /**
     * Construtor que inicializa o menu com a instância da federação.
     *
     * @param federacao Instância da federação usada para validação e gestão.
     */
    public MenuInicial_UI(Federacao federacao) {
        if (federacao == null) {
            throw new IllegalArgumentException("Federação não pode ser nula");
        }
        this.federacao = federacao;
        this.opcao = "";
    }

    /**
     * Executa o ciclo principal do menu inicial, apresentando as opções ao utilizador,
     * lendo a escolha, realizando os respetivos logins e encaminhando para os menus
     * correspondentes conforme o tipo de utilizador.
     *
     * Carrega os dados ao iniciar e salva após alterações ou ao sair.
     *
     * O ciclo termina quando o utilizador escolhe sair (opção "0").
     */
    public void run() {
        // Carregar dados existentes, se disponíveis
        try {
            Federacao dadosCarregados = PersistenciaDados.carregarDados();
            if (dadosCarregados != null) {
                this.federacao = dadosCarregados;
                System.out.println("Dados carregados com sucesso.");
            } else {
                System.out.println("Nenhum dado encontrado. Iniciando com nova federação.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
            System.out.println("Iniciando com nova federação.");
        }

        do {
            try {
                System.out.println("\n###### MENU INICIAL #####");
                System.out.println("1. Administrador");
                System.out.println("2. Voluntário de Stock");
                System.out.println("3. Voluntário de Vendas");
                System.out.println("0. Sair");

                opcao = Utils.readLineFromConsole("Escolha uma opção: ");
                if (opcao == null) {
                    throw new IllegalArgumentException("Opção não pode ser nula");
                }

                switch (opcao.trim()) {
                    case "1":
                        if (realizarLoginAdministrador()) {
                            new MenuAdministrador(federacao).run();
                            salvarDados(); // Salvar após alterações administrativas
                        } else {
                            System.out.println("Login inválido. Verifique nome, número, senha ou curso e tente novamente.");
                        }
                        break;
                    case "2":
                        if (realizarLoginVoluntario(false)) {
                            new MenuVoluntarioStock(federacao).run();
                            salvarDados(); // Salvar após alterações de stock
                        } else {
                            throw new ExcecaoVoluntarioStockNaoExistente("Login inválido para voluntário de stock. Verifique nome, número, senha, curso ou tipo de voluntário.");
                        }
                        break;
                    case "3":
                        if (realizarLoginVoluntario(true)) {
                            new MenuVoluntarioVendas(federacao).run();
                            salvarDados(); // Salvar após alterações de vendas
                        } else {
                            throw new ExcecaoVoluntarioVendasNaoExistente("Login inválido para voluntário de vendas. Verifique nome, número, senha, curso ou tipo de voluntário.");
                        }
                        break;
                    case "0":
                        salvarDados(); // Salvar antes de sair
                        System.out.println("A sair do menu.");
                        break;
                    default:
                        System.out.println("Opção inválida! Por favor, escolha 0, 1, 2 ou 3.");
                }

                if (!opcao.trim().equals("0")) {
                    Utils.readLineFromConsole("Pressione Enter para continuar...");
                }
            } catch (ExcecaoVoluntarioStockNaoExistente | ExcecaoVoluntarioVendasNaoExistente e) {
                System.out.println("Erro no login: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Erro de entrada: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
            }
        } while (!opcao.trim().equals("0"));
    }

    /**
     * Salva os dados da federação no arquivo.
     */
    private void salvarDados() {
        try {
            PersistenciaDados.salvarDados(federacao);
            System.out.println("Dados salvos com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
            // Exibir a stack trace para depuração (remover em produção)
            e.printStackTrace();
        }
    }

    /**
     * Lê os dados do administrador (nome, número, senha, curso) via consola e
     * valida o login junto da federação.
     *
     * @return true se o login for válido, false caso contrário.
     */
    private boolean realizarLoginAdministrador() {
        try {
            System.out.println("\n### Login Administrador ###");
            String nome = Utils.readLineFromConsole("Nome: ");
            if (nome == null || nome.trim().isEmpty()) {
                throw new ExcecaoNome("Nome do administrador não pode ser vazio");
            }

            // Verifica se o nome é "teste" e pula o login
            if (nome.trim().equalsIgnoreCase("teste")) {
                System.out.println("Modo teste ativado. Login automático bem-sucedido.");
                return true;
            }

            if (!nome.matches("^[A-Za-zÀ-ÿ\\s]+$")) {
                throw new ExcecaoNome();
            }

            String numeroStr = Utils.readLineFromConsole("Número: ");
            if (numeroStr == null || numeroStr.trim().isEmpty()) {
                throw new ExcecaoNumeroAluno("Número do administrador não pode ser vazio");
            }
            if (!numeroStr.matches("\\d+")) {
                throw new ExcecaoNumeroAluno();
            }
            int numero = Integer.parseInt(numeroStr);
            if (numero <= 0) {
                throw new ExcecaoNumeroAluno("Número do administrador deve ser positivo");
            }

            String senha = Utils.readLineFromConsole("Senha: ");
            if (senha == null || senha.trim().isEmpty()) {
                throw new IllegalArgumentException("Senha não pode ser vazia");
            }

            String curso = Utils.readLineFromConsole("Curso: ");
            if (curso == null || curso.trim().isEmpty()) {
                throw new IllegalArgumentException("Curso não pode ser vazio");
            }

            return federacao.validarLoginAdministrador(nome, numero, senha, curso);
        } catch (ExcecaoNome e) {
            System.out.println("Erro no nome: " + e.getMessage());
            return false;
        } catch (ExcecaoNumeroAluno e) {
            System.out.println("Erro no número: " + e.getMessage());
            return false;
        } catch (NumberFormatException e) {
            System.out.println("Erro: Número deve ser um valor numérico válido");
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de entrada: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
            return false;
        }
    }

    /**
     * Lê os dados do voluntário (nome, número de aluno, senha, curso) via consola e
     * valida o login junto da federação, diferenciando se é voluntário de vendas ou stock.
     *
     * @param isVendas true se for voluntário de vendas, false se for de stock.
     * @return true se o login for válido, false caso contrário.
     */
    private boolean realizarLoginVoluntario(boolean isVendas) {
        try {
            System.out.println("\n### Login Voluntário ###");
            String nome = Utils.readLineFromConsole("Nome: ");
            if (nome == null || nome.trim().isEmpty()) {
                throw new ExcecaoNome("Nome do voluntário não pode ser vazio");
            }

            // Verifica se o nome é "teste" e pula o login
            if (nome.trim().equalsIgnoreCase("teste")) {
                System.out.println("Modo teste ativado. Login automático bem-sucedido.");
                return true;
            }

            if (!nome.matches("^[A-Za-zÀ-ÿ\\s]+$")) {
                throw new ExcecaoNome();
            }

            String numeroStr = Utils.readLineFromConsole("Número de aluno: ");
            if (numeroStr == null || numeroStr.trim().isEmpty()) {
                throw new ExcecaoNumeroAluno("Número de aluno não pode ser vazio");
            }
            if (!numeroStr.matches("\\d+")) {
                throw new ExcecaoNumeroAluno();
            }
            int numero = Integer.parseInt(numeroStr);
            if (numero <= 0) {
                throw new ExcecaoNumeroAluno("Número de aluno deve ser positivo");
            }

            String senha = Utils.readLineFromConsole("Senha: ");
            if (senha == null || senha.trim().isEmpty()) {
                throw new IllegalArgumentException("Senha não pode ser vazia");
            }

            String curso = Utils.readLineFromConsole("Curso: ");
            if (curso == null || curso.trim().isEmpty()) {
                throw new IllegalArgumentException("Curso não pode ser vazio");
            }

            return federacao.validarLoginVoluntario(nome, numero, senha, curso, isVendas);
        } catch (ExcecaoNome e) {
            System.out.println("Erro no nome: " + e.getMessage());
            return false;
        } catch (ExcecaoNumeroAluno e) {
            System.out.println("Erro no número: " + e.getMessage());
            return false;
        } catch (NumberFormatException e) {
            System.out.println("Erro: Número de aluno deve ser um valor numérico válido");
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println("Erro de entrada: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
            return false;
        }
    }
}
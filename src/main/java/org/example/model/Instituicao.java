package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Instituicao {
    private String nome;
    private final List<Voluntario> listaVoluntarios; // Lista de voluntários
    private final List<Produto> lstProdutos;
    private List<Barraca> barracas = new ArrayList<>();  // Inicializando a lista de barracas.


    public Instituicao(String nome) {
        this.nome = nome;
        this.listaVoluntarios = new ArrayList<>();  // Inicializando a lista de voluntários
        this.lstProdutos = new ArrayList<>();
    }

    public List<Barraca> getBarracas() {
        return this.barracas;
    }


    public List<Produto> getLstProdutos() {
        return lstProdutos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Voluntario> getListaVoluntarios() {
        return listaVoluntarios;
    }

    public boolean adicionarBarraca(Barraca barraca) {
        if (!getBarracas().contains(barraca)) {  // Verifica se a barraca já existe na lista.
            getBarracas().add(barraca);  // Se não existir, adiciona a barraca.
            return true;  // Retorna verdadeiro se a barraca foi adicionada com sucesso.
        }
        return false;  // Retorna falso se a barraca já existir na lista.
    }


    // Metodo para adicionar um voluntário à lista (agora usando o número de aluno como int)
    public boolean adicionarVoluntario(Voluntario voluntario) {
        if (!listaContemVoluntario(voluntario.getNumeroAluno())) {  // Verifica com base no número
            listaVoluntarios.add(voluntario);
            return true;
        } else {
            return false;
        }
    }

    // Verificar se o voluntário já está na lista, usando o número de aluno como int
    public boolean listaContemVoluntario(int numeroAluno) {
        for (Voluntario voluntario : listaVoluntarios) {
            if (voluntario.getNumeroAluno() == numeroAluno) {  // Comparando número de aluno (agora int)
                return true;
            }
        }
        return false;
    }

    // Buscar Voluntário por número de aluno
    public Voluntario buscarVoluntarioPorNumeroAluno(int numeroAluno) {
        for (Voluntario v : listaVoluntarios) {
            if (v.getNumeroAluno() == numeroAluno) {
                return v; // Retorna o voluntário se o número de aluno corresponder
            }
        }
        return null; // Retorna null se não encontrar o voluntário
    }

    // Retorna voluntário de vendas
    public VoluntarioVendas getVoluntarioVendasPorNumeroAluno(int numeroAluno) {
        for (Voluntario voluntario : listaVoluntarios) {
            if (voluntario instanceof VoluntarioVendas && voluntario.getNumeroAluno() == numeroAluno) {
                return (VoluntarioVendas) voluntario;
            }
        }
        return null;
    }

    // Retorna voluntário de stock
    public VoluntarioStock getVoluntarioStockPorNumeroAluno(int numeroAluno) {
        for (Voluntario voluntario : listaVoluntarios) {
            if (voluntario instanceof VoluntarioStock && voluntario.getNumeroAluno() == numeroAluno) {
                return (VoluntarioStock) voluntario;
            }
        }
        return null;
    }

    // Retorna qualquer voluntário
    public Voluntario getVoluntarioPorNumeroAluno(int numeroAluno) {
        for (Voluntario voluntario : listaVoluntarios) {
            if (voluntario.getNumeroAluno() == numeroAluno) {
                return voluntario;
            }
        }
        return null;
    }


    // Metodo toString atualizado para detalhar melhor a federação

    @Override
    public String toString() {
        return "Instituicao{" +
                "nome='" + nome + '\'' +
                ", listaVoluntarios=" + listaVoluntarios +
                ", lstProdutos=" + lstProdutos +
                '}';
    }
}

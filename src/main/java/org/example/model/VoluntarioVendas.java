package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VoluntarioVendas extends Voluntario {
    private final List<Double> vendas;

    public VoluntarioVendas(String nome, int numeroAluno, Instituicao instituicao) {
        super(nome, numeroAluno, instituicao);
        this.vendas = new ArrayList<>();
    }

    public VoluntarioVendas(String nome, int numeroAluno, Instituicao instituicao, List<Double> vendasAntigas) {
        super(nome, numeroAluno, instituicao);
        this.vendas = new ArrayList<>(vendasAntigas); // cópia defensiva
    }

    public void registarVenda(double valor) {
        if (valor > 0) {
            vendas.add(valor);
        }
    }

    public boolean removerUltimaVenda() {
        if (!vendas.isEmpty()) {
            vendas.remove(vendas.size() - 1);
            return true;
        }
        return false;
    }

    public boolean removerVendaPorValor(double valor) {
        return vendas.remove(valor); // remove a primeira ocorrência
    }

    public double getTotalVendas() {
        double total = 0.0;
        for (double v : vendas) {
            total += v;
        }
        return total;
    }

    public void limparVendas() {
        vendas.clear();
    }

    public List<Double> getTodasVendas() {
        return new ArrayList<>(vendas); // cópia de segurança
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        VoluntarioVendas that = (VoluntarioVendas) o;
        return Objects.equals(vendas, that.vendas);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("VoluntarioVendas: ").append(super.toString());
        sb.append("Total de Vendas: %.2f€").append(getTodasVendas());
        sb.append('}');
        return sb.toString();
    }
}

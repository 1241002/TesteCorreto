package org.example.model;

import java.util.ArrayList;
import java.util.List;
import org.example.utils.Data;
import java.time.LocalDate;

public class EscalaDiaria implements Comparable<EscalaDiaria> {
    // Variáveis de instância
    private Data data;
    private List<Barraca> barracas;
    private double vendasTotais;
    private int stockFinal;

    // Construtor completo
    public EscalaDiaria(Data data) {
        if (data == null) {
            throw new IllegalArgumentException("Data não pode ser nula");
        }
        this.data = data;
        this.barracas = new ArrayList<>();
        this.vendasTotais = 0.0;
        this.stockFinal = 0;
    }

    // Construtor vazio
    public EscalaDiaria() {
        this.data = null;
        this.barracas = new ArrayList<>();
        this.vendasTotais = 0.0;
        this.stockFinal = 0;
    }

    // Construtor de cópia
    public EscalaDiaria(EscalaDiaria outra) {
        this.data = outra.data; // Assume-se referência partilhada para Data
        this.barracas = new ArrayList<>(outra.barracas);
        this.vendasTotais = outra.vendasTotais;
        this.stockFinal = outra.stockFinal;
    }

    // Seletores
    public Data getData() {
        return this.data;
    }

    public List<Barraca> getBarracas() {
        return new ArrayList<>(this.barracas); // Cópia defensiva
    }

    public double getVendasTotais() {
        return this.vendasTotais;
    }

    public int getStockFinal() {
        return this.stockFinal;
    }

    // Modificadores
    public void setData(Data data) {
        if (data == null) {
            throw new IllegalArgumentException("Data não pode ser nula");
        }
        this.data = data;
    }

    // Métodos de instância
    public void adicionarVendaTotal(double vendas) {
        if (vendas < 0) {
            throw new IllegalArgumentException("Vendas não podem ser negativas");
        }
        this.vendasTotais += vendas;
    }

    public void adicionarStockFinal(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("Stock não pode ser negativo");
        }
        this.stockFinal += stock;
    }

    public void adicionarBarraca(Barraca b) {
        if (b == null) {
            throw new IllegalArgumentException("Barraca não pode ser nula");
        }
        this.barracas.add(b);
    }

    // Método estático
    public static EscalaDiaria criarEscalaHoje() {
        LocalDate hoje = LocalDate.now();
        Data dataHoje = new Data(hoje.getYear(), hoje.getMonthValue(), hoje.getDayOfMonth());
        return new EscalaDiaria(dataHoje);
    }

    // Implementação de Comparable (ordena por data)
    @Override
    public int compareTo(EscalaDiaria outra) {
        if (this.data == null && outra.data == null) return 0;
        if (this.data == null) return -1;
        if (outra.data == null) return 1;
        // Assume que Data implementa Comparable ou tem método de comparação
        return this.data.toString().compareTo(outra.data.toString());
    }

    // Método equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EscalaDiaria)) return false;
        EscalaDiaria that = (EscalaDiaria) o;
        return Double.compare(this.vendasTotais, that.vendasTotais) == 0 &&
                this.stockFinal == that.stockFinal &&
                (this.data == null ? that.data == null : this.data.equals(that.data)) &&
                this.barracas.equals(that.barracas);
    }

    // Método toString
    @Override
    public String toString() {
        return "EscalaDiaria{" +
                "data=" + (data == null ? "N/A" : data) +
                ", numBarracas=" + barracas.size() +
                ", vendasTotais=" + String.format("%.2f", vendasTotais) + "€" +
                ", stockFinal=" + stockFinal +
                '}';
    }
}
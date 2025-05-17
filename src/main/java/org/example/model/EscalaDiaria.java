package org.example.model;

import java.util.ArrayList;
import java.util.List;
import org.example.utils.Data;

/**
 * Representa uma escala diária com barracas associadas, total de vendas e stock final.
 * Serve para registar os dados de funcionamento de um dia específico.
 */
public class EscalaDiaria implements Comparable<EscalaDiaria> {
    private Data data;
    private List<Barraca> barracas;
    private double vendasTotais;
    private int stockFinal;

    /**
     * Construtor que inicializa a escala com uma data específica.
     * @param data Data da escala.
     */
    public EscalaDiaria(Data data) {
        this.data = data;
        this.barracas = new ArrayList<>();
        this.vendasTotais = 0.0;
        this.stockFinal = 0;
    }

    /**
     * Construtor por omissão.
     */
    public EscalaDiaria() {
        this.data = null;
        this.barracas = new ArrayList<>();
        this.vendasTotais = 0.0;
        this.stockFinal = 0;
    }

    /**
     * Construtor de cópia.
     * @param outra Escala a copiar.
     */
    public EscalaDiaria(EscalaDiaria outra) {
        this.data = outra.data;
        this.barracas = new ArrayList<>(outra.barracas);
        this.vendasTotais = outra.vendasTotais;
        this.stockFinal = outra.stockFinal;
    }

    /**
     * Obtém a data da escala.
     * @return Data da escala.
     */
    public Data getData() {
        return this.data;
    }

    /**
     * Define a data da escala.
     * @param data Nova data.
     */
    public void setData(Data data) {
        this.data = data;
    }

    /**
     * Retorna uma cópia da lista de barracas associadas à escala.
     * @return Lista de barracas.
     */
    public List<Barraca> getBarracas() {
        return new ArrayList<>(this.barracas);
    }

    /**
     * Retorna o total de vendas registadas nesta escala.
     * @return Total de vendas.
     */
    public double getVendasTotais() {
        return this.vendasTotais;
    }

    /**
     * Retorna o stock final registado nesta escala.
     * @return Quantidade total de stock.
     */
    public int getStockFinal() {
        return this.stockFinal;
    }

    /**
     * Adiciona uma nova barraca à escala.
     * @param b Barraca a adicionar.
     */
    public void adicionarBarraca(Barraca b) {
        this.barracas.add(b);
    }

    /**
     * Adiciona valor ao total de vendas.
     * @param vendas Valor a adicionar.
     */
    public void adicionarVendaTotal(double vendas) {
        this.vendasTotais += vendas;
    }

    /**
     * Adiciona valor ao stock final.
     * @param stock Valor a adicionar.
     */
    public void adicionarStockFinal(int stock) {
        this.stockFinal += stock;
    }

    /**
     * Cria uma nova escala com a data de hoje (fixa para 2025-05-16 neste exemplo).
     * @return Nova instância de EscalaDiaria.
     */
    public static EscalaDiaria criarEscalaHoje() {
        Data dataHoje = new Data(2025, 5, 16); // Substituir por data real se necessário
        return new EscalaDiaria(dataHoje);
    }

    /**
     * Compara duas escalas com base na data.
     * @param outra Outra escala a comparar.
     * @return Valor da comparação.
     */
    @Override
    public int compareTo(EscalaDiaria outra) {
        return this.data.toString().compareTo(outra.data.toString());
    }

    /**
     * Verifica se duas escalas são iguais com base na data.
     * @param o Objeto a comparar.
     * @return true se forem iguais, false caso contrário.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EscalaDiaria)) return false;
        EscalaDiaria that = (EscalaDiaria) o;
        return this.data.equals(that.data);
    }

    /**
     * Retorna uma representação textual da escala.
     * @return String com informações da escala.
     */
    @Override
    public String toString() {
        return "EscalaDiaria{" +
                "data=" + data +
                ", numBarracas=" + barracas.size() +
                ", vendasTotais=" + String.format("%.2f", vendasTotais) + "€" +
                ", stockFinal=" + stockFinal +
                '}';
    }
}

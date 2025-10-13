package com.example.model;
import com.example.Calculator;

import java.util.ArrayList;
import java.util.List;

public class Order {
    // Atributos
    private String id;
    private List<Article> articulos;
    private final Calculator calculator = new Calculator();

    // Constructor
    public Order(String id) {
        this.id = id;
        this.articulos = new ArrayList<>();
    }

    // Getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Article> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<Article> articulos) {
        this.articulos = articulos;
    }

    // Método para añadir artículos
    public void addArticle(Article article) {
        articulos.add(article);
    }

    // getGrossTotal: suma de todos los getGrossAmount
    public double getGrossTotal() {
        List<Double> totales = new ArrayList<>();
        for (Article a : articulos) {
            totales.add(a.getGrossAmount());
        }
        return calculator.calculateTotal(totales);
    }

    // getDiscountedTotal: suma de todos los getDiscountedAmount
    public double getDiscountedTotal() {
        List<Double> totales = new ArrayList<>();
        for (Article a : articulos) {
            totales.add(a.getDiscountedAmount());
        }
        return calculator.calculateTotal(totales);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Order ID: " + id + "\nArticles:\n");
        for (Article a : articulos) {
            sb.append("- ").append(a.toString()).append("\n");
        }
        sb.append("Gross total: ").append(getGrossTotal()).append("€\n");
        sb.append("Discounted total: ").append(getDiscountedTotal()).append("€");
        return sb.toString();
    }
}

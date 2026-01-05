package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Order {
    @JsonProperty("id")
    private String id;

    @JsonProperty("articles")
    private List<Article> articulos;

    // Constructor vacío (Jackson lo necesita)
    public Order() {}

    // Constructor normal (por si lo usas en tests)
    public Order(String id) {
        this.id = id;
        this.articulos = new ArrayList<>();
    }

    // Getters y setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public List<Article> getArticulos() { return articulos; }
    public void setArticulos(List<Article> articulos) { this.articulos = articulos; }

    // Lógica existente
    public void addArticle(Article article) {
        articulos.add(article);
    }

    public double getGrossTotal() {
        List<Double> totales = new ArrayList<>();
        for (Article a : articulos) {
            totales.add(a.getGrossAmount());
        }
        return Calculator.calculateTotal(totales);
    }

    public double getDiscountedTotal() {
        List<Double> totales = new ArrayList<>();
        for (Article a : articulos) {
            totales.add(a.getDiscountedAmount());
        }
        return Calculator.calculateTotal(totales);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Order ID: " + id + "\nArticles:\n");
        for (Article a : articulos) {
            sb.append("- ").append(a.toString()).append("\n");
        }
        sb.append("Gross total: ").append(getGrossTotal()).append(" EUR\n");
        sb.append("Discounted total: ").append(getDiscountedTotal()).append(" EUR");
        return sb.toString();
    }
}

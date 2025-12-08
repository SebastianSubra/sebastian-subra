package com.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Article {
    // Mapeo entre nombres del JSON y tus variables
    @JsonProperty("name")
    private String nombre;

    @JsonProperty("quantity")
    private int cantidad;

    @JsonProperty("unitPrice")
    private double precio;

    @JsonProperty("discount")
    private double descuento;

    // Constructor vacío (necesario para Jackson)
    public Article() {}

    // Tu constructor existente
    public Article(String nombre, int cantidad, double precio, double descuento) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.descuento = descuento;
    }

    // Getters y setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public double getDescuento() { return descuento; }
    public void setDescuento(double descuento) { this.descuento = descuento; }

    // Métodos de cálculo (sin cambios)
    public double getGrossAmount() {
        return Calculator.multiply(cantidad, precio);
    }

    public double getDiscountedAmount() {
        double gross = getGrossAmount();
        return Calculator.discount(gross, descuento);
    }

    @Override
    public String toString() {
        return nombre + " (" + cantidad + " x " + precio + " EUR, desc: " + descuento + "%)";
    }

}

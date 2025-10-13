package com.example.model;
import com.example.Calculator;

public class Article {
    // Atributos
    private String nombre;
    private int cantidad;
    private double precio;
    private double descuento;

    // Usamos la clase Calculator del mismo paquete
    private final Calculator calculator = new Calculator();

    // Constructor
    public Article(String nombre, int cantidad, double precio, double descuento) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.descuento = descuento;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    // Método getGrossAmount: multiplica cantidad × precio usando Calculator
    public double getGrossAmount() {
        return calculator.multiply(cantidad, (int) precio);
    }

    // Método getDiscountedAmount: aplica descuento al total bruto
    public double getDiscountedAmount() {
        double gross = getGrossAmount();
        return calculator.discount(gross, descuento);
    }

    @Override
    public String toString() {
        return nombre + " (" + cantidad + " x " + precio + "€, desc: " + descuento + "%)";
    }
}

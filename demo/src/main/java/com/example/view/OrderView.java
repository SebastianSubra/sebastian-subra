package com.example.view;

import javax.swing.*;
import com.example.model.Order;
import java.awt.*;

public class OrderView extends JFrame {
    private JTextField searchField = new JTextField(10);
    private JButton searchButton = new JButton("Search");
    private JTextArea resultArea = new JTextArea(10, 40);
    private JLabel labelTotalUSD;


    public OrderView() {
        setTitle("Order Management");

        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/app.png")));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        add(new JLabel("Order ID:"));
        add(searchField);
        add(searchButton);
        add(new JScrollPane(resultArea));

        labelTotalUSD = new JLabel("USD Total: ---");
        add(labelTotalUSD);

        pack();
        setVisible(true);
    }

    public String getSearchId() {
        return searchField.getText().trim();
    }

    public JButton getSearchButton() {
        return searchButton; 
    }

    public void displayOrder(Order order, double rate) {
        if (order == null) {
            resultArea.setText("Order not found.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Order ").append(order.getId()).append(":\n");

        order.getArticulos().forEach(article -> {
            double subtotal = article.getCantidad() * article.getPrecio() * (1 - article.getDescuento() / 100.0);
            sb.append(article.getNombre())
            .append(" x").append(article.getCantidad())
            .append(" @ ").append(article.getPrecio()).append("$ (discount ")
            .append(article.getDescuento()).append("%) = ")
            .append(String.format("%.2f", subtotal)).append("$\n");
        });

        double grossEUR = order.getGrossTotal();
        double discountedEUR = order.getDiscountedTotal();

        double grossUSD = grossEUR * rate;
        double discountedUSD = discountedEUR * rate;

        sb.append("\nGross total (EUR): ").append(String.format("%.2f", grossEUR)).append("€");
        sb.append("\nDiscounted total (EUR): ").append(String.format("%.2f", discountedEUR)).append("€");

        sb.append("\nGross total (USD): ").append(String.format("%.2f", grossUSD)).append("$");
        sb.append("\nDiscounted total (USD): ").append(String.format("%.2f", discountedUSD)).append("$");

        resultArea.setText(sb.toString());

        // actualizar la etiqueta extra
        labelTotalUSD.setText("USD Total: " + String.format("%.2f", discountedUSD) + "$");
        

}

}

package com.example.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.example.model.Order;
import com.example.view.OrderView;
import com.example.model.CurrencyService;


public class OrderController {

    private final OrderView view;
    private final List<Order> orders;

    public OrderController(OrderView view, List<Order> orders) {
        this.view = view;
        this.orders = orders;

        // Añadimos la acción al botón Search
        view.getSearchButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = view.getSearchId().trim();
                if (id.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter an Order ID.");
                    return;
                }

                Order found = findOrderById(id);

                if (found != null) {
                    double rate = CurrencyService.getEURtoUSD();
                    view.displayOrder(found, rate);

                } else {
                    JOptionPane.showMessageDialog(null, "Order not found.");
                }
            }
        });
    }

    // Método auxiliar para buscar el pedido
    private Order findOrderById(String id) {
        for (Order o : orders) {
            if (o.getId().equalsIgnoreCase(id)) {
                return o;
            }
        }
        return null;
    }
}
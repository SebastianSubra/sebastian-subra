package com.example.controller;

import javax.swing.*;
import java.util.List;
import com.example.model.Order;
import com.example.model.OrderRepository;
import com.example.model.CurrencyService;
import com.example.view.OrderView;

public class OrderController {

    private final OrderView view;
    private final List<Order> orders;

    public OrderController(OrderView view, List<Order> orders) {
        this.view = view;
        this.orders = orders;

        view.showOrderIds(orders);

        view.getSearchButton().addActionListener(e -> {
            String id = view.getSearchId();
            Order found = findOrderById(id);
            if (found != null) {
                view.displayOrder(found, CurrencyService.getEURtoUSD());
                view.getOrderIdList().setSelectedValue(found.getId(), true);
            } else {
                JOptionPane.showMessageDialog(view, "Order not found.");
            }
        });

        view.getCreateOrderButton().addActionListener(e -> {
            String input = JOptionPane.showInputDialog(view, "Enter new Order ID:");
            if (input == null || input.trim().isEmpty()) return;

            String id = input.trim();
            if (findOrderById(id) != null) {
                JOptionPane.showMessageDialog(view, "Order ID already exists.");
                return;
            }

            Order newOrder = new Order(id);
            orders.add(newOrder);
            OrderRepository.saveOrders(orders);
            view.showOrderIds(orders);
        });

        view.getDeleteOrderButton().addActionListener(e -> {
            String id = view.getOrderIdList().getSelectedValue();
            if (id == null) {
                JOptionPane.showMessageDialog(view, "Select an order first.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(view,
                    "Delete order " + id + "?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) return;

            orders.removeIf(o -> o.getId().equalsIgnoreCase(id));
            OrderRepository.saveOrders(orders);
            view.showOrderIds(orders);
            view.clearOrderDetails();
        });

        view.getEditOrderButton().addActionListener(e -> {
            String id = view.getOrderIdList().getSelectedValue();
            if (id == null) {
                JOptionPane.showMessageDialog(view, "Select an order first.");
                return;
            }

            Order order = findOrderById(id);
            if (order == null || order.getArticulos().isEmpty()) {
                JOptionPane.showMessageDialog(view, "Nothing to edit.");
                return;
            }

            order.getArticulos().forEach(a -> {
                String q = JOptionPane.showInputDialog(view,
                        "New quantity for " + a.getNombre() +
                        " (current: " + a.getCantidad() + ")");
                if (q != null) {
                    try {
                        int nq = Integer.parseInt(q.trim());
                        if (nq >= 0) a.setCantidad(nq);
                    } catch (NumberFormatException ignored) {}
                }

                String d = JOptionPane.showInputDialog(view,
                        "New discount (%) for " + a.getNombre() +
                        " (current: " + a.getDescuento() + ")");
                if (d != null) {
                    try {
                        double nd = Double.parseDouble(d.trim());
                        if (nd >= 0 && nd <= 100) a.setDescuento(nd);
                    } catch (NumberFormatException ignored) {}
                }
            });

            OrderRepository.saveOrders(orders);
            view.displayOrder(order, CurrencyService.getEURtoUSD());
        });
    }

    private Order findOrderById(String id) {
        return orders.stream()
                .filter(o -> o.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }
}

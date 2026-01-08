package com.example.controller;

import javax.swing.*;
import java.util.List;

import com.example.model.*;
import com.example.view.EditOrderDialog;
import com.example.view.OrderView;

public class OrderController {

    private final OrderView view;
    private final List<Order> orders;
    private final double eurToUsdRate;

    public OrderController(OrderView view, List<Order> orders) {
        this.view = view;
        this.orders = orders;

        this.eurToUsdRate = CurrencyService.getEURtoUSD();
        view.showOrderIds(orders);

        // SEARCH
        view.getSearchButton().addActionListener(e -> {
            String id = view.getSearchId();
            Order found = findOrderById(id);

            if (found != null) {
                view.displayOrder(found, eurToUsdRate);
                view.getOrderIdList().setSelectedValue(found.getId(), true);
            } else {
                JOptionPane.showMessageDialog(view, "Order not found.");
            }
        });

        // CREATE ORDER (tabla vacía)
        view.getCreateOrderButton().addActionListener(e -> {

            String id = JOptionPane.showInputDialog(view, "Enter new Order ID:");
            if (id == null || id.trim().isEmpty()) return;

            if (findOrderById(id) != null) {
                JOptionPane.showMessageDialog(view, "Order ID already exists.");
                return;
            }

            Order newOrder = new Order(id);

            EditOrderDialog dialog =
                    new EditOrderDialog(view, newOrder, true);

            dialog.setVisible(true);

            if (newOrder.getArticulos().isEmpty()) {
                JOptionPane.showMessageDialog(view, "Order must have at least one article.");
                return;
            }

            orders.add(newOrder);
            OrderRepository.saveOrders(orders);
            view.showOrderIds(orders);
        });

        // DELETE ORDER
        view.getDeleteOrderButton().addActionListener(e -> {
            String id = view.getOrderIdList().getSelectedValue();
            if (id == null) {
                JOptionPane.showMessageDialog(view, "Select an order first.");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    view,
                    "Delete order " + id + "?",
                    "Confirm",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm != JOptionPane.YES_OPTION) return;

            orders.removeIf(o -> o.getId().equalsIgnoreCase(id));
            OrderRepository.saveOrders(orders);
            view.showOrderIds(orders);
            view.clearOrderDetails();
        });

        // EDIT ORDER (tabla rellena)
        view.getEditOrderButton().addActionListener(e -> {

            String id = view.getOrderIdList().getSelectedValue();
            if (id == null) {
                JOptionPane.showMessageDialog(view, "Select an order first.");
                return;
            }

            Order order = findOrderById(id);
            if (order == null) return;

            EditOrderDialog dialog =
                    new EditOrderDialog(view, order, false);

            dialog.setVisible(true);

            OrderRepository.saveOrders(orders);
            view.displayOrder(order, eurToUsdRate);
        });
    }

    private Order findOrderById(String id) {
        return orders.stream()
                .filter(o -> o.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }
}

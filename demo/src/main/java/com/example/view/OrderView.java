package com.example.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import com.example.model.Order;

public class OrderView extends JFrame {

    private JTextField searchField = new JTextField(10);
    private JButton searchButton = new JButton("Search");

    private JTextArea resultArea = new JTextArea(10, 40);
    private JLabel labelTotalUSD;

    private JList<String> orderIdList;
    private DefaultListModel<String> orderIdListModel;

    private JButton createOrderButton = new JButton("Create Order");
    private JButton editOrderButton = new JButton("Edit Order");
    private JButton deleteOrderButton = new JButton("Delete Order");

    public OrderView() {
        setTitle("Order Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        setIconImage(Toolkit.getDefaultToolkit().getImage(
                getClass().getResource("/app.png")));

        JPanel centerPanel = new JPanel(new FlowLayout());

        centerPanel.add(new JLabel("Order ID:"));
        centerPanel.add(searchField);
        centerPanel.add(searchButton);

        resultArea.setEditable(false);
        centerPanel.add(new JScrollPane(resultArea));

        labelTotalUSD = new JLabel("USD Total: ---");
        centerPanel.add(labelTotalUSD);

        add(centerPanel, BorderLayout.CENTER);

        orderIdListModel = new DefaultListModel<>();
        orderIdList = new JList<>(orderIdListModel);
        orderIdList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createTitledBorder("Orders"));
        leftPanel.add(new JScrollPane(orderIdList), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        buttonPanel.add(createOrderButton);
        buttonPanel.add(editOrderButton);
        buttonPanel.add(deleteOrderButton);

        leftPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(leftPanel, BorderLayout.WEST);

        pack();
        setVisible(true);
    }

    public void showOrderIds(List<Order> orders) {
        orderIdListModel.clear();
        for (Order o : orders) {
            orderIdListModel.addElement(o.getId());
        }
    }

    public void displayOrder(Order order, double rate) {
        if (order == null) {
            resultArea.setText("Order not found.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Order ").append(order.getId()).append("\n\n");

        order.getArticulos().forEach(a -> {
            double subtotal = a.getCantidad() * a.getPrecio()
                    * (1 - a.getDescuento() / 100.0);
            sb.append(a.getNombre())
              .append(" x").append(a.getCantidad())
              .append(" @ ").append(a.getPrecio()).append("EUR (discount ")
              .append(a.getDescuento()).append("%) = ")
              .append(String.format("%.2f", subtotal)).append("EUR\n");
        });

        double grossEUR = order.getGrossTotal();
        double discountedEUR = order.getDiscountedTotal();

        sb.append("\nGross total (EUR): ").append(String.format("%.2f", grossEUR)).append("EUR");
        sb.append("\nDiscounted total (EUR): ").append(String.format("%.2f", discountedEUR)).append("EUR");

        double grossUSD = grossEUR * rate;
        double discountedUSD = discountedEUR * rate;

        sb.append("\nGross total (USD): ").append(String.format("%.2f", grossUSD)).append("USD");
        sb.append("\nDiscounted total (USD): ").append(String.format("%.2f", discountedUSD)).append("USD");

        resultArea.setText(sb.toString());
        labelTotalUSD.setText("USD Total: " + String.format("%.2f", discountedUSD) + "USD");
    }

    public void clearOrderDetails() {
        resultArea.setText("");
        labelTotalUSD.setText("USD Total: ---");
    }

    public JButton getSearchButton() { return searchButton; }
    public String getSearchId() { return searchField.getText().trim(); }
    public JButton getCreateOrderButton() { return createOrderButton; }
    public JButton getEditOrderButton() { return editOrderButton; }
    public JButton getDeleteOrderButton() { return deleteOrderButton; }
    public JList<String> getOrderIdList() { return orderIdList; }
}

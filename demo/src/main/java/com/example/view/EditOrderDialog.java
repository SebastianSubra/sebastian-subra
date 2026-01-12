package com.example.view;

import com.example.model.Article;
import com.example.model.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EditOrderDialog extends JDialog {

    private JTable table;
    private DefaultTableModel model;
    private boolean creating;

    public EditOrderDialog(JFrame parent, Order order, boolean creating) {
        super(parent, creating ? "Create Order" : "Edit Order " + order.getId(), true);
        this.creating = creating;

        setSize(700, 300);
        setLayout(new BorderLayout());

        model = new DefaultTableModel(
                new Object[]{"Name", "Quantity", "Price (EUR)", "Discount (%)"},
                0
        ) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return true;
            }
        };

        if (!creating) {
            for (Article a : order.getArticulos()) {
                model.addRow(new Object[]{
                        a.getNombre(),
                        a.getCantidad(),
                        a.getPrecio(),
                        a.getDescuento()
                });
            }
        }

        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttons = new JPanel();

        JButton addRow = new JButton("Add article");
        JButton removeRow = new JButton("Remove selected");
        JButton save = new JButton("Save");

        addRow.addActionListener(e ->
                model.addRow(new Object[]{"", 0, 0.0, 0.0})
        );

        removeRow.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) model.removeRow(row);
        });

        save.addActionListener(e -> saveToOrder(order));

        buttons.add(addRow);
        buttons.add(removeRow);
        buttons.add(save);

        add(buttons, BorderLayout.SOUTH);
        setLocationRelativeTo(parent);
    }

    private void saveToOrder(Order order) {

        order.getArticulos().clear();

        for (int i = 0; i < model.getRowCount(); i++) {
            try {
                String name = model.getValueAt(i, 0).toString();
                int quantity = Integer.parseInt(model.getValueAt(i, 1).toString());
                double price = Double.parseDouble(model.getValueAt(i, 2).toString());
                double discount = Double.parseDouble(model.getValueAt(i, 3).toString());

                if (name.isBlank()) continue;

                Article a = new Article(name, quantity, price, discount);
                order.addArticle(a);

            } catch (Exception ignored) {}
        }

        dispose();
    }
}

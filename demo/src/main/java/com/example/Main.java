package com.example;

import com.example.controller.OrderController;
import com.example.model.Order;
import com.example.view.OrderView;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        log.info("Starting Order Management System...");

        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Order> orders;

            File externalFile = new File("orders.json");

            if (externalFile.exists()) {
                // 1️⃣ Leer desde JSON externo (persistente)
                orders = mapper.readValue(
                        externalFile,
                        new TypeReference<List<Order>>() {}
                );
                log.info("Orders loaded from external orders.json");
            } else {
                // 2️⃣ Leer desde resources (carga inicial)
                InputStream inputStream = Main.class.getResourceAsStream("/orders.json");

                if (inputStream == null) {
                    throw new RuntimeException("orders.json not found in resources");
                }

                orders = mapper.readValue(
                        inputStream,
                        new TypeReference<List<Order>>() {}
                );

                log.info("Orders loaded from resources orders.json");
            }

            log.info("✅ Orders loaded successfully. Total: {}", orders.size());

            OrderView view = new OrderView();
            new OrderController(view, orders);
            view.setVisible(true);

        } catch (Exception e) {
            log.error("Error reading orders.json", e);
        }

    }
}

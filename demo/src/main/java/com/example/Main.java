package com.example;

import com.example.controller.OrderController;
import com.example.model.Order;
import com.example.view.OrderView;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.List;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        
        log.info("Starting Order Management System...");

        try {
            // Crear el ObjectMapper (lector JSON)
            ObjectMapper mapper = new ObjectMapper();

            // Leer el archivo desde la carpeta resources
            InputStream inputStream = Main.class.getResourceAsStream("/orders.json");

            // Parsear el contenido del archivo a una lista de objetos Order
            List<Order> orders = mapper.readValue(inputStream, new TypeReference<List<Order>>() {});

            // Mostrar cada pedido y registrar en el log
            for (Order order : orders) {
                log.debug("Loaded order: {}", order.getId());
                System.out.println(order + "\n");
            }

            log.info("✅ Orders loaded successfully. Total: {}", orders.size());

             // Initialize MVC
            OrderView view = new OrderView();
            new OrderController(view, orders);
            view.setVisible(true);

        } catch (Exception e) {
            log.error("Error reading orders.json: {}", e.getMessage());
            e.printStackTrace();
        }

         
    }
}

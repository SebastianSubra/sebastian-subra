package com.example.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.util.List;

public class OrderRepository {

    private static final String FILE_PATH = "orders.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    // Guarda TODA la lista de pedidos (sobrescribe el JSON)
    public static void saveOrders(List<Order> orders) {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                  .writeValue(new File(FILE_PATH), orders);
        } catch (Exception e) {
            throw new RuntimeException("Error saving orders.json", e);
        }
    }
}

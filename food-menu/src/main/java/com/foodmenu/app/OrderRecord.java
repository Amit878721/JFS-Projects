package com.foodmenu.app;

import java.util.List;

/** Stores a completed order so the admin can see who ordered what. */
public class OrderRecord {
    private final String username;
    private final List<OrderLine> lines;
    private final double total;
    private final String paymentMethod;

    public OrderRecord(String username, List<OrderLine> lines, double total, String paymentMethod) {
        this.username = username;
        this.lines = List.copyOf(lines);
        this.total = total;
        this.paymentMethod = paymentMethod;
    }

    public String getUsername() {
        return username;
    }

    public List<OrderLine> getLines() {
        return lines;
    }

    public double getTotal() {
        return total;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
}

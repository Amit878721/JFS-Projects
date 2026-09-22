package com.foodmenu.app;

/** Snapshot of one ordered food item for order history. */
public class OrderLine {
    private final String foodName;
    private final int quantity;
    private final double unitPrice;

    public OrderLine(String foodName, int quantity, double unitPrice) {
        this.foodName = foodName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getSubtotal() {
        return unitPrice * quantity;
    }
}
